package com.appdhome.controller;

import com.appdhome.entities.Customer;
import com.appdhome.entities.District;
import com.appdhome.entities.Employee;
import com.appdhome.entities.Specialty;
import com.appdhome.services.ICustomerService;
import com.appdhome.services.IDistrictService;
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
import springfox.documentation.spring.web.json.Json;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
@Api(tags="Customer", value = "RESTFul de Customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDistrictService districtService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar todos los clientes", notes = "Método para listar a todos los clientes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customers encontrados"),
            @ApiResponse(code = 404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findAll()
    {
        try {
            List<Customer> customers = customerService.getAll();
            if (customers.size() > 0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar cliente por Id", notes = "Método para encontrar un cliente por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer encontrado"),
            @ApiResponse(code = 404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.getById(id);

            if (!customer.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByIdAccount/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Buscar cliente por id de la cuenta", notes ="Método para encontrar un cleinte con su respectivo id de la cuenta" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Cliente encontrado"),
            @ApiResponse(code =404,message ="Cliente no encontrado")
    })

    public ResponseEntity<Customer> findByIdAccount(@PathVariable("id") Long id){
        try{
            Optional<Customer> customer= customerService.findByIdAccount(id);
            if(!customer.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer.get(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/searchFirstname/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar cliente por su nombre", notes = "Método para encontrar clientes por su nombre")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customers encontrados"),
            @ApiResponse(code = 404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByFirstName(@PathVariable("firstname") String firstname){
        try{
            List<Customer> customers = customerService.findByFirstName(firstname);
            if (customers.size() > 0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchLastname/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE) //Poner los datos iguales
    @ApiOperation(value = "Buscar clientes por su apellido", notes = "Método par encontrar clientes por su apellido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customers encontrados"),
            @ApiResponse(code = 404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByLastName(@PathVariable("lastname") String lastname){
        try{
            List<Customer> customers = customerService.findByLastName(lastname);
            if (customers.size() > 0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchFirstnameAndLastname/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Bucar clientes por sus nombres y apellidos", notes = "Método para encontrar clientes por sus respectivos nombre y apellido ")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customers encontrados"),
            @ApiResponse(code = 404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByFirstNameAndLastName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        try{
            List<Customer> customers = customerService.findByFirstNameAndLastName(firstname, lastname);
            if (customers.size()>0){
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchDni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar cliente por DNI", notes = "Método para encontrar cliente por DNI")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer encontrado"),
            @ApiResponse(code = 404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> findByDni(@PathVariable("dni") String dni){
        try{
            Customer customer = customerService.findByDni(dni);
            if (customer == null)
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ingreso de Clientes", notes = "Método para ingresar clientes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer creado"),
            @ApiResponse(code = 404, message = "Customer no creado")
    })
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer){
        try {
            Customer customerNew = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerNew);
        }catch (Exception ex){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping( value = "/validateEmail",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Validar correo de customer", notes = "Validar corre de customer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Valid Email"),
            @ApiResponse(code = 404, message = "Invalid Email")
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

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Customers", notes = "Método para actualizar los datos del customer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de customer actualizados"),
            @ApiResponse(code = 404, message = "Datos de customer no actualizados")
    })
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") Long id, @RequestBody Customer customer) {
        try{
            Optional<Customer> customerUp = customerService.getById(id);
            if(!customerUp.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            customer.setId(id);
            customerService.save(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de los customers", notes = "Método para eliminar Customers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer eliminado"),
            @ApiResponse(code = 404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")Long id){
        try{
            Optional<Customer> customerDelete = customerService.getById(id);
            if(!customerDelete.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

            customerService.delete(id);
            return new ResponseEntity<Customer>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}