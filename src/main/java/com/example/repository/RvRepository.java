package com.example.repository;

import com.example.entities.Medecin;
import com.example.entities.Rv;
import com.example.datasource.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RvRepository {
    
    private RepositoryBDImpl repositoryBD;


    public RvRepository(DataSource dataSource) {
        this.repositoryBD = new RepositoryBDImpl(dataSource);
    }

    // Ajouter un client
    public int addRv(Rv rv) throws SQLException {
        String[] fields = {"id", "date", "heure"};
        Object[] values = {
            rv.getDate(),
            rv.getHeure(),
        
        };

        return repositoryBD.insert("rv", fields, values);
    }

    // Récupérer tous les clients
    public List<Rv> getAllRvs() throws SQLException {
        ResultSet resultSet = repositoryBD.selectAll("rvs");

        List<Rv>rvs= new ArrayList<>();

        while (resultSet.next()) {
            Rv rv = mapToRv(resultSet);
            rvs.add(rv);
        }

        return rvs;
    }

    // Récupérer un client par son ID
    public Rv getRvById(Long id) throws SQLException {
        String sql = "SELECT * FROM rvs WHERE id = ?";
        ResultSet resultSet = repositoryBD.getDataSource().executeQuery(sql, id);

        if (resultSet.next()) {
            return mapToRv(resultSet);
        }

        return null; // Aucun client trouvé avec cet ID
    }


    }


    // Mapper le ResultSet à l'objet Client
    private Rv mapToRv(ResultSet resultSet) throws SQLException {
        Rv rv = new Rv();
        rv.setId(resultSet.getLong("id"));
        rv.setDate(resultSet.getString("date"));
        rv.setHeure(resultSet.getString("heure"));
        

    


}
    
    







    
    






