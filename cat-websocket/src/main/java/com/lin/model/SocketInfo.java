package com.lin.model;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-05-04 18:15:28
 */
@Data
public class SocketInfo {
    private String id;
    private String username;
    public SocketInfo() {
    }

    public SocketInfo(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
