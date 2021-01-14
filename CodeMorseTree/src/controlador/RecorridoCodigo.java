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
import javafx.scene.shape.Circle;

/**
 *
 * @author Noelia Intriago
 */
public class RecorridoCodigo implements Runnable{

    public LinkedList<String> recorrido;
    public Pane pane;
    private double x;
    private double y;
    public static final int DIAMETRO = 20;
    public int ANCHO = 650;
    public int ALTO = 60;
    
    public RecorridoCodigo(LinkedList<String> recorrido, Pane pane){
        this.recorrido = recorrido;
        this.pane = pane;
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
                    if(code.charAt(i) == '.')
                        irNodoDerecho();
                    else if(code.charAt(i) == '-')
                        irNodoIzquierdo();
                }
                Platform.runLater(new CreadorRecorrido(this.pane, x, y));
                Thread.sleep(500);
            }
        }catch(InterruptedException ex){
            System.err.println(ex);
        }
        
    }
    
    private void irNodoDerecho(){
        x += ANCHO/2;
        y += ALTO;
        ANCHO /= 2;
        playSound("/recursos/Punto.mpeg");
    }
    
    private void irNodoIzquierdo(){
        x -= ANCHO;
        y += ALTO;
        ANCHO /= 2;
        playSound("/recursos/Raya.mpeg");
    }
    
    private void playSound(String audio) {
        Media sonido = new Media(this.getClass().getResource(audio).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sonido);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(1);
    }
}
