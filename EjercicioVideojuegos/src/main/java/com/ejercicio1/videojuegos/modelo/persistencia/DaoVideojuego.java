package com.ejercicio1.videojuegos.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ejercicio1.videojuegos.modelo.entidad.Videojuego;

@Component
public class DaoVideojuego {
	private List<Videojuego> listaVideojuegos;
	private int contador = 0;
	
	public DaoVideojuego() {
		Videojuego v1 = new Videojuego();
		v1.setId(contador++);
		v1.setNombre("Sonic");
		v1.setCompañia("Games");
		v1.setPrecio(46);
		v1.setPuntuacion(124);
		
		Videojuego v2 = new Videojuego();
		v2.setId(contador++);
		v2.setNombre("Tetris");
		v2.setCompañia("Games");
		v2.setPrecio(28);
		v2.setPuntuacion(280);
		
		listaVideojuegos = new ArrayList<Videojuego>();
		listaVideojuegos.add(v1);
		listaVideojuegos.add(v2);
	}
	
	public List<Videojuego> listado(){
		return listaVideojuegos;
	}
	
	public Videojuego buscar(int id) {
		Videojuego videojuego = null;
		for(Videojuego v : listaVideojuegos) {
			if(v.getId() == id) {
				videojuego = v;
				break;
			}
		}
		
		return videojuego;
	}
	
	public Videojuego alta(Videojuego v) {
		v.setId(contador++);
		listaVideojuegos.add(v);
		return v;
	}
	
	public Videojuego modificar(Videojuego vModificar) {
		Videojuego v = buscar(vModificar.getId());
		if(v != null) {
			v.setPuntuacion(vModificar.getPuntuacion());
			v.setPrecio(vModificar.getPrecio());
			v.setCompañia(vModificar.getCompañia());
			v.setNombre(vModificar.getNombre());
		}		
		return v;
	}
	
	public Videojuego borrar(int id) {
		Videojuego v = buscar(id);
		if(v != null) {
			listaVideojuegos.remove(id);
		}
		return v;
	}
	

}
