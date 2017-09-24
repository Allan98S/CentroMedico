package TDA;

import java.io.Serializable;
import java.util.Random;

public class ArbolBinarioSimple implements Arbol, Serializable {

    // Atributos
    private NodoBinario raiz;// Representa la entrada única al árbol
    private Random random;// Generar valores aleatorios

    public ArbolBinarioSimple() {

        this.random = new Random();

    }

    public String nodos1Hijo() throws ArbolException {

        if (getSize() == 1 || getSize() == 0) {

            return "No hay nodos con sólo 1 hijo";

        }

        return nodos1Hijo(raiz);
    }

    private String nodos1Hijo(NodoBinario nodo) {

        if (nodo.izquierda != null && nodo.derecha != null) {

            return nodos1Hijo(nodo.izquierda) + nodos1Hijo(nodo.derecha);

        } else if (nodo.izquierda != null && nodo.derecha == null) {

            return "Nodo Padre: " + nodo.elemento.toString() + " Hijo: "
                    + nodo.izquierda.elemento.toString() + "\n"
                    + nodos1Hijo(nodo.izquierda);

        } else if (nodo.izquierda == null && nodo.derecha != null) {

            return "Nodo Padre: " + nodo.elemento.toString() + " Hijo: "
                    + nodo.derecha.elemento.toString() + "\n"
                    + nodos1Hijo(nodo.derecha);

        } else {

            return "";

        }

    }

    public int numeroHojas() {

        return numeroHojas(raiz);

    }

    private int numeroHojas(NodoBinario nodo) {

        if (nodo.izquierda == null && nodo.derecha == null) {

            return 1;

        }

        if (nodo.izquierda != null && nodo.derecha == null) {

            return numeroHojas(nodo.izquierda);

        }

        if (nodo.izquierda == null && nodo.derecha != null) {

            return numeroHojas(nodo.derecha);

        } else {

            return numeroHojas(nodo.izquierda) + numeroHojas(nodo.derecha);

        }

    }

    public String nodoConHijos() {

        return nodoConHijos(raiz);

    }

    private String nodoConHijos(NodoBinario nodo) {

        if (nodo.izquierda != null && nodo.derecha != null) {

            return "Nodo Padre: " + nodo.elemento.toString() + " Hijos: "
                    + nodo.izquierda.elemento.toString() + " - "
                    + nodo.derecha.elemento.toString() + "\n"
                    + nodoConHijos(nodo.izquierda) + nodoConHijos(nodo.derecha);

        } else if (nodo.izquierda != null && nodo.derecha == null) {

            return "Nodo Padre: " + nodo.elemento.toString() + " Hijos: "
                    + nodo.izquierda.elemento.toString() + "\n"
                    + nodoConHijos(nodo.izquierda);

        } else if (nodo.izquierda == null && nodo.derecha != null) {

            return "Nodo Padre: " + nodo.elemento.toString() + " Hijos: "
                    + nodo.derecha.elemento.toString() + "\n"
                    + nodoConHijos(nodo.derecha);

        } else {

            return "";

        }

    }

