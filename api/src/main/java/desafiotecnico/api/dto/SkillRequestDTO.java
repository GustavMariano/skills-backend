package desafiotecnico.api.dto;

import desafiotecnico.api.model.Skill;

public record SkillRequestDTO(Long id, Skill skill, int level) {

    public void setUsuarioSkillId(Long id2) {
    }
    
}
