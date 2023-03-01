package com.scooter.service.service;


import com.scooter.service.entity.Scooter;
import com.scooter.service.respository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {

    @Autowired
    private ScooterRepository scooterRepository;

    public boolean existById(int id ){
        return scooterRepository.existsById(id);
    }

    public List<Scooter> getAll(){
        return scooterRepository.findAll();
    }

    public Scooter getScooterById(int id){
        return scooterRepository.findById(id).orElse(null);
    }

    public  Scooter save(Scooter scooter){
        Scooter newScooter = scooterRepository.save(scooter);
        return newScooter;
    }

    public List<Scooter> byUserId(int userId){
        return scooterRepository.findByUserId(userId);
    }

    public void delete(int id){
        scooterRepository.deleteById(id);
    }
}
