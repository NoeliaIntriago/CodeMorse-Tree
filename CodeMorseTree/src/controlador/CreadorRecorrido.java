/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Noelia Intriago
 */
public class CreadorRecorrido implements Runnable {
    private double x;
    private double y;
    private Pane pane;
    private static final int DIAMETRO = 20;

    public CreadorRecorrido(Pane pane, double x, double y) {
        this.pane = pane;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        Circle cTrace = new Circle(x, y, DIAMETRO);
        cTrace.setFill(Color.RED);
        cTrace.setStroke(Color.BLACK);
        pane.getChildren().add(cTrace);
    }  
}
