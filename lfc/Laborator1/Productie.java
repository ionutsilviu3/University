package Laborator1;

public class Productie
{
    private String membruStang;
    private String membruDrept;

    private boolean prodFinala = false;

    public Productie(String membruStang, String membruDrept, boolean prodFinala) {
        this.membruStang = membruStang;
        this.membruDrept = membruDrept;
        this.prodFinala = prodFinala;
    }


    public Productie()
    {

    }

    public Productie(String membruStang, String membruDrept)
    {
        this.membruStang = membruStang;
        this.membruDrept = membruDrept;
    }
    public String getMembruStang()
    {
        return this.membruStang;
    }

    public String getMembruDrept()
    {
        return this.membruDrept;
    }

    public boolean isProdFinala() {
        return prodFinala;
    }

    public void setProdFinala(boolean prodFinala) {
        this.prodFinala = prodFinala;
    }

    @Override
    public String toString()
    {
        return membruStang + " -> " + membruDrept;
    }
    
}