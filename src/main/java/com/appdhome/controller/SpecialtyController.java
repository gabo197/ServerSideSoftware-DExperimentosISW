package com.appdhome.controller;

import com.appdhome.entities.Specialty;
import com.appdhome.services.ISpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/specialty")
public class SpecialtyController {
    @Autowired
    private ISpecialtyService specialtyService;

    @GetMapping()
    @Operation(summary = "Lista de todas las especialidades", description = "Método para listar a todas las especialidades")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Specialties encontradas"),
            @ApiResponse(responseCode = "404", description = "Specialties no encontradas")
    })
    public ResponseEntity<List<Specialty>> findAll(){
        try {
            List<Specialty> specialties = specialtyService.getAll();
            if (specialties.size()>0){
                return new ResponseEntity<>(specialties, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar especialidad por Id", description = "Método para encontrar una especialidad por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Specialty encontrada"),
            @ApiResponse(responseCode = "404", description = "Specialty no encontrada")
    })
    public ResponseEntity<Specialty> findById(@PathVariable("id") Long id){
        try{
            Optional<Specialty> specialty=specialtyService.getById(id);
            if (!specialty.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(specialty.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchName/{name}")
    @Operation(summary = "Buscar especialidad por su nombre", description = "Método para encontrar una especialidad por su nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Specialties encontradas"),
            @ApiResponse(responseCode = "404", description = "Specialties no encontradas")
    })
    public ResponseEntity<Specialty> finByName(@PathVariable("name") String name){
        try {
            Optional<Specialty> specialty=specialtyService.findByName(name);
            if (!specialty.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Registro de una especialidad", description = "Método que registra una especialidad")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Specialty creada"),
            @ApiResponse(responseCode = "404", description = "Specialty no creada")
    })
    public ResponseEntity<Specialty> insertSpecialty(@RequestBody Specialty specialty){
        try{
            Specialty specialtyNew = specialtyService.save(specialty);
            return ResponseEntity.status(HttpStatus.CREATED).body(specialtyNew);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualización de datos de especialidades", description = "Método para actualizar los datos de especialidades")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de Specialty actualizadas"),
            @ApiResponse(responseCode = "404", description = "Datos de Specialty no actualizadas")
    })
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable("id") Long id, @RequestBody Specialty specialty){
        try {
            Optional<Specialty> specialtyUp=specialtyService.getById(id);
            if (!specialtyUp.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            specialty.setId(id);
            specialtyService.save(specialty);
            return new ResponseEntity<>(specialty,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de las especialidades", description = "Método para eliminar especialidades")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Specialty eliminada"),
            @ApiResponse(responseCode = "404", description = "Specialty no encontrada")
    })
    public ResponseEntity<Specialty> deleteSpecialty (@PathVariable("id") Long id){
        try {
            Optional<Specialty> specialtyDelete=specialtyService.getById(id);
            if(!specialtyDelete.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            specialtyService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}