package desafiotecnico.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafiotecnico.api.model.Skill;
import desafiotecnico.api.repository.SkillRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill existingSkill = skillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habilidade não encontrada com ID: " + id));

        existingSkill.setUrl(updatedSkill.getUrl());
        existingSkill.setNome(updatedSkill.getNome());
        existingSkill.setDescricao(updatedSkill.getDescricao());

        return skillRepository.save(existingSkill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
