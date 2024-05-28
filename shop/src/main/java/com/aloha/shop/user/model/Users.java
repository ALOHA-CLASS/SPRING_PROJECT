package com.aloha.shop.user.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String profile;
    private Date createdAt;
    private Date updatedAt;
    private int enabled;
    private List<UserAuth> authList;
}
