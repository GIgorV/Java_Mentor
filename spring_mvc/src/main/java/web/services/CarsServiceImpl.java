package web.services;

import org.springframework.stereotype.Component;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarsServiceImpl implements CarsService {

    @Override
    public List<Car> returnCarsByCount(Long count) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "Granta", "111aa"));
        cars.add(new Car(2L, "Kalina", "222cc"));
        cars.add(new Car(3L, "Priora", "333ee"));
        cars.add(new Car(4L, "Niva", "444kk"));
        cars.add(new Car(5L, "Vesta", "555oo"));
        List<Car> result = cars.stream().limit(count).collect(Collectors.toList());
        return result;
    }
}
