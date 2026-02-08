package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.AssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarAssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.service.AssistenteSocialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/assistentes-sociais")
@RequiredArgsConstructor
public class AssistenteSocialController {

    private final Logger logger = LoggerFactory.getLogger(AssistenteSocialController.class);
    private final AssistenteSocialService assistenteSocialService;

    @GetMapping
    public ResponseEntity<Page<BuscarAssistenteSocialDTO>> buscarTodosAssistentesSociais(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        this.logger.info("GET -> /api/v1/assistentes-sociais?page={}&size={}", page, size);
        Page<BuscarAssistenteSocialDTO> assistentesSociaisDTO = this.assistenteSocialService.buscarTodosAssistentesSociais(page, size);
        return new ResponseEntity(assistentesSociaisDTO.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BuscarAssistenteSocialDTO> buscarAssistenteSocialPorId(
            @PathVariable String id
    ) {
        this.logger.info("GET -> /api/v1/assistentes-sociais/{}", id);
        List<BuscarAssistenteSocialDTO> buscandoAssistenteSocial = this.assistenteSocialService.buscarAssistenteSocialPorId(id);
        return new ResponseEntity(buscandoAssistenteSocial, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarAssistenteSocial(
            @Valid @RequestBody AssistenteSocialDTO dto
    ) {
        this.logger.info("POST -> /api/v1/assistentes-sociais");
        this.assistenteSocialService.cadastrarAssistenteSocial(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarAssistenteSocial(
            @PathVariable("id") String id,
            @Valid @RequestBody AssistenteSocialDTO dto
    ) {
        this.logger.info("PUT -> /api/v1/assistentes-sociais/{}", id);
        this.assistenteSocialService.atualizarAssistenteSocial(id, dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerAssistenteSocial(
            @PathVariable("id") String id
    ) {
        this.logger.info("DELETE -> /api/v1/assistentes-sociais/{}", id);
        this.assistenteSocialService.removerAssistenteSocial(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
