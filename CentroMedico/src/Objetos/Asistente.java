/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author allan
 */
public class Asistente {
    private String nombre;
    private String apellidos;
    private String cedula;
    private String codigo;
    private int id;
    static int contador=0;

    public Asistente(String nombre, String apellidos, String cedula, String codigo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.codigo = codigo;
        this.id =++contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Asistente{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", cedula=" + cedula + ", codigo=" + codigo + ", id=" + id + '}';
    }
    
}
