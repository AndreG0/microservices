package com.motorcycle.service.controller;


import com.motorcycle.service.entity.Motorcycle;
import com.motorcycle.service.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;


    //lista de motos
    @GetMapping
    public ResponseEntity<List<Motorcycle>> motorcycleList(){
        List<Motorcycle> motorcycles = motorcycleService.getAll();
        if (motorcycles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycles);
    }
    //buscar moto por id
    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycle(@PathVariable("id") int id){
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(id);
        if (motorcycle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorcycle);
    }
    //guardar moto
    @PostMapping
    public ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody Motorcycle motorcycle){
        Motorcycle newMotorcycle = motorcycleService.save(motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }

    //lista de motos por usuario ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!motorcycleService.existById(id))
            return new ResponseEntity("Doesn't Exist", HttpStatus.NOT_FOUND);
        motorcycleService.delete(id);
        return new ResponseEntity("Item Removed", HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Motorcycle>> motorcycleByUserId(@PathVariable("userId") int id){
        List<Motorcycle> motorcycles = motorcycleService.byUserId(id);
        if (motorcycles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycles);
    }


}
