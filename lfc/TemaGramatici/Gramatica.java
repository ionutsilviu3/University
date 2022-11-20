package TemaGramatici;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Gramatica {

    private String adresaFisier;

    private ArrayList<String> vN = new ArrayList<>();
    private ArrayList<String> vT = new ArrayList<>();
    private String S;
    private ArrayList<Productie> P= new ArrayList<>();
    private int n;

    private Random regulaRandom = new Random(), pozitieRandom = new Random();
    private Scanner scanner = new Scanner(System.in);
    private HashSet<String> cuvinte = new HashSet<>();

    public Gramatica(String adresaFisier) {
        this.adresaFisier = adresaFisier;
    }
    void citireFisier() {
        try (Scanner scanner =new Scanner(new File("lfc/TemaGramatici/"+adresaFisier+".txt"))){
            if (scanner.hasNext())
                vN.addAll(List.of(scanner.nextLine().split("\\s+")));
            if (scanner.hasNext())
                vT.addAll(List.of(scanner.nextLine().split("\\s+")));
            if(scanner.hasNext())
                S = scanner.nextLine();
            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                P.add(new Productie(productiile[0], productiile[1]));
            }
        } catch(
                FileNotFoundException fnfe){
            System.out.println("EROARE la citirea fisierului. Fisierul nu a fost gasit!");
        }
    }

    public void citire()
    {
        System.out.println();
        System.out.print("Cate cuvinte doriti sa fie generate ? ");
        n = scanner.nextInt();
    }

    public boolean verificare()
    {

        int contorS = 0;

        if(vN.isEmpty())
        {
            System.out.println("EROARE: Nu exista Vn.");
            return false;
        }
        if(vT.isEmpty())
        {
            System.out.println("EROARE: Nu exista Vt.");
            return false;
        }

        if(S.isEmpty())
        {
            System.out.println("EROARE: Nu exista S.");
            return false;
        }

        if(P.isEmpty())
        {
            System.out.println("EROARE: Nu exista productii.");
            return false;
        }

        for(String vn : vN)
            for(String vt : vT)
                if(vt.equals(vn))
                {
                    System.out.println("EROARE: Vn se intersecteaza cu VT.");
                    return false;
                }

        if(!vN.contains(S))
        {
            System.out.println("EROARE: Simbolul de start nu apartine de Vn.");
            return false;
        }

        for(Productie p : P)
        {
            if (!vN.contains(p.getMembruStang()))
            {
                System.out.println("EROARE: Membrul stang al productiei \" " + p +  " \" nu contine niciun element neterminal.");
                return false;
            }

            if(p.getMembruStang().equalsIgnoreCase(S))
                contorS++;


            String v = "";
            for(String vn : vN)
                v += vn;
            for(String vt : vT)
                v += vt;

            for(char c : p.getMembruDrept().toCharArray())
            {
                if(!v.contains(String.valueOf(c)))
                {
                    System.out.println("EROARE: Productia \" " + p + " \" nu contine doar elemente din VN si VT.");
                    return false;
                }
            }
        }

        if(contorS == 0)
        {
            System.out.println("EROARE: Nu exista nicio productie care contine doar S.");
            return false;
        }

        return true;
    }

    public void afisare()
    {
        System.out.println();
        System.out.println("Gramatica dumneavoastra contine urmatoarele: " + "\n");
        System.out.println("Vn: " + vN);
        System.out.println("Vt: " + vT);
        System.out.println("S: " + S);
        System.out.println("P: ");
        for(Productie productie : P)
                System.out.println(productie.getMembruStang() + " -> " + productie.getMembruDrept());
    }

    public void generare() {
        boolean cuvantTerminal;
        for (int i = 0; cuvinte.size() < n; i++) {
            cuvantTerminal = false;
            System.out.println("Cuvantul #" + (i + 1)+ ": ");
            String cuvantNou = S;
            System.out.print(cuvantNou);
            do {
                ArrayList<Productie> indiciProductii = new ArrayList<>();
                ArrayList<Integer> indiciPozitii = new ArrayList<>();

                for (int j = 0; j < P.size(); j++) {
                    Productie productie = P.get(j);

                    if (cuvantNou.contains(productie.getMembruStang())) {
                        {
                            indiciProductii.add(productie);
                        }
                    }
                }

                if (indiciProductii.isEmpty()) {
                    System.out.println();
                    cuvinte.add(cuvantNou);
                    cuvantTerminal = true;
                } else {

                    Productie productieRandom = indiciProductii.get(regulaRandom.nextInt(indiciProductii.size()));
                    cuvantNou = cuvantNou.replaceFirst(productieRandom.getMembruStang(), productieRandom.getMembruDrept());
                    System.out.print(" ->  " + cuvantNou);
            }
            }
            while (!cuvantTerminal);
        }
        System.out.println();
        System.out.println("Cuvintele distincte sunt: " + cuvinte);
    }

    public void ruleaza()
    {
        citireFisier();
        if(verificare()) {
            afisare();
            citire();
            generare();
        }
    }

    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("gramatica");
        gramatica.ruleaza();
    }

}
