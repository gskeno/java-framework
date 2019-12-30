package com.gson.java8.sort;

public class Record {
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "time=" + time +
                '}';
    }
}
