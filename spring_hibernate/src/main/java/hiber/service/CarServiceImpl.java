package hiber.service;

import hiber.model.Car;
import hiber.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void add(Car car) {
        carDao.add(car);
    }

}
