package com.worldoffice.empleados.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamentos",uniqueConstraints = @UniqueConstraint (columnNames = "nombre_departamento"))
@Data
public class Departamento {

    @Id
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;

    @OneToMany(mappedBy = "departamento")
    private List<Empleado> empleado;
}
