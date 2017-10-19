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
public class AnnosRaakaAine {
    
    private int id;
    private int annosId;
    private int raakaAineId;
    private int jarjestys;
    private String maara;
    private String ohje;

    public AnnosRaakaAine(int id, int annosId, int raakaAineId, int jarjestys, String maara, String ohje) {
        this.id = id;
        this.annosId = annosId;
        this.raakaAineId = raakaAineId;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public int getId() {
        return id;
    }

    public int getAnnosId() {
        return annosId;
    }

    public int getRaakaAineId() {
        return raakaAineId;
    }

    public int getJarjestys() {
        return jarjestys;
    }

    public String getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }
    
    
    
    
}
