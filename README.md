# 🧩 Graph Algorithms Project

This Java project implements several fundamental graph algorithms, including **topological sorting**, **shortest and longest path in a DAG**, **strongly connected components (SCC)**, and **graph condensation**.

---

## 📂 Project Structure

main/java/org/example/
├── Condensation.java
├── DAGShortestLongest.java
├── Graph.java
├── GraphReader.java
├── KahnTopo.java
├── Main.java
└── TarjanSCC.java

resources/data/
├── small1.json
├── small2.json
├── small3.json
├── medium1.json
├── medium2.json
├── medium3.json
├── large1.json
├── large2.json
└── large3.json

test/java/org/example/
├── DAGShortestLongestTest.java
├── SCCFinderTest.java
└── TopoSortTest.java

---

## ⚙️ How to Run

1. Open the project in **IntelliJ IDEA** (or any Java IDE).  
2. Make sure **Java 17+** is installed.  
3. Add **json-simple-1.1.1.jar** to the project libraries.  
4. Run **Main.java**.  

---

## 🧠 Features Overview

| Class                  | Description |
|------------------------|-------------|
| `Graph`                | Core graph structure (adjacency list, weights, etc.) |
| `GraphReader`          | Loads a graph from JSON files in `resources/data/` |
| `TarjanSCC`            | Finds strongly connected components |
| `Condensation`         | Builds the condensed DAG of SCCs |
| `KahnTopo`             | Performs topological sorting on DAG |
| `DAGShortestLongest`   | Computes shortest and longest paths in DAG |
| `Main`                 | Runs all algorithms together |

---

## 🧪 Tests

- `TopoSortTest.java` — verifies topological sort correctness  
- `DAGShortestLongestTest.java` — verifies shortest/longest paths in DAG  
- `SCCFinderTest.java` — verifies SCC detection (Tarjan algorithm)  

---

## 📌 Notes

- The project uses **JSON datasets** for graph inputs.  
- Designed for **directed graphs**, especially DAGs.  
- Strongly connected components are compressed into a DAG for easier analysis.  
