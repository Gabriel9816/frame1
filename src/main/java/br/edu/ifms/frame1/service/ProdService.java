package br.edu.ifms.frame1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.frame1.model.Prod;
import br.edu.ifms.frame1.model.ProdNotFoundException;
import br.edu.ifms.frame1.repository.ProdRepository;

@Service
public class ProdService {
    @Autowired
    private ProdRepository prodRepository;


    public List<Prod> getProds() {
        return this.prodRepository.findAll();
    }
    public void saveProd(Prod prod) {
        this.prodRepository.save(prod);
    }
    public void delete(Prod prod){
        prodRepository.delete(prod);

    }
    public Optional<Prod> findById(UUID id){
        return prodRepository.findById(id);
    }
    public Prod get(UUID prodID) throws ProdNotFoundException{
        Optional<Prod> resultado = this.prodRepository.findById(prodID);
        if(resultado.isPresent()){
            return resultado.get();
        }
        throw new ProdNotFoundException("Não foi encontrado"+ prodID);
    }
}
