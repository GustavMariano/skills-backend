package desafiotecnico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import desafiotecnico.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    UserDetails findByLogin(String login);
}
