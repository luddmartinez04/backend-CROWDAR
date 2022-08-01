package com.challenge.crowdar.service;

import com.challenge.crowdar.converter.UserConverter;
import com.challenge.crowdar.dto.ErrorDTO;
import com.challenge.crowdar.dto.UserDTO;
import com.challenge.crowdar.entities.UserEntity;
import com.challenge.crowdar.repository.UserRepository;
import com.challenge.crowdar.utils.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {


    @Autowired
    private UserRepository userRepository;

    private UserConverter userConverter = new UserConverter();

    public ResponseEntity<Object> createUser(UserDTO userDTO){
        if (userDTO == null) {
            log.error("Not found parameters");
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS), HttpStatus.BAD_REQUEST);
        }
        try {
            UserEntity userEntity = userConverter.dtoToEntity(userDTO);
            userRepository.saveAndFlush(userEntity);
            log.info("User creado");
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ENTITY_CREATED), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ocurrio un error al crear user: " + e.getMessage(), e);
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.CREATE_ENTITY_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> assignDocument(Long documentId, List<Long> ids){
        if (documentId == null){
            log.error("ID Nulo");
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS),HttpStatus.BAD_REQUEST);
        }
        try {
            if (ids == null || ids.isEmpty()){
                log.error("ID Users null");
                return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS),HttpStatus.BAD_REQUEST);
            } else {
                for (Long userId : ids) {
                    if (userRepository.existsById(userId)){
                        userRepository.assignDocumentByUserId(documentId, userId);
                        log.info("User con ID " + userId + " actualizado");
                    } else {
                        log.info("No existe user");
                        return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ENTITY_NOT_FOUND),HttpStatus.NOT_FOUND);
                    }
                }
                return new ResponseEntity<>(new ErrorDTO(ErrorCodes.NO_ERROR), HttpStatus.OK);
            }
        } catch (Exception e){
            log.error("Ocurrio un error: " + e.getMessage(), e);
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.GET_ENTITY_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


