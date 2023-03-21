package algorithm;

import java.util.*;

//https://www.javatpoint.com/java-graph
//Klasse um den Graphen zu erstellen und darzustellen
public class DirectedGraph implements Graph {


    private int startTime = 0;
    protected List<algorithm.Node> finished = new ArrayList<>();
    protected List<algorithm.Node> used = new ArrayList<>();
    protected List<algorithm.Node> unused = new ArrayList<>();
    protected List<Integer> successors = new ArrayList<>();
    protected List<algorithm.Successor> successorsInfos = new ArrayList<>();
    protected List<algorithm.Node> eligible = new ArrayList<>();
    private int endTime = 0;
    private int realTime = 0;
    private final int robustTime = 2;
    private int[] resources = new int[]{12, 13, 4, 12};
    //Liste für die Kanten
    List<algorithm.Edge> edges = new ArrayList<>();
    //Liste für die benachbarten Knoten
    List<List<Integer>> adjlist = new ArrayList<>();


    //Konstruktor für einen Graphen
    public DirectedGraph(List<algorithm.Node> nodes) {

        // Die Kanten aus den Knoten mit Nachfolgern erstellen
        for (algorithm.Node n : nodes) {
            int s = n.getId();
            List<Integer> list = n.getSuccessors();
            // For-Schleife, falls mehrere Nachfolger vorhanden sind
            for (Integer integer : list) {
                int d = integer;
                // Neue Kante wird der Liste der Kanten hinzugefügt
                algorithm.Edge edge = new algorithm.Edge(s, d);
                edges.add(edge);
            }
        }

        int k = 0;
        //for-Schleife, die über die Kanten iteriert
        for (algorithm.Edge e : edges) {
            //Stellt den höchst nummerierten Knoten fest
            k = Integer.max(k, Integer.max(e.getS(), e.getD()));
        }
        //Platz in der Liste der Nachbarn schaffen
        for (int i = 0; i <= k; i++) {
            adjlist.add(i, new ArrayList<>());
        }
        //Fügt die Kanten zu einem ungerichteten Graphen hinzu
        for (Edge current : edges) {
            //Neuer Knoten aus der Liste von dem Ursprung zum Ziel zuordnen
            adjlist.get(current.getS()).add(current.getD());
        }
    }

    // Methode, die den Knoten mit der geringsten Id
    @Override
    public algorithm.Node lowestNode(List<algorithm.Node> nodes) {

        algorithm.Node startNode = new algorithm.Node(0, 0, new int[]{0, 0, 0, 0}, true, false, List.of(0), List.of());
        for (algorithm.Node n : nodes) {
            if (n.isStart()) {
                startNode = n;
            }
        }
        return startNode;
    }

    // Methode, die Knoten zu den möglichen Knoten hinzufügt
    @Override
    public void addToEligible(List<algorithm.Node> nodes, List<Integer> successors) {

        for (algorithm.Node n : unused) {
            List<algorithm.Node> predecessor = n.getPredecessor();
            Set<algorithm.Node> usedSet = new HashSet<>(used);
            int[] r = n.getR();

            boolean allElementsPresent = true;
            for (algorithm.Node p : predecessor) {
                if (!usedSet.contains(p)) {
                    allElementsPresent = false;
                    break;
                }
            }

            for (Integer successor : successors) {
                if (successor == n.getId() && !eligible.contains(n) && !n.isEnd() && allElementsPresent || used.size() == nodes.size() - 1 && n.isEnd()) {
                    eligible.add(n);
                    if (resources[0] - r[0] < 0 && resources[1] - r[1] < 0 && resources[2] - r[2] < 0 && resources[3] - r[3] < 0) {
                        eligible.remove(n);
                    }
                    break;
                }
            }
        }
    }

