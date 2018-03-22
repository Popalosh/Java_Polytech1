import javafx.util.Pair;

import java.util.*;

public class Graph {

    private Map<String, Map<String, Integer>> directedGraph;

    Graph() {
        directedGraph = new HashMap<>();
    }

    Graph(Map<String, Map<String, Integer>> directedGraph) {
        this.directedGraph = directedGraph;
    }

    public void addVertex(String vertexName) {
        if (!directedGraph.isEmpty()) {
            directedGraph.put(vertexName, new HashMap<>());
        } else {
            directedGraph.put(vertexName, new HashMap<>());
        }
    }

    public void addArc(String vertexName1, String vertexName2, Integer arcWeight) {
        if (directedGraph.containsKey(vertexName1) && directedGraph.containsKey(vertexName2)) {
            directedGraph.get(vertexName1).put(vertexName2, arcWeight);
        } else throw new IllegalArgumentException();
    }

    public void deleteVertex(String vertexName) {
        directedGraph.remove(vertexName, directedGraph.get(vertexName));

        Map<String, Pair<String, Integer>> names = new HashMap<>();

        for (String name : directedGraph.keySet()) {
            for (String key : directedGraph.get(name).keySet()) {
                if (key.equals(vertexName)) {
                    names.put(name, new Pair<>(key, directedGraph.get(name).get(key)));
                }
            }
        }

        for (String name : names.keySet()) {
            directedGraph.get(name).remove(names.get(name).getKey(),
                    names.get(name).getValue());
        }
    }

    public void deleteArc(String vertexName1, String vertexName2) {
        for (Map<String, Integer> neighbors : directedGraph.values()) {
            if (neighbors == directedGraph.get(vertexName1)) {
                for (String key : neighbors.keySet()) {
                    if (key.equals(vertexName2)) {
                        neighbors.remove(key, neighbors.get(key));
                        break;
                    }
                }
            }
        }
    }

    public void renameVertex(String oldName, String newName) {
        Map<String, Pair<String, Integer>> arcs = new HashMap<>();

        for (String vertex : directedGraph.keySet()) {
            if (!vertex.equals(oldName)) {
                for (String key : directedGraph.get(vertex).keySet()) {
                    if (key.equals(oldName)) {
                        arcs.put(vertex, new Pair<>(key, directedGraph.get(vertex).get(key)));
                    }
                }
            }
        }


        for (String name : arcs.keySet()) {
            Integer oldWeight = arcs.get(name).getValue();
            directedGraph.get(name).remove(oldName, oldWeight);
            directedGraph.get(name).put(newName, oldWeight);
        }

        Map<String, Integer> oldNeighbors = directedGraph.get(oldName);
        directedGraph.remove(oldName, directedGraph.get(oldName));
        directedGraph.put(newName, oldNeighbors);
    }

    public void reweight(String vertexName1, String vertexName2, Integer newWeight) {
        Map<String, Integer> neighbors = directedGraph.get(vertexName1);
        Map<String, Integer> map = new HashMap<>();
        for (String key : neighbors.keySet()) {
            if (key.equals(vertexName2)) {
                map.put(key, neighbors.get(key));
                break;
            }
        }
        for (String key : map.keySet()) {
            neighbors.remove(key, map.get(key));
            neighbors.put(key, newWeight);
        }
    }

    public Map<String, Integer> getOutputArcs(String vertexName) {
        return directedGraph.get(vertexName);
    }

    public Map<String, Integer> getInputArcs(String vertexName) {
        Map<String, Integer> map = new HashMap<>();
        for (String otherVertex : directedGraph.keySet()) {
            for (String key : directedGraph.get(otherVertex).keySet()) {
                if (!otherVertex.equals(vertexName) && key.equals(vertexName)) {
                    map.put(otherVertex, directedGraph.get(otherVertex).get(key));
                }
            }
        }
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Graph)) return false;
        Graph graph = (Graph) o;
        return Objects.equals(directedGraph, graph.directedGraph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directedGraph);
    }
}