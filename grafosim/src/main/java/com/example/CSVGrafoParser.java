package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;

public class CSVGrafoParser {
    
    public HashMap<String, Nodo> createNodesFromCSV(String filename) {
    HashMap<String, Nodo> nodes = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String nodeName1 = values[0].trim();
            String nodeName2 = values[1].trim();
            int nodeValue1 = Integer.parseInt(values[2].trim());
            int nodeValue2 = Integer.parseInt(values[3].trim());
            int edgeWeight = Integer.parseInt(values[4].trim());

            Nodo node1 = nodes.getOrDefault(nodeName1, new Nodo(nodeName1, nodeValue1));
            Nodo node2 = nodes.getOrDefault(nodeName2, new Nodo(nodeName2, nodeValue2));

            node1.crear_arista(node2, edgeWeight);

            nodes.put(nodeName1, node1);
            nodes.put(nodeName2, node2);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        return nodes;
    }

    public String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }

    public HashMap<String,Nodo> load_graph(){
        CSVGrafoParser parser = new CSVGrafoParser();
        String filename = parser.chooseFile();
        HashMap<String, Nodo> nodes = parser.createNodesFromCSV(filename);
        return nodes;
    }
    
}
