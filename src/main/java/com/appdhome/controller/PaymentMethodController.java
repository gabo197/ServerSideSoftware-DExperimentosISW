package com.appdhome.controller;

import com.appdhome.entities.PaymentMethod;
import com.appdhome.services.IPaymentMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/paymentmethods")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService paymentMethodService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista de todas las formas de pago", description = "Método para listar a todas las formas de pago")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment Methods encontrados"),
            @ApiResponse(responseCode = "404", description = "Payment Methods no encontrados")
    })
    public ResponseEntity<List<PaymentMethod>> findAll(){
        try {
            List<PaymentMethod> paymentMethods= paymentMethodService.getAll();
            if (paymentMethods.size()>0){
                return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Payment Method por Id", description = "Método para encontrar Payment Method por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment Method encontrado"),
            @ApiResponse(responseCode = "404", description = "Payment Method no encontrado")
    })
    public ResponseEntity<PaymentMethod> findByPaymentM(@PathVariable("id") Long id){
        try{
            Optional<PaymentMethod> paymentMethod=paymentMethodService.getById(id);
            if (!paymentMethod.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(paymentMethod.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping()
    @Operation(summary = "Registro de método de pago", description = "Método que registra el método de pagoD")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Payment Method creado"),
            @ApiResponse(responseCode = "404", description = "Payment Method no creado")
    })
    public ResponseEntity<PaymentMethod> insertPaymentMethod(@RequestBody PaymentMethod paymentMethod){
        try{
            PaymentMethod paymentMethodNew = paymentMethodService.save(paymentMethod);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodNew);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualización de datos de Payment Method", description = "Método para actualizar los datos del district")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de Payment Method actualizados"),
            @ApiResponse(responseCode = "404", description = "Datos de Payment Method no actualizados")
    })
    public ResponseEntity<PaymentMethod> update(@PathVariable("id") Long id, @RequestBody PaymentMethod paymentMethod){
        try {
            Optional<PaymentMethod> paymentMethodUp=paymentMethodService.getById(id);
            if (!paymentMethodUp.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            paymentMethod.setId(id);
            paymentMethodService.save(paymentMethod);
            return new ResponseEntity<>(paymentMethod,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de los Payment Methods ", description = "Método para eliminar Payment Methods")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment Method eliminado"),
            @ApiResponse(responseCode = "404", description = "Payment Method no encontrado")
    })
    public ResponseEntity<PaymentMethod> deletePaymentMethod (@PathVariable("id") Long id){
        try {
            Optional<PaymentMethod> paymentMethodDelete=paymentMethodService.getById(id);
            if(!paymentMethodDelete.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            paymentMethodService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}