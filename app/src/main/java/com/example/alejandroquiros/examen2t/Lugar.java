package com.example.alejandroquiros.examen2t;

/**
 * Created by alejandroquiros on 19/2/18.
 */

public class Lugar {

    Long lat;
    Long lon;
    String nombre;

    Lugar(){

    }

    Lugar(Long lat, Long lon, String nombre){
        this.lat = lat;
        this.lon = lon;
        this.nombre = nombre;
    }
}
