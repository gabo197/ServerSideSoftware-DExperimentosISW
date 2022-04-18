package com.appdhome.controller;

import com.appdhome.entities.Account;
import com.appdhome.services.IAccountService;
import com.appdhome.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/account")
@Api(tags="Account", value = "RESTFul de Accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(value ="/validateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Validacion de un usuario", notes ="Método que validar un usuario" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Appointment creado"),
            @ApiResponse(code=404, message = "Appointment no creado")
    })
    public ResponseEntity<?> validateUser(@Valid @RequestBody Account account){
        try{
            System.out.println("account " + account);
            Optional<Account> accountUsername = accountService.findByUsername(account.getUsername());
            if(!accountUsername.isPresent()) {
                Account accountNew = accountService.save(account);
                return new ResponseEntity<Account>(accountNew,HttpStatus.CREATED);
            }
            Response res = new Response();
            res.setMsj("Nombre de usuario ya existe");
            return new ResponseEntity<>(res, HttpStatus.OK);


            //return new ResponseEntity<Account>(HttpStatus.OK);


        }catch (Exception e){
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un usuario", notes ="Método que registra un usuario" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Appointment creado"),
            @ApiResponse(code=404, message = "Appointment no creado")
    })
    public ResponseEntity<Account> register(@Valid @RequestBody Account account){
        try{
            Account accountNew = accountService.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(accountNew);


        }catch (Exception e){
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Inicio sesion de un usuario", notes ="Método para iniciar sesion" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Inicio de sesion exitoso"),
            @ApiResponse(code=404, message = "Inicio de sesion no exitoso")
    })
    public ResponseEntity<?> login(@Valid @RequestBody Account account){
        try{

            Optional<Account> accountLogin = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
            System.out.println("account login" + accountLogin);
            if(accountLogin.isPresent())
                return new ResponseEntity<Optional<Account>>(accountLogin, HttpStatus.OK);

            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);


        }catch (Exception e){
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
