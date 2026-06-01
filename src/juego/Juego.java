package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
		
	
	// Variables y métodos propios de cada grupo
	
	Personaje personaje;
	Mapa mapa;
	//teclas extras para moverse
	char a = 'a';
	char d = 'd';
	char w = 'w';
	
	
	
	//genera el mapa
	public void generarMapa() {
		this.mapa = new Mapa(this.entorno);
	}
	
	
	public void dibujarInicio() {
		//crea el personaje
		this.personaje = new Personaje(this.entorno);
		personaje.dibujar(20, 50);
	}

	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.generarMapa();
		this.dibujarInicio();

		// ...

		// Inicia el juego!
		this.entorno.iniciar();

	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		personaje.puedeMoverse = true;
		if((int)personaje.x == (int)entorno.ancho()/2) {
			this.mapa.moverCamara();
			personaje.mover("izq");
		}
		//actualiza el mapa
		this.mapa.dibujarMapa();
		
		


		//se COMPRUEBA si se presiona la tecla para MOVER IZQUIERDA
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) || this.entorno.estaPresionada(a)) {	
			// COMPRUEBA que NO se COLICIONE
			for(int j=0;j<this.mapa.cantFilas;j++) {
				for(int i=0;i<this.mapa.nivel[j].length;i++) {
					if((Math.round(personaje.bordeIzquierdo) == Math.round(this.mapa.nivel[j][i].bordeDerecho) && (personaje.bordeInferior > this.mapa.nivel[j][i].bordeSuperior && personaje.bordeSuperior < this.mapa.nivel[j][i].bordeInferior))) {
						personaje.puedeMoverse = false;
						break;
					}
				}
			}

			//MOVER IZQUIERDA
			personaje.mover("izq");
		}
		
		//se COMPRUEBA si se presiona la tecla para MOVER DERECHA
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) || this.entorno.estaPresionada(d)) {
			//COMPRUEBA que NO se COLICIONE
			for(int j=0;j<this.mapa.cantFilas;j++) {
				for(int i=0;i<this.mapa.nivel[j].length;i++) {
					if((Math.round(personaje.bordeDerecho) == Math.round(this.mapa.nivel[j][i].bordeIzquierdo) && (personaje.bordeInferior > this.mapa.nivel[j][i].bordeSuperior && personaje.bordeSuperior < this.mapa.nivel[j][i].bordeInferior))) {
						personaje.puedeMoverse = false;
						break;
					}
				}
			}

			//MOVER DERECHA
			personaje.mover("der");
		}
		
		
		// **COMPROBAR SALTO**
		
		// COMPRUEBA SI se esta PRECIONANDO una tecla para SALTAR
		if(this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) || this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO) || this.entorno.estaPresionada(w)) {
			//COMPRUEBA que el PERSONAJE este SOBRE una PLATAFORMA
			for(int j=0;j<this.mapa.cantFilas;j++) {
				for(int i=0;i<this.mapa.nivel[j].length;i++) {
					if((Math.round(personaje.bordeInferior) == Math.round(this.mapa.nivel[j][i].bordeSuperior) && (personaje.bordeDerecho > this.mapa.nivel[j][i].bordeIzquierdo && personaje.bordeIzquierdo < this.mapa.nivel[j][i].bordeDerecho))) {
						personaje.alturaSalto = 0;
						personaje.saltar = true;
					}
				}
			}

			
		}
		
		// COMPRUEBA SI CHOCA la cabeza CONTRA una PLATAFORMA SUPERIOR
		for(int j=0;j<this.mapa.cantFilas;j++) {
			for(int i=0;i<this.mapa.nivel[j].length;i++) {
				if((Math.round(personaje.bordeSuperior) == Math.round(this.mapa.nivel[j][i].bordeInferior) && (personaje.bordeDerecho > this.mapa.nivel[j][i].bordeIzquierdo && personaje.bordeIzquierdo < this.mapa.nivel[j][i].bordeDerecho))) {
					personaje.saltar = false;
				}
			}
		}

		
		//SALTA
		personaje.salta();
			
			
		// **COMPROBAR SI CAE**
		personaje.pisaPlataforma = false;
		//comprueba que el personaje NO este sobre una plataforma
		for(int j=0;j<this.mapa.cantFilas;j++) {
			for(int i=0;i<this.mapa.nivel[j].length;i++) {
				if((Math.round(this.personaje.bordeInferior) == Math.round(this.mapa.nivel[j][i].bordeSuperior) && (personaje.bordeDerecho > this.mapa.nivel[j][i].bordeIzquierdo && personaje.bordeIzquierdo < this.mapa.nivel[j][i].bordeDerecho))) {
					personaje.pisaPlataforma = true;
					break;
				}
			}
		}
		// System.out.println(personaje.pisaPlataforma);

		
		// COMPRUEBA que el PERSONAJE NO este en un SALTO
		if(!personaje.pisaPlataforma) {
			personaje.gravedad = true;
		} else {
			personaje.gravedad = false;
		}
		personaje.caer();
		


		personaje.dibujar();

		
		// Procesamiento de un instante de tiempo
		// ...
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
