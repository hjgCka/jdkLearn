package com.hjg.wscontroller;

import com.hjg.util.JsonUtil;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import lombok.SneakyThrows;

/**
 * 收到消息之后解码，参数能用解码返回的类型。
 * @Description
 * @Author hjg
 * @Date 2025-05-29 16:47
 */
public class EndpointDecoder implements Decoder.Text<WsMsg> {

    @SneakyThrows
    @Override
    public WsMsg decode(String s) throws DecodeException {
        WsMsg wsMsg = JsonUtil.mapper.readValue(s, WsMsg.class);
        return wsMsg;
    }

    @Override
    public boolean willDecode(String s) {
        return s != null && s.trim().length() > 0;
    }
}
