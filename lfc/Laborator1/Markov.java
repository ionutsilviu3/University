package Laborator1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Markov
{
    String vocabular;

    ArrayList<Productie> productii = new ArrayList<>();

    public Markov()
    {

    }

    public void citire()
    {
        try (Scanner scanner = new Scanner(new File("/home/ionut/University/lfc/Laborator1/test.txt"))) {
            if(scanner.hasNext())
            vocabular = scanner.nextLine();
            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                productii.add(new Productie(productiile[0], productiile[1]));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Eroare la citirea fisierului");;
        }
        System.out.println(vocabular);
        System.out.println(productii);
    }
    public void validare()
    {
        
    }
    public void derivare()
    {

    }
    public void afisare()
    {

    }
}