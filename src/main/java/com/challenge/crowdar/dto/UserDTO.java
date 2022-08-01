package com.challenge.crowdar.dto;

import com.challenge.crowdar.entities.DocumentEntity;

import java.util.Date;

public class UserDTO {

    public Long id;
    public Date created;
    public String email;
    public String firstName;
    public String lastName;
    public DocumentEntity documentId;
}
