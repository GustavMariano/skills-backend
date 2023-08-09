package desafiotecnico.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import desafiotecnico.api.dto.SkillRequestDTO;
import desafiotecnico.api.dto.UsuarioSkillRequestDTO;
import desafiotecnico.api.dto.UsuarioSkillUpdateRequestDTO;
import desafiotecnico.api.model.UsuarioSkill;
import desafiotecnico.api.service.UsuarioSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/usuarioSkill")
@SecurityRequirement(name = "bearer-key")
public class UsuarioSkillController {

    @Autowired
    private UsuarioSkillService usuarioSkillService;

    @PostMapping("/associar")
    @Operation(summary = "Associar Skill a um Usuário",
               description = "Associa uma habilidade (Skill) a um usuário, informando o ID do usuário, o ID da skill e o level da habilidade.",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Associação realizada com sucesso.",
                                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
                   @ApiResponse(responseCode = "400", description = "Usuário ou Skill não encontrados.")
               })
    public ResponseEntity<UsuarioSkill> associarSkill(@RequestBody UsuarioSkillRequestDTO request) {
        Long usuarioId = request.usuarioId();
        Long skillId = request.skillId();
        int level = request.level();

        UsuarioSkill usuarioSkill = usuarioSkillService.associarSkill(usuarioId, skillId, level);

        return new ResponseEntity<>(usuarioSkill, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{id}/skills")
    @Operation(summary = "Listar Skills de um Usuário",
               description = "Retorna todas as habilidades (Skills) associadas a um usuário, informando o ID do usuário.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de Skills do usuário.",
                                content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillRequestDTO.class))),
                   @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
               })
    public ResponseEntity<List<SkillRequestDTO>> listarSkillsDoUsuario(@PathVariable("id") Long usuarioId) {
        List<SkillRequestDTO> usuarioSkills = usuarioSkillService.listarSkillsDoUsuario(usuarioId);
        return new ResponseEntity<>(usuarioSkills, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar level de Habilidade",
               description = "Atualiza o level da habilidade (Skill) de um usuário, informando o ID da associação da skill e o novo level.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Level da habilidade atualizado com sucesso.",
                                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioSkill.class))),
                   @ApiResponse(responseCode = "404", description = "Associação de Skill não encontrada.")
               })
    public ResponseEntity<UsuarioSkill> atualizarNivelHabilidade(
            @PathVariable("id") @Parameter(description = "ID da associação da Skill") Long usuarioSkillId,
            @RequestBody UsuarioSkillUpdateRequestDTO request) {

        int novoNivel = request.novoNivel();
        UsuarioSkill usuarioSkillAtualizado = usuarioSkillService.atualizarNivelHabilidade(usuarioSkillId, novoNivel);

        return new ResponseEntity<>(usuarioSkillAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Associação de Skill",
               description = "Exclui a associação de uma habilidade (Skill) de um usuário, informando o ID da associação.",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Associação excluída com sucesso."),
                   @ApiResponse(responseCode = "404", description = "Associação de Skill não encontrada.")
               })
    public ResponseEntity<Void> excluirUsuarioSkill(@PathVariable("id") @Parameter(description = "ID da associação da Skill") Long usuarioSkillId) {
        usuarioSkillService.excluirUsuarioSkill(usuarioSkillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
