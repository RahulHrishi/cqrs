package com.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Integer id;
    private String first_name;
    private String last_name;
    private Long phone;
    private String  email;
    private String  address;
    private String  city;
    private String  state;
    private Long pin;
}
