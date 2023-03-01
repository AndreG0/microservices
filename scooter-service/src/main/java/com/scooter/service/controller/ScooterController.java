package com.scooter.service.controller;


import com.scooter.service.entity.Scooter;
import com.scooter.service.service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooter")
public class ScooterController {

    @Autowired
    private ScooterService scooterService;

    //lista de scooters
    @GetMapping
    public ResponseEntity<List<Scooter>> scooterList(){
        List<Scooter> scooters = scooterService.getAll();
        if (scooters.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }


    //buscar scooter por id
    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooter(@PathVariable("id") int id) {
        Scooter scooter = scooterService.getScooterById(id);
        if (scooter == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }

    //guardar scooter
    @PostMapping
    public  ResponseEntity<Scooter> saveScooter(@RequestBody Scooter scooter){
        Scooter newScooter = scooterService.save(scooter);
        return ResponseEntity.ok(newScooter);
    }

    //eliminar scooter
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if (!scooterService.existById(id))
            return new ResponseEntity("Doesn't Exist", HttpStatus.NOT_FOUND);
        scooterService.delete(id);
        return new ResponseEntity("Item Removed", HttpStatus.OK);
    }

    //lista de scooters por usuario id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Scooter>> scooterListByUserId(@PathVariable("userId") int id){
        List<Scooter> scooters = scooterService.byUserId(id);
        if (scooters.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(scooters);
    }
}
