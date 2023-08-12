package com.example.datamongo.dto;

import com.example.datamongo.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "userDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private LocalDateTime birthDate;
    private LocalDateTime createdAt;
}
