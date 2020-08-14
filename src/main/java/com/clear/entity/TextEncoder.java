package com.clear.entity;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * ClassName TextEncoder
 *
 * @author qml
 * Date 2020/8/12 17:40
 * Version 1.0
 **/

public class TextEncoder implements Encoder.Text<Object> {

    @Override
    public String encode(Object chatMessage) throws EncodeException {
        return JSONObject.toJSONString(chatMessage);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}