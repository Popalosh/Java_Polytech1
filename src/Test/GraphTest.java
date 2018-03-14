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

        graph.addArc("A", "C", 3); // Данный метод работает как-то неверно
        graph.addArc("A", "B", 56); // Данный метод работает как-то неверно
        graph.addArc("C", "A", 4);  // Данный метод работает как-то неверно

        graph.deleteVertex("B");

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
