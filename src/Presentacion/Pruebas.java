/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.LogicaMedico;
import javax.swing.JOptionPane;

/**
 *
 * @author allan
 */
public class Pruebas {
    Logica.LogicaMedico lo=new LogicaMedico();
    
    public void insertarClientes(){
        String nombre= JOptionPane.showInputDialog("Ingrese el nombre del paciente");
        String cedula= JOptionPane.showInputDialog("Ingrese la cedula del paciente");
    }
}
