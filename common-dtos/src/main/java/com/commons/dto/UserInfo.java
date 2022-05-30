package com.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    private Integer id;
    private String name;
    private Integer phone;
    private String  email;
    private String  address;
    private String  city;
    private String  state;
    private Integer pin;
}
