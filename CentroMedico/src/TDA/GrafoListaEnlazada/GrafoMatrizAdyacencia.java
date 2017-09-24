/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.GrafoListaEnlazada;

import TDA.Listas.ListaException;
import TDA.Colas.ColaArreglo;
import TDA.Colas.ColaException;
import TDA.PilaEnlazada;
import TDA.PilaLlenaException;
import TDA.PilaVaciaException;
import java.io.Serializable;

public class GrafoMatrizAdyacencia implements Grafo,Serializable {
//Atributos

    private Vertice listaVertices[]; //ESPECIE DE ARREGLO DE VERTICES
    private Object matrizAdyacencia[][];//MATRIZ DE ADYACENCIA
    private int contador;//TIENE EL NUMERO ACTUAL DE VERTICES
    private int n;
    private PilaEnlazada pila;//recorrido por dfs
    private ColaArreglo cola;// recorrido por bfs

    public GrafoMatrizAdyacencia(int n) {
        if (n <= 0) {
            System.exit(0);
        }//if
        this.n = n;
        pila = new PilaEnlazada();
        cola = new ColaArreglo(n);
        anular();
        //INICIALIZA LA VARIABLE
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizAdyacencia[i][j] = 0;
            }
        }

    }//constructor()

    @Override
    public void anular() {
        listaVertices = new Vertice[n];
        contador = 0;
//INICIALIZAMOS LA MATRIZ
        matrizAdyacencia = new Object[n][n];
    }//anular()

    @Override
    public boolean isEmpty() {
        return contador == 0;
    }//isEmpty()

    @Override
    public int getSize() throws GrafoException, ListaException {
        if (isEmpty()) {
            throw new GrafoException("EL GRAFO NO EXISTE");
        }//if
        return contador;//EL TAMANO DEL GRAFO ES DADO POR EL NUMERO DE VERTICES
    }//getSize()

    @Override
    public boolean existeVertice(Object elemento) throws GrafoException, ListaException {
        if (isEmpty()) {
            throw new GrafoException("EL GRAFO NO EXISTE");
        }//if
        for (int i = 0; i < contador; i++) {
            if (listaVertices[i].elemento.equals(elemento)) {
                return true;
            }
        }//for
        return false;
    }//existeVertice()

    @Override
    public boolean existeArista(Object v1, Object v2) throws GrafoException, ListaException {
        if (!existeVertice(v1) || !existeVertice(v2)) {
            throw new GrafoException("Algun vertice no existe");
        }//if
        return !matrizAdyacencia[getPosicion(v1)][getPosicion(v2)].equals(0); //si existe la arista
        //if
        //no existe arista entre v1 y v2
    }//existeArista()

    @Override
    public void agregaVertice(Object elemento) throws GrafoException, ListaException {
        if (contador >= listaVertices.length) {
            throw new GrafoException("El Grafo esta lleno");
        }//if
        listaVertices[contador++] = new Vertice(elemento);
    }//agregaVertice()

    @Override
    public void agregaArista(Object v1, Object v2) throws GrafoException, ListaException {
        if (!existeVertice(v1) || !existeVertice(v2)) {
            throw new GrafoException("Algun vertice no existe");
        }//if
        matrizAdyacencia[getPosicion(v1)][getPosicion(v2)] = 1;
        matrizAdyacencia[getPosicion(v2)][getPosicion(v1)] = 1;
    }//agregaArista()

    @Override
    public void agregaPeso(Object v1, Object v2, Object peso) throws GrafoException, ListaException {
        if (!existeArista(v1, v2)) {
            throw new GrafoException("Algun vertice no existe");
        }//if
        matrizAdyacencia[getPosicion(v1)][getPosicion(v2)] = peso;
        matrizAdyacencia[getPosicion(v2)][getPosicion(v1)] = peso;
    }//agregaPeso

    //DEVUELVE LA POSICION EN LA LISTA DE VERTICE
    //LISTAVERTICES ES UN VECTOR DE OBJETOS DE TIPO VERTICE
    private int getPosicion(Object v1) {
        for (int i = 0; i < contador; i++) {
            if (listaVertices[i].elemento.equals(v1)) {
                return i;// LA POSICION DEL VERTICE EN EL ARREGLO
            }//if
        }//for
        return -1; //significa que el vertice no existe
    }//getPosicion()

    public String dfs() throws GrafoException, PilaVaciaException, PilaLlenaException, ListaException {
        String res = "";
        visitado(false);
        listaVertices[0].visitado = true;
        res = muestraVertice(0) + " ";
        pila.anular();
        pila.push(0);
        while (!pila.isEmpty()) {
            int vertice = verticeNoVisitado(Integer.parseInt(pila.top().toString()));
            if (vertice == -1) {
                pila.pop();
            } else {
                listaVertices[vertice].visitado = true;
                res += muestraVertice(vertice) + " ";
                pila.push(vertice);

            }//else
        }//while
        //VUELVE A PONER LOS VISITADOS EN FALSE
        for (int i = 0; i < contador; i++) {
            listaVertices[i].visitado = false;
        }//for
        return res;
    }//dfs()

    public String bfs() throws GrafoException, ColaException, ListaException {
        String res = "";
        visitado(false);
        listaVertices[0].visitado = true;
        res = muestraVertice(0) + " ";
        cola.anular();
        cola.encolar(0);
        int vertice;
        while (!cola.isEmpty()) {
            int vertice1 = Integer.parseInt(cola.desencolar().toString());
            while ((vertice = verticeNoVisitado(vertice1)) != -1) {

                listaVertices[vertice].visitado = true;
                res += muestraVertice(vertice) + " ";
                cola.encolar(vertice);
            } //while
        }//while
        //VUELVE A PONER LOS VISITADOS EN FALSE
        for (int i = 0; i < contador; i++) {
            listaVertices[i].visitado = false;
        }//for
        return res;
    }//bfs

    //CAMBIA EL VALOR CUANDO SE VISITA UN VERTICE
    private void visitado(boolean valor) {
        for (int i = 0; i < contador; i++) {
            listaVertices[i].visitado = valor;
        }//for
    }//visitado()

    public int verticeNoVisitado(int vertice) throws GrafoException, ListaException {
        for (int i = 0; i < contador; i++) {
            if (matrizAdyacencia[vertice][i] != (Object) 0 && !listaVertices[i].visitado) {
                return i;
            }//if
        }//for
        return -1;
    } //VerticeNoVisitado()

    private Object muestraVertice(int posicion) throws GrafoException {
        if (posicion < 0 || posicion >= contador) {
            throw new GrafoException("No existe el Vertice en el Grafo");
        }
        return listaVertices[posicion].elemento;
    }//fin de muestraVertice

    public String toSring() {
        String res = "\n----------------GRAFO MATRIZ DE ADYACENCIA----------------\n";
        for (int i = 0; i < contador; i++) {
            res += "El vertice en posicion " + i + " es: " + listaVertices[i].elemento + "\n";

        }//for
        for (int i = 0; i < contador; i++) {
            for (int j = 0; j < contador; j++) {
                if (!matrizAdyacencia[i][j].equals(0)) {
                    res += "\n" + listaVertices[i].elemento + "\t" + listaVertices[j].elemento;

                    res += "\tPeso " + matrizAdyacencia[i][j] + "\n";
                }
            }//for j
        }//for i
        return res;
    }//toString()

    public Grafo prim(Object verticeInicial) throws GrafoException, ListaException {

        if (isEmpty()) {

            throw new GrafoException("El grafo no existe.");

        }

        GrafoMatrizAdyacencia nuevoGrafo = new GrafoMatrizAdyacencia(getSize());

        for (Vertice vertice : listaVertices) {

            if (vertice != null) {
                nuevoGrafo.agregaVertice(vertice.elemento);
            }
        }

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

    private boolean verticesConectados() throws GrafoException, ListaException {

        int cont = 0;

        for (int i = 0; i < listaVertices.length; i++) {

            for (int j = 0; j < listaVertices.length; j++) {

                if (!matrizAdyacencia[j][i].equals(0)) {

                    cont++;
                    break;
                }
            }
        }
        return (cont == getSize());
    }

    private Vertice obtenervertice(Object elemento) {

        for (int i = 0; i < contador; i++) {
            if (listaVertices[i].elemento.equals(elemento)) {
                return listaVertices[i];
            };
        }//for
        return null;
    }

    private Object[] aristaMenor() {

        int peso = 1000000;
        Object inicio = null;
        Object destino = null;

        for (int i = 0; i < listaVertices.length; i++) {
            for (int j = 0; j < listaVertices.length; j++) {

                if (listaVertices[i] != null && listaVertices[i].visitado) {

                    if (listaVertices[j] != null && !listaVertices[j].visitado) {

                        if ((int) matrizAdyacencia[i][j] < peso && !matrizAdyacencia[i][j].equals(0)) {

                            peso = (int) matrizAdyacencia[i][j];
                            inicio = listaVertices[i].elemento;
                            destino = listaVertices[j].elemento;
                        }
                    }
                }
            }
        }

        Object[] v = {inicio, destino, peso};
        return v;
    }

    // setteamos el atributo visitado del vertice respectivo
    private void setVisitado(boolean valor) {

        for (int i = 0; i < contador; i++) {
            listaVertices[i].visitado = valor; // valor==true o false
        }
    }

    public String kruskal() throws GrafoException {

        boolean[][] aristasVisitadas = new boolean[matrizAdyacencia.length][matrizAdyacencia[0].length];

        if (isEmpty()) {
            throw new GrafoException("El grafo no existe.");
        }

        setVisitado(false);

        String resultado = "";

        int verticeRaiz = 0;
        int verticeDestino = 0;
        int pesoTotal = 0;
        int posicion = 0;

        int pesoMenor = Integer.MAX_VALUE;

        while (posicion < contador) {

            for (int i = 0; i < contador; i++) {

                for (int j = 0; j < contador; j++) {

                    // if ((int) matrizAdyacencia[i][j] > 0
                    // && (!listaVertices[i].visitado ||
                    // !listaVertices[j].visitado)) {
                    if ((int) matrizAdyacencia[i][j] > 0
                            && (!aristasVisitadas[i][j] || !aristasVisitadas[j][i])) {

                        if ((int) matrizAdyacencia[i][j] < pesoMenor) {

                            pesoMenor = (int) matrizAdyacencia[i][j];

                            verticeRaiz = i;

                            verticeDestino = j;

                        }
                    }
                }
            }

            // if (!listaVertices[verticeRaiz].visitado ||
            // !listaVertices[verticeDestino].visitado ) {
            if (!aristasVisitadas[verticeRaiz][verticeDestino]
                    && !aristasVisitadas[verticeDestino][verticeRaiz]) {

                resultado += "\nArista: " + listaVertices[verticeRaiz].elemento
                        + " -- "
                        + listaVertices[verticeDestino].elemento
                        + " --> Peso: "
                        + this.matrizAdyacencia[verticeRaiz][verticeDestino];

                aristasVisitadas[verticeRaiz][verticeDestino] = true;
                aristasVisitadas[verticeDestino][verticeRaiz] = true;

                pesoTotal += (int) matrizAdyacencia[verticeRaiz][verticeDestino];

                listaVertices[verticeRaiz].visitado = true;

                listaVertices[verticeDestino].visitado = true;

            }

            pesoMenor = Integer.MAX_VALUE;

            posicion++;

        }

        return resultado + "\nPeso final: " + pesoTotal;

    }

}//GrafoMatrizAdyacencia