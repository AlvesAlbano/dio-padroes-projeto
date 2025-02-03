package dio.padroes_projeto.service;

import dio.padroes_projeto.model.Usuario;
import dio.padroes_projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorid(Integer id){
        return usuarioRepository.findById(id);
    }

    public void deletarPorId(Integer id){
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario não encontrado"));

        usuario.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuario.setEmailUsuario(usuarioAtualizado.getEmailUsuario());
        usuario.setSenhaUsuario(usuarioAtualizado.getSenhaUsuario());

        return usuarioRepository.save(usuarioAtualizado);
    }
}