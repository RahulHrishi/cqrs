package com.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "buyer")
public class Buyer {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Integer id;
    private Integer productId;
    private Integer bidAmount;
    private UserInfo info;
}
