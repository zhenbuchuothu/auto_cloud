package com.kk.thxu.common.entity;

import java.util.HashMap;

//统一相应格式的类
public class ThxuResponse extends HashMap<String, Object> {

    
    private static final long serialVersionUID = -5379817691212044450L;

    public ThxuResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public ThxuResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public ThxuResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}