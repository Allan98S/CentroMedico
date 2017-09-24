/**
 * File     : ColaEnlazadaHeader.java
 *
 * @author : Prof. Gilberth Chaves Avila Date : 2017-05-09
 */
package TDA.Colas;

import TDA.Cola;
import TDA.ColaException;
import TDA.Colas.Nodo;
import TDA.Utilidades;
import java.io.Serializable;

public class ColaEnlazadaHeader implements Cola, Serializable {

    private Nodo inicio, anterior, posterior;
    private Nodo fin;
    private int contador;// Nos lleva el control de los elementos encolados
    Utilidades u = new Utilidades();

    public ColaEnlazadaHeader() {
        this.anterior = this.posterior = new Nodo();
        inicio = fin = new Nodo();// al inicio apuntan a un nodo vacío
        //posterior = anterior;
        this.contador = 0;

    }

    @Override
    public int getSize() throws ColaException {
        if (isEmpty()) {
            //throw new ColaException("La cola está vacía");
            return 0;
        }
        return contador;
    }

    @Override
    public void anular() {

        inicio.sgte = null;
        fin = inicio;
        this.contador = 0;

    }

    @Override
    public boolean isEmpty() {

        return inicio == fin;
    }

    public int posicion(Object elemento) throws ColaException {

        if (isEmpty()) {
            throw new ColaException("La cola se encuentra vacía.");

        }

        int posicion = 1;// El primer elemento de la cola en la posición 1

        Nodo aux = inicio.sgte;

        while (aux != null) {

            if (u.igual(aux.elemento, elemento)) {
                return posicion;
            }
            aux = aux.sgte;
            posicion++;
        }
        return -1;
    }

    @Override
    public void encolar(Object elemento) throws ColaException {

        fin.sgte = new Nodo(elemento);

        fin = fin.sgte;

        anterior = fin.ant;

        System.out.println(fin.elemento);

        contador++;

    }

    public Nodo getNodo(int posicion) throws ColaException {

        Nodo aux = null;
        int cont = 1;

        if (!isEmpty()) {

            aux = inicio.sgte;

            while (aux != null) {

                if (cont == posicion) {

                    return aux;
                }
                aux = aux.sgte;
                cont++;
            }

        }

        return aux;
    }

    @Override
    public Object desencolar() throws ColaException {
        if (this.isEmpty()) {
            throw new ColaException("La cola esta vacia");
        }
        //elemento a desencolar
        Object elemento = anterior.sgte.elemento;

        //CASO 1: SOLO SE TIENE UN ELEMENTO ENCOLADO
        if (anterior.sgte == posterior) {
            this.anular(); //elimina la cola
        }//if
        //CASO 2: EXISTEN VARIOS ELEMENTOS ENCOLADOS, AL MENOS 2
        else {
            anterior.sgte = anterior.sgte.sgte;
        }
        this.contador--; //para actualizar la cantidad de elementos
        return elemento;
    }

    @Override
    public boolean existe(Object elemento) throws ColaException {

//        Nodo aux = anterior.sgte;
//        while (aux != null) {
//            if (aux.elemento.equals(elemento)) {
//                return true;
//            }
//            aux = aux.sgte;
//        }
//        return false;
        return posicion(elemento) != -1;
    }

    @Override
    public Object frente() throws ColaException {

        if (isEmpty()) {

        }
        return inicio.sgte.elemento;// Primer elemento de la cola
    }

    @Override
    public String toString() {

        String result = "La cola no existe.";

        if (!isEmpty()) {

            result = "";

            Nodo aux = inicio.sgte;

            while (aux != null) {

                result += aux.elemento + "\n";

                aux = aux.sgte;

            }

        }

        return result;
    }

    public int getPosicion(Object elemento) throws ColaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
