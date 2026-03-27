package com.exemplu.fleetmanagement.repository;

import com.exemplu.fleetmanagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findByMarcaContainingIgnoreCaseAndModelContainingIgnoreCaseAndTipCombustibilContainingIgnoreCase(
            String marca,
            String model,
            String tipCombustibil
    );
}