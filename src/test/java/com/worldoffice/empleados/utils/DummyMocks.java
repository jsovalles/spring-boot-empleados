package com.worldoffice.empleados.utils;


import com.worldoffice.empleados.dao.entity.Departamento;
import com.worldoffice.empleados.dao.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class DummyMocks {

    public List<Departamento> listEmployeesByDepartmentMock() {

        List<Departamento> departamentos = new ArrayList<>();
        Departamento departamento = new Departamento();
        departamento.setNombreDepartamento("City Manager's Office");
        departamento.setEmpleado(topFiveSalariesMock());
        departamentos.add(departamento);

        return departamentos;
    }

    public List<Empleado> topFiveSalariesMock() {

        List<Empleado> empleados = new ArrayList<>();

        Empleado empleado1 = new Empleado();
        empleado1.setNombre("Jones Marcus D.");
        empleado1.setCargo("City Manager");
        empleado1.setSalario(318000.0);
        empleado1.setTiempoCompleto(true);

        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Lewis Jr John M");
        empleado2.setCargo("Transit  Director");
        empleado2.setSalario(245924.3);
        empleado2.setTiempoCompleto(true);

        Empleado empleado3 = new Empleado();
        empleado3.setNombre("Hagemann Robert E");
        empleado3.setCargo("City Attorney");
        empleado3.setSalario(248491.82);
        empleado3.setTiempoCompleto(true);

        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);

        return empleados;

    }

}