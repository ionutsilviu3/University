package Laborator1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Markov
{
    String vocabular;

    ArrayList<Productie> productii = new ArrayList<>();
    String cuvant;

    public Markov()
    {
        
    }

    public void citireFisier()
    {
        try (Scanner scanner = new Scanner(new File("/home/ionut/University/lfc/Laborator1/test.txt"))) {
            if(scanner.hasNext())
            vocabular = scanner.nextLine();
            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                productii.add(new Productie(productiile[0], productiile[1]));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Eroare la citirea fisierului. Fisierul nu a fost gasit!");;
        }
        afisare();
    }

    public void citire()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceti un cuvant: ");
        cuvant = sc.nextLine();
        if(validare() == false)
        citire();

        derivare();
    }
    public boolean validare()
    {
        for(char c : cuvant.toCharArray())
        {
            if(vocabular.indexOf(c) == -1)
            return false;
        }
        return true;
    }
    public void derivare()
    {

    for(Productie p : productii)
    {
            while(cuvant.contains(p.getMembruStang()))
                {
                    if(p.getMembruDrept().equals("#"))
                    cuvant = cuvant.replaceFirst(p.getMembruStang(), "");
                    else
                    cuvant.replaceFirst(p.getMembruStang(), p.getMembruDrept());

                    System.out.print(cuvant + " -> ");
                }
                

        }
    }
    
    public void afisare()
    {
        System.out.println("Vocabularul este: " + vocabular);
        System.out.println("Productii: ");
        for(Productie p : productii)
        {
            System.out.println(p.getMembruStang() + " -> " + p.getMembruDrept());
        }
    }
}