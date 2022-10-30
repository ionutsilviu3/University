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
        try (Scanner scanner = new Scanner(new File("lfc/Laborator1/test.txt"))) {
            if(scanner.hasNext())
            vocabular = scanner.nextLine();
            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                if(productiile[1].contains("."))
                    productii.add(new Productie(productiile[0], productiile[1], true));
                else
                productii.add(new Productie(productiile[0], productiile[1], false));
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
        {
            System.out.println("Cuvant invalid!");
            citire();
        }

        derivare();
        System.out.println();
        System.out.println("Mai doriti sa cititi un cuvant ? ( Da / Nu )");

        if(sc.nextLine().toLowerCase().equals("da"))
        {

            afisare();
            citire();
        }
        else
            sc.close();
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
        System.out.print(cuvant);
    boolean modificare = true;

    while(modificare){
        modificare = false;
        for (Productie p : productii) {
            if (cuvant.contains(p.getMembruStang())) {
                if(p.getMembruDrept().equals("#"))
                cuvant = cuvant.replaceFirst(p.getMembruStang(), "");
                else
                cuvant = cuvant.replaceFirst(p.getMembruStang(), p.getMembruDrept());
                if(cuvant.isEmpty())
                    cuvant = p.getMembruDrept();
                modificare = true;
                System.out.print(" -> " + cuvant);

                if (p.isProdFinala()) {
                    return;
                }
                break;
            }

        }
        if(modificare == false)
            return;
    }

    }
    
    public void afisare()
    {
        System.out.println();
        System.out.println("Vocabularul este: " + vocabular);
        System.out.println("Productii: ");
        for(Productie p : productii)
        {
            System.out.println(p.getMembruStang() + " -> " + p.getMembruDrept() + " | " + p.isProdFinala());
        }
    }

}