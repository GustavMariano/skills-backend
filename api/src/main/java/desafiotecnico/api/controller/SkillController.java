package desafiotecnico.api.controller;

import desafiotecnico.api.model.Skill;
import desafiotecnico.api.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
@SecurityRequirement(name = "bearer-key")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as skills", description = "Retorna uma lista contendo todas as skills cadastradas no sistema.")
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar skill por ID", description = "Retorna uma skill com base no ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao encontrar a skill.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada.")
    })
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        Optional<Skill> skill = skillService.getSkillById(id);
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar nova skill", description = "Cria uma nova skill com base nos dados fornecidos no corpo da requisição, não precisa passar o id, pois ele é gerado automaticamente pelo banco.", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso ao criar a skill.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class)))
    })
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar skill por ID", description = "Atualiza uma skill existente com base no ID fornecido e nos dados fornecidos no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar a skill.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Skill.class))),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada.")
    })
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        Skill updatedSkill = skillService.updateSkill(id, skill);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir skill por ID", description = "Exclui uma skill existente com base no ID fornecido.", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso ao excluir a skill."),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada.")
    })
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
