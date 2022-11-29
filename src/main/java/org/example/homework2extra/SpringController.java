package org.example.homework2extra;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SpringController {
    @Autowired
    CarDao carDao;

    @PostMapping(path = "/createCar")
    public Integer receiveCar(@RequestBody Map<String, Object> map) {
        Car car = new Car();
        car.setMark(map.get("mark").toString());
        car.setModel(map.get("model").toString());
        carHistoryMapper(car, map.get("history"));
        return carDao.addCar(car);
    }

    @GetMapping(path = "car/{id}")
    public Map<String, String> findCar(@PathVariable Integer id) {
        return carDao.getCar(id);
    }

    private void carHistoryMapper(Car car, Object map) {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Integer> history = mapper.convertValue(map, HashMap.class);
        car.setCar_age(history.get("created_year"));
        car.setMileage(history.get("mileage"));
        int basePrice = 0;
        switch (car.getMark()) {
            case "bmw" : {
                basePrice = 50000;
                break;
            }
            case "mercedes" : {
                basePrice = 70000;
                break;
            }
            case "kia" : {
                basePrice = 35000;
                break;
            }
        }
        Double price = basePrice - (history.get("mileage") * 0.1);
        car.setPrice(history.get("count_owners") > 1 ? price * 0.2 : price);
    }
}
