package br.com.projetofiap.sus_microsservicos_core.model.factory;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.CriarUsuarioDTO;
import br.com.projetofiap.sus_microsservicos_core.model.Usuario;

public interface IUsuarioFactory<T extends CriarUsuarioDTO> {
    Usuario criandoUsuario(T dto);
}
