package com.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

@Data
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
