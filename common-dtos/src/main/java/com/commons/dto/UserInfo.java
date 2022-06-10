package com.commons.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

   //private Integer id;
    private String first_name;
    private String last_name;
    private Long phone;
    private String  email;
    private String  address;
    private String  city;
    private String  state;
    private Long pin;
}
