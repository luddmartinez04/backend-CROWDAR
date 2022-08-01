package com.challenge.crowdar.service;

import com.challenge.crowdar.converter.DocumentConverter;
import com.challenge.crowdar.dto.DocumentDTO;
import com.challenge.crowdar.dto.ErrorDTO;
import com.challenge.crowdar.entities.DocumentEntity;
import com.challenge.crowdar.repository.DocumentRepository;
import com.challenge.crowdar.utils.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    private DocumentConverter documentConverter = new DocumentConverter();


    public ResponseEntity<Object> createDocument(DocumentDTO documentDTO) {

        if (documentDTO == null) {
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS), HttpStatus.BAD_REQUEST);
        }
        try {
            DocumentEntity documentEntity = documentConverter.dtoToEntity(documentDTO);
            documentRepository.saveAndFlush(documentEntity);
            log.info("Documento  creado");
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ENTITY_CREATED), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ocurrio un error al crear documento: " + e.getMessage(), e);
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.CREATE_ENTITY_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getListDocuments() {
        try {
            List<DocumentEntity> documentEntities = documentRepository.findAll();
            if (documentEntities.isEmpty() || documentEntities == null) {
                log.info("No existen documentos");
                return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS), HttpStatus.NOT_FOUND);
            } else {
                List<DocumentDTO> documents = new ArrayList<>();

                for (DocumentEntity documentEntity : documentEntities) {
                    DocumentDTO documentDTO = documentConverter.entityToDto(documentEntity);
                    documents.add(documentDTO);
                }
                return new ResponseEntity<>(documents, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Ocurrio un error al obtener lista de documentos: " + e.getMessage(), e);
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.GET_ENTITY_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateDocument(Long id, DocumentDTO documentDTO) {
        try {
            if (id == null) {
                log.error("El id es nulo");
                return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS), HttpStatus.BAD_REQUEST);
            } else {
                if (documentRepository.existsById(id)) {
                    DocumentEntity documentEntity = documentRepository.getById(id);
                    documentConverter.fill(documentDTO, documentEntity);
                    documentRepository.saveAndFlush(documentEntity);
                    log.info("Documento ID: " + id + " actualizado");
                    return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ENTITY_UPDATED), HttpStatus.OK);
                } else {
                    log.error("No existe Documento con ID: " + id);
                    return new ResponseEntity<>(new ErrorDTO(ErrorCodes.GET_ENTITY_ERROR), HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("Ocurrio un error: " + e.getMessage(), e);
            return new ResponseEntity<Object>(new ErrorDTO(ErrorCodes.UPDATE_ENTITY_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteDocument(Long id) {
        try {
            if (id != null) {
                if (documentRepository.existsById(id)) {
                    documentRepository.deleteById(id);
                    log.info("Documento ID: " + id + " eliminado");
                    return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ENTITY_DELETED), HttpStatus.OK);
                } else {
                    log.error("Documento ID: " + id + " no existe");
                    return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ENTITY_NOT_FOUND), HttpStatus.NOT_FOUND);
                }
            } else {
                log.error("Parametros nulos");
                return new ResponseEntity<>(new ErrorDTO(ErrorCodes.EMPTY_PARAMETERS),HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Ocurrio un error: " + e.getMessage(), e);
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.DELETE_ENTITY_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getDocuments(Integer page, Integer pageLength, String orderBy, String orientation,
                                               String nameDocument, String ownerDocument, Date date) {

        page = page == null ? 0 : page;
        pageLength = pageLength == null ? 10 : pageLength;

        List<DocumentDTO> documentList = new ArrayList<>();

        PageRequest pr = null;
        if (orderBy != null && orientation != null) {
            orderBy = orderBy.replace('-', '.');
            if (orientation.equalsIgnoreCase("asc")) {
                pr = PageRequest.of(page, pageLength, Sort.by(Sort.Direction.ASC, orderBy));
            } else {
                if (orientation.equalsIgnoreCase("desc")) {
                    pr = PageRequest.of(page, pageLength, Sort.by(Sort.Direction.DESC, orderBy));
                }
            }
        } else {
            pr = PageRequest.of(page, pageLength);
        }

        Page<DocumentEntity> pagedEntities = null;

        if (nameDocument != null) {
            //FIltra solo por nombre
            pagedEntities = this.documentRepository.getDocumentsByNameDocument(nameDocument.toUpperCase(), pr);
        } else {
            if (ownerDocument != null) {
                //FIltra solo por owner
                pagedEntities = this.documentRepository.getDocumentsByOwnerDocument(ownerDocument.toUpperCase(), pr);
            } else {
                if (date != null){
                    //filtra por date
                    date.toString();
                    pagedEntities = this.documentRepository.getDocumentsByDate(date, pr);
                }  else {
                    //No hay filtros, trae todo
                    pagedEntities = this.documentRepository.findAll(pr);
                }
            }
        }

        if (documentRepository.count() == 0) {
            throw new EntityNotFoundException();
        }

        for (DocumentEntity document : pagedEntities.getContent()) {
            DocumentDTO dto = documentConverter.entityToDto(document);
            documentList.add(dto);
        }
        Page<DocumentDTO> documentDTOS = new PageImpl<>(documentList, pr, pagedEntities.getTotalElements());

        return new ResponseEntity<>(documentDTOS, HttpStatus.OK);
    }
}