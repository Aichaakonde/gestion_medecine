package com.example.services;
import com.example.entities.Medecin;
import com.example.entities.Rv;
import com.example.repository.MedecinRepository;
import com.example.repository.RvRepository;

import java.sql.SQLException;
import java.util.List;


public class RvService {
    private final RvRepository rvRepository;

    public RvService(RvRepository rvRepository) {
        this.rvRepository = rvRepository;
    }

    public void addClient(Rv rv) throws SQLException {
        rvRepository.addRv(rv);
    }

    public List<Rv> listRv() throws SQLException {
        return rvRepository.getAllRvs();
    }
    
}
