package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
		
	
	// Variables y métodos propios de cada grupo

	private Enemigos[] enemigos;
	Vida vida;
	Personaje personaje;
	Mapa mapa;
	//teclas extras para moverse
	char a = 'a';
	char d = 'd';
	char w = 'w';
	
	
	
	//genera el mapa
	public void generarMapa() {
		this.mapa = new Mapa(this.entorno);
		this.mapa.dibujarMapa();
	}
	
	
	public void dibujarPersonaje() {
		//crea el personaje
		this.personaje = new Personaje(this.entorno);
		//crea el personaje y la vida
		this.personaje = new Personaje(this.entorno);
		this.vida = new Vida();

		personaje.dibujar(this.entorno.ancho()/2, -5);
	}

	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP - Jara Guido - Gabriel Pereyra - Orbegozo Aarón", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.generarMapa();
		this.dibujarPersonaje();
		
		this.enemigos = new Enemigos[10];

		for(int i = 0; i < 10; i++) {
		    this.enemigos[i] = new Enemigos();
		}

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
		//Fondos:
		entorno.dibujarImagen(this.mapa.fondo, this.entorno.ancho()/2, this.entorno.alto()/2, 0,1.5);
		
		
		//Base
		if(vida.estaMuerta()) {

		    entorno.escribirTexto(
		        "GAME OVER",
		        320,
		        300);

		    return;
		}
		
		  // =========================
	    // CONTROL DE ENEMIGOS
	    // =========================

		int vivos = 0;

		for (int i = 0; i < enemigos.length; i++) {

		    if (enemigos[i] != null) {

		        Enemigos e = enemigos[i];

		        vivos++;

		        double distanciaX =
		                Math.abs(e.posicionX - personaje.x);

		        double distanciaY =
		                Math.abs(e.posicionY - personaje.y);

		        if (distanciaX < 30 && distanciaY < 30) {

		            vida.perderVida();
	                e.estaVivo = false;
		        }
		        // Proyectil
		        if(personaje.proyectil != null) {

		            double distanciaProyectilX =
		                    Math.abs(personaje.proyectil.x - e.posicionX);

		            double distanciaProyectilY =
		                    Math.abs(personaje.proyectil.y - e.posicionY);

		            if(distanciaProyectilX < 25 &&
		               distanciaProyectilY < 25) {
		                e.estaVivo = false;

		                personaje.proyectil = null;
		            }
		        }


		        if(enemigos[i] != null) {
		    	   enemigos[i].mover();
		       	}

		        if (!e.estaVivo) {

		            enemigos[i] = null;
		            vivos--;
		        }
		    }
		}
		
		// Cantidad minima de enemigos
		while (vivos < 5) {

		    for (int i = 0; i < enemigos.length; i++) {

		        if (enemigos[i] == null) {

		            enemigos[i] = new Enemigos();
		            vivos++;
		            break;
		        }
		    }
		}
	
		
		// comprueba si se llego al castillo
		if(personaje.gano(this.mapa.castillo)) {
			this.entorno.cambiarFont(null, 50, Color.white);
			this.entorno.escribirTexto("ganaste", (this.entorno.ancho()/2)-25, this.entorno.alto()/2);
			return;
		}
		
		
		//actualiza el mapa
		this.mapa.dibujarMapa();
		
		personaje.puedeMoverse = true;
		personaje.pisaPlataforma = false;
		
		
		
		if(this.entorno.sePresionoBoton(this.entorno.BOTON_IZQUIERDO)) {
			if(personaje.proyectil == null) {
				personaje.proyectil = new Proyectil(this.entorno);
				personaje.proyectil.x = personaje.x;
				personaje.proyectil.y = personaje.y;
				personaje.conseguirDireccion(this.personaje.x, this.personaje.y, this.entorno.mouseX(), this.entorno.mouseY());
			}
		}
		if(personaje.proyectil != null) {
			personaje.disparar();
		}


		//se COMPRUEBA si se presiona la tecla para MOVER IZQUIERDA
		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) || this.entorno.estaPresionada(a)) {	
			// COMPRUEBA que NO se COLICIONE
			personaje.sePuedeMoverIzq(this.mapa);
			//MOVER IZQUIERDA
			personaje.mover("izq");
		}
		
		//se COMPRUEBA si se presiona la tecla para MOVER DERECHA
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) || this.entorno.estaPresionada(d)) {
			//COMPRUEBA que NO se COLICIONE
			personaje.sePuedeMoverDer(this.mapa);
			//MOVER DERECHA
			personaje.mover("der");
		}
		
		// ****CAMARA****
		
		// si el personaje esta en la mitad de la pantalla y avanza, la camara se mueve
		if((int)personaje.x == (int)entorno.ancho()/2) {
			this.mapa.moverCamara();
			personaje.mover("izq");
		}
		
		
		// **COMPROBAR SALTO**
		
		// COMPRUEBA SI se esta PRECIONANDO una tecla para SALTAR
		if(this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) || this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO) || this.entorno.estaPresionada(w)) {
			//COMPRUEBA que el PERSONAJE este SOBRE una PLATAFORMA
			personaje.puedeSaltar(this.mapa);
		}
		
		// COMPRUEBA SI CHOCA la cabeza CONTRA una PLATAFORMA SUPERIOR
		personaje.chocaCabeza(this.mapa);
		
		//SALTA
		personaje.salta();
			
			
		// **COMPROBAR SI CAE**

		//comprueba que el personaje este sobre una plataforma
		personaje.estaSobrePlataforma(this.mapa);

		
		// COMPRUEBA que el PERSONAJE NO este en un SALTO
		if(!personaje.pisaPlataforma) {
			personaje.gravedad = true;
		} else {
			personaje.gravedad = false;
		}
		personaje.caer();
		personaje.dibujar();

		if(personaje.cayoAlVacio) {

		    vida.perderVida();

		    personaje.cayoAlVacio = false;

		    personaje.dibujar(this.entorno.ancho()/2,-5);
		}
		


		personaje.dibujar();
		vida.dibujar(entorno);
		for(int i = 0; i < enemigos.length; i++) {

		    if(enemigos[i] != null) {

		        enemigos[i].dibujar(entorno);
		    }
		}
		
		// Procesamiento de un instante de tiempo
		// ...
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}