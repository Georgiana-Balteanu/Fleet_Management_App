package com.exemplu.fleetmanagement.controller;

import com.exemplu.fleetmanagement.model.AppUser;
import com.exemplu.fleetmanagement.model.Car;
import com.exemplu.fleetmanagement.repository.AppUserRepository;
import com.exemplu.fleetmanagement.service.CarService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    private final AppUserRepository appUserRepository;

    public CarController(CarService carService, AppUserRepository appUserRepository) {
        this.carService = carService;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelCar,
            @RequestParam(required = false) String combustibil,
            Authentication authentication,
            Model model
    ) {
        List<Car> cars = carService.filterCars(marca, modelCar, combustibil);

        model.addAttribute("cars", cars);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("marca", marca);
        model.addAttribute("modelCar", modelCar);
        model.addAttribute("combustibil", combustibil);
        model.addAttribute("message", buildFilterMessage(marca, modelCar, combustibil));

        return "user-dashboard";
    }

    @GetMapping("/editor/dashboard")
    public String editorDashboard(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelCar,
            @RequestParam(required = false) String combustibil,
            Authentication authentication,
            Model model
    ) {
        List<Car> cars = carService.filterCars(marca, modelCar, combustibil);

        model.addAttribute("cars", cars);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("marca", marca);
        model.addAttribute("modelCar", modelCar);
        model.addAttribute("combustibil", combustibil);
        model.addAttribute("message", buildFilterMessage(marca, modelCar, combustibil));

        if (!model.containsAttribute("newCar")) {
            model.addAttribute("newCar", new Car());
        }
        if (!model.containsAttribute("updateCar")) {
            model.addAttribute("updateCar", new Car());
        }

        return "editor-dashboard";
    }

    @PostMapping("/editor/add")
    public String addCar(
            @Valid @ModelAttribute("newCar") Car newCar,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("username", authentication.getName());
            model.addAttribute("message", "Toate masinile");
            model.addAttribute("updateCar", new Car());
            return "editor-dashboard";
        }

        AppUser user = appUserRepository.findByUtilizator(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost gasit"));

        newCar.setAddedBy(user);
        carService.save(newCar);

        return "redirect:/editor/dashboard?successAdd=true";
    }

    @PostMapping("/editor/update")
    public String updateCar(@ModelAttribute("updateCar") Car updateCar) {
        carService.updatePartial(updateCar.getNrInmatriculare(), updateCar);
        return "redirect:/editor/dashboard?successUpdate=true";
    }

    @PostMapping("/editor/delete")
    public String deleteCar(@RequestParam String nrInmatriculare) {
        carService.deleteById(nrInmatriculare);
        return "redirect:/editor/dashboard?successDelete=true";
    }

    private String buildFilterMessage(String marca, String modelCar, String combustibil) {
        boolean hasMarca = marca != null && !marca.isBlank();
        boolean hasModel = modelCar != null && !modelCar.isBlank();
        boolean hasCombustibil = combustibil != null && !combustibil.isBlank();

        if (!hasMarca && !hasModel && !hasCombustibil) {
            return "Toate masinile";
        }

        StringBuilder sb = new StringBuilder("Masinile filtrate dupa: ");
        boolean first = true;

        if (hasMarca) {
            sb.append("marca=").append(marca);
            first = false;
        }
        if (hasModel) {
            if (!first) sb.append(", ");
            sb.append("model=").append(modelCar);
            first = false;
        }
        if (hasCombustibil) {
            if (!first) sb.append(", ");
            sb.append("combustibil=").append(combustibil);
        }

        return sb.toString();
    }
}