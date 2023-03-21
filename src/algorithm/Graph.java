package algorithm;

import java.util.List;

public interface Graph {

    // Methode, die den Knoten mit der geringsten Id
    algorithm.Node lowestNode(List<algorithm.Node> nodes);

    // Methode, die Knoten zu den möglichen Knoten hinzufügt
    void addToEligible(List<algorithm.Node> nodes, List<Integer> successors);

    List<algorithm.Successor> getSuccessorInfos(List<algorithm.Node> eligible);

    algorithm.Node getBestNode(List<algorithm.Successor> successorsInfos, List<algorithm.Node> nodes);

    void checkIfPossibleNodes(List<Successor> successorsInfos, List<algorithm.Node> eligible, List<algorithm.Node> nodes);

    // Methode, die den Durchgang erstellt
    void generateSchedule(List<Node> nodes);
}
