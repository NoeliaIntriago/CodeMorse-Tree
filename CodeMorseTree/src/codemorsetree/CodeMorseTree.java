/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codemorsetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tda.ArbolMorse;

/**
 *
 * @author Noelia Intriago
 */

public class CodeMorseTree extends Application{
    public static HashMap<String, String> codigos = new HashMap<>();
    public static ArbolMorse arbolMorse = new ArbolMorse();
    
    public static void main(String[] args) {
        leerArchivo();
        arbolMorse.crearArbolMorse(codigos);
        arbolMorse.anchura();
        launch();
    }
    
    public static void leerArchivo(){
        try{
            List<String> lineas = Files.readAllLines(Paths.get("src/recursos/traducciones.txt"));
            for (String l: lineas){
                String[] separado = l.split("\\|");
                StringBuilder clave = new StringBuilder();
                int i = 1;
                while(i < separado.length){
                    clave.append(separado[i]);
                    i++;
                }
                codigos.put(separado[0], clave.toString());
            }
        }catch(IOException e){
            System.err.println("Archivo no encontrado.");
            System.err.println(e);
        }
    }

    @Override
    public void start(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaMorse.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Traductor Código Morse");
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(CodeMorseTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
