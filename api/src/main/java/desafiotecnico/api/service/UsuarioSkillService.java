package desafiotecnico.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafiotecnico.api.dto.SkillRequestDTO;
import desafiotecnico.api.model.Skill;
import desafiotecnico.api.model.Usuario;
import desafiotecnico.api.model.UsuarioSkill;
import desafiotecnico.api.repository.SkillRepository;
import desafiotecnico.api.repository.UsuarioRepository;
import desafiotecnico.api.repository.UsuarioSkillRepository;

@Service
public class UsuarioSkillService {
    
    @Autowired
    private UsuarioSkillRepository usuarioSkillRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SkillRepository skillRepository;

    public UsuarioSkill associarSkill(Long usuarioId, Long skillId, int level) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID informado."));
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new IllegalArgumentException("Habilidade não encontrada com o ID informado."));

        UsuarioSkill usuarioSkill = new UsuarioSkill();
        usuarioSkill.setUsuario(usuario);
        usuarioSkill.setSkill(skill);
        usuarioSkill.setLevel(level);
        return usuarioSkillRepository.save(usuarioSkill);
    }

    public List<SkillRequestDTO> listarSkillsDoUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID informado."));
    
        List<UsuarioSkill> usuarioSkills = usuarioSkillRepository.findByUsuario(usuario);
    
        List<SkillRequestDTO> usuarioSkillDTOs = new ArrayList<>();
        for (UsuarioSkill usuarioSkill : usuarioSkills) {
            Skill skill = usuarioSkill.getSkill();
            int level = usuarioSkill.getLevel();
            Long id = usuarioSkill.getId(); 
    
            SkillRequestDTO usuarioSkillDTO = new SkillRequestDTO(id, skill, level);
            usuarioSkillDTO.setUsuarioSkillId(id); 
            usuarioSkillDTOs.add(usuarioSkillDTO);
        }
    
        return usuarioSkillDTOs;
    }

    public UsuarioSkill atualizarNivelHabilidade(Long usuarioSkillId, int novoNivel) {
        UsuarioSkill usuarioSkill = usuarioSkillRepository.findById(usuarioSkillId)
            .orElseThrow(() -> new IllegalArgumentException("Associação de habilidade não encontrada com o ID informado."));

        usuarioSkill.setLevel(novoNivel);
        return usuarioSkillRepository.save(usuarioSkill);
    }

    public void excluirUsuarioSkill(Long usuarioSkillId) {
        UsuarioSkill usuarioSkill = usuarioSkillRepository.findById(usuarioSkillId)
            .orElseThrow(() -> new IllegalArgumentException("Associação de habilidade não encontrada com o ID informado."));

        usuarioSkillRepository.delete(usuarioSkill);
    }
    
}
