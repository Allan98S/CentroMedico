/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.Listas;

import java.io.Serializable;

/**
 *
 * @author Juan Carlos Mora B44540
 */
public class ListaEnlazadaDoble implements Lista, Serializable {

    private Nodo inicio;

    public ListaEnlazadaDoble() {
        inicio = null;
    }

    @Override
    public int getSize() {
        if (isEmpty()) {
            return 0;
        }
        Nodo aux = inicio;
        int cont = 0;
        while (aux != null) {
            aux = aux.sgte;
            cont++;
        }
        return cont;
    }

    @Override
    public void anular() {
        inicio = null;
    }

    @Override
    public boolean isEmpty() {
        return inicio == null;
    }

    @Override
    public int getPosicion(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio;
        int pos = 1;
        while (aux != null) {
            if (aux.elemento.equals(elemento)) {
                return pos;
            }
            aux = aux.sgte;
            pos++;
        }
        return -1; // el elemento no existe

    }

    @Override
    public Nodo getNodo(int posicion) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio;
        int pos = 1;
        while (aux != null) {
            if (pos == posicion) {
                return aux;
            }
            aux = aux.sgte;
            pos++;
        }
        return null;//no existe laposicion
    }

    @Override
    public Nodo getNodo(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.elemento.equals(elemento)) {
                return aux;
            }
            aux = aux.sgte;
        }
        return null;//no existe elemento
    }

    @Override
    public void insertar(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (inicio == null) {
            inicio = nuevoNodo;

        } else {
            Nodo aux = inicio;
            while (aux.sgte != null) {
                aux = aux.sgte;
            }//while
            aux.sgte = nuevoNodo;
            nuevoNodo.ant = aux; //doble enlace

        }//else
    }//insertar

    @Override
    public void insertarOrdenado(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (inicio == null) {
            inicio = nuevoNodo;
        } else if (inicio.sgte == null && esMayorQ(inicio.elemento, elemento)) {
            nuevoNodo.sgte = inicio;
            inicio.ant = nuevoNodo;
            inicio = nuevoNodo;

        } else if (inicio.sgte == null && !esMayorQ(inicio.elemento, elemento)) {
            inicio.sgte = nuevoNodo;
            nuevoNodo.ant = inicio;

        } else if (esMayorQ(inicio.elemento, elemento)) {
            nuevoNodo.sgte = inicio;
            inicio.ant = nuevoNodo;
            inicio = nuevoNodo;
        } else {
            boolean insertado = false;
            Nodo nodoAnterior = inicio;//un nodo atras
            Nodo aux = inicio.sgte;
            while (aux != null && !insertado) {
                if (esMayorQ(aux.elemento, elemento)) {
                    nodoAnterior.sgte = nuevoNodo;
                    nuevoNodo.ant = nodoAnterior;
                    nuevoNodo.sgte = aux;
                    aux.ant = nuevoNodo;
                    insertado = true;
                }
                nodoAnterior = aux;
                aux = aux.sgte;
            }//while
            if (!insertado) {
                if (esMayorQ(nodoAnterior.elemento, elemento)) {
                    nodoAnterior.sgte = nuevoNodo;
                    nuevoNodo.ant = nodoAnterior;
                    nuevoNodo.sgte = aux;
                    aux.ant = nuevoNodo;
                }//if
                else {
                    nodoAnterior.sgte = nuevoNodo;
                    nuevoNodo.ant = aux;

                }//else
            }//if(!insertado)
        }//else

    }

    private boolean esMayorQ(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "entero":
                int x = (int) a;
                int y = (int) b;
                return x > y;
            case "String":
                String p = (String) a;
                String q = (String) b;
                return p.compareToIgnoreCase(q) > 0;

        }
        return false;
    }

    public String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "entero";
        }
        if (a instanceof String && b instanceof String) {
            return "String";
        }
        if (a instanceof Integer && b instanceof Integer) {
            return "entero";
        }

        return "desconocido";
    }

    @Override
    //este metodo elimina todas las ocurrencias del elemento
    public void suprimir(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        //si es el primer elemento de la lista
        if (inicio.elemento.equals(elemento)) {
            inicio = inicio.sgte;
        } else {
            Nodo aux = inicio.sgte;
            Nodo nodoAnterior = inicio;
            while (aux != null) {
                if (aux.elemento.equals(elemento)) {
                    nodoAnterior.sgte = aux.sgte;
                    //hacemos el doble enlace
                    if (aux.sgte != null) {
                        aux.sgte.ant = nodoAnterior;
                    }//if
                    //break; //si se deja solo elimina el primer elemento q encuentra
                }//if
                else {
                    nodoAnterior = aux;
                }//else
                aux = aux.sgte;
            }//while
        }//else
    }

    @Override
    public boolean existe(Object elemento) throws ListaException {
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.elemento.equals(elemento)) {
                return true;
            }
            aux = aux.sgte;
        }
        return false;
    }

    @Override
    public Object primero() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        return inicio.elemento;
    }

    @Override
    public Object ultimo() throws ListaException {
        Nodo aux = inicio;
        while (aux != null) {
            aux = aux.sgte;
        }
        return aux.elemento;
    }

    @Override
    public void ordenar() throws ListaException {

    }

    @Override
    public Object anterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        } else {
            Nodo aux = inicio.sgte;
            //  Nodo ant = inicio;
            while (aux != null) {
                if (aux.elemento.equals(elemento)) {
                    return aux.ant.elemento;
                } else {
                    aux = aux.sgte;
                }

            }
        }
        return "El elemento anterior no existe";

    }

    @Override
    public Object posterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        } else {
            Nodo aux = inicio;
            Nodo post = inicio.sgte;
            while (aux != null) {
                if (aux.elemento.equals(elemento)) {
                    if (post != null) {
                        return post.elemento;
                    } else {
                        return "No tiene elemento posterior";
                    }
                } else {

                    aux = aux.sgte;
                    if (aux != null) {
                        post = aux.sgte;
                    }
                }

            }
        }
        return "El elemento no existe";
    }
     @Override
    public String toString() {

        String result = "";

        if (isEmpty()) {

            result = "La lista se encuentra vacía.";

        } else {

            Nodo aux = inicio;

            while (aux != null) {

                result += "\n"+aux.elemento + "\n";

                aux = aux.sgte;

            }

        }
        return result;
    }
    

    public void modificarPosicion(int posicion, Object nuevoElemento) throws ListaException {
        if (getNodo(posicion) != null) {
            getNodo(posicion).elemento = nuevoElemento;
        }
    }
}
