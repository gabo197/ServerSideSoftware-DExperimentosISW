package com.appdhome.controller;

import com.appdhome.entities.City;
import com.appdhome.services.ICityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cities")
@Api(tags = "City", value = "RESTFul de Cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lista de todas las ciudades", notes = "Método para listar a todas las ciudades")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cities encontrados"),
            @ApiResponse(code = 404, message = "Cities no encontrados")
    })
    public ResponseEntity<List<City>>findAll(){
        try {
            List<City> cities = cityService.getAll();
            if (cities.size()>0){
                return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
            }else{
                return new ResponseEntity<List<City>>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<List<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar ciudad por Id", notes = "Método para encontrar una ciudad por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City encontrado"),
            @ApiResponse(code = 404, message = "City no encontrado")
    })
    public ResponseEntity<City> findById(@PathVariable("id") Long id){
        try{
            Optional<City> city=cityService.getById(id);
            if (!city.isPresent()){
                return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<City>(city.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<City>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value = "/searchName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar ciudad por su nombre", notes = "Método para encontrar ciudades por su nombre")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City encontrados"),
            @ApiResponse(code = 404, message = "City no encontrados")
    })
    public ResponseEntity<City> findByName(@PathVariable("name") String name){
        try {
            City city=cityService.findByName(name);
            if (city == null){
                return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<City>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<City>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ingreso de Ciudades", notes = "Método para ingresar ciudades")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City creado"),
            @ApiResponse(code = 404, message = "City no creado")
    })
    public ResponseEntity<City> insertCity(@RequestBody City city){
        try {
            City cityNew = cityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(cityNew);
        }catch (Exception e){
            return new ResponseEntity<City>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Cities", notes = "Método para actualizar los datos del city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Datos de city actualizados"),
            @ApiResponse(code = 404, message = "Datos de city no actualizados")
    })
    public ResponseEntity<City> updateCity(@PathVariable("id") Long id, @RequestBody City city){
        try {
            Optional<City> cityUp=cityService.getById(id);
            if (!cityUp.isPresent()){
                return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
            }
            city.setId(id);
            cityService.save(city);
            return new ResponseEntity<City>(city,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<City>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de los cities", notes = "Método para eliminar Cities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "City eliminado"),
            @ApiResponse(code = 404, message = "City no encontrado")
    })
    public ResponseEntity<City> deleteCity (@PathVariable("id") Long id){
        try {
            Optional<City> cityDelete=cityService.getById(id);
            if(!cityDelete.isPresent()){
                return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
            }
            cityService.delete(id);
            return new ResponseEntity<City>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<City>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
