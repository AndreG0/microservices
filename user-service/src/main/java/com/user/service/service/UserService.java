package com.user.service.service;


import com.user.service.entity.User;
import com.user.service.feignclients.CarFeignClient;
import com.user.service.feignclients.MotorcycleFeignClient;
import com.user.service.models.Car;
import com.user.service.models.Motorcycle;
import com.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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



//RestTemplate
    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/user/" + userId, List.class);
        return cars;
    }
//RestTemplate
    public List<Motorcycle> getMotorcycles(int userId){
        List<Motorcycle> motorcycles = restTemplate.getForObject("http://localhost:8003/motorcycle/user/" + userId, List.class);
       return motorcycles;
    }

//Feign client
    public Car saveCar(int userId, Car car){
      car.setUserId(userId);
      Car newCar = carFeignClient.save(car);
      return newCar;
    }
 //Feign client
    public Motorcycle saveMotorcycle(int userId, Motorcycle motorcycle){
        motorcycle.setUserId(userId);
        Motorcycle newMotorcycle = motorcycleFeignClient.save(motorcycle);
        return newMotorcycle;
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
