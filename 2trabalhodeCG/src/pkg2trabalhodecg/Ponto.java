/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2trabalhodecg;

/**
 * Classe ponto para guardar os valores(x e y) recolhidos ao clique do mouse 
 * @author Guilherme
 */
public class Ponto {
    /**
     * Posicoes x e y do clique
     */
    private int x;
    private int y;
    Ponto (int x, int y){
        this.x = x;
        this.y = y;
    }
    int getX(){
        return this.x;
    }
     int getY(){
        return this.y;
    }
}
