package com.exemplu.fleetmanagement.service;

import com.exemplu.fleetmanagement.model.Car;
import com.exemplu.fleetmanagement.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> filterCars(String marca, String model, String combustibil) {
        return carRepository.findByMarcaContainingIgnoreCaseAndModelContainingIgnoreCaseAndTipCombustibilContainingIgnoreCase(
                safe(marca), safe(model), safe(combustibil)
        );
    }

    @Override
    public Optional<Car> getById(String nrInmatriculare) {
        return carRepository.findById(nrInmatriculare);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(String nrInmatriculare) {
        carRepository.deleteById(nrInmatriculare);
    }

    @Override
    public Car updatePartial(String nrInmatriculare, Car updatedData) {
        Car existing = carRepository.findById(nrInmatriculare)
                .orElseThrow(() -> new RuntimeException("Masina nu a fost gasita"));

        if (notBlank(updatedData.getMarca())) {
            existing.setMarca(updatedData.getMarca());
        }
        if (notBlank(updatedData.getModel())) {
            existing.setModel(updatedData.getModel());
        }
        if (notBlank(updatedData.getCuloare())) {
            existing.setCuloare(updatedData.getCuloare());
        }
        if (updatedData.getAnulFabricatiei() != null) {
            existing.setAnulFabricatiei(updatedData.getAnulFabricatiei());
        }
        if (updatedData.getCapacitateaCilindrica() != null) {
            existing.setCapacitateaCilindrica(updatedData.getCapacitateaCilindrica());
        }
        if (notBlank(updatedData.getTipCombustibil())) {
            existing.setTipCombustibil(updatedData.getTipCombustibil());
        }
        if (updatedData.getPuterea() != null) {
            existing.setPuterea(updatedData.getPuterea());
        }
        if (updatedData.getCuplul() != null) {
            existing.setCuplul(updatedData.getCuplul());
        }
        if (updatedData.getVolumulPortbagajului() != null) {
            existing.setVolumulPortbagajului(updatedData.getVolumulPortbagajului());
        }
        if (updatedData.getPretul() != null) {
            existing.setPretul(updatedData.getPretul());
        }

        return carRepository.save(existing);
    }

    private String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private boolean notBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}