package com.appdhome.controller;

import com.appdhome.entities.Customer;
import com.appdhome.services.ICustomerService;
import com.appdhome.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping()
    @Operation(summary = "Listar todos los clientes", description = "Método para listar a todos los clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers encontrados"),
            @ApiResponse(responseCode = "404", description = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findAll()
    {
        try {
            List<Customer> customers = customerService.getAll();
            if (customers.size() > 0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por Id", description = "Método para encontrar un cliente por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer encontrado"),
            @ApiResponse(responseCode = "404", description = "Customer no encontrado")
    })
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.getById(id);
            if (!customer.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByIdAccount/{id}")
    @Operation(summary="Buscar cliente por id de la cuenta", description ="Método para encontrar un cleinte con su respectivo id de la cuenta" )
    @ApiResponses({
            @ApiResponse(responseCode="200", description = "Cliente encontrado"),
            @ApiResponse(responseCode ="404",description ="Cliente no encontrado")
    })

    public ResponseEntity<Customer> findByIdAccount(@PathVariable("id") Long id){
        try{
            Optional<Customer> customer= customerService.findByIdAccount(id);
            if(!customer.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchFirstname/{firstname}")
    @Operation(summary = "Buscar cliente por su nombre", description = "Método para encontrar clientes por su nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers encontrados"),
            @ApiResponse(responseCode = "404", description = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByFirstName(@PathVariable("firstname") String firstname){
        try{
            List<Customer> customers = customerService.findByFirstName(firstname);
            if (customers.size() > 0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchLastname/{lastname}") //Poner los datos iguales
    @Operation(summary = "Buscar clientes por su apellido", description = "Método par encontrar clientes por su apellido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers encontrados"),
            @ApiResponse(responseCode = "404", description = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByLastName(@PathVariable("lastname") String lastname){
        try{
            List<Customer> customers = customerService.findByLastName(lastname);
            if (customers.size() > 0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchFirstnameAndLastname/{firstname}/{lastname}")
    @Operation(summary = "Bucar clientes por sus nombres y apellidos", description = "Método para encontrar clientes por sus respectivos nombre y apellido ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers encontrados"),
            @ApiResponse(responseCode = "404", description = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByFirstNameAndLastName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        try{
            List<Customer> customers = customerService.findByFirstNameAndLastName(firstname, lastname);
            if (customers.size()>0){
                return new ResponseEntity<>(customers, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchDni/{dni}")
    @Operation(summary = "Buscar cliente por DNI", description = "Método para encontrar cliente por DNI")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer encontrado"),
            @ApiResponse(responseCode = "404", description = "Customer no encontrado")
    })
    public ResponseEntity<Customer> findByDni(@PathVariable("dni") String dni){
        try{
            Customer customer = customerService.findByDni(dni);
            if (customer == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Ingreso de Clientes", description = "Método para ingresar clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer creado"),
            @ApiResponse(responseCode = "404", description = "Customer no creado")
    })
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer){
        try {
            Customer customerNew = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerNew);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validateEmail")
    @Operation(summary = "Validar correo de customer", description = "Validar corre de customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Valid Email"),
            @ApiResponse(responseCode = "404", description = "Invalid Email")
    })
    public ResponseEntity<?> validateEmailCustomer(@Valid @RequestBody Customer customer){
        try {
            System.out.println("email" + customer);
            Optional<Customer> customerEmail = customerService.findByEmail(customer.getEmail());
            if(!customerEmail.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            Response res = new Response();
            res.setMsj("El email ya existe");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualización de datos de Customers", description = "Método para actualizar los datos del customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de customer actualizados"),
            @ApiResponse(responseCode = "404", description = "Datos de customer no actualizados")
    })
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") Long id, @RequestBody Customer customer) {
        try{
            Optional<Customer> customerUp = customerService.getById(id);
            if(!customerUp.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            customer.setId(id);
            customerService.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de los customers", description = "Método para eliminar Customers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer eliminado"),
            @ApiResponse(responseCode = "404", description = "Customer no encontrado")
    })
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")Long id){
        try{
            Optional<Customer> customerDelete = customerService.getById(id);
            if(!customerDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}