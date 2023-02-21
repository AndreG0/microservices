package com.car.service.controller;


import com.car.service.entity.Car;
import com.car.service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {


    @Autowired
    private CarService carService;

    //lista de carros
    @GetMapping
    public ResponseEntity<List<Car>> carList() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }


    //buscar carro por Id
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    //guardar carro
    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    //eliminar carro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!carService.existById(id))
            return new ResponseEntity("Doesn't Exist", HttpStatus.NOT_FOUND);
        carService.delete(id);
        return new ResponseEntity("Item Removed", HttpStatus.OK);
    }

    //lista de carros por usuario Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> carListByUserId(@PathVariable("userId") int id) {
        List<Car> cars = carService.byUserId(id);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }


}
