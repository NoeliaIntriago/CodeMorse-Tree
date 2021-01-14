/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Iterator;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Noelia Intriago
 */
public class RecorridoCodigo implements Runnable{

    private LinkedList<String> recorrido;
    private Pane pane;
    private double x;
    private double y;
    private int inicio;
    private double ANCHO = 650;
    private double ALTO = 60;
    
    public RecorridoCodigo(LinkedList<String> recorrido, Pane pane, int inicio){
        this.recorrido = recorrido;
        this.pane = pane;
        this.inicio = inicio;
        this.x = ANCHO;
        this.y = 200;
    }

    public LinkedList<String> getRecorrido() {
        return recorrido;
    }

    public Pane getPane() {
        return pane;
    }
    
    @Override
    public void run() {
        Platform.runLater(new CreadorRecorrido(this.pane, x, y));
        try{
            Thread.sleep(500);
            Iterator<String> it = recorrido.iterator();
            while(it.hasNext()){
                String code = it.next();
                for(int i = 0; i < code.length(); i++){
                    if(code.charAt(i) == '.'){
                        irNodoDerecho();
                        Platform.runLater(new CreadorRecorrido(this.pane, x, y));
                    }else if(code.charAt(i) == '-'){
                        irNodoIzquierdo();
                        Platform.runLater(new CreadorRecorrido(this.pane, x, y));
                    }else{
                        regresar();
                    }
                    Thread.sleep(500);
                }
            }
        }catch(InterruptedException ex){
            System.err.println(ex);
            Thread.currentThread().interrupt();
        }
        limpiar();
    }
    
    private void irNodoDerecho(){
        x += ANCHO/2;
        y += ALTO;
        ANCHO /= 2;
        playSound("/recursos/Punto.mpeg");
    }
    
    private void irNodoIzquierdo(){
        x -= ANCHO/2;
        y += ALTO;
        ANCHO /= 2;
        playSound("/recursos/Raya.mpeg");
    }
    
    private void regresar(){
        try{
            x = 650;
            y = 200;
            Thread.sleep(500);
        }catch(InterruptedException ex){
            System.err.println(ex);
            Thread.currentThread().interrupt();
        }
    }
    
    private void limpiar(){
        Platform.runLater(() -> pane.getChildren().remove(inicio, pane.getChildren().size()));
    }
    
    private void playSound(String audio) {
        Media sonido = new Media(this.getClass().getResource(audio).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sonido);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(1);
    }
}
