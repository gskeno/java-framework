package com.gsonkeno.javagenerics;

import java.util.HashMap;
import java.util.Map;

public enum EventType {
    SUPPLIER_OFF_SHELF(SupplierOffShelfEvent.class, SupplierOffShelfHandler.class);

    public static Map<String, EventType> eventTypeMap;

    static {
        eventTypeMap = new HashMap<>();
        for(EventType eventType : EventType.values()){
            eventTypeMap.put(eventType.name(), eventType);
        }
    }
    private Class<? extends Event> eventClass;
    private Class<? extends EventHandler> eventHandlerClass;

    EventType(Class<? extends Event> eventClass, Class<? extends EventHandler> eventHandlerClass) {
        this.eventClass = eventClass;
        this.eventHandlerClass = eventHandlerClass;
    }

    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    public Class<? extends EventHandler> getEventHandlerClass() {
        return eventHandlerClass;
    }
}
