package com.user.service.feignclients;


import com.user.service.models.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Scooter-service")
@RequestMapping("/scooter")
public interface ScooterFeignClient {

    @PostMapping()
    public Scooter save(@RequestBody Scooter scooter);

    @GetMapping("/user/{userId}")
    public List<Scooter> getScooters(@PathVariable("userId") int userId);

}
