# CNAPP Engine — Cloud Infrastructure Security Laboratory
### Cloud-Native Security Platform for Infrastructure Protection, Distributed Systems and Software Engineering

A modular engineering project that explores the complete software stack—from operating system primitives and low-level programming to cloud-native applications deployed on AWS.

---

## Overview
The **CNAPP Engine** is a modular engineering project designed to study and implement the core technologies involved in building cloud-native security platforms. Rather than relying exclusively on high-level frameworks, the project progressively constructs each software layer from fundamental computing concepts, including operating system primitives, custom data structures, concurrent execution, storage engines, and cloud deployment.

The objective is not only to build a functional application, but also to demonstrate a deep understanding of how modern software systems are designed, implemented and integrated across multiple abstraction layers.

The project is organized into five independent yet connected engineering phases, covering topics such as:

* Operating Systems
* Systems Programming
* Data Structures
* Concurrency
* Storage Engines
* Distributed Systems
* Cloud Computing
* Software Architecture
* Infrastructure Engineering

---

## Engineering Goals
The project was designed with the following objectives:

* Understand software systems from low-level execution to cloud deployment;
* Implement fundamental data structures without relying on Java collections;
* Explore process creation and inter-process communication using POSIX system calls;
* Design concurrent execution models for distributed workloads;
* Build storage and indexing mechanisms optimized for performance;
* Deploy cloud-native services using Docker and AWS.

---

## Technology Stack

| Layer | Technologies |
| :--- | :--- |
| **Low-Level Programming** | C (POSIX), Linux System Calls, Memory Management |
| **Programming Language** | Java |
| **Data Structures** | Custom ArrayList, LinkedList, HashMap, Binary Search Tree |
| **Concurrency** | Java Threads, Synchronization, Locks |
| **Algorithms** | Graphs, Dijkstra, Priority Queue |
| **Persistence** | Binary Storage Engine, Hybrid Cache, Inverted Index |
| **Backend** | Spring Boot |
| **Infrastructure** | Docker, AWS, Maven |
| **Architecture** | Cloud-Native Microservices |

---

## Global Architecture

```text
Phase 1 ─ Operating System Foundations
        │
        ▼
Phase 2 ─ Custom Data Structures
        │
        ▼
Phase 3 ─ Concurrent Processing Engine
        │
        ▼
Phase 4 ─ Storage & Caching Layer
        │
        ▼
Phase 5 ─ Cloud-Native REST Platform


Each phase is intentionally independent while contributing to the overall architecture of the platform.

---

## Project Phases

### Phase 1 — Operating System Foundations
* **Language:** C

Implementation of operating system primitives using POSIX system calls.

Topics include:
* Shell implementation
* Command parser
* Abstract Syntax Tree (AST)
* Process creation
* `fork()`
* `execvp()`
* `pipe()`
* File descriptors
* Memory management

### Phase 2 — Core Data Structures
* **Language:** Java

Implementation of fundamental data structures without using `java.util`.

Implemented structures include:
* `MyArrayList`
* `MyLinkedList`
* `MyHashMap`
* `MyBinarySearchTree`

Focus:
* Complexity analysis
* Memory allocation
* Pointer manipulation
* Algorithmic efficiency

### Phase 3 — Concurrent Processing
Implementation of concurrent execution models for distributed workloads.

Topics:
* Thread synchronization
* Race condition prevention
* Locking strategies
* Priority Queue (Min Heap)
* Graph traversal
* Dijkstra shortest path algorithm

### Phase 4 — Storage Engine
Design of a lightweight persistence layer.

Components:
* Binary Storage Engine
* Hybrid Cache
* Inverted Index
* Constant-time lookup structures

Objectives:
* Reduce disk I/O
* Improve lookup performance
* Optimize memory utilization

### Phase 5 — Cloud-Native Platform
Deployment of the platform as containerized services.

Technologies:
* Spring Boot
* REST APIs
* Docker
* AWS EC2
* AWS CloudShell
* VPC
* Maven

Special attention is given to multi-platform compatibility (ARM64 and AMD64 environments).

---

## Engineering Decisions
Some architectural decisions intentionally prioritize learning and system transparency over convenience.

Examples include:
* Using C before higher-level abstractions to understand operating system behavior.
* Implementing core data structures manually instead of relying on `java.util`.
* Designing custom indexing mechanisms before integrating external databases.
* Solving ARM64/AMD64 deployment compatibility during cloud deployment.

All significant architectural decisions are documented in the project's Decision Notes.

---

## Repository Structure

```text
CNAPP-Engine/
├── README.md
├── phase-1/
│   ├── docs/
│   └── src/
├── phase-2/
│   ├── docs/
│   └── src/
├── phase-3/
│   ├── docs/
│   └── src/
├── phase-4/
│   ├── docs/
│   └── src/
└── phase-5/
    ├── docs/
    └── src/

## Documentation
The repository includes complementary engineering documentation:

* Architecture Documentation
* Engineering Reports
* Development Reports
* Decision Notes
* Design Notes
* Technical Studies

These documents describe the rationale behind architectural decisions, implementation trade-offs and engineering choices throughout the project.

