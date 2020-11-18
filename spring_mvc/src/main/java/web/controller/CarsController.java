package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.services.CarsService;
import web.services.CarsServiceImpl;

@Controller
public class CarsController {

    CarsService carsService = new CarsServiceImpl();

    @GetMapping(value = "/cars")
    public String loopCars(@RequestParam(value = "count", required = false) Long count, Model model) {
        if (count != null) {
            model.addAttribute("cars", carsService.returnCarsByCount(count));
            return "cars";
        } else {
            count = 10L;
            model.addAttribute("cars", carsService.returnCarsByCount(count));
            return "cars";
        }
    }
}