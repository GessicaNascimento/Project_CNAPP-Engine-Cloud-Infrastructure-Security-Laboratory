O **CNAPP Engine** é uma plataforma de segurança nativa em nuvem projetada para analisar, monitorar e proteger infraestruturas distribuídas. O core do projeto foi desenvolvido sem dependências de frameworks externos para as estruturas de dados fundamentais, demonstrando proficiência desde a manipulação de primitivos do sistema operacional até a orquestração de microsserviços em nuvem na AWS.

O ecossistema é dividido em **5 fases estruturais**, cobrindo desde o desenvolvimento de um Shell/Parser em C, passando pela engenharia de coleções de dados exclusivas, ordenação paralela de tarefas, indexação com persistência em disco e fornecimento de APIs via containers Docker na nuvem AWS.

---

## 1. Arquitetura Global do Projeto

```text
[Fase 1: Silicon & OS] ──> [Fase 2: Estruturas Independentes] ──> [Fase 3: Simuladores Paralelos]
      (Linguagem: C)                    (Linguagem: Java)                     (Linguagem: Java)
   Kernel, Shell & Parser            Custom Collections & Maps             Threads & Topologia de Grafos
                                                                                      │
[Fase 5: Nuvem Distribuída] <─── [Fase 4: Infra de Dados & Cache] <───────────────────┘
     (Spring & Docker)                    (Java + SQL)
   Gateway Engine CNAPP              Motor de Persistência Binária

