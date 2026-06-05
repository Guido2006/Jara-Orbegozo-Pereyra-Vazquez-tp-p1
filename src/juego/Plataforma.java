package juego;

import entorno.Entorno;

import java.awt.Color;

public class Plataforma {
	Entorno entorno;
	double tipo;
	double x;
	double y;
	double ancho;
	double alto;
	double bordeInferior;
	double bordeSuperior;
	double bordeIzquierdo;
	double bordeDerecho;
	Color color;
	
	Plataforma(Entorno e){
		this.tipo = (int)(Math.random()*(6 - 1 + 1))+1;
		this.tipo = (int)(Math.random()*(3 - 1 + 1))+1;

		this.ancho = 50*tipo;
		this.alto = 20;
		this.color = Color.blue;
		this.bordeSuperior = this.y-this.alto/2;
		this.bordeInferior = this.y+this.alto/2;
		this.bordeIzquierdo = this.x-this.ancho/2;
		this.bordeDerecho = this.x+this.ancho/2;
		this.entorno = e;
	}
	
	public void dibujar(double x, double y){
		this.x = x;
		this.y = y;
		this.bordeSuperior = this.y-this.alto/2;
		this.bordeInferior = this.y+this.alto/2;
		this.bordeIzquierdo = this.x-this.ancho/2;
		this.bordeDerecho = this.x+this.ancho/2;
		
		
		this.entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
		
		
	}
}