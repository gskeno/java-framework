package com.gsonkeno.javagenerics;

import com.alibaba.fastjson.JSONObject;

public class MessageConsumer {

    public void consume(String  message) throws InstantiationException, IllegalAccessException {
        System.out.println("消费消息：" + message);
        CommonMessage commonMessage = JSONObject.parseObject(message, CommonMessage.class);
        String eventType = commonMessage.getEventType();
        EventType eventTypeEnum = EventType.eventTypeMap.get(eventType);

        Class<? extends EventHandler> eventHandlerClass = eventTypeEnum.getEventHandlerClass();
        Class<? extends Event> eventClass = GenericUtils.getParentInterfaceGenericType(eventHandlerClass);

        Event event = JSONObject.parseObject(commonMessage.getMessageBody(), eventClass);
        EventHandler eventHandler = eventHandlerClass.newInstance();
        eventHandler.handle(event);
    }
}
