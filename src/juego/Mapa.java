package juego;

import entorno.Entorno;

public class Mapa {
	Plataforma[][] nivel;
	Plataforma[] plataformas;
	double cantFilas;
	double altura;
	double camara;
	
	Mapa(Entorno entorno){
		this.cantFilas = 3;
		this.camara = 0;
		this.plataformas = new Plataforma[11];
		this.nivel = new Plataforma[(int)this.cantFilas][this.plataformas.length];
		for(int i=0;i<this.cantFilas;i++) {
			for(int j=0;j<this.plataformas.length;j++) {
				this.nivel[i][j] = this.plataformas[j] = new Plataforma(entorno);
			}
		}
	}
	

	
	public void dibujarMapa() {
		double espacioEntre = 100;
		for(int j=0;j<this.cantFilas;j++) {
			double x =(this.nivel[j][0].ancho/2)-this.camara;
			double y =200+j*100;
			//System.out.println("Inicio");
			// los espacios entre plataforma estan MAAALLLLL***************************************************
			for(int i=0;i<this.nivel[j].length;i++) {
				this.nivel[j][i].dibujar(x, y);
				//System.out.print("i1 = "+ this.plataformas[i].ancho/2 +" ");
				x += (this.nivel[j][i].ancho/2)+espacioEntre;
				if(i > 0) {
					if(this.nivel[j][i-1] != null) {
						//System.out.println("i-1 = "+ this.plataformas[i-1].ancho/2 +" ");
						//System.out.println("x = "+ x);
						x += this.nivel[j][i-1].ancho/2;
					}
				}

			}
		}

	}
	
	public void moverCamara() {
		this.camara += 1;
	}
}
