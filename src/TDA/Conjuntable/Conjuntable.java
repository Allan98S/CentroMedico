/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import TDA.Listas.ListaException;

/**
 *
 * @author gichav
 */
public interface Conjuntable {

	public int getSize() throws ConjuntoException, ListaException; // Devuelve
																   // el numero
																   // de
																   // elementos
																   // en el
																   // conjunto

	public void anular(); // Elimina todo el conjunto

	public boolean isEmpty(); // true si el conjunto esta vacio

	public int getPosicion(Object elemento) throws ConjuntoException,
	        ListaException; // devuelve la posicion de un elemento en el
							// conjunto

	public Object getElemento(int posicion) throws ConjuntoException,
	        ListaException; // devuelve el elemento de la posicion indicada

	public void insertar(Object elemento) throws ConjuntoException,
	        ListaException; // Agrega un elemento en el conjunto

	public void suprimir(Object elemento) throws ConjuntoException,
	        ListaException; // Suprime un elemento del conjunto

	public boolean existe(Object elemento) throws ConjuntoException,
	        ListaException; // true si el elemento existe en el conjunto

	// Operaciones clasicas: union, interseccion, diferencia

	public Conjuntable union(Conjuntable a, Conjuntable b)
	        throws ConjuntoException, ListaException;

	public Conjuntable interseccion(Conjuntable a, Conjuntable b)
	        throws ConjuntoException, ListaException;

	public Conjuntable diferencia(Conjuntable a, Conjuntable b)
	        throws ConjuntoException, ListaException;
}
