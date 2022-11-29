package org.example.homework2extra;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CarDao {
    private Integer id = 0;
    private Map<Integer, Car> carMap = new HashMap<>();

    public Integer addCar(Car car) {
        carMap.put(id, car);
        id++;
        return id - 1;
    }

    public Map<String, String> getCar(Integer id) {
        if (!carMap.containsKey(id)) {
            return new HashMap<>(Map.of("Нет такого", "id"));
        }
        Car car = carMap.get(id);
        Map<String, String> carMap = new LinkedHashMap<>();
        carMap.put("name", car.getMark() + " " + car.getModel());
        carMap.put("mileage", car.getMileage().toString());
        carMap.put("car_age", car.getCar_age().toString());
        carMap.put("price", car.getPrice().toString());
        return carMap;
    }
}
