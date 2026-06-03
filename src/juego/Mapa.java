package juego;

import entorno.Entorno;

public class Mapa {
	Plataforma[][] nivel;
	Plataforma[] plataformas;
	double cantFilas;
	double altura;
	double camara;
	Castillo castillo;
	
	Mapa(Entorno entorno){
		this.cantFilas = 3;
		this.camara = 0;
		this.plataformas = new Plataforma[5];
		this.nivel = new Plataforma[(int)this.cantFilas][this.plataformas.length];
		//genera plataformas de distintos tamaños
		for(int i=0;i<this.cantFilas;i++) {
			for(int j=0;j<this.plataformas.length;j++) {
				this.nivel[i][j] = this.plataformas[j] = new Plataforma(entorno);
			}
		}
		// hace que la ultima fila tenga plataformas de mismo tamaño y a misma distancia
		for(int i=0;i<this.plataformas.length;i++) {
			this.nivel[(int)this.cantFilas-1][i].ancho = 300; 
		}
		this.castillo = new Castillo(entorno);
	}
	

	
	public void dibujarMapa() {
		double espacioEntre = 100;
		for(int j=0;j<this.cantFilas;j++) {
			double x =(this.nivel[j][0].ancho/2)-this.camara;
			double y =100+(j+1)*100;
			for(int i=0;i<this.nivel[j].length;i++) {
				this.nivel[j][i].dibujar(x, y);
				x += (this.nivel[j][i].ancho/2)+espacioEntre;
				if(i<this.nivel[j].length-1) {
					if(this.nivel[j][i+1] != null) {
							x += this.nivel[j][i+1].ancho/2;
					}
				}
			}
			// Hace que en la ultima plataforma de la ultima fila se cree el castillo
			if(j==this.cantFilas-1) {
				this.castillo.x = x-100-(this.nivel[nivel.length-1][this.plataformas.length-1].ancho/2); 
				this.castillo.y = y-(this.castillo.alto/2)-(plataformas[0].alto/2);
				this.castillo.dibujarCastillo();
			}
		}

	}
	
	public void moverCamara() {
		this.camara += 1;
	}
}
