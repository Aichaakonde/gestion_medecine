package com.example.services;

import com.example.entities.Medecin;
import com.example.entities.Rv;
import com.example.repository.MedecinRepository;

import java.sql.SQLException;
import java.util.List;


public class MedecinService {
    private final MedecinRepository medecinRepository;

    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    public void addClient(Medecin medecin) throws SQLException {
        medecinRepository.addMedecin(medecin);
    }

    public List<Medecin> listClients() throws SQLException {
        return medecinRepository.getAllMedecins();
    }

    public void addMedecin(Medecin medecin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMedecin'");
    }

    public void addRv(Rv rv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRv'");
    }

    // public Medecin findClientByPhone(String phone) throws SQLException {
    //     return medecinRepository.getClientByPhone(phone);
    // }

    
}







