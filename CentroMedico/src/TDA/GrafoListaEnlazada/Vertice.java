/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.GrafoListaEnlazada;

import TDA.Listas.ListaEnlazada;
import java.io.Serializable;

public class Vertice implements Serializable{

    public Object elemento;//EL OBJETO QUE SE ALMACENA
    public boolean visitado;//SE USA PARA LOS RECORRIDOS
    //GUARDA LAS ARISTAS Y PESOS QUE INCIDEN EN EL VERTICE CORRESPONDIENTE
    public ListaEnlazada listaAristas, listaPesos;

    //Constructor
    public Vertice(Object elemento) {
        this.elemento = elemento;
        this.visitado = false;
        this.listaPesos = new ListaEnlazada();
        this.listaAristas = new ListaEnlazada();
    }//Constructor()
        

}//Vertice
