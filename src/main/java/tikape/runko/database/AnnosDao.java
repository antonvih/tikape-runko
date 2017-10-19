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

/**
 *
 * @author ilmar
 */
public class AnnosDao implements Dao<Annos, Integer>{
    
    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }

    @Override
    public Annos findOne(Integer key) throws SQLException {
        Annos annos = null;
        
        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, nimi, hinta FROM Annos WHERE id ="+key).executeQuery()) {

            while (result.next()) {
                annos = new Annos(result.getInt("id"), result.getInt("hinta"), result.getString("nimi"));
            }
        }

        return annos;
    }

    @Override
    public List<Annos> findAll() throws SQLException {
        List<Annos> annokset = new ArrayList<>();
        
        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, nimi, hinta FROM Annos").executeQuery()) {

            while (result.next()) {
                annokset.add(new Annos(result.getInt("id"), result.getInt("hinta"), result.getString("nimi")));
            }
        }

        return annokset;
        
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Annos save(Annos object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Annos (nimi, hinta) VALUES (?,?)");
            stmt.setString(1, object.getNimi());
            stmt.setInt(2, object.getHinta());
            stmt.executeUpdate();
        }

        return object;
    }
    
    
    
    
    
}
