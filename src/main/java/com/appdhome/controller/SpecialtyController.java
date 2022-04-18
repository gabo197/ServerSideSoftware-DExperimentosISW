package com.appdhome.controller;


import com.appdhome.entities.Employee;
import com.appdhome.entities.Specialty;
import com.appdhome.services.IEmployeeService;
import com.appdhome.services.ISpecialtyService;
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
@RequestMapping("/api/specialty")
@Api(tags = "Specialty", value = "RESTFul de Specialties")
public class SpecialtyController {
    @Autowired
    private ISpecialtyService specialtyService;



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lista de todas las especialidades", notes = "Método para listar a todas las especialidades")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Specialties encontradas"),
            @ApiResponse(code = 404, message = "Specialties no encontradas")
    })
    public ResponseEntity<List<Specialty>> findAll(){
        try {
            List<Specialty> specialties = specialtyService.getAll();
            if (specialties.size()>0){
                return new ResponseEntity<List<Specialty>>(specialties, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<Specialty>>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<List<Specialty>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar especialidad por Id", notes = "Método para encontrar una especialidad por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Specialty encontrada"),
            @ApiResponse(code = 404, message = "Specialty no encontrada")
    })
    public ResponseEntity<Specialty> findById(@PathVariable("id") Long id){
        try{
            Optional<Specialty> specialty=specialtyService.getById(id);
            if (!specialty.isPresent()){
                return new ResponseEntity<Specialty>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Specialty>(specialty.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar especialidad por su nombre", notes = "Método para encontrar una especialidad por su nombre")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Specialties encontradas"),
            @ApiResponse(code = 404, message = "Specialties no encontradas")
    })
    public ResponseEntity<Specialty> finByName(@PathVariable("name") String name){
        try {
            Specialty specialty=specialtyService.findByName(name);
            if (specialty == null){
                return new ResponseEntity<Specialty>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Specialty>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de una especialidad", notes = "Método que registra una especialidad")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Specialty creada"),
            @ApiResponse(code = 404, message = "Specialty no creada")
    })
    public ResponseEntity<Specialty> insertSpecialty(@RequestBody Specialty specialty){
        try{
            Specialty specialtyNew = specialtyService.save(specialty);
            return ResponseEntity.status(HttpStatus.CREATED).body(specialtyNew);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de especialidades", notes = "Método para actualizar los datos de especialidades")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de Specialty actualizadas"),
            @ApiResponse(code = 404, message = "Datos de Specialty no actualizadas")
    })
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable("id") Long id, @RequestBody Specialty specialty){
        try {
            Optional<Specialty> specialtyUp=specialtyService.getById(id);
            if (!specialtyUp.isPresent()){
                return new ResponseEntity<Specialty>(HttpStatus.NOT_FOUND);
            }
            specialty.setId(id);
            specialtyService.save(specialty);
            return new ResponseEntity<Specialty>(specialty,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de las especialidades", notes = "Método para eliminar especialidades")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Specialty eliminada"),
            @ApiResponse(code = 404, message = "Specialty no encontrada")
    })
    public ResponseEntity<Specialty> deleteSpecialty (@PathVariable("id") Long id){
        try {
            Optional<Specialty> specialtyDelete=specialtyService.getById(id);
            if(!specialtyDelete.isPresent()){
                return new ResponseEntity<Specialty>(HttpStatus.NOT_FOUND);
            }
            specialtyService.delete(id);
            return new ResponseEntity<Specialty>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Specialty>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
