package algorithm;

public class Successor {

    private final int id;
    private final int[] remainingResources;
    private final int finishingTime;
    private final float decisionQuotient;
    boolean enoughResources;
    boolean allPredecessorsFinished;

    public Successor(int id, int[] remainingResources, int finishingTime, float decisionQuotient, boolean enoughResources, boolean allPredecessorsFinished) {
        this.id = id;
        this.remainingResources = remainingResources;
        this.finishingTime = finishingTime;
        this.decisionQuotient = decisionQuotient;
        this.enoughResources = enoughResources;
        this.allPredecessorsFinished = allPredecessorsFinished;
    }

    public int getId() {
        return id;
    }

    public int[] getRemainingResources() {
        return remainingResources;
    }

    public int getFinishingTime() {
        return finishingTime;
    }

    public float getDecisionQuotient() {
        return decisionQuotient;
    }

}
