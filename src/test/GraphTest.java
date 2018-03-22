import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph graph;

    @BeforeEach
    void setup() {
        graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("P");

        graph.addArc("A", "C", 3);
        graph.addArc("A", "B", 56);
        graph.addArc("C", "A", 4);
        graph.addArc("P","A",228);


        graph.deleteVertex("B");
        graph.deleteVertex("P");

        graph.renameVertex("C", "D");

        graph.reweight("D", "A", 47);
    }

    @Test
    void getOutputArcs() {
        assertEquals(Map.of("D", 3), graph.getOutputArcs("A"));
        assertEquals(Map.of("A", 47), graph.getOutputArcs("D"));
    }

    @Test
    void getInputArcs() {
        assertEquals(Map.of("D", 47), graph.getInputArcs("A"));
        assertEquals(Map.of("A", 3), graph.getInputArcs("D"));
    }
}