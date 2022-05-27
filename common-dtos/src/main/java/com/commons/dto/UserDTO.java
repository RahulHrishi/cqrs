package com.commons.dto;


import java.io.Serializable;
import java.util.Arrays;


public class UserDTO implements Serializable {
    private int id;
    private String name;
    private String[] address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public UserDTO(int id, String name, String[] address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                '}';
    }
}
