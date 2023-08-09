package desafiotecnico.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import desafiotecnico.api.model.Usuario;
import desafiotecnico.api.model.UsuarioSkill;

public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Long>{
    
    @Query("SELECT us FROM UsuarioSkill us WHERE us.usuario = :usuario")
    List<UsuarioSkill> findByUsuario(Usuario usuario);
}
