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
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente {

    private String nomeFantasia;
    private String razaoSocial;

    @Override
    @Transient
    public String getTipo() {
        return "PJ";
    }

}
