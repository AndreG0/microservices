package com.user.service.feignclients;

import com.user.service.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service",url = "http://localhost:8002")
@RequestMapping("/car")
public interface CarFeignClient {

   @PostMapping()
   public Car save(@RequestBody Car car);

   @GetMapping("/user/{userId}")
   public List<Car> getCars(@PathVariable("userId") int userId);



}
