package com.gsonkeno.pattern.prototype;

/**
 * 广告信模板
 * 包含两个字段，分别是广告信名称与广告信
 * Created by gaosong on 2018-01-30
 */
public class AdvTemplate {
    private String advSubject = "XX银行国庆节信用卡抽奖活动";

    private String advContent = "国庆抽奖活动通知:只要中奖就送100万";

    public String getAdvSubject() {
        return advSubject;
    }

    public String getAdvContent() {
        return advContent;
    }
}
