package com.gson.power.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.HashMap;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateUtils.class)
public class MockTest {


    /**
     * 要测试的对象
     */
    @InjectMocks
    private CacheService cacheService = new ThreadLocalCacheService();

    /**
     * 被mock的对象
     */
    @Mock
    private DBService dbService;

    @Before
    public void before(){
        // 设置cacheService的私有成员为 dbService
        Whitebox.setInternalState(cacheService, "dbService", dbService);
    }

    @Test
    public void test(){
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        result.setRow(map);
        // 模拟类的方法行为
        PowerMockito.when(dbService.getResult(Mockito.anyString())).thenReturn(result);

        Object value = cacheService.getValue("12");
        Assert.assertEquals(result, value);
    }

    @Test
    public void testGetNow(){
        PowerMockito.mockStatic(DateUtils.class);
        PowerMockito.when(DateUtils.getNow()).thenReturn("2022-11-07 00:00:00");
        String now = DateUtils.getNow();
        Assert.assertEquals(now, "2022-11-07 00:00:00");
    }
}
