package juego;

import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class Castillo {
	Entorno entorno;
	double x;
	double y;
	double ancho;
	double alto;
	double bordeInferior;
	double bordeSuperior;
	double bordeIzquierdo;
	double bordeDerecho;
	Color color;
	Image castillo;

	Castillo(Entorno e){
		this.castillo = Herramientas.cargarImagen("castillo.png");
		this.ancho = 125;
		this.alto = 60;
		this.color = Color.green;
		this.bordeSuperior = this.y-this.alto/2;
		this.bordeInferior = this.y+this.alto/2;
		this.bordeIzquierdo = this.x-this.ancho/2;
		this.bordeDerecho = this.x+this.ancho/2;
		this.entorno = e;
	}
	
	public void dibujarCastillo() {
		this.bordeSuperior = this.y-this.alto/2;
		this.bordeInferior = this.y+this.alto/2;
		this.bordeIzquierdo = this.x-this.ancho/2;
		this.bordeDerecho = this.x+this.ancho/2;
		this.entorno.dibujarImagen(castillo, this.x+20, this.y, 0,0.4);
	}	
}