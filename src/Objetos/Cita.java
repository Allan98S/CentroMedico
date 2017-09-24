/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import TDA.Utilidades;
import java.util.Date;

/**
 *
 * @author allan
 */
public class Cita {

    Utilidades util;
    private String descripcion;
    private Date fecha;
    private String cedulaPaciente;
    private String cedulaDoctor;
    private int id;
    static int contador = 0;

    public Cita() {

    }

    public Cita(String descripcion, Date fecha, String cedulaPaciente, String cedulaDoctor) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cedulaPaciente = cedulaPaciente;
        this.cedulaDoctor = cedulaDoctor;
        this.id = contador++;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getCedulaDoctor() {
        return cedulaDoctor;
    }

    public void setCedulaDoctor(String cedulaDoctor) {
        this.cedulaDoctor = cedulaDoctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cita{" + "descripcion=" + descripcion + ", fecha=" + util.formatoFecha(fecha) + ", cedulaPaciente=" + cedulaPaciente + ", cedulaDoctor=" + cedulaDoctor + ", id=" + id + '}';
    }

}
