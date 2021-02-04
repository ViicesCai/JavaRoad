package edu.fdzc.beans;

/**
 * @author CAI
 * @time 2021/1/6
 */
public class AirPlane {
    private String engine; // 引擎
    private String wingLen; // 翼长
    private Integer capacity; // 容量
    private String captain; // 机长
    private String subCaption; // 副机长

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getWingLen() {
        return wingLen;
    }

    public void setWingLen(String wingLen) {
        this.wingLen = wingLen;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getSubCaption() {
        return subCaption;
    }

    public void setSubCaption(String subCaption) {
        this.subCaption = subCaption;
    }

    @Override
    public String toString() {
        return "AirPlane{" +
                "engine='" + engine + '\'' +
                ", wingLen='" + wingLen + '\'' +
                ", capacity='" + capacity + '\'' +
                ", captain='" + captain + '\'' +
                ", subCaption='" + subCaption + '\'' +
                '}';
    }
}

