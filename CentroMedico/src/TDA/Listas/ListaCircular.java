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
public class ListaCircular implements Lista, Serializable {

    //Atributos
    private Nodo inicio;
    private Nodo fin;

    public ListaCircular() {
        inicio = fin = null;
    }

    @Override
    public int getSize() {
        if (isEmpty()) {
            return 0;
        }
        Nodo aux = inicio;
        int cont = 1;
        while (aux != fin) {
            aux = aux.sgte;
            cont++;
        }
        return cont;
    }

    @Override
    public void anular() {
        inicio = fin = null;
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
        int post = 1;
        while (aux != fin) {
            if (aux.elemento.equals(elemento)) {
                return post;
            }
            aux = aux.sgte;
            post++;
        }//while
        //se sale del while cuando llegamos al ultimo nodo, nodo=fin
        if (aux.elemento.equals(elemento)) {
            return post;
        }

        return -1;//significa   que el metodo no 

    }

    @Override
    public void insertar(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (inicio == null) {
            inicio = fin = nuevoNodo;
        } else {
            Nodo aux = inicio;
            while (aux != fin) {
                aux = aux.sgte;
            }//while
            //se sale del while cuando aux ==fin
            aux.sgte = nuevoNodo;
            //hacemos que fin apunte al nuevo nodo
            fin = nuevoNodo;
            //hacemos enlace circular
            fin.sgte = inicio;
        }//else
    }

    @Override
    public void insertarOrdenado(Object elemento) {

        Nodo nuevoNodo = new Nodo(elemento);
        if (inicio == null) {
            inicio = fin = nuevoNodo;
        } else if (inicio.sgte == fin.sgte && esMayorQ(inicio.elemento, elemento)) {
            nuevoNodo.sgte = inicio;
            inicio = nuevoNodo;
            fin.sgte = inicio;//enlace circular
        } else if (inicio.sgte == fin.sgte && !esMayorQ(inicio.elemento, elemento)) {
            inicio.sgte = nuevoNodo;
            fin = nuevoNodo;
            fin.sgte = inicio;//enlace circula
        } else if (esMayorQ(inicio.elemento, elemento)) {
            nuevoNodo.sgte = inicio;
            inicio = nuevoNodo;
            fin.sgte = inicio;//enlace circular
        } else {
            boolean insertado = false;
            Nodo nodoAnterior = inicio;//un nodo atras
            Nodo aux = inicio.sgte;
            while (aux != fin && !insertado) {
                if (esMayorQ(aux.elemento, elemento)) {
                    nodoAnterior.sgte = nuevoNodo;
                    nuevoNodo.sgte = aux;
                    insertado = true;
                }
                nodoAnterior = aux;
                aux = aux.sgte;
            }//while
            if (!insertado) {
                if (esMayorQ(aux.elemento, elemento)) {
                    nodoAnterior.sgte = nuevoNodo;
                    nuevoNodo.sgte = aux;
                }//if
                else {
                    aux.sgte = nuevoNodo;
                    fin = nuevoNodo;
                    fin.sgte = inicio;
                }//else
            }//if(!insertado)
        }//else
        //hacemos el enlace circular
        fin.sgte = inicio;

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
            case "Estudiante":
//                Estudiante r = (Estudiante) a;
//                Estudiante s = (Estudiante) b;
//                return r.getCarne().compareToIgnoreCase(s.getCarne()) > 0;
            case "Curso":
//                Curso m = (Curso) a;
//                Curso n = (Curso) b;
//                return m.getCurso().compareToIgnoreCase(n.getCurso()) > 0;

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
//        if (a instanceof Estudiante && b instanceof Estudiante) {
//            return "Estudiante";
//        }
//        if (a instanceof Curso && b instanceof Curso) {
//            return "Curso";
//        }

        return "desconocido";
    }

    @Override
    public void suprimir(Object elemento) throws ListaException {
        //este metodo elimina todas las ocurrencias del elemento
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        if (inicio.elemento.equals(elemento)) {
            inicio = inicio.sgte;
        } else {
            Nodo aux = inicio.sgte;
            Nodo nodoant = inicio;
            while (aux != fin) {
                if (aux.elemento.equals(elemento)) {
                    nodoant.sgte = aux.sgte;

                } else {
                    nodoant = aux;
                }//else
                aux = aux.sgte;
            }//while
            //se sale del while cuando nodo es igual a fin
            if (aux.elemento.equals(elemento)) {
                nodoant.sgte = aux.sgte;

                //mea seguro que fin apunte al ultimo nodo
                if (aux == fin) {
                    fin = nodoant;
                }
            }//if

            //mantengo el enlace circular
            fin.sgte = inicio;

        }//else

    }

    @Override
    public boolean existe(Object elemento) throws ListaException {

        Nodo aux = inicio;

        while (aux != fin) {
            if (aux.elemento.equals(elemento)) {
                return true;
            }
            aux = aux.sgte;

        }//while
        //se sale del while cuando llegamos al ultimo nodo, nodo=fin

        if (aux.elemento.equals(elemento)) {
            return true;
        }

        return false;//significa  que el metodo no 
    }

    @Override
    public Object primero() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        return inicio.elemento;
    }

    @Override
    public Object ultimo() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio;
        while (aux.sgte != fin) {
            aux = aux.sgte;
        }
        return aux;
    }

    @Override
    public void ordenar() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Object temp;
        int n = getSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getNodo(j) == null || getNodo(j + 1) == null) {
                    break;
                }
                if (esMayorQ(getNodo(j).elemento, getNodo(j + 1).elemento)) {
                    temp = getNodo(j).elemento;
                    getNodo(j).elemento = getNodo(j + 1).elemento;
                    getNodo(j + 1).elemento = temp;
                }//if
            }//for
        }//for
    }

    @Override
    public Object anterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio.sgte;
        Nodo ant = inicio;

        if (inicio.elemento.equals(elemento)) {
            return fin.elemento;
        }
        while (aux != fin) {
            if (aux.elemento.equals(elemento)) {
                return ant.elemento;
            }
            ant = aux;
            aux = aux.sgte;

        }
        if (aux.elemento.equals(elemento)) {
            return ant.elemento;
        }

        return "El elemento no existe";
    }

    @Override
    public Object posterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }

        Nodo aux = inicio;
        Nodo post = inicio.sgte;
        while (aux != fin) {
            if (aux.elemento.equals(elemento)) {
                if (post != null) {
                    return post.elemento;
                } else {
                    return "No tiene";
                }
            }
            aux = aux.sgte;
            if (aux != null) {
                post = aux.sgte;
            }
        }

        if (aux.elemento.equals(elemento)) {
            return post.elemento;
        }

        return "El elemento no existe";
    }

    @Override
    public Nodo getNodo(int posicion) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio;
        int post = 1;
        while (aux != fin) {
            if (post == posicion) {
                return aux;
            }
            aux = aux.sgte;
            post++;
        }
        if (post == posicion) {
            return aux;
        }
        return null;//significa  que la poscicion no existe
    }

    @Override
    public Nodo getNodo(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        Nodo aux = inicio;
        while (aux != fin) {
            if (aux.elemento.equals(elemento)) {
                return aux;
            }
            if (aux.elemento.equals(elemento)) {
                return aux;
            }
            aux = aux.sgte;
        }
        return null;//significa  que la poscicion no existe
    }

}//ListaEnlazadaDoble

