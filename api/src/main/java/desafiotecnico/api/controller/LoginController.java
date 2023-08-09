package desafiotecnico.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafiotecnico.api.config.security.TokenService;
import desafiotecnico.api.dto.LoginRequestDTO;
import desafiotecnico.api.dto.TokenResponseDTO;
import desafiotecnico.api.model.Usuario;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Efetuar login", description = "Endpoint para autenticação de usuários.", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao efetuar login. Retorna o token de acesso."),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. Não foi possível autenticar o usuário.")
    })
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody @Valid LoginRequestDTO loginRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.senha());
        var authentication = manager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT, usuario.getId()));
    }
}