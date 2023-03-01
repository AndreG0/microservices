package com.user.service.service;


import com.user.service.entity.User;
import com.user.service.feignclients.BikeFeignClient;
import com.user.service.feignclients.CarFeignClient;
import com.user.service.feignclients.MotorcycleFeignClient;
import com.user.service.feignclients.ScooterFeignClient;
import com.user.service.models.Bike;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;
import com.user.service.models.Scooter;
import com.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private MotorcycleFeignClient motorcycleFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;

    @Autowired
    private ScooterFeignClient scooterFeignClient;


//RestTemplate CAR
    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://car-service/car/user/" + userId, List.class);
        return cars;
    }
//RestTemplate MOTORCYCLE
    public List<Motorcycle> getMotorcycles(int userId){
        List<Motorcycle> motorcycles = restTemplate.getForObject("http://motorcycle-service/motorcycle/user/" + userId, List.class);
       return motorcycles;
    }

    //RestTemplate BIKE
    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/user/"+ userId, List.class);
        return bikes;
    }

    //RestTemplate SCOOTER
    public List<Scooter> getScooters(int userId){
        List<Scooter> scooters = restTemplate.getForObject("http://scooter-service/scooter/user/"+ userId, List.class);
        return scooters;
    }

//Feign client CAR
    public Car saveCar(int userId, Car car){
      car.setUserId(userId);
      Car newCar = carFeignClient.save(car);
      return newCar;
    }
 //Feign client MOTORCYCLE
    public Motorcycle saveMotorcycle(int userId, Motorcycle motorcycle){
        motorcycle.setUserId(userId);
        Motorcycle newMotorcycle = motorcycleFeignClient.save(motorcycle);
        return newMotorcycle;
    }
    //Feign client BIKE
    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.save(bike);
        return newBike;
    }

    //Feign client SCOOTER
    public Scooter saveScooter(int userId, Scooter scooter){
        scooter.setUserId(userId);
        Scooter newScooter = scooterFeignClient.save(scooter);
        return newScooter;
    }

//VEHICLES LIST BY USER
    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            result.put("Message", "The user does not exist");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            result.put("Cars","The user does not have cars");
        }else {
            result.put("Cars", cars);
        }
        List<Motorcycle> motorcycles = motorcycleFeignClient.getMotorcycles(userId);
        if (motorcycles.isEmpty()){
            result.put("Motorcycles", "The user does not have motorcycles");
        }else {
            result.put("Motorcycles", motorcycles);
        }
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()){
            result.put("Bikes", "The user does not have bikes");
        }else {
            result.put("Bikes", bikes);
        }
        List<Scooter> scooters = scooterFeignClient.getScooters(userId);
        if (scooters.isEmpty()){
            result.put("Scooters", "The user does not have scooter");
        }else {
            result.put("Scooters", scooters);
        }

        return result;
    }


//////////////////////////////////////////////////////////////////
    public boolean existById(int id){
        return userRepository.existsById(id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User newUser  = userRepository.save(user);
        return newUser;
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

}
