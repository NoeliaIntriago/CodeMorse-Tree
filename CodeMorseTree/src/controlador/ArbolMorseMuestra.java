/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static codemorsetree.CodeMorseTree.arbolMorse;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import tda.ArbolMorse;
import tda.ArbolMorse.Node;

/**
 *
 * @author Noelia Intriago
 */
public class ArbolMorseMuestra{
    private static final int DIAMETRO = 20;
    private static final int ANCHO = 650;
    private static final int ALTO = 60;
    private static ArbolMorse arbol;

    public ArbolMorseMuestra() {
        ArbolMorseMuestra.arbol = arbolMorse;
    }
    
    public void dibujar(Pane pane){
        pane.getChildren().clear();
        if (ArbolMorse.getRoot() != null) {
            dibujar(pane, ArbolMorse.getRoot(), ANCHO, 200, ANCHO/2);
        }
    }
    
    private void dibujar(Pane pane, Node n, double x, double y, double cambio) {
        Circle circle = new Circle(x, y, DIAMETRO);
        circle.setFill(Color.WHITE);
        if (n.getLeft() != null) {
            pane.getChildren().add(new Line(x - cambio, y + ALTO, x, y));
            dibujar(pane, n.getLeft(), x - cambio, y + ALTO, cambio/2);
        }
        if (n.getRight() != null) {
            pane.getChildren().add(new Line(x + cambio, y + ALTO, x, y));
            dibujar(pane, n.getRight(), x + cambio, y + ALTO, cambio/2);
        }
        circle.setStroke(Color.BLACK);
        pane.getChildren().addAll(circle, new Text(x - 5, y + 5, n.getData()));
    }
    
}
