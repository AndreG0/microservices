package com.user.service.controller;


import com.user.service.entity.User;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;
import com.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


   @Autowired
   private UserService userService;

//LISTA DE USUARIOS
   @GetMapping("/list")
   public ResponseEntity<List<User>> userList(){
       List<User> users = userService.getAll();
       if (users.isEmpty()){
           return  ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(users);
   }

//BUSQUEDA POR ID
   @GetMapping("/{id}")
   public ResponseEntity<User> gerUser(@PathVariable("id") int id){
       User user = userService.getUserById(id);
       if (user == null){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(user);
   }

   //GUARDAR UN USUARIO
   @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
       User newUser  = userService.save(user);
       return  ResponseEntity.ok((newUser));
   }

   //ELIMINAR UN USUARIO
   @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
       if (!userService.existById(id))
           return new ResponseEntity("Doesn't Exist",HttpStatus.NOT_FOUND);
       userService.delete(id);
       return new ResponseEntity("Item Removed",HttpStatus.OK);
   }



    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> carList(@PathVariable("userId")int id){
       User user = userService.getUserById(id);
       if (user == null){
           return ResponseEntity.notFound().build();
       }
       List<Car> cars = userService.getCars(id);
       return ResponseEntity.ok(cars);
   }


    @GetMapping("/motorcycle/{userId}")
    public ResponseEntity<List<Motorcycle>> motorcycleList(@PathVariable("userId")int id){
        User user = userService.getUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        List<Motorcycle> motorcycles = userService.getMotorcycles(id);
        return ResponseEntity.ok(motorcycles);
    }

//FEIGN SAVE CAR
    @PostMapping("/car/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId")int userId, @RequestBody Car car){
       Car newCar = userService.saveCar(userId, car);
       return ResponseEntity.ok(newCar);
    }
//FEIGN SAVE MOTORCYCLE

    @PostMapping("/motorcycle/{userId}")
    public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable("userId")int userId, @RequestBody Motorcycle motorcycle){
       Motorcycle newMotorcycle = userService.saveMotorcycle(userId, motorcycle);
       return  ResponseEntity.ok(newMotorcycle);
    }

//VEHICLES LIST BY USER
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> listOfAllVehicles(@PathVariable("userId") int userId){
       Map<String, Object> result = userService.getUserAndVehicles(userId);
       return ResponseEntity.ok(result);
    }
}
