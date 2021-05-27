package com.gson.java8.stream;

public class Point {
    Long pointId;

    Long value;

    public Long getPointId() {
        return pointId;
    }

    public Long getValue() {
        return value;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Point(Long pointId, Long value) {
        this.pointId = pointId;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pointId=" + pointId +
                ", value=" + value +
                '}';
    }
}
