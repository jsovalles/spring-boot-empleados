package com.worldoffice.empleados.controller;

import com.worldoffice.empleados.dao.IDepartamentoDAO;
import com.worldoffice.empleados.dao.IEmpleadosDAO;
import com.worldoffice.empleados.dao.entity.Departamento;
import com.worldoffice.empleados.dao.entity.Empleado;
import com.worldoffice.empleados.utils.CsvUtils;
import com.worldoffice.empleados.utils.DummyMocks;
import com.worldoffice.empleados.utils.TotalValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class EmpleadoControllerTest {

    @InjectMocks
    EmpleadoController controller;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Mock
    IDepartamentoDAO daoDepartamento;

    @Mock
    IEmpleadosDAO daoEmpleado;

    DummyMocks dummyMocks;

    @Before
    public void init() {
        dummyMocks = new DummyMocks();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void uploadEmployees() throws Exception {

        Resource fileResource = new ClassPathResource("empleados.csv");
        MockMultipartFile file = new MockMultipartFile(
                "attachments", fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());
        List<Empleado> empleados = CsvUtils.csvToEmployees(file.getInputStream());
        Mockito.when(daoEmpleado.saveAll(empleados)).thenReturn(null);
        controller.uploadEmployees(file);

    }

    @Test(expected = Exception.class)
    public void uploadEmployeesException() throws Exception {
        MockMultipartFile file = null;
        List<Empleado> empleados = CsvUtils.csvToEmployees(file.getInputStream());
        controller.uploadEmployees(null);

    }

    @Test
    public void listEmployeesByDepartment() {
        List<Departamento> departamentosOut = dummyMocks.listEmployeesByDepartmentMock();
        Page<Departamento> pagedResponse = new PageImpl(departamentosOut);
        String department = "City Manager's Office";
        Pageable pageable = null;
        Mockito.when(daoDepartamento.findByNombreDepartamento(department, pageable)).thenReturn(pagedResponse);
        Page<Departamento> out = controller.listEmployeesByDepartment(department, pageable);
        assertNotNull(out);
    }

    @Test
    public void listTopFiveSalaries() {
        Mockito.when(daoEmpleado.listTopFiveSalaries()).thenReturn(dummyMocks.topFiveSalariesMock());
        List<Empleado> empleados = controller.listTopFiveSalaries();
        assertNotNull(empleados);
    }

    @Test
    public void listDepartmentAverageSalaries() {
        String department = "City Manager's Office";
        Mockito.when(daoDepartamento.listDeparmentAverageSalaries(department)).thenReturn(() -> "32000");
        TotalValue total = controller.listDepartmentAverageSalaries(department);
        Assert.assertEquals("32000", total.getTotal());
    }
}