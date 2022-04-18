package com.appdhome.controller;


import com.appdhome.entities.Customer;
import com.appdhome.entities.PaymentMethod;
import com.appdhome.services.ICustomerService;
import com.appdhome.services.IPaymentMethodService;
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
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/paymentmethods")
@Api(tags = "PaymentMethods", value = "RESTFul de PaymentMethods")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService paymentMethodService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lista de todas las formas de pago", notes = "Método para listar a todas las formas de pago")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Payment Methods encontrados"),
            @ApiResponse(code = 404, message = "Payment Methods no encontrados")
    })
    public ResponseEntity<List<PaymentMethod>> findAll(){
        try {
            List<PaymentMethod> paymentMethods= paymentMethodService.getAll();
            if (paymentMethods.size()>0){
                return new ResponseEntity<List<PaymentMethod>>(paymentMethods, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<PaymentMethod>>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<List<PaymentMethod>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Payment Method por Id", notes = "Método para encontrar Payment Method por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Payment Method encontrado"),
            @ApiResponse(code = 404, message = "Payment Method no encontrado")
    })
    public ResponseEntity<PaymentMethod> findByPaymentM(@PathVariable("id") Long id){
        try{
            Optional<PaymentMethod> paymentMethod=paymentMethodService.getById(id);
            if (!paymentMethod.isPresent()){
                return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<PaymentMethod>(paymentMethod.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de método de pago", notes = "Método que registra el método de pagoD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Payment Method creado"),
            @ApiResponse(code = 404, message = "Payment Method no creado")
    })
    public ResponseEntity<PaymentMethod> insertPaymentMethod(@RequestBody PaymentMethod paymentMethod){
        try{
            PaymentMethod paymentMethodNew = paymentMethodService.save(paymentMethod);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodNew);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Payment Method", notes = "Método para actualizar los datos del district")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Payment Method actualizados"),
            @ApiResponse(code = 404, message = "Datos de Payment Method no actualizados")
    })
    public ResponseEntity<PaymentMethod> update(@PathVariable("id") Long id, @RequestBody PaymentMethod paymentMethod){
        try {
            Optional<PaymentMethod> paymentMethodUp=paymentMethodService.getById(id);
            if (!paymentMethodUp.isPresent()){
                return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
            }
            paymentMethod.setId(id);
            paymentMethodService.save(paymentMethod);
            return new ResponseEntity<PaymentMethod>(paymentMethod,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de los Payment Methods ", notes = "Método para eliminar Payment Methods")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Method eliminado"),
            @ApiResponse(code = 404, message = "Payment Method no encontrado")
    })
    public ResponseEntity<PaymentMethod> deletePaymentMethod (@PathVariable("id") Long id){
        try {
            Optional<PaymentMethod> paymentMethodDelete=paymentMethodService.getById(id);
            if(!paymentMethodDelete.isPresent()){
                return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
            }
            paymentMethodService.delete(id);
            return new ResponseEntity<PaymentMethod>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
