package juego;

import java.awt.Color;
import entorno.Entorno;

public class Enemigos {

    double posicionX;
    double posicionY;
    double velocidadX;
    int direccion;
    boolean estaVivo;
    float anchoImagen;
    float altoImagen;

    public Enemigos() {

        this.anchoImagen = 50;
        this.altoImagen = 50;

        this.velocidadX = 3;

        if (Math.random() < 0.5) {

            this.direccion = 1;
            this.posicionX = -this.anchoImagen;

        } else {

            this.direccion = -1;
            this.posicionX = 805 + this.anchoImagen;
        }

        int fila = (int)(Math.random() * 3);

        if (fila == 0) {
            this.posicionY = 180 + Math.random() * 50;
        } else if(fila == 1){
            this.posicionY = 310 + Math.random() * 50;
        }else {
            this.posicionY = 480 + Math.random() * 50;
        }


        this.estaVivo = true;
        
    }

    public void mover() {

        if (!this.estaVivo) {
            return;
        }

        this.posicionX += this.velocidadX * this.direccion;

        if (this.direccion == 1 &&
            this.posicionX > 805 + this.anchoImagen) {

            this.estaVivo = false;
        }

        if (this.direccion == -1 &&
            this.posicionX < -this.anchoImagen) {

            this.estaVivo = false;
        }
    }

    public void dibujar(Entorno entorno) {

        if (!this.estaVivo) {
            return;
        }

        // Parte superior
        entorno.dibujarCirculo(
                posicionX,
                posicionY - 8,
                20,
                Color.CYAN);

        // Base
        entorno.dibujarRectangulo(
                posicionX,
                posicionY + 5,
                50,
                15,
                0,
                Color.DARK_GRAY);

        // Luces
        entorno.dibujarCirculo(
                posicionX - 15,
                posicionY + 5,
                5,
                Color.YELLOW);

        entorno.dibujarCirculo(
                posicionX,
                posicionY + 5,
                5,
                Color.GREEN);

        entorno.dibujarCirculo(
                posicionX + 15,
                posicionY + 5,
                5,
                Color.RED);
    }
}