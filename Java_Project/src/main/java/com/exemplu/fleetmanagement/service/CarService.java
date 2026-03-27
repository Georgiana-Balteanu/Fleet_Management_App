package com.exemplu.fleetmanagement.service;

import com.exemplu.fleetmanagement.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();
    List<Car> filterCars(String marca, String model, String combustibil);
    Optional<Car> getById(String nrInmatriculare);
    Car save(Car car);
    void deleteById(String nrInmatriculare);
    Car updatePartial(String nrInmatriculare, Car updatedData);
}