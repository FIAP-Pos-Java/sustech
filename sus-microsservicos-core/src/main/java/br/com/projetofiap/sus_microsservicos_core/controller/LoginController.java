package br.com.projetofiap.sus_microsservicos_core.controller;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarLoginDTO;
import br.com.projetofiap.sus_microsservicos_core.service.LoginService;
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
@RequestMapping("api/v1/logins")
@RequiredArgsConstructor
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    @GetMapping
    public ResponseEntity<Page<BuscarLoginDTO>> buscarTodosLogins(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        this.logger.info("GET -> /api/v1/logins?page={}&size={}", page, size);
        Page<BuscarLoginDTO> buscarLoginsDTO = this.loginService.buscarTodosLogins(page, size);
        return new ResponseEntity(buscarLoginsDTO.getContent(), HttpStatus.OK);
    }
}
