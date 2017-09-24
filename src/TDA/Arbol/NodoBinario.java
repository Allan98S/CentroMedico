package TDA;

import java.io.Serializable;

public class NodoBinario implements Serializable {

    public Object elemento;
    public NodoBinario izquierda, derecha;// Representa los 2 hijos de cada nodo
    public String etiqueta;
    public int nivel;
    public int posicion;
    static int cont = 0;

    public NodoBinario(Object elemento, String etiqueta, int nivel) {
        this.posicion = ++cont;
        this.elemento = elemento;
        this.izquierda = this.derecha = null;
        this.etiqueta = etiqueta;
        this.nivel = nivel;

    }

    public NodoBinario(Object elemento) {

        this.elemento = elemento;
        this.izquierda = this.derecha = null;
        this.posicion = ++cont;
        this.etiqueta = "Sin etiqueta asignada";

    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

}
