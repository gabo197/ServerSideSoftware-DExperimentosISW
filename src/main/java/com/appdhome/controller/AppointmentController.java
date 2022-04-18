package com.appdhome.controller;

import com.appdhome.entities.Appointment;
import com.appdhome.entities.Customer;
import com.appdhome.entities.Employee;
import com.appdhome.entities.PaymentMethod;
import com.appdhome.services.IAppointmentService;

import com.appdhome.services.ICustomerService;
import com.appdhome.services.IEmployeeService;
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
@RequestMapping("/api/appointments")
@Api(tags="Appointment", value = "RESTFul de Appointments")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar todas las citas", notes = "Método para listar a todas las citas")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Citas encontradas"),
            @ApiResponse(code = 404, message = "Citas no encontradas")
    })
    public ResponseEntity<List<Appointment>> findAll()
    {
        try {
            List<Appointment> appointments = appointmentService.getAll();
            System.out.println("Citas "+ appointments);
            if (appointments.size() > 0)
                return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
            else
                return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Appointment>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar citas por ID", notes = "Método para buscar cita por ID")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cita encontrada"),
            @ApiResponse(code = 404, message = "Cita no encontrada")
    })
    public ResponseEntity<Appointment> findById(@PathVariable("id") Long id)
    {
        try {
            Optional<Appointment> appointment = appointmentService.getById(id);
            System.out.println("Cita por ID " + appointment);
            if (!appointment.isPresent())
                return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Appointment>(appointment.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar citas por estado", notes = "Método para buscar cita por estado")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Citas por estado encontradas"),
            @ApiResponse(code = 404, message = "Citas por estado no encontradas")
    })
    public ResponseEntity<List<Appointment>> findByStatus(@PathVariable("status") String status)
    {
        try {
            List<Appointment> appointments = appointmentService.findByStatus(status);
            System.out.println("Citas por estado "+ appointments);
            if(appointments.size()>0)
                return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
            else
                return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Appointment>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByIdCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar citas por ID Customer", notes = "Método para buscar cita por ID Customer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Citas por estado encontradas"),
            @ApiResponse(code = 404, message = "Citas por estado no encontradas")
    })
    public ResponseEntity<List<Appointment>> findByIdCustomer(@PathVariable("id") Long id)
    {
        try {
            List<Appointment> appointmentsByCustomer = appointmentService.findByIdCustomer(id);
            System.out.println("Citas por ID Customers "+ appointmentsByCustomer);
            if(appointmentsByCustomer.size()>0)
                return new ResponseEntity<List<Appointment>>(appointmentsByCustomer, HttpStatus.OK);
            else
                return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Appointment>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByIdEmployee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar citas por ID Employee", notes = "Método para buscar cita por ID Employee")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Citas por trabajador encontradas"),
            @ApiResponse(code = 404, message = "Citas por trabajador no encontradas")
    })
    public ResponseEntity<List<Appointment>> findByIdEmployee(@PathVariable("id") Long id)
    {
        try {
            List<Appointment> appointmentsByEmployee = appointmentService.findByIdEmployee(id);
            System.out.println("Citas por ID Employee "+ appointmentsByEmployee);
            if(appointmentsByEmployee.size()>0)
                return new ResponseEntity<List<Appointment>>(appointmentsByEmployee, HttpStatus.OK);
            else
                return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<List<Appointment>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de una cita de un Customer", notes ="Método que registra una cita" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Appointment creado"),
            @ApiResponse(code=404, message = "Appointment no creado")
    })
    public ResponseEntity<Appointment> insertAppointment(@Valid @RequestBody Appointment appointment){
        try{
            Appointment appointmentNew = appointmentService.save(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentNew);

        }catch (Exception e){
            return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Citas", notes = "Método para actualizar los datos de la cita")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de la cita actualizados"),
            @ApiResponse(code = 404, message = "Cita no encontrada")
    })
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("id") Long id, @RequestBody Appointment appointment) {
        try{
            Optional<Appointment> appointmentUp = appointmentService.getById(id);
            if(!appointmentUp.isPresent())
                return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
            appointment.setId(id);
            appointmentService.save(appointment);
            return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de las citas", notes = "Método para eliminar citas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cita eliminada"),
            @ApiResponse(code = 404, message = "Cita no encontrado")
    })
    public ResponseEntity<Appointment> deleteAppointment (@PathVariable("id")Long id){
        try{
            Optional<Appointment> appointmentDelete = appointmentService.getById(id);
            if(!appointmentDelete.isPresent())
                return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);

            appointmentService.delete(id);
            return new ResponseEntity<Appointment>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

/*
* @Autowired
    private ICustomerService customerService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPaymentMethodService paymentMethodService;
* */