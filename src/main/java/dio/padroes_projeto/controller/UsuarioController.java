package dio.padroes_projeto.controller;

import dio.padroes_projeto.model.Usuario;
import dio.padroes_projeto.repository.UsuarioRepository;
import dio.padroes_projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/todos-usuarios")
    public ResponseEntity<?> todosUsuarios() {
        if (usuarioService.buscarTodos().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum usuario encontrado");
        }
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> usuarioEspecifico(@PathVariable("id") Integer idUsuario){
        Optional<Usuario> usuarioId = usuarioService.buscarPorid(idUsuario);

        if (usuarioId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum usuario encontrado");
        }

        return ResponseEntity.ok(usuarioId.get());
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Integer idUsuario){

        Optional<Usuario> usuarioId = usuarioService.buscarPorid(idUsuario);

        if (usuarioId.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum usuario encontrado");
        }
        usuarioService.deletarPorId(idUsuario);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Usuario" + usuarioId.get().getNomeUsuario() +"deletado");
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario Criado" + novoUsuario.getNomeUsuario());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable("id") Integer idUsuario, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioId = usuarioService.buscarPorid(idUsuario);

        if (usuarioId.isPresent()){
            Usuario usuario_ = usuarioId.get();
            usuario_.setNomeUsuario(usuario.getNomeUsuario());
            usuario_.setEmailUsuario(usuario.getEmailUsuario());
            usuario_.setSenhaUsuario(usuario.getSenhaUsuario());
            usuarioService.criarUsuario(usuario_);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Usuario atualizado" + usuario_.getNomeUsuario());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuario não existe");
    }
}