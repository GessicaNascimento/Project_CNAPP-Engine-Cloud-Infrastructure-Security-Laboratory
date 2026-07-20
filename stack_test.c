#include <stdio.h>

void estourar_stack(int contador) {
    // Aloca 100KB a cada chamada recursiva diretamente na Stack
    char buffer[102400]; 
    buffer[0] = 'A'; // Força a escrita física na página de memória
    printf("Frames empilhados: %d\n", contador);
    estourar_stack(contador + 1); // Recursão infinita
}

int main() {
    estourar_stack(1);
    return 0;
}


