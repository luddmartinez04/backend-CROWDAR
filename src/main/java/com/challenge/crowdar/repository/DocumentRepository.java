package com.challenge.crowdar.repository;

import com.challenge.crowdar.entities.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    @Query(value = "SELECT * from CROWDAR.documents \n" +
            "where NAME_DOCUMENT = :nameDocument", nativeQuery = true)
    public Page<DocumentEntity> getDocumentsByNameDocument(@Param("nameDocument") String nameDocument,
                                                                   Pageable pr);

    @Query(value = "SELECT * from CROWDAR.documents \n" +
            "where OWNER_DOCUMENT = :ownerDocument\n", nativeQuery = true)
    public Page<DocumentEntity> getDocumentsByOwnerDocument(@Param("ownerDocument") String ownerDocument,
                                                   Pageable pr);


    @Query(value = "SELECT * from CROWDAR.documents\n" +
            "where DATE = :date", nativeQuery = true)
    public Page<DocumentEntity> getDocumentsByDate(@Param("date") Date date,
                                                    Pageable pr);
}
