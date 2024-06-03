package br.ada.caixa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {

    private String nome;
    private LocalDate dataNascimento;

    @Override
    @Transient
    public String getTipo() {
        return "PF";
    }
}
