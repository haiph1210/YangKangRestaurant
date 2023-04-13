package com.haiph.userservice.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMail {
    private String emailRevice;
    private String subject;
    private String message;
}
