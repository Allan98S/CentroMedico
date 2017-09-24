package TDA;

import TDA.Listas.ListaEnlazada;
import TDA.Listas.ListaException;
import java.io.Serializable;

public class ConjuntoEnlazado implements Conjuntable, Serializable {

    private ListaEnlazada lista = new ListaEnlazada();
    

    @Override
    public  int getSize() throws ConjuntoException, ListaException {
        int size = 0;
        try {
            size = lista.getSize();
            
        } catch (Exception e) {
        }
        return size;
    }

    @Override
    public void anular() {
        lista.anular();
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public int getPosicion(Object elemento) throws ConjuntoException,
            ListaException {
        return lista.getPosicion(elemento);
    }

    @Override
    public Object getElemento(int posicion) throws ConjuntoException,
            ListaException {
        Object elemento = null;
        try {
            elemento = lista.getNodo(posicion).elemento;
        } catch (Exception e) {
        }
        return elemento;
    }

    @Override
    public void insertar(Object elemento) throws ConjuntoException,
            ListaException {
        if (lista.isEmpty()) {
            lista.insertar(elemento);
        }
        if (!lista.existe(elemento)) {
            lista.insertar(elemento);
        }
    }

    @Override
    public void suprimir(Object elemento) throws ConjuntoException,
            ListaException {
        lista.suprimir(elemento);
    }

    @Override
    public boolean existe(Object elemento) throws ConjuntoException,
            ListaException {
        return lista.existe(elemento);
    }

    @Override
    public Conjuntable union(Conjuntable a, Conjuntable b)
            throws ConjuntoException, ListaException {

        if (a.isEmpty() && b.isEmpty()) {
            throw new ConjuntoException(
                    "La unión de 2 conjuntos vacíos es CONJUNTO VACÍO.");
        }
        Conjuntable resultante = new ConjuntoEnlazado();
        // Agregamos todos los elementos a
        for (int i = 1; i <= a.getSize(); i++) {
            if (a.getElemento(i) != null) {
                resultante.insertar(a.getElemento(i));
            }
        }
        // Agregamos todos los elementos b
        for (int i = 1; i <= b.getSize(); i++) {
            if (b.getElemento(i) != null) {
                resultante.insertar(b.getElemento(i));
            }
        }
        return resultante;
    }

    @Override
    public Conjuntable interseccion(Conjuntable a, Conjuntable b)
            throws ConjuntoException, ListaException {
        if (a.isEmpty() || b.isEmpty()) {
            throw new ConjuntoException(
                    "Uno de los 2 conjuntos es vacío. La intersección con un conjunto vacío es vacío.");
        }
        Conjuntable resultante = null;
        if (a.getSize() <= b.getSize()) {
            resultante = new ConjuntoEnlazado();
            for (int i = 1; i <= a.getSize(); i++) {
                if (b.existe(a.getElemento(i))) {
                    resultante.insertar(a.getElemento(i));
                }
            }
        } else {
            resultante = new ConjuntoEnlazado();
            for (int i = 1; i <= b.getSize(); i++) {
                if (a.existe(b.getElemento(i))) {
                    resultante.insertar(b.getElemento(i));
                }
            }
        }
        return resultante;
    }

    @Override
    public Conjuntable diferencia(Conjuntable a, Conjuntable b)
            throws ConjuntoException, ListaException {

        if (a.isEmpty()) {
            throw new ConjuntoException("El conjunto está vacío.");
        }
        if (b.isEmpty()) {
            return a;
        }
        Conjuntable resultante = new ConjuntoEnlazado();
        for (int i = 1; i <= a.getSize(); i++) {
            if (!b.existe(a.getElemento(i))) {
                resultante.insertar(a.getElemento(i));
            }
        }
        return resultante;
    }

    public Conjuntable complemento(ConjuntoArreglo A, ConjuntoArreglo U)
            throws ConjuntoException, ListaException {
        if (U.isEmpty()) {
            throw new ConjuntoException("El conjunto universo está vacío");
        }
        if (A.isEmpty()) {
            return U;
        }
        Conjuntable resultante = new ConjuntoEnlazado();
        for (int i = 1; i <= U.getSize(); i++) {
            if (!A.existe(U.getElemento(i))) {
                resultante.insertar(U.getElemento(i));
            }
        }
        return resultante;
    }

    public String productoCartesiano(ConjuntoEnlazado a, ConjuntoEnlazado b)
            throws ConjuntoException, ListaException {
        if (a.isEmpty() || b.isEmpty()) {
            throw new ConjuntoException("Algún conjunto está vacío.");
        }
        String result = "";
        for (int i = 1; i <= a.getSize(); i++) {
            for (int j = 1; j <= b.getSize(); j++) {
                result += "(" + a.getElemento(i) + "," + b.getElemento(j)
                        + ") ";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return lista.toString();
    }

}
