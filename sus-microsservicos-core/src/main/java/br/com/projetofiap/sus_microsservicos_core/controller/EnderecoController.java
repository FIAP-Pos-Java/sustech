package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarEnderecoDTO;
import br.com.projetofiap.sus_microsservicos_core.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final Logger logger = LoggerFactory.getLogger(EnderecoController.class);
    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<Page<BuscarEnderecoDTO>> buscarTodosEnderecos(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        this.logger.info("GET -> /api/v1/enderecos?page={}&size={}", page, size);
        Page<BuscarEnderecoDTO> enderecosDTO = this.enderecoService.buscarTodosEnderecos(page, size);
        return new ResponseEntity(enderecosDTO.getContent(), HttpStatus.OK);
    }
}
