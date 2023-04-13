package com.haiph.salesservice.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PersonDTO {
    private String personCode;
    private String fullName;
}
