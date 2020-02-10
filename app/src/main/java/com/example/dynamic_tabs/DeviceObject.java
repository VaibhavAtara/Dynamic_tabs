package com.example.dynamic_tabs;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceObject implements Parcelable {
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

    protected DeviceObject(Parcel in) {
        thumbnail = in.readInt();
        id = in.readString();
        type = in.readString();
        time = in.readString();
        topic = in.readString();
        start = in.readString();
        close = in.readString();
        command = in.readString();
        source = in.readString();
        watt = in.readString();
        duty = in.readString();
    }

    public static final Creator<DeviceObject> CREATOR = new Creator<DeviceObject>() {
        @Override
        public DeviceObject createFromParcel(Parcel in) {
            return new DeviceObject(in);
        }

        @Override
        public DeviceObject[] newArray(int size) {
            return new DeviceObject[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(thumbnail);
        parcel.writeString(id);
        parcel.writeString(type);
        parcel.writeString(time);
        parcel.writeString(topic);
        parcel.writeString(start);
        parcel.writeString(close);
        parcel.writeString(command);
        parcel.writeString(source);
        parcel.writeString(watt);
        parcel.writeString(duty);
    }
}
