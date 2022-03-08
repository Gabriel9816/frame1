package br.edu.ifms.frame1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.frame1.model.Prod;
import br.edu.ifms.frame1.repository.ProdRepository;

@Service
public class ProdService {
    @Autowired
    private ProdRepository prodRepository;


    public List<Prod> getProds() {

        return this.prodRepository.findAll();
    }
}
