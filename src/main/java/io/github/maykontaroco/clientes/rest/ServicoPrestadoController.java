package io.github.maykontaroco.clientes.rest;

import io.github.maykontaroco.clientes.model.entity.Cliente;
import io.github.maykontaroco.clientes.model.entity.ServicoPrestado;
import io.github.maykontaroco.clientes.model.repository.ClienteRepository;
import io.github.maykontaroco.clientes.model.repository.ServicoPrestadoRepository;
import io.github.maykontaroco.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.maykontaroco.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }
}
