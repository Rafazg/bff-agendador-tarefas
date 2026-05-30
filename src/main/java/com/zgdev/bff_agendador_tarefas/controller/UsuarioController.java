package com.zgdev.bff_agendador_tarefas.controller;


import com.zgdev.bff_agendador_tarefas.business.UsuarioService;
import com.zgdev.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.zgdev.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.zgdev.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.zgdev.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.zgdev.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastor e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }


    @PostMapping("/login")
    @Operation(summary = "Login de Usuário", description = "Login do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                         description = "Usuário logado com sucesso" ),

            @ApiResponse(responseCode = "401",
                         description = "Credenciais Invalidas"),

            @ApiResponse(responseCode = "500",
                         description = "Erro de Servidor" )
    })
    public String login(@RequestBody UsuarioDTORequest usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }


    @GetMapping
    @Operation(summary = "Buscar dados de usuários por Email", description = "Buscar dados de usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuário Encontrado" ),

            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }


    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuário por Email", description = "Buscar dados de usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuário Deletado" ),

            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return  ResponseEntity.ok().build();
    }


    @PutMapping
    @Operation(summary = "Atualizar dados de Usuário", description = "Atualizar dados de Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuário Salvo com sucesso" ),

            @ApiResponse(responseCode = "404",
                    description = "Usuário não cadastrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar Endereço do Usuário", description = "Atualizar dados de endereço do Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Endereço atualizado com sucesso" ),

            @ApiResponse(responseCode = "404",
                    description = "Endereço já cadastrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar Telefone do Usuário", description = "Atualizar dados do telefone do Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Telefone atualizado com sucesso" ),

            @ApiResponse(responseCode = "404",
                    description = "Telefone já cadastrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar Endereço do Usuário", description = "Castrar dados do endereço do Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Endereço Cadastrado com sucesso" ),

            @ApiResponse(responseCode = "404",
                    description = "Endereço já cadastrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar Telefone do Usuário", description = "Castrar dados do Telefone do Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Telefone Cadastrado com sucesso" ),

            @ApiResponse(responseCode = "404",
                    description = "Telefone já cadastrado"),

            @ApiResponse(responseCode = "500",
                    description = "Erro de Servidor" )
    })
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                        @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }
}
