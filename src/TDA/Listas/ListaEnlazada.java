package TDA.Listas;

import TDA.Utilidades;
import java.io.Serializable;

public class ListaEnlazada implements Lista, Serializable {

    static int cantidadReservaciones;
    private Nodo inicio;

    public ListaEnlazada() {

        this.inicio = null;

    }


    @Override
    public int getSize() throws ListaException {

        int cantidadElementos = 0;

        if (isEmpty()) {

            return 0;

        } else {

            Nodo aux = inicio;

            while (aux != null) {

                cantidadElementos++;

                aux = aux.sgte;

            }

        }

        return cantidadElementos;
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

            throw new ListaException("La lista está vacía.");

        } else {

            Nodo aux = inicio;

            int posicion = 1;

            while (aux != null) {

                if (Utilidades.igual(aux.elemento, elemento)) {

                    return posicion;

                }

                aux = aux.sgte;

                posicion++;

            }

        }

        return -1;
    }

    @Override
    public void insertar(Object elemento) {

        if (isEmpty()) {

            inicio = new Nodo(elemento);

        } else {

            Nodo aux = inicio;

            while (aux.sgte != null) {

                aux = aux.sgte;

            }

            aux.sgte = new Nodo(elemento);

        }

    }

    @Override
    public void suprimir(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        //CASO 1 Cuando quiero suprimir el primer elemento de la lista
        if (inicio.elemento.equals(elemento)) {
            inicio = inicio.sgte;//mueve el inicio al sgte nodo

        } //CASO 2 TODO LO DEMAS
        else {
            Nodo aux = inicio;
            Nodo auxAnterior = null;
            while (aux.sgte != null && !aux.elemento.equals(elemento)) {
                auxAnterior = aux;
                aux = aux.sgte;

            }//while
            //se sale cuando alcanza null para aux.sgte o cuando aux.elemento
            // es el elemento que quiero eliminar
            if (aux.elemento.equals(elemento)) {
                auxAnterior.sgte = aux.sgte;// saltamos el nodo con el elemento a eliminar
            }
        }//else

    }

    @Override
    public boolean existe(Object elemento) throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        return getPosicion(elemento) != -1;
    }

    @Override
    public Object primero() throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        return inicio.elemento;
    }

    @Override
    public Object ultimo() throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        Nodo aux = inicio;

        while (aux.sgte != null) {

            aux = aux.sgte;

        }

        return aux.elemento;
    }

    @Override
    public void ordenar() throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        boolean flag;

        do {

            flag = false;

            Nodo aux = inicio;

            while (aux.sgte != null) {

                if (!Utilidades.menorQue(aux.elemento, aux.sgte.elemento)) {

                    Object temp = aux.elemento;

                    aux.elemento = aux.sgte.elemento;

                    aux.sgte.elemento = temp;

                    flag = true;

                }

                aux = aux.sgte;

            }

        } while (flag);

    }

    // public Object anteriorV2(Object elemento) throws ListaException {
    //
    // if (isEmpty()) {
    //
    // throw new ListaException("La lista está vacía.");
    //
    // }
    //
    // Nodo aux = inicio;
    //
    // Nodo anterior = null;// Apunta al nodo anterior aux
    //
    // // Se sale del while cuando estamos en el último nodo
    //
    // while (aux != null) {
    //
    // if (aux.elemento.equals(elemento)) {
    //
    // return anterior != null ? anterior.elemento
    // : "No existe un nodo anterior a " + elemento;
    //
    // }
    //
    // anterior = aux;
    // aux = aux.siguiente;
    //
    // }
    //
    // return "No existe un nodo anterior a " + elemento;
    //
    // }
    @Override
    public Object anterior(Object elemento) throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        int posicion = getPosicion(elemento);

        return (posicion == -1) ? "El elemento " + elemento
                + " no existe en la lista"
                : (posicion == 1) ? "No existe un elemento anterior a: "
                        + elemento : getNodo(posicion - 1).elemento;

    }

    @Override
    public Nodo getNodo(Object elemento) throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        return getNodo(getPosicion(elemento));

    }

    @Override
    public Nodo getNodo(int posicion) throws ListaException {

        if (isEmpty()) {

            throw new ListaException("La lista está vacía.");

        }

        int contador = 1;

        Nodo aux = inicio;

        while (aux != null) {

            if (posicion == contador) {

                return aux;

            }

            aux = aux.sgte;
            contador++;

        }

        throw new ListaException("El nodo no existe.");
    }

    public void modificar(Object nombreVertice, Object peso)
            throws ListaException {

        if (existe(nombreVertice)) {

            getNodo(getPosicion(nombreVertice)).elemento = peso;

        }

    }

    @Override
    public String toString() {

        String result = "";

        if (isEmpty()) {

            result = "La lista se encuentra vacía.";

        } else {

            Nodo aux = inicio;

            while (aux != null) {

                result += aux.elemento + "\n";

                aux = aux.sgte;

            }

        }
        return result;
    }

    public String toStringHorizontal() {

        String result = "";

        if (isEmpty()) {

            result = "La lista se encuentra vacía.";

        } else {

            Nodo aux = inicio;

            while (aux != null) {

                result += aux.elemento + " ";

                aux = aux.sgte;

            }

        }
        return result;

    }

    @Override
    public Object posterior(Object elemento) throws ListaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarOrdenado(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
