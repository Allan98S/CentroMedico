/** 
 * File     : Nodo.java
 * @author  : Prof. Gilberth Chaves Avila
 * Date     : 2017-05-09
 */
package TDA.Colas;

import java.io.Serializable;

public class Nodo implements Serializable{//nodo colas
    //Atributos
    public Object elemento;
    public Nodo sgte,ant;
    
    //Constructor
    public Nodo(Object elemento){
        this.elemento=elemento;
        this.sgte=null;
        this.ant = this.sgte = null;
    }
    
    //RECARGAMOS EL CONSTRUCTOR PARA EL NODO CABECERA
    //NODO CABECERA == NODO VACIO
    public Nodo(){
        this.sgte=null;
        //es para la implementacion de la cola con nodo cabecera
    }

    @Override
    public String toString() {
        return (String) elemento;
    }
    
    
}
