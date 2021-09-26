package io.github.maykontaroco.clientes.model.repository;

import io.github.maykontaroco.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
