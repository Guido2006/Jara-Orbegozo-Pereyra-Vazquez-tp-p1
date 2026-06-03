package juego;

import java.awt.Color;

import entorno.Entorno;

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

	Castillo(Entorno e){
		this.ancho = 150;
		this.alto = 100;
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
		this.entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
	}
	
}
