package br.edu.ifms.frame1.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //sempre ter esse @
@AllArgsConstructor //sempre ter esse @
@NoArgsConstructor //sempre ter esse @
@Entity
public class Prod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(name = "nomeProd", nullable = false)
    @NotBlank
    String nome;
    String descricao;
    Integer quantidade;
    Double valor;
    String fabricante;
}
