
package TDA.GrafoListaEnlazada;

import TDA.Colas.ColaArreglo;
import TDA.Colas.ColaException;
import TDA.Listas.ListaException;
import TDA.PilaEnlazada;
import TDA.PilaLlenaException;
import TDA.PilaVaciaException;
import java.io.Serializable;

public class GrafoListaAdyacencia implements Grafo, Serializable {
    //Atributos 
    private Vertice[] listaVertices;
    private int contador;
    private PilaEnlazada pila;
    private ColaArreglo cola;
    private int n;
    // private Vertice listaVertice[]; //ESPECIE DE ARREGLO DE VERTICES

    public GrafoListaAdyacencia(int n) {
        this.n = n;
        anular();
        this.pila = new PilaEnlazada();
        this.cola = new ColaArreglo(n);
    }//Constructor

    @Override
    public void anular() {
        this.listaVertices = new Vertice[n];
        this.contador=0;
    }//anular()

    @Override
    public boolean isEmpty() throws GrafoException {
        return contador == 0; 
    }//isEmpty()

    @Override
    public int getSize() throws GrafoException, ListaException {
        if (isEmpty()) {
            throw new GrafoException("EL GRAFO NO EXISTE");
        }
        return contador;
    }//getSize()

    @Override
    public boolean existeVertice(Object elemento) throws GrafoException, ListaException {
        if (isEmpty()) {
            throw new GrafoException("EL GRAFO NO EXISTE");
        }//if
        for (int i = 0; i < contador; i++) {
            if (listaVertices[i].elemento.equals(elemento)) {
                return true;
            }//if
        }//for
        return false;
    }//existeVertice()

    @Override
    public boolean existeArista(Object v1, Object v2) throws GrafoException, ListaException {
        if (!existeVertice(v1) || (!existeVertice(v2))) {
            throw new GrafoException("UNO DE LOS VERTICES NO EXISTE");
        }//if
            if (listaVertices[getPosicion(v1)].listaAristas.existe(v2)) {
                return true;
            }//if
        return false;
    }//existeArista()

    @Override
    public void agregaVertice(Object elemento) throws GrafoException, ListaException {
        if (contador >= listaVertices.length) {
            throw new GrafoException("EL GRAFO ESTA LLENO");
        }//if
        listaVertices[contador++] = new Vertice(elemento);
    }//agregaVertice()

    @Override
    public void agregaArista(Object v1, Object v2) throws GrafoException, ListaException {
        if (!existeVertice(v1) || (!existeVertice(v2))) {
            throw new GrafoException("UNO DE LOS VERTICES NO EXISTE");
        }//if
        this.listaVertices[getPosicion(v1)].listaAristas.insertar(v2);
        this.listaVertices[getPosicion(v2)].listaPesos.insertar(v1); 
        
        //PARA CADA VERTICE SE PREPARA EL MANEJO DE LOS PESOS
        this.listaVertices[getPosicion(v1)].listaPesos.insertar(v2);
        this.listaVertices[getPosicion(v2)].listaPesos.insertar(v1);
    }//agregaArista()

    @Override
    public void agregaPeso(Object v1, Object v2, Object peso) throws GrafoException, ListaException {
        if (!existeArista(v1, v2)) {
            throw new GrafoException("NO EXISTE ARISTA ENTRE " + v1 + " Y " + v2);
        }//if
            listaVertices[getPosicion(v1)].listaPesos.modificar(v2, peso);
            listaVertices[getPosicion(v2)].listaPesos.modificar(v1, peso);
   
    }//agregaPeso()

    @Override
    //RECORRIDO EN PROFUNDIDAD
    public String dfs() throws GrafoException, PilaVaciaException, PilaLlenaException, ListaException {
        String info =" ";
        setVisitado(false);//marca todos los vertices como no vistados
        // inicia en el vertice 0
         listaVertices[0].visitado = true; // lo marca
         info =muestraVertice(0)+" "; // Lo muestra
         pila.anular();
         pila.push(0); // lo inserta en la pila
         while( !pila.isEmpty() ){ // hasta que la pila este vacia
             // obtiene un vertice adyacente no visitado, el que esta en el tope de la pila
             int v = VerticeAdyacenteNOVisitado(Integer.parseInt(pila.top().toString()));
             if(v == -1) // no lo encontro
                 pila.pop();
             else{ // if, si existe
                 listaVertices[v].visitado = true; // lo marca
                 info += muestraVertice(v)+" "; // lo muestra
                 pila.push(v); // lo inserta
             }//fin de else
         } //while
         return info;
	}//dfs

@Override
    //RECORRIDO POR AMPLITUD
    public String bfs() throws GrafoException, ColaException, ListaException {
	String info=" ";
         // inicia en el vertice 0
         listaVertices[0].visitado = true; // lo marca
         info = muestraVertice(0)+" "; // lo muestra
         cola.anular();
         cola.encolar(0); // encola el elemento
         int v2;
         while(!cola.isEmpty()){ // hasta que la cola este vacia
             int v1 = Integer.parseInt(cola.desencolar().toString()); // remueve el vertice de la cola

             // hasta que no tenga vecinos sin visitar
             while( (v2=VerticeAdyacenteNOVisitado(v1)) != -1 ){
                 // obtiene uno
                 listaVertices[v2].visitado = true; // lo marca
                 info +=muestraVertice(v2)+" "; // lo muestra
                 cola.encolar(v2); // lo encola
             } // fin de while
         } // fin de while
    setVisitado(false);//marca todos los vertices como no visitados
    return info;
    }//bfs

