package Laborator1;

public class Productie
{
    String membruStang;
    String membruDrept;

    public Productie()
    {

    }

    public Productie(String membruStang, String membruDrept)
    {
        this.membruStang = membruStang;
        this.membruDrept = membruDrept;
    }

    @Override
    public String toString()
    {
        return membruStang + " -> " + membruDrept;
    }
    
}