package com.hjrpc.shirospringboot.mgb.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SUser implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String salt;


    private boolean remremberMe;
}