package desafiotecnico.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import desafiotecnico.api.model.Usuario;
import desafiotecnico.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(String login, String password) {
        if (userRepository.findByLogin(login) != null) {
            throw new IllegalArgumentException("Login já existe");
        }

        Usuario user = new Usuario();
        user.setLogin(login);
        user.setSenha(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
