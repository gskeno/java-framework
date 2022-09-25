package com.gsonkeno.pattern.prototype;

/**
 * Created by gaosong on 2018-01-30
 */
public class Mail implements Cloneable{
    private String receiver;
    private String appellation; //称谓
    private String subject; //邮件名称
    private String context; //邮件内容
    private String tail; //邮件尾部

    public Mail(AdvTemplate advTemplate) {
        this.subject = advTemplate.getAdvSubject();
        this.context = advTemplate.getAdvContent();
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAppellation() {
        return appellation;
    }

    public String getSubject() {
        return subject;
    }

    public String getContext() {
        return context;
    }

    public String getTail() {
        return tail;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    @Override
    protected Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }
}
