package com.worldoffice.empleados.controller;

import com.worldoffice.empleados.dao.IDepartamentoDAO;
import com.worldoffice.empleados.dao.IEmpleadosDAO;
import com.worldoffice.empleados.dao.entity.Departamento;
import com.worldoffice.empleados.dao.entity.Empleado;
import com.worldoffice.empleados.utils.CsvUtils;
import com.worldoffice.empleados.utils.TotalValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("empleados/v0")
public class EmpleadoController {

    @Autowired
    IEmpleadosDAO daoEmpleados;

    @Autowired
    IDepartamentoDAO daoDepartamento;

    @PostMapping("/empleado")
    public ResponseEntity<String> uploadEmployees(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        daoEmpleados.saveAll(CsvUtils.csvToEmployees(file.getInputStream()));

        return new ResponseEntity<String>(HttpStatus.OK);

    }

    @GetMapping("/departamento")
    public Page<Departamento> listEmployeesByDepartment(@RequestParam("departamento") String
                                                                departamento, @PageableDefault(page = 0, size = 20)
                                                                Pageable pageable) {

        return daoDepartamento.findByNombreDepartamento(departamento, pageable);
    }

    @GetMapping("/empleado/top-cinco-salarios")
    public List<Empleado> listTopFiveSalaries() {

        return daoEmpleados.listTopFiveSalaries();
    }

    @GetMapping("/departamento/salario")
    public TotalValue listDepartmentAverageSalaries(@RequestParam("departamento") String departamento) {
        return daoDepartamento.listDeparmentAverageSalaries(departamento);
    }


}
