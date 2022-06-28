package com.appdhome.controller;

import com.appdhome.entities.Employee;
import com.appdhome.services.IEmployeeService;
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
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @PostMapping()
    @Operation(summary = "Registro de un empleado", description = "Método que registra un empleado y su especialidad")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Trabajador creado"),
            @ApiResponse(responseCode = "404", description = "Trabajador no creado")
    })
    public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {
        try {
            Employee employeeNew = employeeService.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeNew);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary="Buscar trabajador por id", description = "Método para encontrar un trabajador con su respectivo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
        try {
            Optional<Employee> employee = employeeService.getById(id);
            if (!employee.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @Operation(summary="Listar trabajadores", description = "Método para listar todo los trabajadores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajadores encontrados"),
            @ApiResponse(responseCode = "404", description = "Trabajadores no encontrados")
    })
    public ResponseEntity<List<Employee>> findAll() {
        try {
            List<Employee> employees = employeeService.getAll();
            if (employees.size() > 0)
                return new ResponseEntity<>(employees, HttpStatus.OK);
            else
                return new ResponseEntity<>(employees, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary="Actualización de datos trabajadores", description = "Método que actualizar los datos de los trabajadores")
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description = "Datos de trabajadores actualizados"),
            @ApiResponse(responseCode = "404", description = "Datos de trabajadores no actualizados")
    })
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody Employee employee) {
        try {
            Optional<Employee> employeeUp = employeeService.getById(id);
            if(!employeeUp.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            employee.setId(id);
            employeeService.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de los trabajadores", description = "Método para eliminar trabajadores")
    @ApiResponses({
            @ApiResponse(responseCode="200", description = "Trabajador eliminado"),
            @ApiResponse(responseCode="404", description = "Trabajador no encontrado")
    })
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        try {
            Optional<Employee> employeeDelete = employeeService.getById(id);
            if(!employeeDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByDni/{dni}")
    @Operation(summary="Buscar trabajador por su DNI", description = "Método para encontrar un trabajador con su respectivo DNI")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<Employee> findByDni(@PathVariable("dni") String dni) {
        try {
            Optional<Employee> employee = employeeService.findByDni(dni);
            if (!employee.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByFirstname/{firstname}")
    @Operation(summary="Buscar trabajador por su nombre", description = "Método para encontrar un trabajador con su respectivo nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajadores encontrados"),
            @ApiResponse(responseCode = "404",description = "Trabajadores no encontrados")
    })
    public ResponseEntity<List<Employee>> findByFirstName(@PathVariable("firstname") String lastname) {
        try {
            List<Employee> employees = employeeService.findByFirstName(lastname);
            if(employees.size() > 0)
                return new ResponseEntity<>(employees,HttpStatus.OK);
            else
                return new ResponseEntity<>(employees,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByLastname/{lastname}")
    @Operation(summary="Buscar trabajador por su apellido", description ="Método para encontrar un trabajador con su respectivo apellido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajadores encontrados"),
            @ApiResponse(responseCode = "404", description = "Trabajadores no encontrados")
    })
    public ResponseEntity<List<Employee>> findByLastName(@PathVariable("lastname") String lastname) {
        try {
            List<Employee> employees = employeeService.findByLastName(lastname);
            if (employees.size() > 0)
                return new ResponseEntity<>(employees,HttpStatus.OK);
            else
                return new ResponseEntity<>(employees,HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByLastNameAndFirstName/{lastname}/{firstname}")
    @Operation(summary="Buscar trabajador por su apellido y nombre", description = "Método para encontrar un trabajador con su respectivo apellido y nombre")
    @ApiResponses({
            @ApiResponse(responseCode="200", description = "Trabajadores encontrados"),
            @ApiResponse(responseCode ="404",description ="Trabajadores no encontrados")
    })
    public ResponseEntity<List<Employee>> findByLastNameAndFirstName (@PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname) {
        try {
            List<Employee> employees=employeeService.findByLastNameAndFirstName(lastname,firstname);
            if(employees.size() > 0)
                return new ResponseEntity<>(employees,HttpStatus.OK);
            else
                return new ResponseEntity<>(employees,HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByEmail/{email}")
    @Operation(summary="Buscar trabajador por su email", description = "Método para encontrar un trabajador con su respectivo email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404",description = "Trabajador no encontrado")
    })
    public ResponseEntity<Employee> findByEmail(@PathVariable("email") String email) {
        try {
            Optional<Employee> employee = employeeService.findByEmail(email);
            if (!employee.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByAccountId/{accountId}")
    @Operation(summary="Buscar trabajador por id de la cuenta", description = "Método para encontrar un trabajador con su respectivo id de la cuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404",description = "Trabajador no encontrado")
    })
    public ResponseEntity<Employee> findByAccountId(@PathVariable("accountId") Long accountId) {
        try {
            Optional<Employee> employee = employeeService.findByAccountId(accountId);
            if (!employee.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByGender/{gender}")
    @Operation(summary="Buscar trabajador por su género", description = "Método para encontrar un trabajador con su respectivo género")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajadores encontrados"),
            @ApiResponse(responseCode = "404",description = "Trabajadores no encontrados")
    })
    public ResponseEntity<List<Employee>> findByGender(@PathVariable("gender") String gender) {
        try {
            List<Employee> employees = employeeService.findByBirthday(gender);
            if(employees.size() > 0)
                return new ResponseEntity<>(employees,HttpStatus.OK);
            else
                return new ResponseEntity<>(employees,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validateEmail")
    @Operation(summary = "Validar correo de employee", description = "Validar corre de employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Valid Email"),
            @ApiResponse(responseCode = "404", description = "Invalid Email")
    })
    public ResponseEntity<?> validateEmailEmployee(@Valid @RequestBody Employee employee) {
        try {
            Optional<Employee> employeeEmail = employeeService.findByEmail(employee.getEmail());
            if(!employeeEmail.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            Response res = new Response();
            res.setMsj("El email ya existe");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}