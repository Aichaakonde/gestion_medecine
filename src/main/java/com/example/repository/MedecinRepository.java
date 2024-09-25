package com.example.repository;

import com.example.entities.Medecin;
import com.example.entities.Rv;
import com.example.datasource.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedecinRepository {
    private final RepositoryBDImpl repositoryBD;

    public MedecinRepository(DataSource dataSource) {
        this.repositoryBD = new RepositoryBDImpl(dataSource);
    }

    // Ajouter un client
    public int addMedecin(Medecin medecin) throws SQLException {
        String[] fields = { "nom", "prenom", "rv_id"};
        Object[] values = {
            medecin.getNom(),
            medecin.getPrenom(),
            (medecin.getRv() != null) ? medecin.getRv().getId() : null
        };

        return repositoryBD.insert("medecin", fields, values);
    }

    // Récupérer tous les clients
    public List<Medecin> getAllMedecins() throws SQLException {
        ResultSet resultSet = repositoryBD.selectAll("medecins");

        List<Medecin> medecins= new ArrayList<>();

        while (resultSet.next()) {
            Medecin medecin = mapToMedecin(resultSet);
            medecins.add(medecin);
        }

        return medecins;
    }

    // Récupérer un client par son ID
    public Medecin getMedecinById(Long id) throws SQLException {
        String sql = "SELECT * FROM medecins WHERE id = ?";
        ResultSet resultSet = repositoryBD.getDataSource().executeQuery(sql, id);

        if (resultSet.next()) {
            return mapToMedecin(resultSet);
        }

        return null; // Aucun client trouvé avec cet ID
    }


    }


    // Mapper le ResultSet à l'objet Client
    private Medecin mapToMedecin(ResultSet resultSet) throws SQLException {
        Medecin medecin = new Medecin();
        medecin.setId(resultSet.getLong("id"));
        medecin.setNom(resultSet.getString("nom"));
        medecin.setPrenom(resultSet.getString("prenom"));
        

        // Mapper l'utilisateur associé
        Rv rv = new Rv();
        rv.setId(resultSet.getLong("rv_id"));
        medecin.setRv(rv);

        return medecin;
    }



    
    






