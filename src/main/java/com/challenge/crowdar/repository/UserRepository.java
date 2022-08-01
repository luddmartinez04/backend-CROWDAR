package com.challenge.crowdar.repository;

import com.challenge.crowdar.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE CROWDAR.users\n" +
            "set DOCUMENT_ID = :documentId\n" +
            "where ID =:userId", nativeQuery = true)
    public void assignDocumentByUserId(@Param("documentId") Long documentId, @Param("userId") Long userId);

}
