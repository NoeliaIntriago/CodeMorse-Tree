/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static codemorsetree.CodeMorseTree.arbolMorse;
import java.awt.Color;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tda.ArbolMorse;

/**
 * FXML Controller class
 *
 * @author Noelia Intriago
 */
public class VistaMorseController implements Initializable {
    @FXML
    private TextField txtPalabra;
    @FXML
    private Button btnTraducir;
    @FXML
    private Button btnBorrar;
    @FXML
    private Pane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArbolMorseMuestra arbolMuestra = new ArbolMorseMuestra();
        arbolMuestra.dibujar(pane);
    } 
    
    public void traducir(ActionEvent event){
        if(txtPalabra.getText().isEmpty()) mostrarAlerta("Campo vacío! Ingrese por lo menos un caracter!", Alert.AlertType.ERROR);
        else{
            String palabra = txtPalabra.getText().toUpperCase();
            System.out.println(palabra);
            LinkedList<String> traduccion = ArbolMorse.codificarPalabra(palabra);
            System.out.println(traduccion);
        }
    }
    
    public void borrarTexto(ActionEvent event){
        txtPalabra.clear();
    }
    
    public void mostrarAlerta(String mensaje, Alert.AlertType e){
        Alert alert = new Alert(e);
        alert.setTitle(" ");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
