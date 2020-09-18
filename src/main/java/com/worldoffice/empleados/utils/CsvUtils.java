package com.worldoffice.empleados.utils;

import com.worldoffice.empleados.dao.entity.Departamento;
import com.worldoffice.empleados.dao.entity.Empleado;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static boolean hasCSVFormat(MultipartFile file) {



        return true;
    }

    public static List<Empleado> csvToEmpleados(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Empleado> empleados = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Empleado empleado = new Empleado();
                empleado.setNombre(csvRecord.get("Nombre"));
                empleado.setCargo(csvRecord.get("Cargo"));
                empleado.setSalario(Double.valueOf(csvRecord.get("Salario")));
                empleado.setTiempoCompleto(Boolean.valueOf(csvRecord.get("Tiempo Completo")));
                empleado.setDepartamento(new Departamento());
                empleado.getDepartamento().setNombreDepartamento(csvRecord.get("Departamento"));

                empleados.add(empleado);
            }

            return empleados;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}