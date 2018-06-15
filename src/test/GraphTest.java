import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph graph;

    @BeforeEach
    void setup() {
        graph = new Graph();
    }

    @Test
    void addVertex() {
        graph.addVertex("A");
        assertEquals(Map.of("A", new HashMap<>()), graph.getDirectedGraph());
    }

    @Test
    void addArc() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        assertEquals(Map.of("A", Map.of("B",3),"B",new HashMap<>()),graph.getDirectedGraph());
    }

    @Test
    void deleteVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        graph.deleteVertex("B");
        assertEquals(Map.of("A",new HashMap<>()),graph.getDirectedGraph());
    }

    @Test
    void deleteArc() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        graph.deleteArc("A","B");
        assertEquals(Map.of("A",new HashMap<>(),"B",new HashMap<>()),graph.getDirectedGraph());
    }

    @Test
    void renameVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        graph.renameVertex("B","D");
        assertEquals(Map.of("A", Map.of("D",3),"D",new HashMap<>()),graph.getDirectedGraph());
    }

    @Test
    void reweight() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        graph.reweight("A","B",1337);
        assertEquals(Map.of("A", Map.of("B",1337),"B",new HashMap<>()),graph.getDirectedGraph());
    }

    @Test
    void getOutputArcs() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        assertEquals(Map.of("B",3),graph.getOutputArcs("A"));
    }

    @Test
    void getInputArcs() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addArc("A" ,"B",3);
        assertEquals(Map.of("A",3),graph.getInputArcs("B"));
    }

}