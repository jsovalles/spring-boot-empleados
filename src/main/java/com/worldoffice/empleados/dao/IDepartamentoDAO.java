package com.worldoffice.empleados.dao;

import com.worldoffice.empleados.dao.entity.Departamento;
import com.worldoffice.empleados.utils.TotalValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDepartamentoDAO extends JpaRepository<Departamento, String> {

    Page<Departamento> findByNombreDepartamento(String departamento, Pageable pageable);

    @Query(value = "SELECT dep.nombre_departamento, (SELECT SUM(salario)  FROM empleados WHERE departamento_nombre_departamento = dep.nombre_departamento) total FROM departamentos dep WHERE nombre_departamento = ?;", nativeQuery = true)
    TotalValue listDeparmentAverageSalaries(String departamento);



}