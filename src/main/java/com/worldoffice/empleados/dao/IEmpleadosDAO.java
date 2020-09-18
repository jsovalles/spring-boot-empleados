package com.worldoffice.empleados.dao;

import com.worldoffice.empleados.dao.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmpleadosDAO extends JpaRepository<Empleado, String> {

    @Query(value = "SELECT TOP (5) salario, * FROM empleados emp ORDER BY salario DESC", nativeQuery = true)
    List<Empleado> listTopFiveSalaries();
}
