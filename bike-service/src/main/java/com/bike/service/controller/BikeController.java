package com.bike.service.controller;


import com.bike.service.entity.Bike;
import com.bike.service.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    //lista de bicicletas
    @GetMapping
    public ResponseEntity<List<Bike>> bikeList(){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty()){
          return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    //buscar bicicleta por id
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable("id") int id){
        Bike bike = bikeService.getBikeById(id);
        if (bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    //guardar bicicleta
    @PostMapping
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike newBike = bikeService.save(bike);
        return ResponseEntity.ok(newBike);
    }

    //elimina bicicleta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!bikeService.existById(id))
            return new ResponseEntity("Doesn't Exist", HttpStatus.NOT_FOUND);
        bikeService.delete(id);
        return new ResponseEntity("Item Removed", HttpStatus.OK);
    }

    //lista de bicicletas por usuario id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bike>> bikeListByUserId(@PathVariable ("userId") int id){
        List<Bike> bikes = bikeService.byUserId(id);
        if (bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

}
