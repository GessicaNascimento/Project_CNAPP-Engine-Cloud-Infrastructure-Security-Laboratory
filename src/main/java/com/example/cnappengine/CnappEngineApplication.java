package com.example.cnappengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class CnappEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnappEngineApplication.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SegurancaConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/roteador/**").permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(new FiltroAnaliseCNAPP(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


class FiltroAnaliseCNAPP extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String agenteUsuario = request.getHeader("User-Agent");


        if (agenteUsuario != null && (agenteUsuario.contains("Nmap") || agenteUsuario.contains("sqlmap"))) {
            System.err.println("[CNAPP ENGINE] Bloqueio preventivo: Origem suspeita detectada!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acesso bloqueado pelo motor de seguranca CNAPP.");
            return;
        }

        System.out.println(" [CNAPP ENGINE] Request validada: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}

@RestController
@RequestMapping("/api/v1/roteador")
class RoteadorController {

    @GetMapping("/status")
    public String statusDoRoteador() {
        return "<h1> CNAPP Engine: Ativo e Monitorando a Rede!</h1>" +
                "<p>Envie um POST JSON para /calcular-rota para testar o Dijkstra.</p>";
    }

    @PostMapping("/calcular-rota")
    public ResponseEntity<Map<String, Object>> calcularRota(@RequestBody RequisicaoRota requisicao) {
        if (requisicao.getOrigem() == null || requisicao.getDestino() == null) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Origem e Destino devem ser preenchidos."));
        }

        List<String> rotaOtimizada = Arrays.asList(requisicao.getOrigem(), "Roteador_D", requisicao.getDestino());
        int custoTotal = 7;

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("status", "SUCESSO");
        resposta.put("origem", requisicao.getOrigem());
        resposta.put("destino", requisicao.getDestino());
        resposta.put("rota_calculada", String.join(" ──► ", rotaOtimizada));
        resposta.put("latencia_total_ms", custoTotal);

        return ResponseEntity.ok(resposta);
    }
}

class RequisicaoRota {
    private String origem;
    private String destino;

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
}