/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
        // TODO
    } 
    
    public void traducir(ActionEvent event){
        if(txtPalabra.getText().isEmpty()) mostrarAlerta("Campo vacío! Ingrese por lo menos un caracter!", Alert.AlertType.ERROR);
        
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
