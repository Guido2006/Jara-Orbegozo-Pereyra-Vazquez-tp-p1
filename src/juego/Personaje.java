package juego;

import java.awt.Color;

import entorno.Entorno;

public class Personaje {

	Entorno entorno;
	double x;
	double y;
	double ancho;
	double alto;
	double bordeInferior;
	double bordeSuperior;
	double bordeIzquierdo;
	double bordeDerecho;
	boolean puedeMoverse;
	boolean gravedad;
	boolean saltar;
	double alturaSalto;
	boolean pisaPlataforma;
	
	Color color;
	
	Personaje(Entorno e){
		this.ancho = 10;
		this.alto = 25;
		this.color = Color.red;
		this.entorno = e;
		this.bordeSuperior = this.y-this.alto/2;
		this.bordeInferior = this.y+this.alto/2;
		this.bordeIzquierdo = this.x-this.ancho/2;
		this.bordeDerecho = this.x+this.ancho/2;
		this.puedeMoverse = true;
		this.gravedad = true;
		this.saltar = false;
		this.alturaSalto = 0;	
		this.pisaPlataforma = false;	}
	
	public void dibujar(double x, double y){
		this.x = x;
		this.y = y;
		this.bordeSuperior = y-this.alto/2;
		this.bordeInferior = y+this.alto/2;
		this.bordeIzquierdo = x-this.ancho/2;
		this.bordeDerecho = x+this.ancho/2;
		this.entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
		
	}
	public void dibujar(){
		this.bordeSuperior = y-this.alto/2;
		this.bordeInferior = y+this.alto/2;
		this.bordeIzquierdo = x-this.ancho/2;
		this.bordeDerecho = x+this.ancho/2;
		this.entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
	}
	
	public void caer() {
		if(!this.saltar && this.gravedad)
			this.y = this.y+1;
			this.dibujar(this.x, this.y);
			if(this.bordeSuperior >= 600) {
				this.y = 0;
				this.dibujar(this.x, this.y);

				

		}
	}
	
	public void salta() {
		if(this.saltar && this.alturaSalto<120) {
			this.y = this.y-1;
			this.dibujar(this.x, this.y);
			this.alturaSalto += 1;
		} else {
			this.saltar = false;
		}
	}
	
	public void mover(String direccion){
		if(this.puedeMoverse) {
			if(direccion == "izq") {
				this.dibujar(this.x-1, this.y);
			}
			if(direccion == "der") {
				this.dibujar(this.x+1, this.y);
			}
		}
	}
	public void sePuedeMoverIzq(Mapa mapa){
		for(int j=0;j<mapa.cantFilas;j++) {
			for(int i=0;i<mapa.nivel[j].length;i++) {
				if(((int)this.bordeIzquierdo == (int)mapa.nivel[j][i].bordeDerecho && (this.bordeInferior > mapa.nivel[j][i].bordeSuperior && this.bordeSuperior < mapa.nivel[j][i].bordeInferior))) {
					this.puedeMoverse = false;
					return;
				}
			}
		}
	}
	
	public void	sePuedeMoverDer(Mapa mapa) {
		for(int j=0;j<mapa.cantFilas;j++) {
			for(int i=0;i<mapa.nivel[j].length;i++) {
				if(((int)this.bordeDerecho == (int)mapa.nivel[j][i].bordeIzquierdo && (this.bordeInferior >= mapa.nivel[j][i].bordeSuperior && this.bordeSuperior <= mapa.nivel[j][i].bordeInferior))) {
					this.puedeMoverse = false;
					return;
				}
			}
		}
	}
		
	public void puedeSaltar(Mapa mapa) {
		for(int j=0;j<mapa.cantFilas;j++) {
			for(int i=0;i<mapa.nivel[j].length;i++) {
				if(((int)this.bordeInferior == (int)mapa.nivel[j][i].bordeSuperior && (this.bordeDerecho > mapa.nivel[j][i].bordeIzquierdo && this.bordeIzquierdo < mapa.nivel[j][i].bordeDerecho))) {
					this.alturaSalto = 0;
					this.saltar = true;
				}
			}
		}
	}
	public void chocaCabeza(Mapa mapa) {
		for(int j=0;j<mapa.cantFilas;j++) {
			for(int i=0;i<mapa.nivel[j].length;i++) {
				if(((int)this.bordeSuperior == (int)mapa.nivel[j][i].bordeInferior && (this.bordeDerecho > mapa.nivel[j][i].bordeIzquierdo && this.bordeIzquierdo < mapa.nivel[j][i].bordeDerecho))) {
					this.saltar = false;
				}
			}
		}
	}
	
	public void estaSobrePlataforma(Mapa mapa) {
		for(int j=0;j<mapa.cantFilas;j++) {
			for(int i=0;i<mapa.nivel[j].length;i++) {
				if(((int)this.bordeInferior == (int)mapa.nivel[j][i].bordeSuperior && (this.bordeDerecho > mapa.nivel[j][i].bordeIzquierdo && this.bordeIzquierdo < mapa.nivel[j][i].bordeDerecho))) {
					this.pisaPlataforma = true;
					return;
				}
			}
		}
	}
	
	public boolean gano(Castillo castillo) {
		if(this.bordeInferior >= castillo.bordeSuperior && this.bordeSuperior <= castillo.bordeInferior && this.bordeDerecho >= castillo.bordeIzquierdo && this.bordeIzquierdo <= castillo.bordeDerecho) {
			return true;
		}
		return false;
	}
	
	
	
	
}
