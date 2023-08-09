package desafiotecnico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import desafiotecnico.api.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    
}
