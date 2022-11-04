package Laborator1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Markov {

    public Markov(String adresaFisier, String lambda) {
        this.adresaFisier = adresaFisier;
        this.lambda = lambda;
    }
    String adresaFisier;
    String vocabular;

    ArrayList<Productie> productii = new ArrayList<>();

    String lambda;
    String cuvant;


    public void setAdresaFisier(String adresaFisier) {
        this.adresaFisier = adresaFisier;
    }
    public String getAdresaFisier() {
        return adresaFisier;
    }

    public void citireFisier() {
        try (Scanner scanner = new Scanner(new File("lfc/Laborator1/" + adresaFisier + ".txt"))) {
            if (scanner.hasNext())
                vocabular = scanner.nextLine();

            TreeSet<Character> vocabTemp = new TreeSet<Character>();
            for(char c : vocabular.toCharArray())
            vocabTemp.add(c);
            vocabular = "";
            Iterator iterator = vocabTemp.iterator();
            while(iterator.hasNext())
                vocabular += iterator.next();

            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                if (productiile[1].contains("."))
                    productii.add(new Productie(productiile[0], productiile[1], true));
                else
                    productii.add(new Productie(productiile[0], productiile[1], false));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Eroare la citirea fisierului. Fisierul nu a fost gasit!");
        }
    }
    public boolean validareVocabularSiProductii() {
        if(vocabular.contains(lambda))
        {
            System.out.println("Eroare: Simbolul pentru lambda este prezent deja in vocabular!");
            return false;
        }
        if(vocabular.contains(" "))
        {
            System.out.println("Eroare: Exista spatii in vocabular!");
            return false;
        }
        if(vocabular.isEmpty())
        {
            System.out.println("Eroare: Nu exista vocabular!");
            return false;
        }
        if(productii.isEmpty())
        {
            System.out.println("Eroare: Nu exista productii!");
            return false;
        }
        for (Productie productieExterioara : productii) {
            for (Productie productieInterioara : productii) {

                if (productieExterioara.getMembruStang().equals(productieInterioara.getMembruDrept()) &&
                        productieExterioara.getMembruDrept().equals(productieInterioara.getMembruStang())) {
                    System.out.println("Eroare: Productii redundante.");
                    return false;
                }
            }
            if (!productieExterioara.getMembruDrept().contains("."))
                if (!(vocabular.contains(productieExterioara.getMembruStang())) ||
                        (!vocabular.contains(productieExterioara.getMembruDrept()))) {
                    if((!productieExterioara.getMembruStang().equals(lambda)) || !(productieExterioara.getMembruDrept().equals(lambda))) {

                    }
                    else{
                    System.out.println("Eroare: Productii care nu respecta vocabularul.");
                    return false;
                }}
            if(productieExterioara.getMembruStang().equals(lambda) && !productieExterioara.getMembruDrept().contains("."))
            {
                System.out.println("Eroare: Lambda nu se duce in regula finala.");
            }
        }
        return true;
    }

    public void afisare() {
        System.out.println();
        System.out.print("V = { ");
        for (int i = 0; i < vocabular.length(); i++) {
            if(i != vocabular.length() - 1)
            System.out.print(vocabular.charAt(i) + ", ");
            else
                System.out.print(vocabular.charAt(i));
        }
        System.out.println(" }");
        System.out.println();
        System.out.println("Productii: ");
        for(Productie p : productii) {
            if(p.isProdFinala())
            System.out.println(p.getMembruStang() + " -> " + p.getMembruDrept() + " | " + "Regula finala");
            else
                System.out.println(p.getMembruStang() + " -> " + p.getMembruDrept());
        }
        System.out.println();
    }
    public void citire() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceti un cuvant: ");
        cuvant = sc.nextLine();
        if (validare() == false) {
            System.out.println("Cuvant invalid!");
            citire();
        }

    }

    public boolean validare() {
        for (char c : cuvant.toCharArray()) {
            if (vocabular.indexOf(c) == -1)
                return false;
        }
        return true;
    }

    public void derivare() {
        System.out.print(cuvant);
        boolean modificare = true;

        while (modificare) {
            modificare = false;
            for (Productie p : productii) {
                if (cuvant.contains(p.getMembruStang())) {
                    if (p.getMembruDrept().equals(lambda))
                        cuvant = cuvant.replaceFirst(p.getMembruStang(), "");
                    else
                        cuvant = cuvant.replaceFirst(p.getMembruStang(), p.getMembruDrept());
                    if (cuvant.isEmpty())
                        cuvant = p.getMembruDrept();
                    modificare = true;
                    System.out.print(" -> " + cuvant);

                    if (p.isProdFinala()) {
                        return;
                    }
                    break;
                }

            }
            if (modificare == false)
                return;
        }

    }



    public void ruleaza()
    {
        Scanner sc = new Scanner(System.in);
        citireFisier();
        String raspuns = "da";
        if (!validareVocabularSiProductii())
            return;
        while(raspuns.toLowerCase().equalsIgnoreCase("da"))
        {
            afisare();
            citire();

            derivare();

            System.out.println();
            System.out.print("Mai doriti sa cititi un cuvant ? ( Orice alt raspuns in afara de \"Da\" inseamna Nu ) :");

            raspuns = sc.nextLine();
            if(!raspuns.toLowerCase().equalsIgnoreCase("da")) {
                System.out.println("O zi buna!");
                sc.close();
                break;
            }
        }
    }

    public static void main(String[] args) {
        Markov markov =new Markov("vocabularSiProductii", "#");
        markov.ruleaza();
    }

}