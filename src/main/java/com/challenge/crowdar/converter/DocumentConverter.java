package com.challenge.crowdar.converter;

import com.challenge.crowdar.dto.DocumentDTO;
import com.challenge.crowdar.entities.DocumentEntity;


public class DocumentConverter {

    public DocumentEntity dtoToEntity(DocumentDTO documentDTO){
            DocumentEntity documentEntity = new DocumentEntity();
            documentEntity.setNameDocument(documentDTO.nameDocument);
            documentEntity.setId(documentDTO.id);
            documentEntity.setDate(documentDTO.date);
            documentEntity.setTypeDocument(documentDTO.typeDocument);
            documentEntity.setOwnerDocument(documentDTO.ownerDocument);
            documentEntity.setDescription(documentDTO.description);

            return documentEntity;
    }

    public DocumentDTO entityToDto(DocumentEntity documentEntity){
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.nameDocument = documentEntity.getNameDocument();
        documentDTO.id = documentEntity.getId();
        documentDTO.date = documentEntity.getDate();
        documentDTO.typeDocument = documentEntity.getTypeDocument();
        documentDTO.ownerDocument = documentEntity.getOwnerDocument();
        documentDTO.description = documentEntity.getDescription();

        return documentDTO;
    }

    public void fill(DocumentDTO dto, DocumentEntity entity) {
        entity.setNameDocument(dto.nameDocument != null ? dto.nameDocument : entity.getNameDocument());
        entity.setDate(dto.date != null ? dto.date : entity.getDate());
        entity.setTypeDocument(dto.typeDocument != null ? dto.typeDocument : entity.getTypeDocument());
        entity.setOwnerDocument(dto.ownerDocument != null ? dto.ownerDocument : entity.getOwnerDocument());
        entity.setDescription(dto.description != null ? dto.description : entity.getDescription());
    }
}
