/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.LogicaMedico;
import Objetos.Asistente;
import Objetos.Enfermedad;
import Objetos.Factura;
import Objetos.Medico;
import Objetos.Paciente;
import Objetos.ServicioMedico;
import TDA.Colas.ColaEnlazadaHeader;
import TDA.Colas.ColaException;
import TDA.Listas.ListaEnlazada;
import TDA.Listas.ListaException;
import javax.swing.JOptionPane;

/**
 *
 * @author allan
 */
public class Pruebas {

    LogicaMedico lo = new LogicaMedico();
    static ListaEnlazada listaEnfermedades = new ListaEnlazada();
    static ColaEnlazadaHeader cola=new ColaEnlazadaHeader();
    public void pruebaPacientes() throws ListaException {
        listaEnfermedades.insertar(new Enfermedad("Sifilis", "Benaria"));
        listaEnfermedades.insertar(new Enfermedad("Cancer", "Genetica"));
        Paciente paciente = new Paciente("Allan", "Solano", "aj", "117040783", listaEnfermedades);
        Paciente paciente2 = new Paciente("Sam", "Sampaoli", "ds", "30202827", listaEnfermedades);
        lo.agregaPaciente(paciente);
        lo.agregaPaciente(paciente2);
        System.out.println(lo.muestraPacientes());
        // System.out.println("----------MODIFICAMOS EL PACIENTE 2---------------\n");
        if (lo.modificaPaciente(paciente2, new Paciente("Dean", "Fioli", "DW", "291818312", listaEnfermedades))) {
            //   System.out.println("PACIENTE MODIFICADO");
            //   System.out.println(lo.muestraPacientes());
        } else {
            //   System.out.println("NO SE MODIFICO");
        }
        System.out.println(lo.buscarPaciente("117040783"));
        lo.darDeAlta(paciente, new Enfermedad("Cancer", "Genetica"));
        System.out.println("Enfermedades actuales "+paciente.getEnfermedades().toStringHorizontal());
    }
    
    public void pruebaCitas(){
        
    }
    public void pruebaFacturas() throws ColaException{
        Medico m=new Medico("Castiel", "Soto", "1291033", "IF12");
        Asistente a= new Asistente("Uriel","Perez","920192","43vd");
        Paciente paciente = new Paciente("Allan", "Solano", "aj", "117040783", listaEnfermedades);
        ServicioMedico s=new ServicioMedico("Quimioterapia", 243423);
        Factura f1=new Factura(m,a,s,false, paciente);
        cola.encolar(f1);
       
    }
    
    
}
