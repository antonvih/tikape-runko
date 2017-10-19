/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Annos;
import tikape.runko.domain.AnnosRaakaAine;

/**
 *
 * @author ilmar
 */
public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer>{
    
    private Database database;

    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnnosRaakaAine> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnnosRaakaAine save(AnnosRaakaAine object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO AnnosRaakaAine (jarjestys, annos_id, raakaaine_id, maara, ohje) VALUES (?,?,?,?,?)");
            stmt.setInt(1, object.getJarjestys());
            stmt.setInt(2, object.getAnnosId());
            stmt.setInt(3, object.getRaakaAineId());
            stmt.setString(4, object.getMaara());
            stmt.setString(5, object.getOhje());
            
            stmt.executeUpdate();
        }

        return object;
    }
    

    public List<AnnosRaakaAine> findAllFor(int id) throws SQLException {
        List<AnnosRaakaAine> annosRaakaAineet = new ArrayList<>();
        
        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, jarjestys, annos_id, raakaaine_id,maara,ohje FROM AnnosRaakaAine WHERE annos_id ="+id).executeQuery()) {

            while (result.next()) {
                annosRaakaAineet.add(new AnnosRaakaAine(result.getInt("id"), result.getInt("annos_id"), result.getInt("raakaaine_id"), result.getInt("jarjestys"), result.getString("maara"),result.getString("ohje")));
            }
        }

        return annosRaakaAineet;
    }
    
    
    
}
