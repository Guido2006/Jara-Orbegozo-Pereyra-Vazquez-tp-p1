package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

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
	Proyectil proyectil;
	boolean cayoAlVacio;
	Color color;
	Image imgPersonaje;
	
	Personaje(Entorno e){
		this.imgPersonaje = Herramientas.cargarImagen("personaje.png");
		this.ancho = 20;
		this.alto = 50;
		this.color = Color.RED;
		this.entorno = e;

		this.bordeSuperior = this.y - this.alto / 2;
		this.bordeInferior = this.y + this.alto / 2;
		this.bordeIzquierdo = this.x - this.ancho / 2;
		this.bordeDerecho = this.x + this.ancho / 2;

		this.puedeMoverse = true;
		this.gravedad = true;
		this.saltar = false;
		this.alturaSalto = 0;
		this.pisaPlataforma = false;
		this.cayoAlVacio = false;

		this.proyectil = null;
	}

	public void dibujar(double x, double y){

		this.x = x;
		this.y = y;

		this.bordeSuperior = y - this.alto / 2;
		this.bordeInferior = y + this.alto / 2;
		this.bordeIzquierdo = x - this.ancho / 2;
		this.bordeDerecho = x + this.ancho / 2;
//		this.entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
		this.entorno.dibujarImagen(imgPersonaje, this.x, this.y, 0, 0.1);
	}

	public void dibujar(){

		this.bordeSuperior = y - this.alto / 2;
		this.bordeInferior = y + this.alto / 2;
		this.bordeIzquierdo = x - this.ancho / 2;
		this.bordeDerecho = x + this.ancho / 2;
		this.entorno.dibujarImagen(imgPersonaje, this.x, this.y, 0, 0.1);
	}

	public void caer() {

		if(!this.saltar && this.gravedad) {

			this.y++;

			this.dibujar(this.x, this.y);

			if(this.bordeSuperior >= 600) {
				this.cayoAlVacio = true;
			}
		}
	}

	public void salta() {

		if(this.saltar && this.alturaSalto < 170) {

			this.y--;

			this.dibujar(this.x, this.y);

			this.alturaSalto++;

		} else {

			this.saltar = false;
		}
	}

	public void mover(String direccion){

		if(this.puedeMoverse) {

			if(direccion.equals("izq")) {
				this.dibujar(this.x - 1, this.y);
			}

			if(direccion.equals("der")) {
				this.dibujar(this.x + 1, this.y);
			}
		}
	}

	public void sePuedeMoverIzq(Mapa mapa){

		for(int j = 0; j < mapa.cantFilas; j++) {

			for(int i = 0; i < mapa.nivel[j].length; i++) {

				if((int)this.bordeIzquierdo ==
				   (int)mapa.nivel[j][i].bordeDerecho

				   &&

				   (this.bordeInferior >
				    mapa.nivel[j][i].bordeSuperior

				    &&

				    this.bordeSuperior <
				    mapa.nivel[j][i].bordeInferior)
				    
				   	||
				    
					this.bordeIzquierdo ==  0	) {

					this.puedeMoverse = false;
					return;
				}
			}
		}
	}
	
	public void sePuedeMoverDer(Mapa mapa){

		for(int j = 0; j < mapa.cantFilas; j++) {

			for(int i = 0; i < mapa.nivel[j].length; i++) {

				if((int)this.bordeDerecho ==
				   (int)mapa.nivel[j][i].bordeIzquierdo

				   &&

				   (this.bordeInferior >=
				    mapa.nivel[j][i].bordeSuperior

				    &&

				    this.bordeSuperior <=
				    mapa.nivel[j][i].bordeInferior)) {

					this.puedeMoverse = false;
					return;
				}
			}
		}
	}
		
	public void puedeSaltar(Mapa mapa){

		for(int j = 0; j < mapa.cantFilas; j++) {

			for(int i = 0; i < mapa.nivel[j].length; i++) {

				if((int)this.bordeInferior ==
				   (int)mapa.nivel[j][i].bordeSuperior

				   &&

				   (this.bordeDerecho >
				    mapa.nivel[j][i].bordeIzquierdo

				    &&

				    this.bordeIzquierdo <
				    mapa.nivel[j][i].bordeDerecho)) {

					this.alturaSalto = 0;
					this.saltar = true;
				}
			}
		}
	}

	public void chocaCabeza(Mapa mapa){

		for(int j = 0; j < mapa.cantFilas; j++) {

			for(int i = 0; i < mapa.nivel[j].length; i++) {

				if((int)this.bordeSuperior ==
				   (int)mapa.nivel[j][i].bordeInferior

				   &&

				   (this.bordeDerecho >
				    mapa.nivel[j][i].bordeIzquierdo

				    &&

				    this.bordeIzquierdo <
				    mapa.nivel[j][i].bordeDerecho)) {

					this.saltar = false;
				}
			}
		}
	}
	
	public void estaSobrePlataforma(Mapa mapa){

		for(int j = 0; j < mapa.cantFilas; j++) {

			for(int i = 0; i < mapa.nivel[j].length; i++) {

				if((int)this.bordeInferior ==
				   (int)mapa.nivel[j][i].bordeSuperior

				   &&

				   (this.bordeDerecho >
				    mapa.nivel[j][i].bordeIzquierdo

				    &&

				    this.bordeIzquierdo <
				    mapa.nivel[j][i].bordeDerecho)) {

					this.pisaPlataforma = true;
					return;
				}
			}
		}
	}
	
	public void conseguirDireccion(
			double personajeX,
			double personajeY,
			double mouseX,
			double mouseY) {

		double diferenciaX = mouseX - personajeX;
		double diferenciaY = mouseY - personajeY;

		double distancia =
				Math.sqrt(
						diferenciaX * diferenciaX +
						diferenciaY * diferenciaY);

		double direccionX = diferenciaX / distancia;
		double direccionY = diferenciaY / distancia;

		this.proyectil.direccionX = direccionX;
		this.proyectil.direccionY = direccionY;
	}
	

	public void disparar() {

		if(this.proyectil == null) {
			return;
		}

		if(this.proyectil.x < 0
				|| this.proyectil.x > this.entorno.ancho()
				|| this.proyectil.y < 0
				|| this.proyectil.y > this.entorno.alto()) {

			this.proyectil = null;
			return;
		}

		this.proyectil.x +=
				this.proyectil.direccionX * 4;

		this.proyectil.y +=
				this.proyectil.direccionY * 4;

		this.proyectil.dibujarProyectil(
				this.proyectil.x,
				this.proyectil.y);
	}

	public boolean gano(Castillo castillo){

		return this.bordeInferior >= castillo.bordeSuperior
				&& this.bordeSuperior <= castillo.bordeInferior
				&& this.bordeDerecho >= castillo.bordeIzquierdo
				&& this.bordeIzquierdo <= castillo.bordeDerecho;
	}
}