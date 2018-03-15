import javafx.util.Pair;

import java.util.*;

public class Graph {

    private Map<String, List<Pair<String, Integer>>> directedGraph;

    Graph() {
        directedGraph = new HashMap<>();
    }

    Graph(Map<String, List<Pair<String, Integer>>> directedGraph) {
        this.directedGraph = directedGraph;
    }

    private boolean nullCheck() {
        return directedGraph == null;
    }

    public void addVertex(String vertexName) {
        if (!nullCheck()) {
            if (!directedGraph.containsKey(vertexName)) {
                directedGraph.put(vertexName, new ArrayList<>());
            } else throw new IllegalArgumentException("Вершина уже существует");
        } else {
            directedGraph.put(vertexName, new ArrayList<>());
        }
    }

    public void addArc(String vertexName1, String vertexName2, Integer arcWeight) {
        if (!nullCheck()) {
            if (directedGraph.containsKey(vertexName1) && directedGraph.containsKey(vertexName2)) {
                if (directedGraph.get(vertexName1).isEmpty()) {
                    directedGraph.get(vertexName1).add(new Pair<>(vertexName2, arcWeight)); // Ошибка должна быть где-то тут
                } else if (!directedGraph.get(vertexName1).contains(new Pair<>(vertexName2, arcWeight))) {
                    directedGraph.get(vertexName1).add(new Pair<>(vertexName2, arcWeight));
                } else throw new IllegalArgumentException("Дуга уже существует");
            } else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void deleteVertex(String vertexName) {
        if (!nullCheck()) {
            if (directedGraph.containsKey(vertexName)) {
                directedGraph.remove(vertexName, directedGraph.get(vertexName));

                Map<String, Pair<String, Integer>> names = new HashMap<>();

                for (String name : directedGraph.keySet()) {
                    for (Pair<String, Integer> pair : directedGraph.get(name)) {
                        if (pair.getKey().equals(vertexName)) {
                            names.put(name, pair);
                        }
                    }
                }

                for (String name : names.keySet()) {
                    directedGraph.get(name).remove(names.get(name));
                }

            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void deleteArc(String vertexName, Integer arcWeight) {
        if (!nullCheck()) {
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
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void renameVertex(String oldName, String newName) {
        if (!nullCheck()) {
            if (directedGraph.containsKey(oldName) && !directedGraph.containsKey(newName)) {

                Map<String, Pair<String, Integer>> arcs = new HashMap<>();

                for (String vertex : directedGraph.keySet()) {
                    if (!vertex.equals(oldName)) {
                        for (Pair<String, Integer> pair : directedGraph.get(vertex)) {
                            if (pair.getKey().equals(oldName)) {
                                arcs.put(vertex, pair);
                            }
                        }
                    }
                }


                for (String name : arcs.keySet()) {
                    Integer oldWeight = arcs.get(name).getValue();
                    directedGraph.get(name).remove(directedGraph.get(name).indexOf(arcs.get(name)));
                    directedGraph.get(name).add(new Pair<>(newName, oldWeight));
                }

                List<Pair<String, Integer>> oldNeighbors = directedGraph.get(oldName);
                directedGraph.remove(oldName, directedGraph.get(oldName));
                directedGraph.put(newName, oldNeighbors);

            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public void reweight(String vertexName, Integer oldWeight, Integer newWeight) {
        if (!nullCheck()) {
            if (directedGraph.containsKey(vertexName)) {
                List<Pair<String, Integer>> neighbors = directedGraph.get(vertexName);
                List<Pair<String, Integer>> list = new ArrayList<>();
                for (Pair<String, Integer> neighbor : neighbors) {
                    if (neighbor.getValue().equals(oldWeight)) {
                        list.add(neighbor);
                        break;
                    }
                }
                for (Pair<String, Integer> neighbor : list) {
                    neighbors.remove(neighbor);
                    neighbors.add(new Pair<>(neighbor.getKey(), newWeight));
                }
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public List<Pair<String, Integer>> getOutputArcs(String vertexName) {
        if (!nullCheck()) {
            if (directedGraph.containsKey(vertexName)) {
                return directedGraph.get(vertexName);
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
    }

    public List<Pair<String, Integer>> getInputArcs(String vertexName) {
        if (!nullCheck()) {
            if (directedGraph.containsKey(vertexName)) {
                List<Pair<String, Integer>> newList = new ArrayList<>();
                for (String otherVertex : directedGraph.keySet()) {
                    for (Pair<String, Integer> neighbor : directedGraph.get(otherVertex)) {
                        if (!otherVertex.equals(vertexName) && neighbor.getKey().equals(vertexName)) {
                            newList.add(new Pair<>(otherVertex, neighbor.getValue()));
                        }
                    }
                }
                return newList;
            } else throw new IllegalArgumentException("Вершина не найдена");
        } else throw new IllegalArgumentException("Граф пустой");
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