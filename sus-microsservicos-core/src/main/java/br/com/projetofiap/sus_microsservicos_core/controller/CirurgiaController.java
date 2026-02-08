package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.CirurgiaDTO;
import br.com.projetofiap.sus_microsservicos_core.service.CirurgiaOrquestradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cirurgias")
@RequiredArgsConstructor
public class CirurgiaController {

    private final Logger logger = LoggerFactory.getLogger(CirurgiaController.class);
    private final CirurgiaOrquestradorService orquestradorService;

    @PostMapping
    public ResponseEntity<Void> agendarCirurgia(@Valid @RequestBody CirurgiaDTO dto) {
        logger.info("POST -> /api/v1/cirurgias");
        orquestradorService.agendarCirurgia(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarCirurgia(@PathVariable String id, @Valid @RequestBody CirurgiaDTO dto) {
        logger.info("PUT -> /api/v1/cirurgias/{}", id);
        UUID cirurgiaId = UUID.fromString(id);
        orquestradorService.atualizarCirurgia(cirurgiaId, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelarCirurgia(@PathVariable String id) {
        logger.info("DELETE -> /api/v1/cirurgias/{}", id);
        UUID cirurgiaId = UUID.fromString(id);
        orquestradorService.cancelarCirurgia(cirurgiaId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
