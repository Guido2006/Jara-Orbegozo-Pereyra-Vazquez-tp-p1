package juego;

import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class Vida {

    int vidas;
    Image corazon;

    public Vida() {
        this.vidas = 10;
        this.corazon = Herramientas.cargarImagen("corazon.png");
    }

    public void perderVida() {
        if (this.vidas > 0) {
            this.vidas--;
        }
    }

    public int obtenerVidas() {
        return this.vidas;
    }

    public boolean estaMuerta() {
        return this.vidas <= 0;
    }

    public void dibujar(Entorno entorno) {
    	

        for (int i = 0; i < this.vidas; i++) {
            
        	entorno.dibujarImagen(corazon, 20+(i*45), 20, 0,0.1);

        }
    }
}
