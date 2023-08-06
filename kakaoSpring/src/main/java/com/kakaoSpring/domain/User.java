package com.kakaoSpring.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class User {
    private String name; // _id로 지정
    private String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String toString() {
        return String.format("User[userId:%s, password: %s]", name, email);
    }

}
