package com.example.datamongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String pais;
    private String region;
    private String provincia;
    private String comuna;
}
