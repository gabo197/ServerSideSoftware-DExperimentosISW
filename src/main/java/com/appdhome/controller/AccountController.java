package com.appdhome.controller;

import com.appdhome.entities.Account;
import com.appdhome.services.IAccountService;
import com.appdhome.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/validateUser")
    @Operation(summary = "Validacion de un usuario", description ="Método que validar un usuario" )
    @ApiResponses({
            @ApiResponse(responseCode="200", description = "Appointment creado"),
            @ApiResponse(responseCode="404", description = "Appointment no creado")
    })
    public ResponseEntity<?> validateUser(@Valid @RequestBody Account account){
        try{
            System.out.println("account " + account);
            Optional<Account> accountUsername = accountService.findByUsername(account.getUsername());
            if(!accountUsername.isPresent()) {
                Account accountNew = accountService.save(account);
                return new ResponseEntity<>(accountNew,HttpStatus.CREATED);
            }
            Response res = new Response();
            res.setMsj("Nombre de usuario ya existe");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Registro de un usuario", description ="Método que registra un usuario" )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Appointment creado"),
            @ApiResponse(responseCode = "404", description = "Appointment no creado")
    })
    public ResponseEntity<Account> register(@Valid @RequestBody Account account){
        try{
            Account accountNew = accountService.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(accountNew);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Inicio sesion de un usuario", description ="Método para iniciar sesion" )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inicio de sesion exitoso"),
            @ApiResponse(responseCode = "404", description = "Inicio de sesion no exitoso")
    })
    public ResponseEntity<?> login(@Valid @RequestBody Account account){
        try{
            Optional<Account> accountLogin = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
            System.out.println("account login" + accountLogin);
            if(accountLogin.isPresent())
                return new ResponseEntity<>(accountLogin, HttpStatus.OK);
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}