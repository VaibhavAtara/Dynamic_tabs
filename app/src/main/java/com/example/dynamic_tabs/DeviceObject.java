package com.example.dynamic_tabs;

public class DeviceObject {
     int thumbnail;
     String id,type,time,topic, start,close,command,source, watt,duty;

    public DeviceObject() {
    }

    public DeviceObject(String id, int thumbnail, String type, String time, String topic, String start, String close, String command, String source, String watt, String duty) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.type = type;
        this.time = time;
        this.topic = topic;
        this.start = start;
        this.close = close;
        this.command = command;
        this.source = source;
        this.watt = watt;
        this.duty = duty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWatt() {
        return watt;
    }

    public void setWatt(String watt) {
        this.watt = watt;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
