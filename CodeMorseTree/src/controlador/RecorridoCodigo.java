/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
    private double ancho = 650;
    private double alto = 60;
    private double cambio = ancho/2;
    
    public RecorridoCodigo(List<String> recorrido, Pane pane, int inicio){
        this.recorrido = (LinkedList<String>) recorrido;
        this.pane = pane;
        this.inicio = inicio;
        this.x = ancho;
        this.y = 200;
    }
    
    public List<String> getRecorrido() {
        return recorrido;
    }

    public Pane getPane() {
        return pane;
    }
    
    @Override
    public void run() {
        try{
            Thread.sleep(600);
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
                        regresar(code, i);
                    }
                    Thread.sleep(600);
                }
            }
        }catch(InterruptedException ex){
            Logger.getLogger(RecorridoCodigo.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
        limpiar();
    }
    
    private void irNodoDerecho(){
        x += cambio;
        y += alto;
        cambio /= 2;
        playSound("/recursos/Punto.mpeg");
    }
    
    private void irNodoIzquierdo(){
        x -= cambio;
        y += alto;
        cambio /= 2;
        playSound("/recursos/Raya.mpeg");
    }
    
    private void regresar(String code, int i){
        try{
            if(code.charAt(i) == ' '){
                this.x = ancho;
                this.y = 200;
                this.cambio = ancho/2;
            }
            Thread.sleep(600);
        }catch(InterruptedException ex){
            Logger.getLogger(RecorridoCodigo.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
        limpiar();
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
