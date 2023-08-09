package desafiotecnico.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafiotecnico.api.dto.UsuarioRequestDTO;
import desafiotecnico.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar um novo usuário",
               description = "Cria um novo usuário no sistema com base nas informações fornecidas.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso.",
                                content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
                   @ApiResponse(responseCode = "400", description = "Já existe um usuário com o mesmo login.")
               })
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioRequestDTO usuarioRequest) {
        try {
            usuarioService.registrarUsuario(usuarioRequest.login(), usuarioRequest.senha());
            return ResponseEntity.ok("Usuário registrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Esse login já existe");
        }
    }
}

