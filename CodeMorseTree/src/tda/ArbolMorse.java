/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import static codemorsetree.CodeMorseTree.codigos;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Noelia Intriago
 */
public class ArbolMorse {
    private static Node root;

    public ArbolMorse() {
        ArbolMorse.root = new Node("");
    }

    public static Node getRoot() {
        return root;
    }
    
    /*Izquierdo: guion, Derecho: punto*/
    public class Node{
        private String data;
        private Node right;
        private Node left;

        public Node(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }
     
    }
    
    public boolean isEmpty(){
        return root == null;
    }
        
    public void crearArbolMorse(HashMap<String, String> mapa){
        for(String clave: mapa.keySet()){
            String codigo = mapa.get(clave);
            crearArbolMorse(codigo, clave, root); 
        }
    }
    
    private void crearArbolMorse(String codigo, String letra, Node n){
        for(int i = 0; i < codigo.length(); i++){
            char c = codigo.charAt(i);
            if(c == '-'){
                if(n.left != null) n = n.left;
                else{
                    n.left = new Node("");
                    n = n.left;
                }
            }else if(c == '.'){
                if(n.right != null) n = n.right;
                else{
                    n.right = new Node("");
                    n = n.right;
                }
            }
        }
        n.data = letra;
    }
    
    public static LinkedList<String> codificarPalabra(String palabra){
        return codificarPalabra(codigos, palabra, root);
    }
    
    private static LinkedList<String> codificarPalabra(HashMap<String, String> morseCodes, String palabra, Node n){
        LinkedList<String> traduccion = new LinkedList<>();
        for(int i = 0; i < palabra.length(); i++){
            String code = morseCodes.get(String.valueOf(palabra.charAt(i)));
            traduccion.add(code);
            traduccion.add(" ");
        }
        return traduccion;
    }
    /*public String codificarMorse(List<String> codigos, ArbolMorse arbol){
        return codificarMorse(codigos, arbol.root);
    }
    
    private String codificarMorse(List<String> codigos, Node n){
        String resultado = "";
        for(String cod: codigos){
            if(cod.equals(".")){
                if(n.right != null) n = n.right;
                else{
                    n.right = new Node("");
                    n = n.right;
                }
            }else if(cod.equals("-")){
                if(n.left != null)n = n.left;
                else{
                    n.left = new Node("");
                    n = n.left;
                }
            }
            if(!n.data.equals("")){
                resultado += n.data;
                n = root;
            } 
        }
        return resultado;
    }*/
    
    public void anchura(){
        if(!isEmpty()){
            Queue<Node> cola = new LinkedList<>();
            cola.offer(root);
            while(!cola.isEmpty()){
                Node n = cola.poll();
                System.out.print(n.data+"-\n");
                if(n.left != null)cola.offer(n.left);
                if(n.right != null)cola.offer(n.right);
            }
        }
        System.out.println("");
    }
    
}
