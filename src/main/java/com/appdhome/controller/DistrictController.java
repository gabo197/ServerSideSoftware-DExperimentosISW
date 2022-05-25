package com.appdhome.controller;

import com.appdhome.entities.City;
import com.appdhome.entities.District;
import com.appdhome.services.ICityService;
import com.appdhome.services.IDistrictService;
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
@RequestMapping("/api/districts")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;
    @Autowired
    private ICityService cityService;

    @GetMapping()
    @Operation(summary = "Lista de todos los distritos", description = "Método para listar a todos los distritos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Districts encontrados"),
            @ApiResponse(responseCode = "404", description = "Districts no encontrados")
    })
    public ResponseEntity<List<District>>findAll(){
        try {
            List<District> districts = districtService.getAll();
            if (districts.size()>0){
                return new ResponseEntity<>(districts, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar distrito por Id", description = "Método para encontrar un distrito por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "District encontrado"),
            @ApiResponse(responseCode = "404", description = "District no encontrado")
    })
    public ResponseEntity<District> findById(@PathVariable("id") Long id){
        try{
            Optional<District> district=districtService.getById(id);
            if (!district.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(district.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getDistrictsByCity/{id}")
    @Operation(summary = "Buscar distrito por id de la ciudad", description = "Método para encontrar un distrito por id de la ciudad")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "District encontrado"),
            @ApiResponse(responseCode = "404", description = "District no encontrado")
    })
    public ResponseEntity<List<District>> findDistrictByIdCity(@PathVariable("id") Long id){
        try{
            List<District> districts =districtService.findByIDCity(id);
            return new ResponseEntity<>(districts,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchName/{name}")
    @Operation(summary = "Buscar distrito por su nombre", description = "Método para encontrar distritos por su nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Districts encontrados"),
            @ApiResponse(responseCode = "404", description = "Districts no encontrados")
    })
    public ResponseEntity<District> finByName(@PathVariable("name") String name){
        try {
            District district=districtService.findByName(name);
            if (district == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    @Operation(summary = "Registro de un distrito de una ciudad", description ="Método que registra un distrito" )
    @ApiResponses({
            @ApiResponse(responseCode="201", description = "Distrito creado"),
            @ApiResponse(responseCode="404", description = "Distrito no creado")
    })
    public ResponseEntity<District> insertDistrict(@PathVariable("id") Long id, @Valid @RequestBody District district){
        try{
            Optional<City>  city = cityService.getById(id);
            if(city.isPresent()){
                district.setCity(city.get());
                District districtNew = districtService.save(district);
                return ResponseEntity.status(HttpStatus.CREATED).body(districtNew);
            }
            else
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualización de datos de Districts", description = "Método para actualizar los datos del district")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos de district actualizados"),
            @ApiResponse(responseCode = "404", description = "Datos de district no actualizados")
    })
    public ResponseEntity<District> updateDistrict(@PathVariable("id") Long id, @RequestBody District district){
        try {
            Optional<District> districtUp=districtService.getById(id);
            if (!districtUp.isPresent()){
                return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
            }
            district.setId(id);
            districtService.save(district);
            return new ResponseEntity<District>(district,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<District>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminación de los districts", description = "Método para eliminar Districts")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "District eliminado"),
            @ApiResponse(responseCode = "404", description = "District no encontrado")
    })
    public ResponseEntity<District> deleteDistrict (@PathVariable("id") Long id){
        try {
            Optional<District> districtDelete=districtService.getById(id);
            if(!districtDelete.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            districtService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
