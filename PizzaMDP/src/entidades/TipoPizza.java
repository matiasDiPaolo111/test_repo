/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import persistencia.PersistentObject;

/**
 *
 * @author PC-MATT
 */
public class TipoPizza  {
    private int id_tipo_pizza;
    private String nombre;
    private String descripcionPizza;

    public String getDescripcionPizza() {
        return descripcionPizza;
    }

    public void setDescripcionPizza(String descripcionPizza) {
        this.descripcionPizza = descripcionPizza;
    }
    
    
    public int getId_tipo_pizza() {
        return id_tipo_pizza;
    }

    public void setId_tipo_pizza(int id_tipo_pizza) {
        this.id_tipo_pizza = id_tipo_pizza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcionPizza;
    }

    public void setDescripcion(String descripcion) {
        this.descripcionPizza = descripcion;
    }
}
