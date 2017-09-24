/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Objetos.Cita;
import Objetos.Enfermedad;
import Objetos.Medico;
import Objetos.Paciente;
import TDA.ArbolBinarioSimple;
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

    public boolean registraMedico(String nombre, String apellidos, String cedula, String codDoctor) throws ListaException {
        boolean insertado = false;
        for (int i = 1; i < listaMedicos.getSize(); i++) {
            Medico medico = (Medico) listaMedicos.getNodo(i).elemento;
            if (!medico.getCedula().equalsIgnoreCase(cedula)) {
                listaMedicos.insertar(new Medico(nombre, apellidos, cedula, codDoctor));
                insertado = true;
                break;
            }
        }
        return insertado;
    }

    public boolean iniciarSeccion(String codDoctor) throws ListaException {
        boolean existe = false;
        for (int i = 1; i < listaMedicos.getSize(); i++) {
            Medico medico = (Medico) listaMedicos.getNodo(i).elemento;
            if (medico.getCodDoctor().equalsIgnoreCase(codDoctor)) {

                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean agregaPaciente(String nombre, String apellidos, String cedula, String contrasena) throws ListaException {
        boolean insertado = false;
        if (listaPacientes.isEmpty()) {
            listaPacientes.insertar(new Paciente(nombre, apellidos, contrasena, cedula, new ListaEnlazada()));
        } else {
            for (int i = 1; i < listaPacientes.getSize(); i++) {
                Paciente paciente = (Paciente) listaPacientes.getNodo(i).elemento;
                if (!paciente.getCedula().equalsIgnoreCase(cedula)) {
                    listaPacientes.insertar(new Paciente(nombre, apellidos, contrasena, cedula,new ListaEnlazada()));
                    insertado = true;
                }
            }
        }
        return insertado;
    }

    public void modificaPaciente(Paciente paciente) throws ListaException {
        for (int i = 1; i < listaPacientes.getSize(); i++) {
            Paciente pacienteAux = (Paciente) listaPacientes.getNodo(i).elemento;
            if (pacienteAux.getCedula().equalsIgnoreCase(paciente.getCedula())) {
                listaPacientes.suprimir(pacienteAux);
                listaPacientes.insertar(paciente);
                System.out.println("Paciente modificado con exito");
                break;
            }
        }
        System.out.println("El paciente no existe");
    }

    public String buscarPaciente(Paciente paciente) throws ListaException {
        String salida = "";
        if (existePaciente(paciente.getCedula())) {
            salida += "\nPACIENTE: " + paciente.toString();
        } else {
            salida = "No existe el paciente";
        }
        return salida;
    }

    public void darDeAlta(Paciente paciente, Enfermedad enfermedad) throws ListaException {
        ListaEnlazada listaAux = paciente.getEnfermedades();
        if (listaAux.getSize() == 0) {
            System.out.println("El paciente no tiene enfermedades");
        } else {
            for (int i = 1; i < listaAux.getSize(); i++) {
                Enfermedad enfermedadAux = (Enfermedad) listaAux.getNodo(i).elemento;
                if (enfermedad.getNombre().equalsIgnoreCase(enfermedadAux.getNombre())) {
                    listaAux.suprimir(enfermedadAux);
                    System.out.println("Paciente dado de alta de la enfermedad " + enfermedadAux.getNombre());
                    break;
                }
            }
            System.out.println("El paciente no tiene la enfermedad "+enfermedad.getNombre());        
        }
    }

    private boolean existePaciente(String cedula) throws ListaException {
        boolean existe = false;
        for (int i = 1; i < listaPacientes.getSize(); i++) {
            Paciente paciente = (Paciente) listaPacientes.getNodo(i).elemento;
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean agregaCita(String cedulaPaciente, Date fecha, String descripcion, String cedulaDoctor) throws ListaException {
        boolean insertado = false;
        for (int i = 1; i < listaCitas.getSize(); i++) {
            Cita cita = (Cita) listaCitas.getNodo(i).elemento;
            if (!util.formatoFecha(cita.getFecha()).equalsIgnoreCase(util.formatoFecha(fecha)) && cedulaPaciente.equalsIgnoreCase(cita.getCedulaPaciente())) {
                listaCitas.insertar(new Cita(descripcion, fecha, cedulaPaciente, cedulaDoctor));
                insertado = true;
                //mandar mensaje o correo de confirmaacion
                break;
            }
        }
        return insertado;
    }

    public String verCitas() throws ListaException {
        String citas = "CITAS ACTUALES DEL SISTEMA\n";

        for (int i = 1; i < listaCitas.getSize(); i++) {
            Cita cita = (Cita) listaCitas.getNodo(i).elemento;
            citas += "\n" + cita.toString();
        }
        return citas;
    }
}
