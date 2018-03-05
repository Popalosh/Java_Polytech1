import javafx.util.Pair;

import java.util.*;

public class Graph {

    public Map<String, List<Pair<String, Integer>>> directedGraph;

    Graph(Map<String, List<Pair<String, Integer>>> directedGraph) {
        this.directedGraph = directedGraph;
    }

    public void addVertex(String vertexName, List<Pair<String, Integer>> neighbors) {
        if (!directedGraph.containsKey(vertexName)) {
            directedGraph.put(vertexName, neighbors);
        } else throw new IllegalArgumentException("Вершина уже существует");
    }

    public void addArc(String vertexName1, String vertexName2, Integer arcWeight) {
        if (directedGraph.containsKey(vertexName1) && directedGraph.containsKey(vertexName2)) {
            for (List<Pair<String, Integer>> neighbors : directedGraph.values()) {
                if (neighbors == directedGraph.get(vertexName1)) {
                    Pair<String, Integer> neighbor = new Pair<>(vertexName2, arcWeight);
                    neighbors.add(neighbor);
                }
            }
        } else throw new IllegalArgumentException();
    }

    public void deleteVertex(String vertexName) {
        if (directedGraph.containsKey(vertexName)) {
            directedGraph.remove(vertexName, directedGraph.get(vertexName));
        } else throw new IllegalArgumentException("Вершина не найдена");
    }

    public void deleteArc(String vertexName, Integer arcWeight) {
        if (directedGraph.containsKey(vertexName)) {
            for (List<Pair<String, Integer>> neighbors : directedGraph.values()) {
                if (neighbors == directedGraph.get(vertexName)) {
                    for (Pair<String, Integer> neighbor : neighbors) {
                        if (neighbor.getValue().equals(arcWeight)) {
                            neighbors.remove(neighbor);
                            break;
                        }
                    }
                }
            }
        } else throw new IllegalArgumentException("Вершина не найдена");
    }

    public void renameVertex(String oldName, String newName) {
        if (directedGraph.containsKey(oldName) && !directedGraph.containsKey(newName)) {
            List<Pair<String, Integer>> oldNeighbors = directedGraph.get(oldName);
            directedGraph.remove(oldName, directedGraph.get(oldName));
            directedGraph.put(newName, oldNeighbors);
        } else throw new IllegalArgumentException("Вершина не найдена");
    }

    public void reweight(String vertexName, Integer oldWeight, Integer newWeight) {
        if (directedGraph.containsKey(vertexName)) {
            List<Pair<String, Integer>> neighbors = directedGraph.get(vertexName);
            for (Pair<String, Integer> neighbor : neighbors) {
                if (neighbor.getValue().equals(oldWeight)) {
                    String name = neighbor.getKey();
                    neighbors.remove(neighbor);
                    Pair<String, Integer> newPair = new Pair<>(name, newWeight);
                    neighbors.add(newPair);
                }
            }
        } else throw new IllegalArgumentException("Вершина не найдена");
    }

    public List<Pair<String, Integer>> getOutputArcs(String vertexName) {
        List<Pair<String, Integer>> newList = null;
        if (directedGraph.containsKey(vertexName)) {
            newList = directedGraph.get(vertexName);
        }
        return newList;
    }

    public List<Pair<String, Integer>> getInputArcs(String vertexName) {
        List<Pair<String, Integer>> newList = null;
        if (directedGraph.containsKey(vertexName)) {
            for (String otherVertex : directedGraph.keySet()) {
                for (Pair<String, Integer> neighbor : directedGraph.get(otherVertex)) {
                    if (otherVertex.equals(vertexName) && neighbor.getKey().equals(vertexName)) {
                        newList.add(neighbor);
                    }
                }
            }
        }
        return newList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(directedGraph, graph.directedGraph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directedGraph);
    }

}