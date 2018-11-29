/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2trabalhodecg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Classe para controlar o PANEL
 * @author Guilherme
 */
public class PainelImagem extends JPanel implements MouseListener, MouseMotionListener {
    BufferedImage bfi = SingletonBufferedImage.getBuffer();
    //Lista de pontos do clique do mouse
    ArrayList<Ponto>pontos = new ArrayList<>();
    //lista de pontos calculados para bezier
    ArrayList<Ponto>pontosB = new ArrayList<>();
    Graphics2D g2d = null;
    public PainelImagem(){
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        limparTela();
    }
    /**
     * Metodo para mostrar na tela os pontos clicados sendo o inicial e final em Vermelho
     * @param graphics 
     */
    public void desenhaPontos(Graphics2D graphics) {
        for (int i = 0; i < pontos.size(); i++) {
            if(i == 0){
                graphics.setColor(Color.red);
                graphics.fillOval((int) pontos.get(i).getX(), (int) pontos.get(i).getY(), 5, 5);
            }else if(i == pontos.size()-1){
                graphics.setColor(Color.red);
                graphics.fillOval((int) pontos.get(i).getX(), (int) pontos.get(i).getY(), 5, 5);
            }else{
                graphics.setColor(Color.black);
                graphics.fillOval((int) pontos.get(i).getX(), (int) pontos.get(i).getY(), 5, 5);
            }
        }
        //repaint();
        
    }
    /**
     * Metodo para limpar a tela
     */
    public void limparTela(){
       
        for(int i =0; i < bfi.getWidth(); i ++){
            for(int j = 0; j < bfi.getHeight(); j ++){
                bfi.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        pontos.clear();
        pontosB.clear();
    }
    /**
     * Metodo para limpar a tela usando o Graphics
     */
    public void limparTelaEspecial(){
         g2d.clearRect(0,0, bfi.getWidth(), bfi.getHeight());
    }
    
    @Override
    public void paint(Graphics g){
        g2d = (Graphics2D) g;
         g2d.drawImage(bfi,null, 0, 0);
        desenhaPontos(g2d);
        
       
    }
    /**
     * Para o calculo dos pontos foi usado o uso de blending functions para calculos de aproximação para novos pontos
     * Metodo de Bezier
     */
    public void Bezier(){
        int n = pontos.size();
        for(double t = 0; t < 1; t +=0.0001){
            double x =0, y =0;
            for(int i = 0; i < n; i ++){
                int xk = pontos.get(i).getX();
                int yk = pontos.get(i).getY();
                double bk, by;
                bk = bernstein(i,t, n-1);
                x += bk*( (double) xk);
                y += bk*( (double) yk);
            }
            //Ponto ponto = new Ponto((int)Math.round(x),(int)Math.round(y));
            //pontosB.add(ponto);
            bfi.setRGB((int)Math.round(x), (int)Math.round(y), Color.RED.getRGB());
        }
        repaint();
    }
    /**
     * Uso de bernestein polynomials
     * Jni(t) = (n!/i!(n-i)!)t^i(1-t)^(n-i)
     * @param k
     * @param u
     * @param n
     * @return 
     */
    public double bernstein(int i, double t, int n){
        
        double value = 0;
        value = (fatorial(n)/(fatorial(i)*fatorial(n-i)))*Math.pow(t,i)*Math.pow(1-t,n-i);
        return value;
    }
    /**
     * Metodo para calcular o fatorial
     * 
     */
    int fatorial(int x){
       int t, fac = 1;
       for(t = x; x > 1; x --){
           fac *= x;
       }
       return fac;
    }
    /**
     * Ao clique do mouse guardar os pontos calculados
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Ponto ponto = new Ponto(x,y);
        pontos.add(ponto);
        repaint();
        System.out.println("x" + e.getX() + " y" + e.getY());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        //Metodos padroes do mouseListener
    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
