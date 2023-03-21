package algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example {

    public static void main(String[] args) {

        Node n1 = new Node(1, 0, new int[]{0, 0, 0, 0}, true, false, Arrays.asList(2, 3, 4), List.of());
        Node n2 = new Node(2, 8, new int[]{4, 0, 0, 0}, false, false, Arrays.asList(6, 11, 15), List.of(n1));
        Node n3 = new Node(3, 4, new int[]{10, 0, 0, 0}, false, false, Arrays.asList(7, 8, 13), List.of(n1));
        Node n4 = new Node(4, 6, new int[]{0, 0, 0, 3}, false, false, Arrays.asList(5, 9, 10), List.of(n1));
        Node n5 = new Node(5, 3, new int[]{3, 0, 0, 0}, false, false, List.of(20), List.of(n4));
        Node n6 = new Node(6, 8, new int[]{0, 0, 0, 8}, false, false, List.of(30), List.of(n2));
        Node n7 = new Node(7, 5, new int[]{4, 0, 0, 0}, false, false, List.of(27), List.of(n3));
        Node n8 = new Node(8, 9, new int[]{0, 1, 0, 0}, false, false, Arrays.asList(12, 19, 27), List.of(n3));
        Node n9 = new Node(9, 2, new int[]{6, 0, 0, 0}, false, false, List.of(14), List.of(n4));
        Node n10 = new Node(10, 7, new int[]{0, 0, 0, 1}, false, false, Arrays.asList(16, 25), List.of(n4));
        Node n11 = new Node(11, 9, new int[]{0, 5, 0, 0}, false, false, Arrays.asList(20, 26), List.of(n2));
        Node n12 = new Node(12, 2, new int[]{0, 7, 0, 0}, false, false, List.of(14), List.of(n8));
        Node n13 = new Node(13, 6, new int[]{4, 0, 0, 0}, false, false, Arrays.asList(17, 18), List.of(n3));
        Node n14 = new Node(14, 3, new int[]{0, 8, 0, 0}, false, false, List.of(17), Arrays.asList(n9, n12));
        Node n15 = new Node(15, 9, new int[]{3, 0, 0, 0}, false, false, List.of(25), List.of(n2));
        Node n16 = new Node(16, 10, new int[]{0, 0, 0, 5}, false, false, Arrays.asList(21, 22), List.of(n10));
        Node n17 = new Node(17, 6, new int[]{0, 0, 0, 8}, false, false, List.of(22), Arrays.asList(n13, n14));
        Node n18 = new Node(18, 5, new int[]{0, 0, 0, 7}, false, false, Arrays.asList(20, 22), List.of(n13));
        Node n19 = new Node(19, 3, new int[]{0, 1, 0, 0}, false, false, Arrays.asList(24, 29), List.of(n8));
        Node n20 = new Node(20, 7, new int[]{0, 10, 0, 0}, false, false, Arrays.asList(23, 25), Arrays.asList(n5, n11, n18));
        Node n21 = new Node(21, 2, new int[]{0, 0, 0, 6}, false, false, List.of(28), List.of(n16));
        Node n22 = new Node(22, 7, new int[]{2, 0, 0, 0}, false, false, List.of(23), Arrays.asList(n16, n17, n18));
        Node n23 = new Node(23, 2, new int[]{3, 0, 0, 0}, false, false, List.of(24), Arrays.asList(n20, n22));
        Node n24 = new Node(24, 3, new int[]{0, 9, 0, 0}, false, false, List.of(30), List.of(n19, n23));
        Node n25 = new Node(25, 3, new int[]{4, 0, 0, 0}, false, false, List.of(30), List.of(n10, n15, n20));
        Node n26 = new Node(26, 7, new int[]{0, 0, 4, 0}, false, false, List.of(31), List.of(n11));
        Node n27 = new Node(27, 8, new int[]{0, 0, 0, 7}, false, false, List.of(28), Arrays.asList(n7, n8));
        Node n28 = new Node(28, 3, new int[]{0, 8, 0, 0}, false, false, List.of(31), List.of(n21, n27));
        Node n29 = new Node(29, 7, new int[]{0, 7, 0, 0}, false, false, List.of(32), List.of(n19));
        Node n30 = new Node(30, 2, new int[]{0, 7, 0, 0}, false, false, List.of(32), Arrays.asList(n6, n24, n25));
        Node n31 = new Node(31, 2, new int[]{0, 0, 2, 0}, false, false, List.of(32), Arrays.asList(n26, n28));
        Node n32 = new Node(32, 0, new int[]{0, 0, 0, 0}, false, true, List.of(), Arrays.asList(n29, n30, n31));

        //Liste der Knoten erstellen
        List<Node> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);
        nodes.add(n8);
        nodes.add(n9);
        nodes.add(n10);
        nodes.add(n11);
        nodes.add(n12);
        nodes.add(n13);
        nodes.add(n14);
        nodes.add(n15);
        nodes.add(n16);
        nodes.add(n17);
        nodes.add(n18);
        nodes.add(n19);
        nodes.add(n20);
        nodes.add(n21);
        nodes.add(n22);
        nodes.add(n23);
        nodes.add(n24);
        nodes.add(n25);
        nodes.add(n26);
        nodes.add(n27);
        nodes.add(n28);
        nodes.add(n29);
        nodes.add(n30);
        nodes.add(n31);
        nodes.add(n32);

        // Den Graphen anhand der Liste der Knoten erstellen
        DirectedGraph directedGraph = new DirectedGraph(nodes);

        directedGraph.generateSchedule(nodes);

    }
}
