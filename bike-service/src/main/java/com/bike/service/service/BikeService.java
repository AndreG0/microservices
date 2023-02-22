package com.bike.service.service;

import com.bike.service.entity.Bike;
import com.bike.service.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public boolean existById(int id){
        return bikeRepository.existsById(id);
    }

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike){
        Bike newBike = bikeRepository.save(bike);
        return newBike;
    }

    public List<Bike> byUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }

    public void delete(int id){
        bikeRepository.deleteById(id);
    }

}
