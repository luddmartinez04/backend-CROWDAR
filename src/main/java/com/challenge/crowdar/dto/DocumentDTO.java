package com.challenge.crowdar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DocumentDTO {

    public Long id;
    public String nameDocument;
    public Date date;
    public String typeDocument;
    public String ownerDocument;
    public String description;
}
