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
    public static final int DIAMETRO = 20;
    public static final int RADIO = DIAMETRO/2;
    public static final int ANCHO = 50;
    private static ArbolMorse arbol;

    public ArbolMorseMuestra() {
        ArbolMorseMuestra.arbol = arbolMorse;
    }
    
    public void dibujar(Pane pane){
        pane.getChildren().clear();
        if (ArbolMorse.getRoot() != null) {
            dibujar(pane, ArbolMorse.getRoot(), pane.getWidth(), ANCHO);
        }
    }
    
    private void dibujar(Pane pane, Node n, double x, double y) {
        Circle circle = new Circle(x, y, DIAMETRO);
        circle.setFill(Color.WHITE);
        if (n.getLeft() != null) {
            pane.getChildren().add(new Line(x - ANCHO, y + ANCHO, x, y));
            dibujar(pane, n.getLeft(), x - ANCHO, y + ANCHO);
        }
        if (n.getRight() != null) {
            pane.getChildren().add(new Line(x + ANCHO, y + ANCHO, x, y));
            dibujar(pane, n.getRight(), x + ANCHO, y + ANCHO);
        }
        circle.setStroke(Color.BLACK);
        pane.getChildren().addAll(circle, new Text(x - 4, y + 4, n.getData()));
    }
    
}
