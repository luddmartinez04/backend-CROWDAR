package com.challenge.crowdar.converter;

import com.challenge.crowdar.dto.UserDTO;
import com.challenge.crowdar.entities.UserEntity;

public class UserConverter {

    public UserEntity dtoToEntity(UserDTO userDTO){
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userDTO.id);
            userEntity.setCreated(userDTO.created);
            userEntity.setEmail(userDTO.email);
            userEntity.setFirstName(userDTO.firstName);
            userEntity.setLastName(userDTO.lastName);

                if (userDTO.documentId != null){
                    userEntity.setDocumentId(userDTO.documentId);
                }
            return userEntity;
    }

    public UserDTO entityToDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

            userDTO.id = userEntity.getId();
            userDTO.created = userEntity.getCreated();
            userDTO.email = userEntity.getEmail();
            userDTO.firstName = userEntity.getFirstName();
            userDTO.lastName = userEntity.getLastName();

                if (userEntity.getDocumentId() != null){
                    userDTO.documentId = userEntity.getDocumentId();
                }

        return userDTO;
    }
}
