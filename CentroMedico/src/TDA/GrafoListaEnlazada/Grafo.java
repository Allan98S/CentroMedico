/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.GrafoListaEnlazada;

import TDA.Colas.ColaException;
import TDA.Listas.ListaException;
import TDA.PilaLlenaException;
import TDA.PilaVaciaException;


public interface Grafo {
    //elimina todo el Grafo
	public void anular();
	// true si el grafo está vacío
	public boolean isEmpty()throws GrafoException;
	// devuelve el número de vértices que tiene el grafo
	public int getSize()throws GrafoException, ListaException; 
	// true si el vértice indicado forma parte del grafo
	public boolean existeVertice(Object elemento)throws GrafoException, ListaException;
	// true si existe una artista que une los dos vértices indicados
	public boolean existeArista(Object v1, Object v2)throws GrafoException, ListaException;
	//agrega un vértice al grafo
	public void agregaVertice(Object elemento)throws GrafoException, ListaException;
	//agrega una artista que permita unir dos vértices (el grafo es no dirigido)
	public void agregaArista(Object v1, Object v2)throws GrafoException, ListaException;
	//agrega peso a una artista que une dos vértices (el grafo es no dirigido)
	public void agregaPeso(Object v1, Object v2, Object peso)throws GrafoException, ListaException;
	
	//recorre el grafo utilizando el algoritmo de búsqueda en profundidad 
	public String dfs()throws GrafoException,PilaVaciaException, PilaLlenaException, ListaException;  //depth-first search
	//recorre el grafo utilizando el algoritmo de búsqueda en amplitud 
	public String bfs()throws GrafoException, ColaException, ListaException;  //breadth-first search
    
}
