package com.hjg.wscontroller;

import lombok.Data;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-29 17:02
 */
@Data
public class WsMsg {
    private String type;
    private String message;
    private String userId;
    private String name;
}
