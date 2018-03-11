import javafx.util.Pair;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph graph;

    @Before
    void setUp() { graph = new Graph();
    }

    @Before
    void addVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
    }

    @Before
    void addArc() {
        graph.addArc("A","C",3);
        graph.addArc("A","B",56);
        graph.addArc("C","A",4);
    }

    @Before
    void deleteVertex() {
        graph.deleteVertex("B");
    }

    @Before
    void deleteArc() {
        graph.deleteArc("A",56);
    }

    @Before
    void renameVertex() {
        graph.renameVertex("C","D");
    }

    @Before
    void reweight() {
        graph.reweight("D",4,47);
    }

    @Test
    void getOutputArcs() {
        assertEquals(graph.getOutputArcs("A"), List.of(new Pair<>("D",3)));
        assertEquals(graph.getOutputArcs("D"), List.of(new Pair<>("A",47)));
    }

    @Test
    void getInputArcs() {
        assertEquals(graph.getInputArcs("A"), List.of(new Pair<>("D",47)));
        assertEquals(graph.getInputArcs("D"), List.of(new Pair<>("A",3)));
    }
}
