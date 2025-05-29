package com.hjg.wscontroller;

import com.hjg.util.JsonUtil;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import lombok.SneakyThrows;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-29 16:47
 */
public class EndpointEncoder implements Encoder.Text<WsMsg> {

    @SneakyThrows
    @Override
    public String encode(WsMsg object) throws EncodeException {
        return JsonUtil.mapper.writeValueAsString(object);
    }
}
