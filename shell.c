#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_LINE 4096   // Tamanho estável de 1 página de memória
#define MAX_ARGS 64     // Limite de argumentos na Stack

// Tokenização In-place (Complexidade Espacial O(1) - Sem malloc)
void parse_command(char *line, char **args) {
    int i = 0;
    char *token = strtok(line, " \t\n\r");
    while (token != NULL && i < MAX_ARGS - 1) {
        args[i] = token; // Ponteiro aponta diretamente para dentro do buffer 'line'
        i++;
        token = strtok(NULL, " \t\n\r");
    }
    args[i] = NULL; // Obrigatório para o término do execvp
}

int main() {
    char line[MAX_LINE];
    char *args[MAX_ARGS];

    while (1) {
        printf("gessica_shell> ");
        fflush(stdout); 

        // Lê a string do teclado diretamente para a Stack
        if (fgets(line, sizeof(line), stdin) == NULL) {
            printf("\nSaindo do Shell...\n");
            break;
        }

        parse_command(line, args);

        if (args[0] == NULL) {
            continue; // Linha vazia
        }

        if (strcmp(args[0], "exit") == 0) {
            printf("Encerrando gessica_shell...\n");
            break;
        }

        // --- BIFURCAÇÃO DE PROCESSOS (FORK) ---
        pid_t pid = fork();

        if (pid < 0) {
            perror("Falha crítica no fork");
        } 
        else if (pid == 0) {
            // ESPAÇO DO FILHO: Transita para o Kernel e carrega o binário pedido
            if (execvp(args[0], args) < 0) {
                printf("Erro: Comando não encontrado: %s\n", args[0]);
            }
            exit(EXIT_FAILURE); // Garante a morte do filho caso o comando falhe
        } 
        else {
            // ESPAÇO DO PAI: O Shell dorme até o filho terminar na CPU
            int status;
            waitpid(pid, &status, 0);
        }
    }
    return 0;
}

