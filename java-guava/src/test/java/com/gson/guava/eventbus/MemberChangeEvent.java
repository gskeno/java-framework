package com.gson.guava.eventbus;

/**
 * 会员变化事件
 */
public class MemberChangeEvent {

    private String changeContent;

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    public MemberChangeEvent(String changeContent) {
        this.changeContent = changeContent;
    }

    @Override
    public String toString() {
        return "MemberChangeEvent{" +
                "changeContent='" + changeContent + '\'' +
                '}';
    }
}
