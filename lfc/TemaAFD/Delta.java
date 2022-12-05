package TemaAFD;

public class Delta {
    private String q, p;
    private char a;

    public Delta(String q, char a, String p) {
        this.q = q;
        this.a = a;
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public char getA() {
        return a;
    }

    public void setA(char a) {
        this.a = a;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "\u03B4( " + q + ", " + a + " ) = " + p;

    }
}
