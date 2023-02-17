package com.motorcycle.service.service;


import com.motorcycle.service.entity.Motorcycle;
import com.motorcycle.service.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;


    public boolean existById(int id){
        return motorcycleRepository.existsById(id);
    }

    public List<Motorcycle> getAll(){
        return motorcycleRepository.findAll();
    }

    public Motorcycle getMotorcycleById(int id){
        return motorcycleRepository.findById(id).orElse(null);
    }

    public Motorcycle save(Motorcycle motorcycle){
        Motorcycle newMotorcycle = motorcycleRepository.save(motorcycle);
        return newMotorcycle;
    }

    public List<Motorcycle> byUserId(int userId){
        return motorcycleRepository.findByUserId(userId);
    }

    public void delete(int id){
        motorcycleRepository.deleteById(id);
    }

}
