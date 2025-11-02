# Smart City / Smart Campus Scheduling – Graph Algorithms Project

This Java project implements several fundamental graph algorithms, including **topological sorting**, **shortest and longest path in a DAG**, **strongly connected components (SCC)**, and **graph condensation**. It is designed for scheduling tasks with dependencies.

---


## 📂 Project Structure

    └── main/
      └── java/
         └── org/example/
     ├── Condensation.java
     ├── DAGShortestLongest.java
     ├── Graph.java
     ├── GraphReader.java
     ├── KahnTopo.java
     ├── Main.java
     └── TarjanSCC.java
     └── resources/
        └── data/
           ├── small1.json
           ├── small2.json
           ├── small3.json
           ├── medium1.json
           ├── medium2.json
           ├── medium3.json
           ├── large1.json
           ├── large2.json
           └── large3.json
    └──test/
     └── java/
        └── org/example/
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

## 📝 Results

### 1️⃣ SCC (Strongly Connected Components)

| Component | Nodes       | Size |
|-----------|------------|------|
| 0         | [3, 2, 1]  | 3    |
| 1         | [0]        | 1    |
| 2         | [7]        | 1    |
| 3         | [6]        | 1    |
| 4         | [5]        | 1    |
| 5         | [4]        | 1    |

- DFS visits: 8  
- Edges seen: 7  

---

### 2️⃣ Condensation (DAG of SCCs)

| Node | Component ID |
|------|--------------|
| 0    | 1            |
| 1    | 0            |
| 2    | 0            |
| 3    | 0            |
| 4    | 5            |
| 5    | 4            |
| 6    | 3            |
| 7    | 2            |

- Condensation nodes: 6  
### 3️⃣ Topological Sort of Components

Topo order of components: [1, 5, 0, 4, 3, 2]
- This is the order to execute SCCs without violating dependencies.  

---

### 4️⃣ Shortest / Longest Paths in DAG

| Component | Shortest distance from 5 | Longest distance from 5 |
|-----------|-------------------------|------------------------|
| 0         | INF                     | -INF                   |
| 1         | INF                     | -INF                   |
| 2         | 8                       | 8                      |
| 3         | 7                       | 7                      |
| 4         | 2                       | 2                      |
| 5         | 0                       | 0                      |

- Critical path (Longest path): `[2]`, length = 8  
- INF / -INF = unreachable nodes from source  

---

## 🔍 Analysis

- **SCC:** The algorithm correctly detected the only cycle `[3,2,1]`.  
- **Topological Sort:** Produces a valid order of components to execute tasks.  
- **DAG-SP:** Shortest and longest paths are consistent with edge weights; the longest path identifies the critical task sequence.  

---

## ✅ Conclusions

- SCC detection is essential to simplify graphs with cycles before scheduling.  
- Topological sort ensures tasks are executed respecting dependencies.  
- DAG shortest/longest path algorithms identify critical paths and minimal durations.  
- Metrics (DFS visits, edge counts, relaxations) help analyze algorithm performance.  

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
