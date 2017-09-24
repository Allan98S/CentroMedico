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
public class Enfermedad {

    private String nombre;
    private String descripcion;
    private int numero;
    private static int contador = 0;

    public Enfermedad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numero = contador++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Enfermedad.contador = contador;
    }

    @Override
    public String toString() {
        return "Enfermedad{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", numero=" + numero + '}';
    }

}
