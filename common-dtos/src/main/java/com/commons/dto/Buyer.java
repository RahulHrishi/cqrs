package com.commons.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
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
