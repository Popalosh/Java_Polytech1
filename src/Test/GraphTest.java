import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
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

        graph.reweight("D", 4, 47);
    }

    @Test
    public void getOutputArcs() {

        assertEquals(List.of(new Pair<>("D", 3)), graph.getOutputArcs("A"));
        assertEquals(List.of(new Pair<>("A", 47)), graph.getOutputArcs("D"));
    }

    @Test
    public void getInputArcs() {

        assertEquals(List.of(new Pair<>("D", 47)), graph.getInputArcs("A"));
        assertEquals(List.of(new Pair<>("A", 3)), graph.getInputArcs("D"));
    }
}