package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.services.CarsService;
import web.services.CarsServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CarsController {

    CarsService carsService = new CarsServiceImpl();

    @GetMapping(value = "/cars")
    public String loopCars(HttpServletRequest request, Model model) {
        int count = Integer.parseInt(request.getParameter("count"));
        model.addAttribute("cars", carsService.returnCarsByCount(count));
        return "cars";
    }
}