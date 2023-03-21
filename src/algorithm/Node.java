package algorithm;

import java.util.List;

//Klasse um die Knoten zu erstellen und zu speichern
public class Node {

    private final int id;                     // Id des Knotens
    private int d;                            // Knotendauer
    private final int[] r;                    // Ressourcenbedarf
    private final boolean start;              // Startknoten
    private final boolean end;                // Endknoten
    private final List<Integer> successors;   // Nachfolger
    private final List<Node> predecessor;     // Vorgänger

    public Node(int id, int d, int[] r, boolean start, boolean end, List<Integer> successors, List<Node> predecessor) {
        this.id = id;
        this.d = d;
        this.r = r;
        this.start = start;
        this.end = end;
        this.successors = successors;
        this.predecessor = predecessor;
    }

    public int getId() {
        return id;
    }

    public int getD() {
        return d;
    }

    public int[] getR() {
        return r;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isEnd() {
        return end;
    }

    public List<Integer> getSuccessors() {
        return successors;
    }

    public List<Node> getPredecessor() {
        return predecessor;
    }

    public void setD(int d) {
        this.d = d;
    }
}
