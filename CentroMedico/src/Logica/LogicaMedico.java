/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Objetos.Cita;
import Objetos.Enfermedad;
import Objetos.Factura;
import Objetos.Medico;
import Objetos.Paciente;
import TDA.ArbolBinarioSimple;
import TDA.Colas.ColaException;
import TDA.Colas.ColaEnlazadaHeader;
import TDA.GrafoListaEnlazada.GrafoListaEnlazada;
import TDA.Listas.ListaCircular;
import TDA.Listas.ListaEnlazada;
import TDA.Listas.ListaEnlazadaDoble;
import TDA.Listas.ListaException;
import TDA.Utilidades;
import java.util.Date;

/**
 *
 * @author allan
 */
public class LogicaMedico {

    Utilidades util;
    static ListaEnlazada listaMedicos;
    static ListaEnlazadaDoble listaPacientes;
    static ListaCircular listaCitas;
    static ArbolBinarioSimple arbolHistorialPaciente;
    static GrafoListaEnlazada grafoReceta;
    static ListaEnlazada listaControles;
    static ColaEnlazadaHeader colaFacturas;

    public LogicaMedico() {
        listaMedicos = new ListaEnlazada();
        listaPacientes = new ListaEnlazadaDoble();
        arbolHistorialPaciente = new ArbolBinarioSimple();
        grafoReceta = new GrafoListaEnlazada();
        listaControles = new ListaEnlazada();
        colaFacturas = new ColaEnlazadaHeader();
        listaCitas = new ListaCircular();
    }

    public static ListaEnlazada getListaMedicos() {
        return listaMedicos;
    }

    public static ListaEnlazadaDoble getListaPacientes() {
        return listaPacientes;
    }

    public static ListaCircular getListaCitas() {
        return listaCitas;
    }

    public static ArbolBinarioSimple getArbolHistorialPaciente() {
        return arbolHistorialPaciente;
    }

    public static GrafoListaEnlazada getGrafoReceta() {
        return grafoReceta;
    }

    public static ListaEnlazada getListaControles() {
        return listaControles;
    }

    public static ColaEnlazadaHeader getColaFacturas() {
        return colaFacturas;
    }

    public void registraMedico(Medico medico) throws ListaException {
        if (listaMedicos.isEmpty()) {
            listaMedicos.insertar(medico);
        } else if (!existeMedico(medico.getCedula())) {
            listaMedicos.insertar(medico);

        } else {
            System.out.println("Ya existe el medico");
        }

    }

    private boolean existeMedico(String cedula) throws ListaException {
        boolean existe = false;
        for (int i = 1; i <= listaMedicos.getSize(); i++) {
            Medico medico = (Medico) listaMedicos.getNodo(i).elemento;
            if (medico.getCedula().equalsIgnoreCase(cedula)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    private boolean loginMedico(String codDoctor) throws ListaException {
        boolean existe = false;
        for (int i = 1; i <= listaMedicos.getSize(); i++) {
            Medico medico = (Medico) listaMedicos.getNodo(i).elemento;
            if (medico.getCodDoctor().equalsIgnoreCase(codDoctor)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean iniciarSeccion(String codDoctor) throws ListaException {
        boolean existe = false;

        if (loginMedico(codDoctor)) {

            existe = true;

        }
        return existe;
    }

    public void agregaPaciente(Paciente paciente) throws ListaException {

        if (listaPacientes.isEmpty()) {
            listaPacientes.insertar(paciente);

        } else if (!existePaciente(paciente.getCedula())) {

            listaPacientes.insertar(paciente);

        } else {
            System.out.println("No se puedo insertar");
        }
    }

    public boolean modificaPaciente(Paciente paciente, Paciente pacienteNuevo) throws ListaException {
        boolean modificado = false;
        for (int i = 1; i <= listaPacientes.getSize(); i++) {
            Paciente pacienteAux = (Paciente) listaPacientes.getNodo(i).elemento;
            if (pacienteAux.getCedula().equalsIgnoreCase(paciente.getCedula())) {
                listaPacientes.modificarPosicion(i, pacienteNuevo);

                modificado = true;
                break;
            }
        }

        return modificado;
    }

    public String buscarPaciente(String cedula) throws ListaException {
        String salida = "";
        if (existePaciente(cedula)) {
            salida += "Se encontro el paciente con cedula : " + cedula + " \n" + buscaPacientePorCedula(cedula).toString();
        } else {
            salida = "No existe el paciente";
        }
        return salida;
    }

    public void darDeAlta(Paciente paciente, Enfermedad enfermedad) throws ListaException {
        String mensaje = "El paciente no tiene la enfermedad " + enfermedad.getNombre();
        ListaEnlazada listaAux = paciente.getEnfermedades();
        if (listaAux.getSize() == 0) {
            System.out.println("El paciente no tiene enfermedades");
        } else {
            for (int i = 1; i <= listaAux.getSize(); i++) {
                Enfermedad enfermedadAux = (Enfermedad) listaAux.getNodo(i).elemento;
                if (enfermedad.getNombre().equalsIgnoreCase(enfermedadAux.getNombre())) {
                    listaAux.suprimir(enfermedadAux);
                    mensaje = "Paciente dado de alta de la enfermedad " + enfermedadAux.getNombre();

                    break;
                }
            }
            System.out.println(mensaje);
        }
    }

    private Paciente buscaPacientePorCedula(String cedula) throws ListaException {
        for (int i = 1; i <= listaPacientes.getSize(); i++) {
            Paciente paciente = (Paciente) listaPacientes.getNodo(i).elemento;
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                return paciente;

            }
        }
        return null;

    }

    private boolean existePaciente(String cedula) throws ListaException {
        boolean existe = false;
        for (int i = 1; i <= listaPacientes.getSize(); i++) {
            Paciente paciente = (Paciente) listaPacientes.getNodo(i).elemento;
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean agregaCita(Cita cita) throws ListaException {
        boolean insertado = false;
        if (listaCitas.isEmpty()) {
            listaCitas.insertar(cita);
        }
        if (!existeCita(cita.getId())) {
            listaCitas.insertar(cita);
            insertado = true;
        }

        return insertado;
    }

    private boolean existeCita(int id) throws ListaException {
        boolean existe = false;
        for (int i = 1; i <= listaCitas.getSize(); i++) {
            Cita cita = (Cita) listaCitas.getNodo(i).elemento;
            if (cita.getId() == (id)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public void agregaFactura(Factura factura) throws ColaException{
        if(colaFacturas.isEmpty()){
            colaFacturas.encolar(factura);
        }
        else if(!existeFactura(factura)){
            colaFacturas.encolar(factura);
        }
        else{
            System.out.println("No se pudo insertar");
        }
    }
    
    private boolean existeFactura(Factura factura) throws ColaException{
       return colaFacturas.existe(factura);
    }
    

    public String verCitas() throws ListaException {
        String citas = "CITAS ACTUALES DEL SISTEMA\n";

        for (int i = 1; i <= listaCitas.getSize(); i++) {
            Cita cita = (Cita) listaCitas.getNodo(i).elemento;
            citas += "\n" + cita.toString();
        }
        return citas;
    }

    public String muestraPacientes() throws ListaException {

        String salida = "PACIENTES ACTUALES EN EL SISTEMA\n";
        salida += listaPacientes.toString();

        return salida;
    }
}
