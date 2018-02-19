package com.example.alejandroquiros.examen2t;

/**
 * Created by alejandroquiros on 19/2/18.
 */

public class Lugar {



    public Double Lat;
    public Double Lon;
    public String nombre;

    Lugar(){

    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public void setLon(Double lon) {
        Lon = lon;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLat() {

        return Lat;
    }

    public Double getLon() {
        return Lon;
    }

    public String getNombre() {
        return nombre;
    }

    Lugar(Double Lat, Double Lon, String nombre){
        this.Lat = Lat;
        this.Lon = Lon;
        this.nombre = nombre;
    }
}
