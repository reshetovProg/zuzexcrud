package com.zuzextask.crud.app;

import lombok.Data;

@Data
public class User {
    private long id;
    private String user_name;
    private int age;
    private String password;
}
