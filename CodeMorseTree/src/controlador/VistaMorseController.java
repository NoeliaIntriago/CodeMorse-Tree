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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tda.ArbolMorse;
import tda.ArbolMorse.Node;

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
        System.out.println(pane.getWidth());
        System.out.println(pane.getHeight());
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
    
    /*public void dibujar(){
        System.out.println(pane.getWidth());
        System.out.println(pane.getHeight());
        pane.setStyle("-fx-background-color: white;");
        dibujarArbol(pane, pane.getWidth()/2, 20, ArbolMorse.getRoot());
        /*Circle circle = new Circle(50, Paint.valueOf("BLUE"));
        circle.relocate(20, 20);
        Rectangle rectangle = new Rectangle(100,100, Paint.valueOf("RED"));
        rectangle.relocate(70,70);
        pane.getChildren().addAll(circle,rectangle);
    }
    
    private void dibujarArbol(Pane pane, double x, double y, Node n){
        if(n != null){
            Circle circle = new Circle(x, y, RADIO, Paint.valueOf("WHITE"));
            circle.setStroke(Paint.valueOf("BLACK"));
            pane.getChildren().addAll(circle, new Text(x - 4, y + 4, n.getData()));
        }
    }*/
    
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
