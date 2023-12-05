package com.example.demo.domain.service.impl;

import com.example.demo.domain.domainException.RegrasDeNegocioException;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.repository.EnderecoRepository;
import com.example.demo.domain.repository.UsuarioRepository;
import com.example.demo.domain.service.UsuarioService;
import com.example.demo.security.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private final int MINUTOS_PARA_NOVA_TENTATIVA = 1;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = false)
    @Override
    public Usuario criarNovoUsuario(Usuario usuario) {
        // Verificar se já existe esse usuário, se não existir então salva, caso contrário gera mensagem de aviso
        if (repository.findByCpf(usuario.getCpf()).isPresent()) {
            // Existe
            throw new RegrasDeNegocioException("O usuário com cpf " + usuario.getCpf() + " já está cadastrado em nosso sistema!");
        } else {
            Endereco endereco = usuario.getEndereco();
            usuario.setEndereco(null);
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            String firstTokenUser = JwtToken.generateTokenJWT(usuario);
            usuario.setToken(firstTokenUser);
            Usuario usuarioSalvo = repository.save(usuario);
            endereco.setUsuario(usuarioSalvo);
            Endereco enderecoSalvo = enderecoRepository.save(endereco);
            usuarioSalvo.setEndereco(enderecoSalvo);
            return usuarioSalvo;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarUsuarioPorId(Long idUsuario) {
        repository.deleteById(idUsuario);
    }

    @Transactional(readOnly = false)
    @Override
    public Usuario atualizarUsuarioPorId(Usuario usuario) {
        if (repository.findById(usuario.getIdUsuario()).isPresent()) {
            // Existe o usuario para atualizar
            return repository.save(usuario);
        } else {
            // Não existe o usuário para atualizar
            throw new RegrasDeNegocioException("Não existe usuário com id " + usuario.getIdUsuario());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return repository.findById(idUsuario).orElseThrow(() -> new RegrasDeNegocioException("Não existe usuário com id " + idUsuario));
    }

    @Override
    public Usuario buscarUsuarioPorNomeDeUsuario(String nomeDeUsuario) {
        return repository.findByUsuario(nomeDeUsuario).orElseThrow(() -> new RegrasDeNegocioException("Username " + nomeDeUsuario + " não existe!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarTodosUsuarios() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public int tentativasDeLoginDoUsuario(String username) {
        return repository.tentativasDoUsuario(username);
    }

    @Transactional(readOnly = false)
    @Override
    public Date liberarLogin(String nomeDeUsuario) {
        // obter data e hora atuais
        LocalDateTime agora = LocalDateTime.now();
        // Adicionar minutos
        LocalDateTime minutos = agora.plusMinutes(MINUTOS_PARA_NOVA_TENTATIVA);
        // data de liberação
        Date dataDeLiberacao = Date.from(minutos.toInstant(ZoneOffset.of("-03:00")));
        repository.updateReleaseDate(dataDeLiberacao, nomeDeUsuario);
        return dataDeLiberacao;
    }

    @Transactional(readOnly = true)
    @Override
    public Date obterDataDeLiberacaoLogin(String nomeDeUsuario) {
        return repository.getDateReleaseLogin(nomeDeUsuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean verificarDataDeLiberacaoLogin(String nomeDeUsuario) {
        return repository.getDateReleaseLogin(nomeDeUsuario) != null;
    }

    @Transactional(readOnly = false)
    @Override
    public void redefinirTentativasELiberarLogin(String nomeDeUsuario) {
        repository.resetAttemptsAndReleaseLogin(nomeDeUsuario);
    }

    @Transactional(readOnly = false)
    @Override
    public Usuario login(Usuario user) {
        user.setToken(JwtToken.generateTokenJWT(user));
        user.setTentativasDeLogin(0);
        user.setDataDeLiberacao(null);
        return repository.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateAttempts(String username) {
        int attempts = repository.attemptsUser(username) + 1;
        repository.updateAttemptsUser(attempts, username);
        return repository.attemptsUser(username);
    }

}
