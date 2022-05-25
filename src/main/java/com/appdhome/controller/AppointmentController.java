package com.appdhome.controller;

import com.appdhome.entities.Appointment;
import com.appdhome.services.IAppointmentService;
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
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;
    
    @GetMapping()
    @Operation(summary = "Listar todas las citas", description = "Método para listar a todas las citas")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Citas encontradas"),
            @ApiResponse(responseCode = "404", description = "Citas no encontradas")
    })
    public ResponseEntity<List<Appointment>> findAll()
    {
        try {
            List<Appointment> appointments = appointmentService.getAll();
            System.out.println("Citas "+ appointments);
            if (appointments.size() > 0)
                return new ResponseEntity<>(appointments, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar citas por ID", description = "Método para buscar cita por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cita encontrada"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Appointment> findById(@PathVariable("id") Long id)
    {
        try {
            Optional<Appointment> appointment = appointmentService.getById(id);
            System.out.println("Cita por ID " + appointment);
            if (!appointment.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByStatus/{status}")
    @Operation(summary = "Buscar citas por estado", description = "Método para buscar cita por estado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Citas por estado encontradas"),
            @ApiResponse(responseCode = "404", description = "Citas por estado no encontradas")
    })
    public ResponseEntity<List<Appointment>> findByStatus(@PathVariable("status") String status)
    {
        try {
            List<Appointment> appointments = appointmentService.findByStatus(status);
            System.out.println("Citas por estado "+ appointments);
            if(appointments.size()>0)
                return new ResponseEntity<>(appointments, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByIdCustomer/{id}")
    @Operation(summary = "Buscar citas por ID Customer", description = "Método para buscar cita por ID Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Citas por estado encontradas"),
            @ApiResponse(responseCode = "404", description = "Citas por estado no encontradas")
    })
    public ResponseEntity<List<Appointment>> findByIdCustomer(@PathVariable("id") Long id)
    {
        try {
            List<Appointment> appointmentsByCustomer = appointmentService.findByIdCustomer(id);
            System.out.println("Citas por ID Customers "+ appointmentsByCustomer);
            if(appointmentsByCustomer.size()>0)
                return new ResponseEntity<>(appointmentsByCustomer, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByIdEmployee/{id}")
    @Operation(summary = "Buscar citas por ID Employee", description = "Método para buscar cita por ID Employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Citas por trabajador encontradas"),
            @ApiResponse(responseCode = "404", description = "Citas por trabajador no encontradas")
    })
    public ResponseEntity<List<Appointment>> findByIdEmployee(@PathVariable("id") Long id)
    {
        try {
            List<Appointment> appointmentsByEmployee = appointmentService.findByIdEmployee(id);
            System.out.println("Citas por ID Employee "+ appointmentsByEmployee);
            if(appointmentsByEmployee.size()>0)
                return new ResponseEntity<>(appointmentsByEmployee, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Registro de una cita de un Customer", description ="Método que registra una cita" )
    @ApiResponses({
            @ApiResponse(responseCode="201", description = "Appointment creado"),
            @ApiResponse(responseCode="404", description = "Appointment no creado")
    })
    public ResponseEntity<Appointment> insertAppointment(@Valid @RequestBody Appointment appointment){
        try{
            Appointment appointmentNew = appointmentService.save(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentNew);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualización de datos de Citas", description = "Método para actualizar los datos de la cita")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de la cita actualizados"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("id") Long id, @RequestBody Appointment appointment) {
        try{
            Optional<Appointment> appointmentUp = appointmentService.getById(id);
            if(!appointmentUp.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            appointment.setId(id);
            appointmentService.save(appointment);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de las citas", description = "Método para eliminar citas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cita eliminada"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrado")
    })
    public ResponseEntity<Appointment> deleteAppointment (@PathVariable("id")Long id){
        try{
            Optional<Appointment> appointmentDelete = appointmentService.getById(id);
            if(!appointmentDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            appointmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}