    public NodoBinario getNodo(Object elemento) throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El árbol binario no existe");

        }

        if (!existe(elemento)) {

            throw new ArbolException(
                    "El elemento no se encuentra en el árbol binario simple");

        }

        return getNodo(raiz, elemento);

    }

    // /**
    // *
    // * @author Profesor Gilberth Chaves Avila Se llama de esta forma: raiz
    // * public void insertar(Object elemento) throws ArbolException {
    // * raiz = insertarConEtiquetayNivel(raiz, elemento,"raiz", 0); }
    // */
    public void insertarConEtiquetayNivel(Object elemento) {

        raiz = insertarConEtiquetayNivel(raiz, elemento, "raiz", 0);

    }

    private NodoBinario insertarConEtiquetayNivel(NodoBinario nodo,
            Object elemento, String etiqueta, int nivel) {

        if (nodo == null) { // el arbol esta vacio
            nodo = new NodoBinario(elemento, etiqueta, nivel);
        } else {
            // debemos establecer algun criterio para insertar el elemento
            int valor = random.nextInt(2); // establece un valor entre 0 y 1
            if (valor == 1) // inserta por la izq
            {
                nodo.izquierda = insertarConEtiquetayNivel(nodo.izquierda,
                        elemento, etiqueta + "/izquierda", ++nivel);
            } else // inserta por la der
            {
                nodo.derecha = insertarConEtiquetayNivel(nodo.derecha,
                        elemento, etiqueta + "/derecha", ++nivel);
            }
        }
        return nodo;
    }

    private NodoBinario getNodo(NodoBinario nodo, Object elemento) {

        if (Utilidades.igual(nodo.elemento, elemento)) {

            return nodo;

        } else {

            if (nodo.izquierda == null && nodo.derecha != null) {

                return getNodo(nodo.derecha, elemento);

            } else if (nodo.izquierda != null && nodo.derecha == null) {

                return getNodo(nodo.izquierda, elemento);

            } else if (nodo.izquierda != null && nodo.derecha != null) {

                NodoBinario izquierda = getNodo(nodo.izquierda, elemento);

                NodoBinario derecha = getNodo(nodo.derecha, elemento);

                return izquierda == null ? derecha : izquierda;

            }

        }

        return null;

    }

    public String dibujarArbol() {

        String result = "****Dibujo del árbol binario simple****\n\n";

        result += dibujarArbol(raiz);

        return result;
    }

    private String dibujarArbol(NodoBinario nodo) {

        String result = "";

        if (nodo != null) {

            result += nodo.elemento + " - " + nodo.etiqueta + " - Nivel: "
                    + nodo.nivel + "\n";
            result += dibujarArbol(nodo.izquierda);
            result += dibujarArbol(nodo.derecha);

        }

        return result;

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

            throw new ArbolException("El Árbol Binario no existe");

        }

        return getSize(raiz);
    }

    @Override
    public boolean existe(Object elemento) throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El Árbol Binario no existe");

        }

        return existe(raiz, elemento);
    }

    @Override
    public void insertar(Object elemento) {

        raiz = insertar(raiz, elemento);

    }

    @Override
    public void suprimir(Object elemento) throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El árbol binario no existe");

        }

        raiz = suprimir(raiz, elemento);

    }

    @Override
    public int alturaNodo(Object elemento) throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El árbol binario no existe");

        }

        return alturaNodo(raiz, elemento, 0);
    }

    @Override
    public int alturaArbol() throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El árbol binario no existe");

        }

        return alturaArbol(raiz);
    }

    @Override
    public Object minimo() throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El árbol binario no existe");

        }

        return minimo(raiz);
    }

    @Override
    public Object maximo() throws ArbolException {

        if (isEmpty()) {

            throw new ArbolException("El árbol binario no existe");

        }

        return maximo(raiz);
    }

    @Override
    public String toString() {

        String result = "****Recorrido por un árbol binario simple****\n\n";

        result += "Preorden:\n" + preOrden(raiz) + "\n";
        result += "InOrden:\n" + inOrden(raiz) + "\n";
        result += "PostOrden:\n" + postOrden(raiz) + "\n";

        return result;
    }

    // Métodos internos para el manejo de
    // recursividad**********************************************************************************************
    private NodoBinario suprimir(NodoBinario nodo, Object elemento) {

        if (nodo != null) {

            if (Utilidades.igual(nodo.elemento, elemento)) {

                // Si ya lo encontré
                // Caso #1 es un nodo sin hijos
                if (nodo.izquierda == null && nodo.derecha == null) {

                    return nodo = null;

                } else {

                    // Caso #2, el nodo sólo tiene un hijo
                    // En este caso el nodo es reempladado por el hijo
                    if (nodo.izquierda == null && nodo.derecha != null) {

                        return nodo = nodo.derecha;

                    } else if (nodo.izquierda != null && nodo.derecha == null) {

                        return nodo = nodo.izquierda;

                    } else {

                        // Caso 3 el nodo hijo tiene 2 hijos
                        if (nodo.izquierda != null && nodo.derecha != null) {

                            // Obtiene el elemento menor del subarbol derecho
                            Object hijoMenorSubArbolDerecho = minimo(nodo.derecha);

                            // Sustituyo el elemento de nodo con el valor mínimo
                            nodo.elemento = hijoMenorSubArbolDerecho;

                            nodo.derecha = suprimir(nodo.derecha,
                                    hijoMenorSubArbolDerecho);

                        }

                    }

                }

            }

            // Necesito recorrer todo el arbol
            nodo.izquierda = suprimir(nodo.izquierda, elemento);
            nodo.derecha = suprimir(nodo.derecha, elemento);

        }

        return nodo;

    }

    private int getSize(NodoBinario nodo) {

        if (nodo == null) {

            return 0;

        } else {

            return 1 + getSize(nodo.izquierda) + getSize(nodo.derecha);

        }
    }

    private int alturaNodo(NodoBinario nodo, Object elemento, int contador) {

        if (nodo == null) {

            return 0;

        } else if (Utilidades.igual(nodo.elemento, elemento)) {

            return contador;

        } else {
            return (int) Utilidades.maximo(
                    alturaNodo(nodo.izquierda, elemento, ++contador),
                    alturaNodo(nodo.derecha, elemento, contador));
        }

    }

    private int alturaArbol(NodoBinario nodo) {

        if (nodo == null) {

            return 0;

        } else {

            return (int) Utilidades.maximo(alturaArbol(nodo.izquierda),
                    alturaArbol(nodo.derecha) + 1);

        }

    }

    private Object maximo(NodoBinario nodo) {

        if (nodo.izquierda != null && nodo.derecha != null) {

            return Utilidades.maximo(nodo.elemento, Utilidades.maximo(
                    maximo(nodo.izquierda), maximo(nodo.derecha)));

        }

        if (nodo.izquierda != null && nodo.derecha == null) {

            return Utilidades.maximo(nodo.elemento, maximo(nodo.izquierda));

        }

        if (nodo.izquierda == null && nodo.derecha != null) {

            return Utilidades.maximo(nodo.elemento, maximo(nodo.derecha));

        }

        return nodo.elemento;
    }

    private Object minimo(NodoBinario nodo) {

        if (nodo.izquierda != null && nodo.derecha != null) {

            return Utilidades.minimo(nodo.elemento, Utilidades.minimo(
                    minimo(nodo.izquierda), minimo(nodo.derecha)));

        }

        if (nodo.izquierda != null && nodo.derecha == null) {

            return Utilidades.minimo(nodo.elemento, minimo(nodo.izquierda));

        }

        if (nodo.izquierda == null && nodo.derecha != null) {

            return Utilidades.minimo(nodo.elemento, minimo(nodo.derecha));

        }

        return nodo.elemento;
    }

    private boolean existe(NodoBinario nodo, Object elemento) {

        if (nodo == null) {

            return false;

        } else if (Utilidades.igual(nodo.elemento, elemento)) {

            return true;

        } else {

            return existe(nodo.izquierda, elemento)
                    || existe(nodo.derecha, elemento);

        }

    }

    private String postOrden(NodoBinario nodo) {

        String result = "";

        if (nodo != null) {

            result += postOrden(nodo.izquierda);
            result += postOrden(nodo.derecha);
            result += nodo.elemento + " ";

        }

        return result;

    }

    private String inOrden(NodoBinario nodo) {

        String result = "";

        if (nodo != null) {

            result += inOrden(nodo.izquierda);
            result += nodo.elemento + " ";
            result += inOrden(nodo.derecha);

        }

        return result;

    }

    private String preOrden(NodoBinario nodo) {

        String result = "";

        if (nodo != null) {

            result += nodo.elemento + " ";
            result += preOrden(nodo.izquierda);
            result += preOrden(nodo.derecha);

        }

        return result;
    }

    private NodoBinario insertar(NodoBinario nodo, Object elemento) {

        if (nodo == null) {// El árbol está vacío

            nodo = new NodoBinario(elemento);

        } else {

            if (nodo.izquierda == null) {

                nodo.izquierda = insertar(nodo.izquierda, elemento);

            } else if (nodo.derecha == null) {

                nodo.derecha = insertar(nodo.derecha, elemento);

            } else {// El nodo tiene 2 hijos ocupados

                // Debemos establecer algún criterio para insertar el elemento
                int valor = this.random.nextInt(2);// Genera un valor entre 0 y
                // 2

                if (valor == 1) {

                    nodo.izquierda = insertar(nodo.izquierda, elemento);

                } else {

                    nodo.derecha = insertar(nodo.derecha, elemento);

                }

            }

        }

        return nodo;
    }

    public NodoBinario getNodoBinario(int posicion) throws ArbolException {
        if (isEmpty()) {
            throw new ArbolException("El arbol binario simple no existe");
        }
        return getNodoBinarioPosicion(raiz, posicion);
    }

    public NodoBinario getNodoBinarioPosicion(NodoBinario nodo, int posicion) throws ArbolException {
        if (nodo == null) {
            return null;
        }//if
        else if (nodo.posicion == posicion) { //ya lo encontro
            return nodo;
        }//if
        return getNodoBinarioPosicion(nodo.izquierda, posicion) != null ?
                getNodoBinarioPosicion(nodo.izquierda, posicion) : getNodoBinarioPosicion(nodo.derecha, posicion);
    }
    //método interno

}
