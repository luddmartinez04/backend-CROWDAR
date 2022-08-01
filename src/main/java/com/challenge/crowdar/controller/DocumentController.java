package com.challenge.crowdar.controller;

import com.challenge.crowdar.dto.DocumentDTO;
import com.challenge.crowdar.service.DocumentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;

@Api(value = "Document Resource REST Endpoint", description = "Shows the Document info")
@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @ApiOperation(value = "Crea un documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Documento creado"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @PostMapping("/documents")
    public ResponseEntity<Object> createDocument(@RequestBody() DocumentDTO documentDTO) {
        return documentService.createDocument(documentDTO);
    }

    @ApiOperation(value = "Retorna una lista de documentos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de  documentos"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @GetMapping("/documents/list")
    public ResponseEntity<Object> getEmployees() {
        return documentService.getListDocuments();
    }

    @ApiOperation(value = "Edita un documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Documento editado"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @PutMapping("/documents/{id}")
    public ResponseEntity<Object> updateDocument(@PathVariable(value = "id") Long id, @RequestBody() DocumentDTO documentDTO) {
        return documentService.updateDocument(id, documentDTO);
    }


    @ApiOperation(value = "Elimina un documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Documento eliminado"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Object> deleteDocument(@PathVariable(value = "id") Long id) {
        return documentService.deleteDocument(id);
    }

    @ApiOperation(value = "Retorna lista de documento por nombre documento, owner y fecha")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de documento por nombre documento, owner y fecha"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @GetMapping(value = "/documents")
    public ResponseEntity<Object> getBenefits(@RequestParam(value = "page", required = false) Integer page,
                                              @RequestParam(value = "length", required = false) Integer length,
                                              @RequestParam(value = "orderBy", required = false) String orderBy,
                                              @RequestParam(value = "orientation", required = false) String orientation,
                                              @RequestParam(value = "nameDocument", required = false) String nameDocument,
                                              @RequestParam(value = "ownerDocument", required = false) String ownerDocument,
                                              @RequestParam(value = "date", required = false) Date date) {
        return this.documentService.getDocuments(page, length, orderBy, orientation,
                nameDocument, ownerDocument, date);
    }
}
