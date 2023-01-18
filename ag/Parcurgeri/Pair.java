package ag.Parcurgeri;

public class Pair {
    private int n;
    private int weight;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Pair(int n, int weight) {
        this.n = n;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return n + "-" + weight;
    }
}
