package com.lin.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * @author lzr
 */
@Data
public class AuthClient implements Serializable {

    private static final long serialVersionUID = -6058548253127651030L;

    private String clientId;

    private String secret;

    private Set<String> scope;

    private List<String> authorities;

    private Integer state;

}
