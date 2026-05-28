/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1;

import javax.swing.SwingUtilities;

/**
 *
 * @author Nathan
 */
public class Main {
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnalizadorArchivos ventana = new AnalizadorArchivos();
            ventana.setVisible(true);
        });
    } 
}
