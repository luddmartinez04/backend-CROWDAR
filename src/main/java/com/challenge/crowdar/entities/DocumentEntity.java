package com.challenge.crowdar.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_DOCUMENT")
    private String nameDocument;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "TYPE_DOCUMENT")
    private String typeDocument;

    @Column(name = "OWNER_DOCUMENT")
    private String ownerDocument;

    @Column(name = "DESCRIPTION")
    private String description;

}
