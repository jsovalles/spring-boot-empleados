package com.worldoffice.empleados.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
@Data
public class Empleado {

    @Id
    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Cargo")
    private String cargo;

    @Column(name = "Salario")
    private Double salario;

    @Column(name = "Tiempo_Completo")
    private Boolean tiempoCompleto;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Departamento departamento;

}
