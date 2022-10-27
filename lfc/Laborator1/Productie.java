package Laborator1;

public class Productie
{
    private String membruStang;
    private String membruDrept;

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

    @Override
    public String toString()
    {
        return membruStang + " -> " + membruDrept;
    }
    
}