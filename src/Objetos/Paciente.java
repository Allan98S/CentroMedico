/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import TDA.Listas.ListaEnlazada;

/**
 *
 * @author allan
 */
public class Paciente {

    private String nombre;
    private String apellidos;
    private String contrasena;
    private String cedula;
    private int numeroPaciente;
    static int contador = 0;
    private static ListaEnlazada enfermedades=new ListaEnlazada();// insertar objeto tipo Enfermedad

    public Paciente(String nombre, String apellidos, String contrasena, String cedula, ListaEnlazada enfermedades) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.cedula = cedula;
        this.enfermedades= enfermedades;
        this.numeroPaciente = contador++;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getNumeroPaciente() {
        return numeroPaciente;
    }

    public static ListaEnlazada getEnfermedades() {
        return enfermedades;
    }

    public static void setEnfermedades(ListaEnlazada enfermedades) {
        Paciente.enfermedades = enfermedades;
    }

    public void setNumeroPaciente(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", contrasena=" + contrasena + ", cedula=" + cedula + ", numeroPaciente=" + numeroPaciente + '}';
    }

}
