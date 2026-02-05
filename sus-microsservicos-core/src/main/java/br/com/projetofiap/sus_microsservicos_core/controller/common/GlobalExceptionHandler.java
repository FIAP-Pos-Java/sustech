package br.com.projetofiap.sus_microsservicos_core.controller.common;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.ErroRespostaDTO;
import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioCadastradoException;
import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioCadastradoException.class)
    public ResponseEntity<ErroRespostaDTO> handlerUsuarioCadastrado(UsuarioCadastradoException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(UsuarioInexistenteException.class)
    public ResponseEntity<ErroRespostaDTO> handlerUsuarioInexistente(UsuarioInexistenteException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ErroRespostaDTO> handlerClassCast(ClassCastException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