    // Hilfsmethoden für getSuccessorInfos()
    public int[] getRemainingResources(algorithm.Node n) {
        int[] remainingResources = {resources[0], resources[1], resources[2], resources[3]};

        for (int i = 0; i < resources.length; i++) {
            if (resources[i] - n.getR()[i] >= 0) {
                remainingResources[i] = resources[i] - n.getR()[i];
            } else {
                break;
            }
        }
        return remainingResources;
    }

    public float getDecisionQuotient(algorithm.Node n) {
        float decisionQuotient;
        decisionQuotient = (float) n.getR()[0] * 13 / n.getD() + (float) n.getR()[1] * 12 / n.getD() + (float) n.getR()[2] * 39 / n.getD() + (float) n.getR()[3] * 13 / n.getD();
        return decisionQuotient;
    }

    public int getFinishingTime(algorithm.Node n) {
        int finishingTime;

        if (resources[0] != 12 || resources[1] != 13 || resources[2] != 4 || resources[3] != 12) {
            finishingTime = startTime + n.getD() + robustTime;
        } else {
            finishingTime = endTime + n.getD() + robustTime;
        }

        return finishingTime;
    }

    public boolean getEnoughResources(algorithm.Node n) {

        boolean enoughResources = false;
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] - n.getR()[i] >= 0 && n.getR()[i] != 0) {
                enoughResources = true;
            }
        }
        return enoughResources;
    }

    public boolean allPredecessorsFinished(algorithm.Node n) {

        List<algorithm.Node> predecessor = n.getPredecessor();
        Set<algorithm.Node> finishedSet = new HashSet<>(finished);
        boolean allElementsPresent = true;
        for (algorithm.Node p : predecessor) {
            if (!finishedSet.contains(p)) {
                allElementsPresent = false;
                break;
            }
        }
        return allElementsPresent;
    }

    @Override
    public List<algorithm.Successor> getSuccessorInfos(List<algorithm.Node> eligible) {
        for (algorithm.Node n : eligible) {
            int[] remainingResources;
            int finishingTime;
            int id = n.getId();
            boolean enoughResources;
            boolean allPredecessorsFinished;

            finishingTime = getFinishingTime(n);
            remainingResources = getRemainingResources(n);
            float decisionQuotient = getDecisionQuotient(n);
            enoughResources = getEnoughResources(n);
            allPredecessorsFinished = allPredecessorsFinished(n);

            algorithm.Successor s = new algorithm.Successor(id, remainingResources, finishingTime, decisionQuotient, enoughResources, allPredecessorsFinished);
            if (!successorsInfos.contains(s) && remainingResources != resources && enoughResources && allPredecessorsFinished) {
                successorsInfos.add(s);
            }
        }
        return successorsInfos;
    }

    // Hilfsmethoden für getBestNode()
    public int getNextSuccessor(List<algorithm.Successor> successorsInfos) {

        int nextId = Integer.MAX_VALUE;
        float maxValue = 0;

        for (algorithm.Successor s : successorsInfos) {
            float current = s.getDecisionQuotient();
            if (current > maxValue) {
                maxValue = current;
            }
        }

        for (algorithm.Successor s : successorsInfos) {
            if (maxValue == s.getDecisionQuotient()) {
                nextId = s.getId();
            }
        }
        return nextId;
    }

    @Override
    public algorithm.Node getBestNode(List<algorithm.Successor> successorsInfos, List<algorithm.Node> nodes) {
        int[] remainingR = {0, 0, 0, 0};
        int oneNodeTime = 0;
        algorithm.Node bestNode = new algorithm.Node(0, 0, new int[]{0, 0, 0, 0}, false, false, List.of(), List.of());

        int nextId = getNextSuccessor(successorsInfos);
        for (algorithm.Successor s : successorsInfos) {
            if (s.getId() == nextId) {
                oneNodeTime = s.getFinishingTime();
                remainingR = s.getRemainingResources();
            }
        }

        for (algorithm.Node e : nodes) {
            if (e.getId() == nextId) {
                if (oneNodeTime > endTime) {
                    endTime = oneNodeTime;
                }
                resources = remainingR;
                bestNode = e;
            }
        }
        return bestNode;
    }

    // Hilfsmethode für checkIfPossibleNodes()
    public void checkIfEnd() {
        for (algorithm.Node e : eligible) {
            if (e.isEnd() && unused.size() == 1) {
                unused.remove(e);
                used.add(e);
                finished.add(e);
            } else {
                break;
            }
        }
    }

    @Override
    public void checkIfPossibleNodes(List<Successor> successorsInfos, List<algorithm.Node> eligible, List<algorithm.Node> nodes) {

        if (!eligible.isEmpty()) {
            for (algorithm.Node e : nodes) {
                checkIfEnd();
                for (int i = 0; i < resources.length; i++) {
                    if (resources[i] >= e.getR()[i] && e.getR()[i] != 0) {
                        getBestNode(successorsInfos, nodes);
                    }
                }
            }
        }
    }

    // Hilfsmethoden für generateSchedule
    public void finishLowestNode(List<algorithm.Node> nodes) {
        unused.addAll(nodes);
        used.add(lowestNode(nodes));
        finished.add(lowestNode(nodes));
        unused.remove(lowestNode(nodes));
        successors.addAll(lowestNode(nodes).getSuccessors());
        addToEligible(nodes, successors);
    }

    public void refillResources(int realTime, List<algorithm.Node> almostFinished) {
        if (endTime == realTime && resources[0] != 12 || endTime == realTime && resources[1] != 13 || endTime == realTime && resources[2] != 4 || endTime == realTime && resources[3] != 12) {
            finished.addAll(almostFinished);
            almostFinished.clear();
            resources[0] = 12;
            resources[1] = 13;
            resources[2] = 4;
            resources[3] = 12;
            startTime = endTime;
            getSuccessorInfos(eligible);
        }
    }

    public void finishPossibleNodes(List<algorithm.Node> nodes) {

        successorsInfos = getSuccessorInfos(eligible);
        List<algorithm.Node> almostFinished = new ArrayList<>();

        while (!unused.isEmpty()) {
            refillResources(realTime, almostFinished);

            if (realTime == 36 || realTime == 18) {
                activateNoRessourceEvent();
            }

            algorithm.Node e = getBestNode(successorsInfos, nodes);
            if (e.getId() != 0) {

                used.add(e);
                almostFinished.add(e);
                unused.remove(e);
                eligible.remove(e);
                successorsInfos.clear();
                int value = 0;
                for (int i = 0; i < successors.size(); i++) {
                    if (successors.get(i) == e.getId()) {
                        value = i;
                    }
                }
                if (!successors.isEmpty()) {
                    successors.remove(value);
                }
                successors.addAll(e.getSuccessors());
                addToEligible(nodes, successors);
                getSuccessorInfos(eligible);
                checkIfPossibleNodes(successorsInfos, eligible, nodes);
            }
            realTime += 1;
        }
    }

    // Methode, die den Durchgang erstellt
    @Override
    public void generateSchedule(List<algorithm.Node> nodes) {
        activateMoreRessourceEvent(nodes);
        finishLowestNode(nodes);
        finishPossibleNodes(nodes);
        for (algorithm.Node n : finished) {
            System.out.println(n.getId());
        }
        System.out.println("Durchlauf wurde erstellt! Mit einer Durchlaufzeit von " + endTime + ".");
    }

    // Methoden für die Ereignisse
    public void activateMoreRessourceEvent(List<algorithm.Node> nodes) {
        for (Node n : nodes) {
            if (n.getId() == 3) {
                n.setD(n.getD() + 5 - robustTime);
            } else if (n.getId() == 11) {
                n.setD(n.getD() + 3 - robustTime);
            }
        }
    }

    public void activateNoRessourceEvent() {
        if (realTime == 36) {
            resources[3] = 0;
            successorsInfos.clear();
            getSuccessorInfos(eligible);
        }
        if (realTime == 18) {
            resources[0] = 0;
            successorsInfos.clear();
            getSuccessorInfos(eligible);
        }
    }

}





