package com.gson.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

public class EventBusTest {

    @Test
    public void testEventBus(){
        // 初始化eventBus
        EventBus eventBus = new EventBus("centerController");
        class MemberChangeListener{
            // 该注解不可少
            @Subscribe
            public void handleMemberChangeEvent(MemberChangeEvent memberChangeEvent){
                System.out.println("收到了会员变更事件:" + memberChangeEvent.getChangeContent()
                        + ", 我们将静默处理");
            }
        }
        eventBus.register(new MemberChangeListener());
        eventBus.post(new MemberChangeEvent("会员等级由L1升级到L2"));
    }
}
