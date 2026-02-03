package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarEnderecoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.EnderecoMapper;
import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public Page<BuscarEnderecoDTO> buscarTodosEnderecos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Endereco> enderecos = this.enderecoRepository.findAll(pageable);
        Page<BuscarEnderecoDTO> enderecosDTO = enderecos.map(enderecoMapper::toDTO);
        return enderecosDTO;
    }
}
