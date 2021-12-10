package com.example.javafx.model;

import java.util.Objects;

public class UserAuthDto {
    public String username;
    public String password;

    public UserAuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthDto userDto = (UserAuthDto) o;
        return Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
    }
}