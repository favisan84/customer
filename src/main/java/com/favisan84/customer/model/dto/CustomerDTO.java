package com.favisan84.customer.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CustomerDTO {

    @NotBlank
    private String name;

    @NotBlank(message = "Email obrigatório não informado")
    private String email;

    private String gender;
    private String documentId;
    private String documentType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}
