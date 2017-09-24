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
public class ListaCircularEnlazadaDoble implements Lista, Serializable {

    private Nodo inicio;
    private Nodo fin;

    public ListaCircularEnlazadaDoble() {
        inicio = fin = null;
    }

    @Override
    public int getSize() {
        if (isEmpty()) {
            return 0;
        }
        Nodo aux = inicio;
        int cont = 1;// para que vaya 1 adelante
        while (aux != fin) {
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
        while (aux != fin) {
            if (aux.elemento.equals(elemento)) {
                return pos;
            }
            aux = aux.sgte;
            pos++;
        }// se sale delwhile cuando sellega al ultimo nodo

        if (aux.elemento.equals(elemento)) {
            return pos;
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
        while (aux != fin) {
            if (pos == posicion) {
                return aux;
            }
            aux = aux.sgte;
            pos++;
        }
        if (pos == posicion) {
            return aux;
        }
        return null;//no existe laposicion
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
            aux = aux.sgte;
        }
        if (aux.elemento.equals(elemento)) {
            return aux;
        }
        return null;//no existe elemento
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
            nuevoNodo.ant = aux;//se conecta con el nodo anterior
            //hacemos que fin apunte al nuevo nodo
            fin = nuevoNodo;
            //hacemos enlace circular
            fin.sgte = inicio;
        }//else
    }//insertar

    @Override
    public void insertarOrdenado(Object elemento) {

        Nodo nuevoNodo = new Nodo(elemento);
        if (inicio == null) {
            inicio = fin = nuevoNodo;
        } else if (inicio.sgte == fin.sgte && esMayorQ(inicio.elemento, elemento)) {
            nuevoNodo.sgte = inicio;
            inicio.ant = nuevoNodo;
            inicio = nuevoNodo;
            fin.sgte = inicio;//enlace circular
            inicio.ant = fin;
        } else if (inicio.sgte == fin.sgte && !esMayorQ(inicio.elemento, elemento)) {
            inicio.sgte = nuevoNodo;
            nuevoNodo.ant = inicio;
            fin = nuevoNodo;
            fin.sgte = inicio;//enlace circula
            inicio.ant = fin;
        } else if (esMayorQ(inicio.elemento, elemento)) {
            nuevoNodo.sgte = inicio;
            inicio.ant = nuevoNodo;
            inicio = nuevoNodo;
            fin.sgte = inicio;//enlace circular
            inicio.ant = fin;
        } else {
            boolean insertado = false;
            Nodo nodoAnterior = inicio;//un nodo atras
            Nodo aux = inicio.sgte;
            while (aux != fin && !insertado) {
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
                if (esMayorQ(aux.elemento, elemento)) {
                    nodoAnterior.sgte = nuevoNodo;
                    nuevoNodo.ant = nodoAnterior;
                    nuevoNodo.sgte = aux;
                    aux.ant = nuevoNodo;
                }//if
                else {
                    aux.sgte = nuevoNodo;
                    nuevoNodo.ant = aux;
                    fin = nuevoNodo;
                    fin.sgte = inicio;
                    inicio.ant = fin;
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
//            case "Estudiante":
//                Estudiante r = (Estudiante) a;
//                Estudiante s = (Estudiante) b;
//                return r.getCarne().compareToIgnoreCase(s.getCarne()) > 0;
//            case "Curso":
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
        //Este metodo elimina todas las ocurrencias  del elemento
        if (isEmpty()) {
            throw new ListaException("La lista no existe");
        }
        if (inicio == fin && inicio.elemento.equals(elemento)) {
            this.anular();// Anulo la lista
        } else {
            if (inicio.elemento.equals(elemento)) {
                inicio = inicio.sgte;
                inicio.ant = fin;
            } else {
                Nodo aux = inicio;
                Nodo ant = null;
                while (aux != fin && !aux.elemento.equals(elemento)) {
                    ant = aux;
                    aux = aux.sgte;
                }
                //se sale del while cuando alcanza el nodo=fin o encuentra el elemento
                if (aux.elemento.equals(elemento)) {
                    ant.sgte = aux.sgte; // Me salto el nodo apuntado por auxiliar
                    aux.sgte.ant = ant;
                }
                //me aseguro que fin apunte al ultimo nodo de la lista
                if (aux == fin) {
                    fin = ant;//dejo fin apuntando al ultimo
                }
            }
        }
        //mantengo el enlace circular
        fin.sgte = inicio;
    }

    @Override
    public boolean existe(Object elemento) throws ListaException {
        Nodo aux = inicio;
        while (aux != fin) {
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
        while (aux != fin) {
            aux = aux.sgte;
        }
        return aux.elemento;
    }

    @Override
    public void ordenar() throws ListaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object anterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        if (inicio.elemento.equals(elemento)) {
            return fin.elemento;
        } else {
            Nodo aux = inicio.sgte;
            Nodo ant = inicio;
            while (aux != fin) {
                if (aux.ant.elemento.equals(elemento)) {
                    return ant.ant.elemento;
                } else if (aux.elemento.equals(elemento)) {
                    return ant.elemento;
                } else {
                    ant = aux;
                    aux = aux.sgte;
                }

            }
            if (aux.elemento.equals(elemento)) {
                return ant.elemento;
            }
        }
        return "El elemento no existe";

    }

    @Override
    public Object posterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        } else {
            Nodo aux = inicio;
            Nodo post = inicio.sgte;
            while (aux != fin) {
                if (aux.elemento.equals(elemento)) {
                    if (post != fin) {
                        return post.elemento;
                    } else {
                        return inicio.elemento;
                    }
                } else {
                    aux = aux.sgte;
                    if (aux != null) {
                        post = aux.sgte;
                    }
                }

            }

            if (aux.elemento.equals(elemento)) {
                return aux.sgte.elemento;
            }

        }
        return "El elemento no existe";
    }

}
