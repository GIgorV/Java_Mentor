package web.services;


import web.models.Car;

import java.util.List;

public interface CarsService {
    List<Car> returnCarsByCount(Long count);
}
