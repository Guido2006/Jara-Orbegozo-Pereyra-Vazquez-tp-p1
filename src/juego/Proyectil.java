package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	Entorno entorno;
	double x;
	double y;
	double diametro;
	double bordeInferior;
	double bordeSuperior;
	double bordeIzquierdo;
	double bordeDerecho;
	double direccionX;
	double direccionY;
	Color color;
	
	Proyectil(Entorno e){
		this.diametro = 50;
		this.color = Color.yellow;
		this.bordeSuperior = this.y;
		this.bordeInferior = this.y;
		this.bordeIzquierdo = this.x;
		this.bordeDerecho = this.x;
		this.entorno = e;
	}
	
	public void dibujarProyectil(double x, double y) {
		this.x = x;
		this.y = y;
		this.bordeSuperior = this.y-this.diametro/2;
		this.bordeInferior = this.y+this.diametro/2;
		this.bordeIzquierdo = this.x-this.diametro/2;
		this.bordeDerecho = this.x+this.diametro/2;
		this.entorno.dibujarCirculo(this.x, this.y, this.diametro, color);
	}
	
}
