#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum { COMANDO_SIMPLES, PIPE } TipoNo;

typedef struct {
    TipoNo tipo;
    char *comando;       // Preenchido se for COMANDO_SIMPLES
    int esquerdo_idx;    // Índice do filho esquerdo (se for PIPE)
    int direito_idx;     // Índice do filho direito (se for PIPE)
} NoAST;

// Pool de nós estático (Garante complexidade de memória estável e zero leaks)
NoAST pool_de_nos[32];
int nos_contados = 0;

// Função para criar nó de Comando
int criar_no_comando(char *cmd) {
    int idx = nos_contados++;
    pool_de_nos[idx].tipo = COMANDO_SIMPLES;
    pool_de_nos[idx].comando = cmd;
    pool_de_nos[idx].esquerdo_idx = -1;
    pool_de_nos[idx].direito_idx = -1;
    return idx;
}

// Função para criar nó de Pipe (Operador pai)
int criar_no_pipe(int esquerdo, int direito) {
    int idx = nos_contados++;
    pool_de_nos[idx].tipo = PIPE;
    pool_de_nos[idx].comando = NULL;
    pool_de_nos[idx].esquerdo_idx = esquerdo;
    pool_de_nos[idx].direito_idx = direito;
    return idx;
}

// Percurso da Árvore (Avaliador Sintático Recursivo)
void imprimir_ast(int no_idx) {
    if (no_idx == -1) return;

    NoAST no = pool_de_nos[no_idx];

    if (no.tipo == COMANDO_SIMPLES) {
        printf("Executar Comando: [%s]\n", no.comando);
    } 
    else if (no.tipo == PIPE) {
        printf("Detectado Operador: [ PIPE | ]\n");
        printf("  -> Lado Esquerdo (Envia dados):\n     ");
        imprimir_ast(no.esquerdo_idx);
        printf("  -> Lado Direito (Recebe dados):\n     ");
        imprimir_ast(no.direito_idx);
    }
}

int main() {
    // Simulando uma string que veio do terminal
    char comando_terminal[] = "ls -la | grep shell | exit";
    printf("String original: %s\n\n", comando_terminal);

    // --- O MINI PARSER (Simulação da lógica de quebra pelo caractere '|') ---
    // Encontrando o caractere Pipe manualmente
    char *pipe_pos = strchr(comando_terminal, '|');

    if (pipe_pos != NULL) {
        *pipe_pos = '\0'; // Divide a string em duas partes de forma In-place
        char *cmd_esquerda = comando_terminal;
        char *cmd_direita = pipe_pos + 1;

        // Criando os nós na nossa árvore estática
        int no_esq = criar_no_comando(cmd_esquerda);
        int no_dir = criar_no_comando(cmd_direita);
        
        // O nó raiz é o Pipe, conectando os índices inteiros dos nós filhos
        int no_raiz = criar_no_pipe(no_esq, no_dir);

        // Executando a árvore sintática através do interpretador recursivo
        printf("--- Resultado da Análise Sintática (AST) ---\n");
        imprimir_ast(no_raiz);
    }

    return 0;
}
