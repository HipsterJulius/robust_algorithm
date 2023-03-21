package algorithm;

//Klasse um die Kanten zu erstellen und zu speichern
public class Edge {

    private final int s;      // Ursprung der Kante
    private final int d;      // Ziel der Kante

    //Konstruktor
    public Edge(int s, int d) {
        this.s = s;
        this.d = d;
    }

    public int getS() {
        return s;
    }

    public int getD() {
        return d;
    }
}