    /**
     * *******************************METODOS ADICIONALES*************************************
     */
    private int getPosicion(Object vertice) {
        for (int i = 0; i < contador; i++) {
            if (listaVertices[i].elemento.equals(vertice)) {
                return i;
            }//if
        }//for
        return -1;
    }//getPosicion()
    
    private void setVisitado(boolean valor) {
        for (int i = 0; i < contador; i++) {
            listaVertices[i].visitado = valor; //valor==true o false
        }//for
    }
 

   private int VerticeAdyacenteNOVisitado(int posicionVertice) throws GrafoException, ListaException {
       Object nombreVertice = muestraVertice(posicionVertice);
        for (int i = 0; i < contador; i++) {
            if(!listaVertices[i].listaAristas.existe(nombreVertice) && !listaVertices[i].visitado)
                return i; //retorna la posicion i
        }//for i
        return -1;
    }

   private Object muestraVertice(int posicion) throws GrafoException {
        if(posicion<0||posicion>=contador){
            throw new GrafoException("No existe el vertice en el grafo");
        }
        return listaVertices[posicion].elemento;
    }

   public String toString() {
        String res = "\n----------------GRAFO LISTA DE ADYACENCIA----------------\n";
        for (int i = 0; i < contador; i++) {
            res += "\nEl vertice en posicion " + i + " es: " + listaVertices[i].elemento + "\n";

            if(!listaVertices[i].listaAristas.isEmpty()){
                res+="..........ARISTAS: " + listaVertices[i].listaAristas.toString()+"\n";
                res+="..........PESOS: " + listaVertices[i].listaPesos.toString()+"\n";
            }//if
        }//for
        return res;
    }//toString()
   
    public Grafo prim(Object verticeInicial) throws GrafoException, ListaException{
      
      GrafoListaAdyacencia nuevoGrafo = new GrafoListaAdyacencia(getSize());
      
      for (Vertice vertice : listaVertices) {
        
        if(vertice != null)
          nuevoGrafo.agregaVertice(vertice.elemento);
      }
      
      Vertice verticeActual = obtenervertice(verticeInicial);
      
      do{
        
        if(verticeActual != null){
          
          verticeActual.visitado = true;
          
          Object[] vector = aristaMenor();
          
          Object inicio = vector[0];
          Object destino = vector[1];
          int peso = (int) vector[2];
          
          if(inicio == null)
            break;
          
          nuevoGrafo.agregaArista(inicio, destino);
          nuevoGrafo.agregaPeso(inicio, destino, peso);
          
          verticeActual = obtenervertice(destino);
        }
      }while(!nuevoGrafo.verticesConectados());
      
      return nuevoGrafo;
      
    }

   private Object[] aristaMenor() {
    
    int peso = 1000000;
    Object inicio = null;
    Object destino = null;
    
    for (int i = 0; i < listaVertices.length; i++) {
     
        
        if(listaVertices[i] != null && listaVertices[i].visitado ){
          
      
            
            if( (int) listaVertices[i].elemento <= peso){
                if (!listaVertices[i].elemento.equals(0)) {
                    peso =  (int) listaVertices[i].elemento;
                    inicio = listaVertices[i].elemento;
                    //destino = listaVertices[j].elemento;
                }
            } else {
            }
          
        }
    }
   
     Object[] v = {inicio, destino, peso};
    return v;
  
   }
    
   private boolean verticesConectados() throws GrafoException, ListaException {
    
    int cont = 0;
    
    for (int i = 0; i < listaVertices.length; i++) {
      
      for (int j = 0; j < listaVertices.length; j++) {
        
        if(!listaVertices[i].elemento.equals(0)){
          
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
 
}
