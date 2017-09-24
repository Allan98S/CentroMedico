/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.Arboles;


import TDA.Arbol;
import TDA.ArbolException;
import static TDA.Listas.MisUtilidades.esMayorQ;
import TDA.NodoBinario;
import TDA.Utilidades;
import java.io.Serializable;

/**
 *
 * @author juang
 */
public class ArbolBB implements Arbol, Serializable {

    private NodoBinario raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    @Override
    public void anular() {
        raiz = null;
    }

    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    @Override
    public int getSize() throws ArbolException {
        if (isEmpty()) {
         //   throw new ArbolException("el arbol no existe");
        }
        return getSize(raiz);
    }

    private int getSize(NodoBinario nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + getSize(nodo.izquierda) + getSize(nodo.derecha);
    }

    @Override
    public boolean existe(Object elemento) throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("el arbol no existe");
        }
        return busquedaBinaria(raiz, elemento);
    }

    private boolean busquedaBinaria(NodoBinario nodo, Object elemento) {
        if (nodo == null) {
            return false;
        } else if (nodo.elemento.equals(elemento)) {
            return true;
        } else if (!esMayorQ(elemento, nodo.elemento)) {
            return busquedaBinaria(nodo.izquierda, elemento);
        } else {
            return busquedaBinaria(nodo.derecha, elemento);
        }
    }

    @Override
    public void insertar(Object elemento){
        raiz = insertar(raiz, elemento);
    }

    private NodoBinario insertar(NodoBinario nodo, Object elemento) {

        if (nodo == null) {
            nodo = new NodoBinario(elemento);
        } else if (Utilidades.menorQue(elemento, nodo.elemento)) {
            nodo.izquierda = insertar(nodo.izquierda, elemento);
        }
        if (Utilidades.igual(nodo.elemento, elemento)) {
            return nodo;
        } else {
            nodo.derecha = insertar(nodo.derecha, elemento);
        }
        return nodo;
    }

    @Override
    public void suprimir(Object elemento) throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("el arbol no existe");
        }
        raiz = suprimir(raiz, elemento);
    }

    private NodoBinario suprimir(NodoBinario nodo, Object elemento) {
        if (nodo != null) {
            if (Utilidades.menorQue(elemento, nodo.elemento)) {
                nodo.izquierda = suprimir(nodo.izquierda, elemento);
            } else if (esMayorQ(elemento, nodo.elemento)) {
                nodo.derecha = suprimir(nodo.derecha, elemento);
            } else if (Utilidades.igual(elemento, nodo.elemento)) {
                //encotro el elemento
                if (nodo.elemento.equals(elemento)) {
                    //caso 1 es un nodo sin hijos 
                    if (nodo.izquierda == null && nodo.derecha == null) {
                        return nodo = null;
                    } //caso 2 el nodo solo tiene un hijo
                    else if (nodo.izquierda == null && nodo.derecha != null) {
                        return nodo = nodo.derecha;
                    } else if (nodo.izquierda != null && nodo.derecha == null) {
                        return nodo = nodo.izquierda;
                    } //caso 3 el nodo tiene dos hijos
                    else if (nodo.izquierda != null && nodo.derecha != null) {
                        Object hijoMenorSubArbolDer = minimo(nodo.derecha);
                        nodo.elemento = hijoMenorSubArbolDer;
                        nodo.derecha = suprimir(nodo.derecha, hijoMenorSubArbolDer);
                    }
                }
            }//encontro al elemento que quiere suprimir

        }
        return nodo;
    }

    private Object getHoja(NodoBinario nodo) {
        if (nodo == null) {
            return null;
        } else if (nodo.izquierda == null && nodo.derecha == null) {
            return nodo.elemento;
        } else {
            return nodo.izquierda != null ? getHoja(nodo.izquierda) : getHoja(nodo.derecha);
        }
    }

    @Override
    public int alturaNodo(Object elemento) throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("el arbol no existe");
        }
        return alturaNodo(raiz, elemento, 0);
    }

    private int alturaNodo(NodoBinario nodo, Object elemento, int contador) {
        if (nodo == null) {
            return 0;
        } else if (nodo.elemento.equals(elemento)) {
            return contador;
        } else if (!esMayorQ(elemento, nodo.elemento)) {
            return alturaNodo(nodo.izquierda, elemento, ++contador);
        } else {
            return alturaNodo(nodo.derecha, elemento, ++contador);
        }

    }

    @Override
    public int alturaArbol() throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("el arbol no existe");
        }
        return alturaArbol(raiz);
    }

    private int alturaArbol(NodoBinario nodo) throws ArbolException {
        if (nodo == null) {
            return 0;
        } else {
            return Math.max(alturaArbol(nodo.izquierda), alturaArbol(nodo.derecha)) + 1;
        }
    }

    @Override
    public Object minimo() throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("el arbol no existe");
        }
        return minimo(raiz);
    }

    private Object minimo(NodoBinario nodo) {
        if (nodo.izquierda != null) {
            return minimo(nodo.izquierda);
        }
        return nodo.elemento;
    }

    @Override
    public Object maximo() throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("el arbol no existe");
        }
        return maximo(raiz);
    }

    private Object maximo(NodoBinario nodo) {
        if (nodo.derecha == null) {

            return nodo.elemento;

        } else {

            return maximo(nodo.derecha);

        }
    }

    @Override
    public String toString() {
        String result = "\n ARBOL BINARIO SIMPLE";
        result += "\n PREORDEN: " + preOrden(raiz);
        result += "\n IN ORDEN: " + inOrden(raiz);
        result += "\n POST ORDEN: " + postOrden(raiz);
        return result;
    }
    //preOrden: recorre el árbol de la forma: nodo-izq-der

    private String preOrden(NodoBinario nodo) {
        String result = "";
        if (nodo != null) {
            result = nodo.elemento.toString();
            result += preOrden(nodo.izquierda);
            result += preOrden(nodo.derecha);
        }
        return result;
    }
    //inOrden: recorre el árbol de la forma: izq-nodo-der

    private String inOrden(NodoBinario nodo) {
        String result = "";
        if (nodo != null) {
            result = inOrden(nodo.izquierda);
            result += nodo.elemento.toString();
            result += inOrden(nodo.derecha);
        }
        return result;
    }

    private String postOrden(NodoBinario nodo) {
        String result = "";
        if (nodo != null) {
            result += postOrden(nodo.izquierda);
            result += postOrden(nodo.derecha);
            result += nodo.elemento.toString() + " ";
        }
        return result;
    }

    public void modificar(Object elemento, Object nuevoElemento) throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("El arbol no existe");
        }
        raiz = modificar(raiz, elemento, nuevoElemento);
    }

    private NodoBinario modificar(NodoBinario nodo, Object elemento, Object nuevoElemento) throws ArbolException {
        if (nodo != null) {
            //encontro el elmento
            if (nodo.elemento.equals(elemento)) {
                nodo.setElemento(nuevoElemento);
            }
            nodo.izquierda = modificar(nodo.izquierda, elemento, nuevoElemento);
            nodo.derecha = modificar(nodo.derecha, elemento, nuevoElemento);
        }
        return nodo;
    }

    public NodoBinario getNodoBinario(int posicion) throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("El arbol binario no existe");
        }
        return getNodoBinario(raiz, posicion);
    }

    private NodoBinario getNodoBinario(NodoBinario nodo, int posicion) {
        if (nodo == null) {
            return null;
        } else if (nodo.posicion == posicion) {
            return nodo;
        }
        NodoBinario nodoIzq = getNodoBinario(nodo.izquierda, posicion);
        NodoBinario nodoDer = getNodoBinario(nodo.derecha, posicion);
        return nodoIzq != null ? nodoIzq
                : nodoDer != null ? nodoDer : null;
    }
}