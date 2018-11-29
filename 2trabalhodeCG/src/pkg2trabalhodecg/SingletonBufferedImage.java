/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2trabalhodecg;

import java.awt.image.BufferedImage;

/**
 * Classe usada para garantir a existencia de apenas uma instancia do BufferedImage
 * @author Guilherme
 */
public class SingletonBufferedImage {
    public static BufferedImage bfi;
    private SingletonBufferedImage (){}
    public static  BufferedImage getBuffer(){
        if(bfi == null){
            bfi = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
        }
        return bfi;
    }
}
