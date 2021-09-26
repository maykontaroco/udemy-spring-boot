package io.github.maykontaroco.clientes.model.repository;

import io.github.maykontaroco.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
