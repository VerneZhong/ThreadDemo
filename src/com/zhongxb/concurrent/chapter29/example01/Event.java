package com.zhongxb.concurrent.chapter29.example01;

/**
 * Event只包含了该Event所属的类型和所包含的数据
 * @author Mr.zxb
 * @date 2018-11-07 15:26
 */
public class Event {

    private final String type;
    private final String data;

    public Event(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
