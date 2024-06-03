package br.ada.caixa.entity;

import br.ada.caixa.enums.StatusCliente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_pessoa", discriminatorType = DiscriminatorType.STRING, length = 10)
public abstract class Cliente {

    @Id
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusCliente status;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Conta> contas;

    @Transient
    public abstract String getTipo();

}
