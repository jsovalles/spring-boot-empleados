package com.worldoffice.empleados.controller;

import com.worldoffice.empleados.dao.IDepartamentoDAO;
import com.worldoffice.empleados.dao.IEmpleadosDAO;
import com.worldoffice.empleados.dao.entity.Departamento;
import com.worldoffice.empleados.dao.entity.Empleado;
import com.worldoffice.empleados.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("empleados/v0/empleado")
public class EmpleadoController {

    @Autowired
    IEmpleadosDAO daoEmpleados;

    @Autowired
    IDepartamentoDAO daoDepartamento;

    @PostMapping()
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        daoEmpleados.saveAll(CsvUtils.csvToEmpleados(file.getInputStream()));
    }

    @GetMapping("/departamento")
    public Page<Departamento> listEmployeesByDepartment(@RequestParam("departamento") String departamento, @PageableDefault(page = 0, size = 20)
            Pageable pageable) {

        return daoDepartamento.findByNombreDepartamento(departamento, pageable);
    }

    @GetMapping("/salario/topfive")
    public List<Empleado> listTopFiveSalaries() {

        return daoEmpleados.listTopFiveSalaries();
    }

    @GetMapping("/salario/departamento")

    public IDepartamentoDAO.NameOnly listDepartmentAverageSalaries(@RequestParam("departamento") String departamento) {
        return daoDepartamento.listDeparmentAverageSalaries(departamento);
    }


}
