package com.commons.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "seller")
public class Seller {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Integer id;
    private UserInfo info;
    private Product product;


}
