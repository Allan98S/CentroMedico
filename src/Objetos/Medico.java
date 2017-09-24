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
public class Medico {
    private String nombre;
    private String apellidos;
    private String cedula;
    private String codDoctor;
    private  int numeroDoctor;
    static int contador=0;

    public Medico(String nombre, String apellidos, String cedula,String codDoctor) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.codDoctor=codDoctor;
        this.numeroDoctor=contador++;
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

    public int getNumeroDoctor() {
        return numeroDoctor;
    }

    public void setNumeroDoctor(int numeroDoctor) {
        this.numeroDoctor = numeroDoctor;
    }

    public String getCodDoctor() {
        return codDoctor;
    }

    public void setCodDoctor(String codDoctor) {
        this.codDoctor = codDoctor;
    }

    @Override
    public String toString() {
        return "Medico{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", cedula=" + cedula + ", codDoctor=" + codDoctor + ", numeroDoctor=" + numeroDoctor + '}';
    }

   
    
    
}
