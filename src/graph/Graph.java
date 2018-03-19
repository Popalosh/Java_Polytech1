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

    private boolean checkEmpty() {
        return directedGraph.isEmpty();
    }

    public void addVertex(String vertexName) {
        if (!checkEmpty()) {
            if (!directedGraph.containsKey(vertexName)) {
                directedGraph.put(vertexName, new HashMap<>());
            } else throw new IllegalArgumentException("Вершина уже существует");
        } else {
            directedGraph.put(vertexName, new HashMap<>());
        }
    }

    public void addArc(String vertexName1, String vertexName2, Integer arcWeight) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(vertexName1) && directedGraph.containsKey(vertexName2)) {
                if (directedGraph.get(vertexName1).isEmpty()) {
                    directedGraph.get(vertexName1).put(vertexName2, arcWeight);
                } else if (!directedGraph.get(vertexName1).containsKey(vertexName2) ) {
                    directedGraph.get(vertexName1).put(vertexName2, arcWeight);
                } else throw new IllegalArgumentException("Дуга уже существует");
            } else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void deleteVertex(String vertexName) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(vertexName)) {
                directedGraph.remove(vertexName, directedGraph.get(vertexName));

                Map<String, Pair<String, Integer>> names = new HashMap<>();

                for (String name : directedGraph.keySet()) {
                    for ( String key : directedGraph.get(name).keySet()) {
                        if (key.equals(vertexName)) {
                            names.put(name, new Pair<>(key,directedGraph.get(name).get(key)));
                        }
                    }
                }

                for (String name : names.keySet()) {
                    directedGraph.get(name).remove( names.get(name).getKey(),
                            names.get(name).getValue() );
                }

            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void deleteArc(String vertexName1, String vertexName2) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(vertexName1)) {
                for (Map<String, Integer> neighbors : directedGraph.values()) {
                    if (neighbors == directedGraph.get(vertexName1)) {
                        for (String key : neighbors.keySet()) {
                            if (key.equals(vertexName2)) {
                                neighbors.remove(key,neighbors.get(key));
                                break;
                            }
                        }
                    }
                }
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void renameVertex(String oldName, String newName) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(oldName) && !directedGraph.containsKey(newName)) {

                Map<String, Pair<String, Integer>> arcs = new HashMap<>();

                for (String vertex : directedGraph.keySet()) {
                    if (!vertex.equals(oldName)) {
                        for (String key : directedGraph.get(vertex).keySet()) {
                            if (key.equals(oldName)) {
                                arcs.put(vertex, new Pair<>(key,directedGraph.get(vertex).get(key)));
                            }
                        }
                    }
                }


                for (String name : arcs.keySet()) {
                    Integer oldWeight = arcs.get(name).getValue();
                    directedGraph.get(name).remove(oldName,oldWeight);
                    directedGraph.get(name).put(newName, oldWeight);
                }

                Map<String, Integer> oldNeighbors = directedGraph.get(oldName);
                directedGraph.remove(oldName, directedGraph.get(oldName));
                directedGraph.put(newName, oldNeighbors);

            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void reweight(String vertexName1, String vertexName2, Integer newWeight) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(vertexName1)) {
                Map<String, Integer> neighbors = directedGraph.get(vertexName1);
                Map<String, Integer> map = new HashMap<>();
                for (String key : neighbors.keySet()) {
                    if (key.equals(vertexName2)) {
                        map.put(key,neighbors.get(key));
                        break;
                    }
                }
                for (String key : map.keySet()) {
                    neighbors.remove(key,map.get(key));
                    neighbors.put(key, newWeight);
                }
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public Map<String, Integer> getOutputArcs(String vertexName) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(vertexName)) {
                return directedGraph.get(vertexName);
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public Map<String, Integer> getInputArcs(String vertexName) {
        if (!checkEmpty()) {
            if (directedGraph.containsKey(vertexName)) {
                Map<String, Integer> map = new HashMap<>();
                for (String otherVertex : directedGraph.keySet()) {
                    for (String key : directedGraph.get(otherVertex).keySet()) {
                        if (!otherVertex.equals(vertexName) && key.equals(vertexName)) {
                            map.put(otherVertex,directedGraph.get(otherVertex).get(key));
                        }
                    }
                }
                return map;
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
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