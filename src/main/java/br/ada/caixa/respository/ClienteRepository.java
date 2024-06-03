package br.ada.caixa.respository;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("select c from ClientePF c")
    List<ClientePF> findAllPF();

    @Query("select c from ClientePJ c")
    List<ClientePJ> findAllPJ();

    @Query("select c from ClientePF c where c.documento = :cpf")
    Optional<ClientePF> findByCpf(@Param("cpf") String cpf);

}
