package com.appdhome.controller;

import com.appdhome.entities.City;
import com.appdhome.services.ICityService;
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
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping()
    @Operation(summary = "Lista de todas las ciudades", description = "Método para listar a todas las ciudades")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cities encontrados"),
            @ApiResponse(responseCode = "404", description = "Cities no encontrados")
    })
    public ResponseEntity<List<City>>findAll(){
        try {
            List<City> cities = cityService.getAll();
            if (cities.size()>0){
                return new ResponseEntity<>(cities, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ciudad por Id", description = "Método para encontrar una ciudad por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City encontrado"),
            @ApiResponse(responseCode = "404", description = "City no encontrado")
    })
    public ResponseEntity<City> findById(@PathVariable("id") Long id){
        try{
            Optional<City> city=cityService.getById(id);
            if (!city.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(city.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchName/{name}")
    @Operation(summary = "Buscar ciudad por su nombre", description = "Método para encontrar ciudades por su nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City encontrados"),
            @ApiResponse(responseCode = "404", description = "City no encontrados")
    })
    public ResponseEntity<City> findByName(@PathVariable("name") String name){
        try {
            Optional<City> city = cityService.findByName(name);
            if (!city.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Ingreso de Ciudades", description = "Método para ingresar ciudades")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "City creado"),
            @ApiResponse(responseCode = "404", description = "City no creado")
    })
    public ResponseEntity<City> insertCity(@RequestBody City city){
        try {
            City cityNew = cityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(cityNew);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualización de datos de Cities", description = "Método para actualizar los datos del city")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de city actualizados"),
            @ApiResponse(responseCode = "404", description = "Datos de city no actualizados")
    })
    public ResponseEntity<City> updateCity(@PathVariable("id") Long id, @RequestBody City city){
        try {
            Optional<City> cityUp=cityService.getById(id);
            if (!cityUp.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            city.setId(id);
            cityService.save(city);
            return new ResponseEntity<>(city,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de los cities", description = "Método para eliminar Cities")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City eliminado"),
            @ApiResponse(responseCode = "404", description = "City no encontrado")
    })
    public ResponseEntity<City> deleteCity (@PathVariable("id") Long id){
        try {
            Optional<City> cityDelete=cityService.getById(id);
            if(!cityDelete.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            cityService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}