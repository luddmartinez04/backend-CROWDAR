package com.challenge.crowdar.controller;

import com.challenge.crowdar.dto.UserDTO;
import com.challenge.crowdar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "User Resource REST Endpoint", description = "Shows the User info")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Crea un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario creado"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody() UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @ApiOperation(value = "Asigna a un grupo de Usuarios un documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se ha asignado el documento correctamente"),
            @ApiResponse(code = 403, message = "Usted no tiene permisos para ingresar"),
            @ApiResponse(code = 500, message = "Se ha generado una excepcion/error"),
    })
    @PostMapping("users/assignDocument/{documentId}")
    public ResponseEntity<Object> assignDocument(@PathVariable(value = "documentId") Long documentId, @RequestParam(value = "users") List<Long> usersId) {
        return userService.assignDocument(documentId, usersId);
    }
}



