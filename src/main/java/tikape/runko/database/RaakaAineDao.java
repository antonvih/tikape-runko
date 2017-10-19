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
import tikape.runko.domain.RaakaAine;

/**
 *
 * @author ilmar
 */
public class RaakaAineDao implements Dao<RaakaAine, Integer> {
    
    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        List<RaakaAine> aineet = new ArrayList<>();

        try(Connection c = database.getConnection();
                ResultSet rs = c.prepareStatement("SELECT id, nimi FROM RaakaAine").executeQuery()){
            while(rs.next()){
                aineet.add(new RaakaAine(rs.getInt("id"), rs.getString("nimi")));
            }
        }
        return aineet;

    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RaakaAine save(RaakaAine object) throws SQLException {

        try(Connection c = database.getConnection()){


              PreparedStatement stmt= c.prepareStatement("INSERT INTO RaakaAine (nimi) VALUES (?)");
                stmt.setString(1, object.getNimi());
                stmt.executeUpdate();


        }
            return object;
    }
    
    
    
}
