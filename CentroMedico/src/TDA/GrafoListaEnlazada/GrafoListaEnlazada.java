package TDA.GrafoListaEnlazada;

import TDA.Colas.ColaException;
import TDA.Listas.ListaEnlazada;
import TDA.Listas.ListaException;
import TDA.PilaLlenaException;
import TDA.PilaVaciaException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrafoListaEnlazada implements Grafo, Serializable {

    //Atributos 
    public ListaEnlazada listaVertices;
   // private PilaEnlazada pila;
    //private ColaEnlazada cola;
//    private Vertice listaVertice[]; //ESPECIE DE ARREGLO DE VERTICES

    //Constructor
    public GrafoListaEnlazada() {
        listaVertices = new ListaEnlazada();
       // pila = new PilaEnlazada();
        //cola = new ColaEnlazada();
    }//Constructor()

    @Override
    public void anular() {
        listaVertices.anular();
    }//anular()

    @Override
    public boolean isEmpty() throws GrafoException {
        return listaVertices.isEmpty();
    }//isEmpty()

    @Override
    public int getSize() throws GrafoException, ListaException {
        if (isEmpty()) {
            return 0;
        }//if
        return listaVertices.getSize();
    }//getSize()

    @Override
    public boolean existeVertice(Object elemento) throws GrafoException, ListaException {
        if (isEmpty()) {
            throw new GrafoException("EL GRAFO NO EXISTE");
        }//if
        for (int i = 1; i <= listaVertices.getSize(); i++) {
            Vertice vertice = (Vertice) listaVertices.getNodo(i).elemento;
            if (vertice.elemento.equals(elemento)) {
                return true;
            }//if
        }//for
        return false;
    }//existeVertice()

    @Override
    public boolean existeArista(Object v1, Object v2) throws GrafoException, ListaException {
        if (!existeVertice(v1) || !existeVertice(v2)) {
            throw new GrafoException("Alguno o ninguno de los vértices existe");
        }//if
        for (int i = 1; i <= listaVertices.getSize(); i++) {
            Vertice vertice = (Vertice) listaVertices.getNodo(i).elemento;
            if (vertice.elemento.equals(v1) && vertice.listaAristas.existe(v2)) {
                return true;
            }//if
        }//for
        return false;
    }//existeArista()

    @Override
    public void agregaVertice(Object elemento) throws GrafoException, ListaException {
        if (listaVertices.isEmpty()) {
            listaVertices.insertar(new Vertice(elemento));
        }//if 
        else if (!existeVertice(elemento)) {
            listaVertices.insertar(new Vertice(elemento));
        }//else if
    }//agregaVertice()

    public void suprimirVertice(Object elemento) throws ListaException {
        System.out.println(listaVertices.getSize());
        
        listaVertices.suprimir(elemento);
        System.out.println(listaVertices.getSize());
    }

    @Override
    public void agregaArista(Object v1, Object v2) throws GrafoException, ListaException {
        if (!existeVertice(v1) || !existeVertice(v2)) {
            throw new GrafoException("Alguno o ninguno de los vertices no existe");
        }//if
        //ESTABLECE LA ARISTA ENTRE EL VERTICE 1 Y EL VERTICE 2
        setVerticeAristaYPeso(v1, v2, "insertar", null);
        //ESTABLECE LA ARISTA ENTRE EL VERTICE 2 Y EL VERTICE 1
        setVerticeAristaYPeso(v2, v1, "insertar", null);

    }//agregaArista()

    @Override
    public void agregaPeso(Object v1, Object v2, Object peso) throws GrafoException, ListaException {
        if (!existeArista(v1, v2)) {
            throw new GrafoException("No existe la lista entre los vertices " + v1 + " y " + v2);
        }
        setVerticeAristaYPeso(v1, v2, "modificar", peso);
        setVerticeAristaYPeso(v2, v1, "modificar", peso);

    }//agregaPeso()

    public String dfs() throws GrafoException, PilaLlenaException, PilaVaciaException, ListaException {
//        String info;
//
//        setVisitados(false);
//        Vertice vertice = (Vertice) listaVertices.getNodo(1).elemento;
//        vertice.visitado = true;
//        info = muestraVertice(1) + " ";
//        pila.anular();
//        pila.push(1);
//        while (!pila.isEmpty()) {
//
//            int v;
//            v = VerticeAdyacenteNOVisitado(Integer.parseInt(pila.top().toString()));
//
//            if (v == -1) {
//                pila.pop();
//            } else {
//
//                vertice = (Vertice) listaVertices.getNodo(v).elemento;
//                vertice.visitado = true;
//                info += muestraVertice(v) + " ";
//                pila.push(v);
//
//            }//else
//        } //while
//        for (int j = 1; j <= listaVertices.getSize(); j++) {
//            vertice = (Vertice) listaVertices.getNodo(j).elemento;
//            vertice.visitado = false;
//        }//for

       // return info;
       return null;
    }

    @Override
    public String bfs() throws GrafoException, ColaException, ListaException {
//        String info;
//
//        setVisitados(false);
//        Vertice vertice = (Vertice) listaVertices.getNodo(1).elemento;
//        vertice.visitado = true;
//        info = muestraVertice(1) + " ";
//
//        cola.anular();
//
//        cola.encolar(1);
//        int v2;
//
//        while (!cola.isEmpty()) {
//
//            int v1;
//
//            v1 = Integer.parseInt(cola.desencolar().toString());
//
//            while ((v2 = VerticeAdyacenteNOVisitado(v1)) != -1) {
//
//                vertice = (Vertice) listaVertices.getNodo(v2).elemento;
//                vertice.visitado = true;
//                info += muestraVertice(v2) + " "; // lo muestra
//                cola.encolar(v2); // lo encola
//
//            } // fin de while
//        } // fin de while
//
//        setVisitados(false);
//        return info;
return null;
    }

    private void setVerticeAristaYPeso(Object v1, Object v2, String opcion, Object peso) throws ListaException {

        for (int i = 1; i <= listaVertices.getSize(); i++) {
            Vertice vertice = (Vertice) listaVertices.getNodo(i).elemento;
            if (vertice.elemento.equals(v1)) {
                if (opcion.equalsIgnoreCase("insertar")) {
                    vertice.listaAristas.insertar(v2);
                    vertice.listaPesos.insertar(v2);
                } else if (opcion.equalsIgnoreCase("modificar")) {
                    vertice.listaPesos.modificar(v2, peso);
                } else if (opcion.equalsIgnoreCase("suprimir")) {
                    if (!vertice.listaAristas.isEmpty()) {
                        vertice.listaAristas.suprimir(v2);
                    }
                    if (!vertice.listaPesos.isEmpty()) {
                        vertice.listaPesos.suprimir(v2);
                        vertice.listaPesos.suprimir(peso);
                    }
                }//else if
            }//if 
        }//for
    }//setVerticeAristaYPeso()

    private void setVisitados(boolean valor) throws ListaException {

        for (int i = 1; i <= listaVertices.getSize(); i++) {
            Vertice vertice = (Vertice) listaVertices.getNodo(i).elemento;
            vertice.visitado = valor;

        }//fin de for
    }//fin de setVisitados

    public int VerticeAdyacenteNOVisitado(int v) throws ListaException, GrafoException {
        Object nombreVertice = muestraVertice(v);
        for (int j = 1; j <= listaVertices.getSize() - 1; j++) {
            Vertice vertice = (Vertice) listaVertices.getNodo(j).elemento;
            if (!vertice.visitado) {
                return j;//si no ha sido setVisitados lo visita
            }
        }
        return -1;
    } // fin de VerticeAdyacenteNOVisitado

    private Object muestraVertice(int posicion) throws GrafoException, ListaException {

        if (posicion < 0 || posicion > listaVertices.getSize()) {
            throw new GrafoException("No existe el Vertice en el Grafo");
        }
        Vertice vertice = (Vertice) listaVertices.getNodo(posicion).elemento;

        return vertice.elemento.toString();

    }//fin de muestraVertice

    public Object getElementoVertice(int posicion) throws GrafoException, ListaException {
        
        if (posicion < 0 || posicion > listaVertices.getSize()) {
            throw new GrafoException("No existe el Vertice en el Grafo");
        }
        Vertice vertice = (Vertice) listaVertices.getNodo(posicion).elemento;

        return vertice.elemento;

    }//fin de muestraVertice

    @Override
    public String toString() {
        String resultado = "GRAFO CON LISTAS ENLAZADAS\n";
        try {
            for (int i = 1; i <= listaVertices.getSize(); i++) {

                Vertice vertice = (Vertice) listaVertices.getNodo(i).elemento;
                resultado += "El vertice en la posicion " + i + " es: " + vertice.elemento.toString() + "\n";
                resultado += "..........ARISTAS: " + vertice.listaAristas.toString() + "\n";
                resultado += "..........PESOS: " + vertice.listaPesos.toString() + "\n";

            }
        } catch (ListaException ex) {
            Logger.getLogger(GrafoListaEnlazada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public Vertice getVertice(Object elemento) throws ListaException, GrafoException {
        if (!isEmpty()) {
            for (int i = 1; i <= listaVertices.getSize(); i++) {
                Vertice v = (Vertice) listaVertices.getNodo(i).elemento;
                if (v.elemento.equals(elemento)) {
                    return v; //retorna la posicion i
                }
            }//for i
        }
        return null;
    }

    public Grafo prim(Object verticeInicial) throws GrafoException, ListaException {

        GrafoListaEnlazada nuevoGrafo = new GrafoListaEnlazada();

        for (int i = 1; i <= listaVertices.getSize(); i++) {
            Vertice vertice = (Vertice) listaVertices.getNodo(i).elemento;
            if (vertice != null) {
                nuevoGrafo.agregaVertice(vertice.elemento);
            }

        }//Fin for
//       // for (Vertice vertice : listaVertice) {
//            for(int i =0; i<= listaVertice.length;i++){
//         Vertice vertice = (Vertice) listaVertice[i];
//             //    .getNodo(i).elemento;
//            if (vertice != null) {
//                nuevoGrafo.agregaVertice(vertice.elemento);
//            }
//        }

        Vertice verticeActual = obtenervertice(verticeInicial);

        do {

            if (verticeActual != null) {

                verticeActual.visitado = true;

                Object[] vector = aristaMenor();

                Object inicio = vector[0];
                Object destino = vector[1];
                int peso = (int) vector[2];

                if (inicio == null) {
                    break;
                }

                nuevoGrafo.agregaArista(inicio, destino);
                nuevoGrafo.agregaPeso(inicio, destino, peso);

                verticeActual = obtenervertice(destino);
            }
        } while (!nuevoGrafo.verticesConectados());

        return nuevoGrafo;

    }

    private Object[] aristaMenor() throws ListaException {

        int peso = Integer.MAX_VALUE;
        Object inicio = null;
        Object destino = null;

        for (int i = 1; i <= listaVertices.getSize(); i++) {

            Vertice v = (Vertice) listaVertices.getNodo(i).elemento;

            if (v.elemento != null && v.visitado) {

                if ((int) v.elemento < peso && !v.elemento.equals(0)) {

                    peso = (int) listaVertices.getNodo(i).elemento;
                    inicio = v.elemento;
//              destino = listaVertice[j].elemento;

                }//Fin metodo if()

            }

        }

        Object[] v = {inicio, destino, peso};
        return v;
    }

    private boolean verticesConectados() throws ListaException, GrafoException {
        int cont = 0;

        for (int i = 1; i <= listaVertices.getSize(); i++) {

            Vertice v = (Vertice) listaVertices.getNodo(i).elemento;

            if (!v.elemento.equals(0)) {
                cont++;
                break;
            }//Fin if
        }//Fin ciclo for
        return (cont == getSize());

    }//Fin metodo verticesConectados

    private Vertice obtenervertice(Object elemento) throws ListaException {

        for (int i = 1; i <= listaVertices.getSize(); i++) {

            Vertice v = (Vertice) listaVertices.getNodo(i).elemento;

            if (v.elemento.equals(elemento)) {

                return v;

            }//Fin if
        }//for
        return null;
    }//Fin metodo obtenerVertice()

}
