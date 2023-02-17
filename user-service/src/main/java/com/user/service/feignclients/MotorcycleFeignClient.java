package com.user.service.feignclients;


import com.user.service.models.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "motorcycle-service",url = "http://localhost:8003")
@RequestMapping("/motorcycle")
public interface MotorcycleFeignClient {


    @PostMapping()
    public Motorcycle save(@RequestBody Motorcycle motorcycle);


    @GetMapping("/user/{userId}")
    public List<Motorcycle> getMotorcycles(@PathVariable("userId") int userId);

}
