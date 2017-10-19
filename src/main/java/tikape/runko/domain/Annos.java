/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

/**
 *
 * @author ilmar
 */
public class Annos {
    
    private int id;
    private int hinta;
    private String nimi;

    public Annos(int id, int hinta, String nimi) {
        this.id = id;
        this.hinta = hinta;
        this.nimi = nimi;
    }

    public int getId() {
        return id;
    }

    public int getHinta() {
        return hinta;
    }

    public String getNimi() {
        return nimi;
    }

    
    
    
    
    
}
