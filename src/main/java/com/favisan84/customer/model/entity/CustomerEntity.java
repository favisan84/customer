package com.favisan84.customer.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Document(collection = "customers")
public class CustomerEntity {

    @NotBlank
    private String name;
    @Indexed
    @NotBlank
    private String email;
    private String gender;
    private String documentId;
    private String documentType;
    private Date birthDate;
}
