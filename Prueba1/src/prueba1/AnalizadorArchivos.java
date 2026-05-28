/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 *
 * @author Nathan
 */

public class AnalizadorArchivos extends JFrame {

    private JTextField txtRuta;
    private JTextField txtBusqueda;
    private JTextArea txtResultados;
    private JButton btnSeleccionar;
    private JButton btnAnalizar;
    private JButton btnBuscar;

    private AnalizadorLogica logica = new AnalizadorLogica();

    public AnalizadorArchivos() {
        setTitle("Analizador de Sistema de Archivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel panelEntrada = new JPanel(new GridLayout(4, 1, 4, 4));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));

        JPanel filaRuta = new JPanel(new BorderLayout(4, 0));
        filaRuta.add(new JLabel("Directorio:"), BorderLayout.WEST);
        txtRuta = new JTextField();
        filaRuta.add(txtRuta, BorderLayout.CENTER);
        btnSeleccionar = new JButton("Examinar");
        filaRuta.add(btnSeleccionar, BorderLayout.EAST);

        JPanel filaBusqueda = new JPanel(new BorderLayout(4, 0));
        filaBusqueda.add(new JLabel("Buscar:     "), BorderLayout.WEST);
        txtBusqueda = new JTextField();
        filaBusqueda.add(txtBusqueda, BorderLayout.CENTER);

        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btnAnalizar = new JButton("Analizar por extensión");
        btnBuscar   = new JButton("Buscar archivos");
        filaBotones.add(btnAnalizar);
        filaBotones.add(btnBuscar);

        panelEntrada.add(new JLabel("Analizador de Sistema de Archivos"));
        panelEntrada.add(filaRuta);
        panelEntrada.add(filaBusqueda);
        panelEntrada.add(filaBotones);

        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 13));

        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        btnSeleccionar.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                txtRuta.setText(fc.getSelectedFile().getAbsolutePath());
        });

        btnAnalizar.addActionListener(e -> {
            if (!validar()) return;
            txtResultados.setText(logica.contarArchivosPorExtension(new File(txtRuta.getText().trim())));
        });

        btnBuscar.addActionListener(e -> {
            if (!validar()) return;
            if (txtBusqueda.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un texto de búsqueda.");
                return;
            }
            txtResultados.setText(logica.buscarArchivosPorNombre(
                new File(txtRuta.getText().trim()), txtBusqueda.getText().trim()));
        });
    }

    private boolean validar() {
        String ruta = txtRuta.getText().trim();
        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una ruta.");
            return false;
        }
        File f = new File(ruta);
        if (!f.exists()) {
            JOptionPane.showMessageDialog(this, "La ruta no existe.");
            return false;
        }
        if (!f.isDirectory()) {
            JOptionPane.showMessageDialog(this, "La ruta no es un directorio.");
            return false;
        }
        return true;
    }

}