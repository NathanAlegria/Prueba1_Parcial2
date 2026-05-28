/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nathan
 */

public class AnalizadorLogica {

    private int contTxt, contJava, contPdf, contOtros;

    public String contarArchivosPorExtension(File dirRaiz) {
        contTxt = contJava = contPdf = contOtros = 0;
        recorrerDirectorio(dirRaiz);
        int total = contTxt + contJava + contPdf + contOtros;
        return "TXT   : " + contTxt   + " archivos\n"
             + "JAVA  : " + contJava  + " archivos\n"
             + "PDF   : " + contPdf   + " archivos\n"
             + "OTROS : " + contOtros + " archivos\n"
             + "─────────────────\n"
             + "TOTAL : " + total     + " archivos\n";
    }

    private void recorrerDirectorio(File directorio) {
        File[] contenido = directorio.listFiles();
        if (contenido == null) return;
        for (File elemento : contenido) {
            if (elemento.isFile()) clasificarArchivo(elemento);
            else if (elemento.isDirectory()) recorrerDirectorio(elemento);
        }
    }

    private void clasificarArchivo(File archivo) {
        String ext = obtenerExtension(archivo.getName().toLowerCase());
        switch (ext) {
            case "txt"  -> contTxt++;
            case "java" -> contJava++;
            case "pdf"  -> contPdf++;
            default     -> contOtros++;
        }
    }

    private String obtenerExtension(String nombre) {
        int idx = nombre.lastIndexOf('.');
        return (idx == -1 || idx == nombre.length() - 1) ? "" : nombre.substring(idx + 1);
    }

    public String buscarArchivosPorNombre(File dirRaiz, String textoBusq) {
        List<String> encontrados = new ArrayList<>();
        buscarRecursivo(dirRaiz, textoBusq.toLowerCase(), encontrados);
        if (encontrados.isEmpty())
            return "No se encontraron archivos que coincidan con el texto.";
        StringBuilder sb = new StringBuilder("Resultados para \"" + textoBusq + "\":\n\n");
        for (String ruta : encontrados) sb.append(ruta).append("\n");
        return sb.toString();
    }

    private void buscarRecursivo(File directorio, String textoBusq, List<String> encontrados) {
        File[] contenido = directorio.listFiles();
        if (contenido == null) return;
        for (File elemento : contenido) {
            if (elemento.isFile()) {
                if (elemento.getName().toLowerCase().contains(textoBusq))
                    encontrados.add(elemento.getAbsolutePath());
            } else if (elemento.isDirectory()) {
                buscarRecursivo(elemento, textoBusq, encontrados);
            }
        }
    }
}
