package com.gson.power.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserDAO userDAO;

    @Test
    public void test(){
        String companyId = "akdhk";
        Integer beginIndex = 0;
        Integer limit = 10;
        Mockito.doReturn(0).when(userDAO).getCount(companyId);

        PageDataVO<UserVO> userVOPageDataVO = userService.pageQueryUser(companyId, beginIndex, limit);
        System.out.println(userVOPageDataVO);
    }
}
