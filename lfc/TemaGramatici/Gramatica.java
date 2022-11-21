package TemaGramatici;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Gramatica {

    private String adresaFisier;

    private ArrayList<String> vN = new ArrayList<>();
    private ArrayList<String> vT = new ArrayList<>();
    private String S;
    private ArrayList<Productie> P = new ArrayList<>();
    private int n;

    private Random regulaRandom = new Random(), pozitieRandom = new Random();
    private Scanner scanner = new Scanner(System.in);
    private HashSet<String> cuvinte = new HashSet<>();

    public Gramatica(String adresaFisier) {
        this.adresaFisier = adresaFisier;
        citireFisier();
    }

    void citireFisier() {
        try (Scanner scanner = new Scanner(new File(adresaFisier))) {
            if (scanner.hasNext())
                vN.addAll(List.of(scanner.nextLine().split("\\s+")));
            if (scanner.hasNext())
                vT.addAll(List.of(scanner.nextLine().split("\\s+")));
            if (scanner.hasNext())
                S = scanner.nextLine();
            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                P.add(new Productie(productiile[0], productiile[1]));
            }
        } catch (
                FileNotFoundException fnfe) {
            System.out.println("EROARE la citirea fisierului. Fisierul nu a fost gasit!");
            System.exit(-1);
        }
    }

    public void citire() {
        System.out.println();

        System.out.print("Cate cuvinte doriti sa fie generate ? ");
        n = scanner.nextInt();
    }

    public void afisareModel() {
        System.out.println();
        System.out.println("Probabil ati gresit inputul. Singurul model de input acceptat este:");
        System.out.println("Vn" + "\n" + "Vt" + "\n" + "S" + "\n" + "P" + "\n");
        System.out.println("Spre exemplu:");
        System.out.println("S A");
        System.out.println("a b");
        System.out.println("S");
        System.out.println("S abS");
        System.out.println("S aSAb");
        System.out.println("S aA");
        System.out.println("A aAb");
        System.out.println("A a");
    }

    private boolean validareDacaExistaMultimi() {
        if (vN.isEmpty()) {
            System.out.println("EROARE: Nu exista Vn.");
            return false;
        }
        if (vT.isEmpty()) {
            System.out.println("EROARE: Nu exista Vt.");
            return false;
        }

        if (S.isEmpty()) {
            System.out.println("EROARE: Nu exista S.");
            return false;
        }

        if (P.isEmpty()) {
            System.out.println("EROARE: Nu exista productii.");
            return false;
        }
        return true;
    }

    public boolean verificare() {

        int contorS = 0;

        if (!validareDacaExistaMultimi())
            return false;


        for (String vn : vN)
            for (String vt : vT)
                if (vt.equals(vn)) {
                    System.out.println("EROARE: Vn se intersecteaza cu VT.");
                    return false;
                }

        if (!vN.contains(S)) {
            System.out.println("EROARE: Simbolul de start nu apartine de Vn.");
            return false;
        }

        for (Productie p : P) {
            for (int i = 0; i < p.getMembruStang().length(); i++) {
                if (!vN.contains(String.valueOf(p.getMembruStang().charAt(i)))) {
                    System.out.println("EROARE: Membrul stang al productiei \" " + p + " \" nu contine niciun element neterminal.");
                    return false;
                }
            }

            if (p.getMembruStang().equalsIgnoreCase(S))
                contorS++;


            String v = "";
            for (String vn : vN)
                v += vn;
            for (String vt : vT)
                v += vt;

            for (char c : p.getMembruDrept().toCharArray()) {
                if (!v.contains(String.valueOf(c))) {
                    System.out.println("EROARE: Productia \" " + p + " \" nu contine doar elemente din VN si VT.");
                    return false;
                }
            }
        }

        if (contorS == 0) {
            System.out.println("EROARE: Nu exista nicio productie care contine doar S.");
            return false;
        }

        return true;
    }

    public void afisare() {
        System.out.println();
        System.out.println("Gramatica dumneavoastra contine urmatoarele: " + "\n");
        System.out.println("Vn: " + vN);
        System.out.println("Vt: " + vT);
        System.out.println("S: " + S);
        System.out.println("P: ");
        for (Productie productie : P)
            System.out.println(productie.getMembruStang() + " -> " + productie.getMembruDrept());
    }

    public void generare() {
        boolean cuvantTerminal;
        for (int i = 0; cuvinte.size() < n; i++) {
            cuvantTerminal = false;
            System.out.println("Cuvantul #" + (i + 1) + ": ");
            String cuvantNou = S;
            System.out.print(cuvantNou);
            do {
                ArrayList<Productie> productiiAplicabile = new ArrayList<>();
                ArrayList<Integer> indiciPozitii = new ArrayList<>();

                for (Productie productie : P) {
                    if (cuvantNou.contains(productie.getMembruStang())) {
                        {
                            productiiAplicabile.add(productie);
                        }
                    }
                }

                if (productiiAplicabile.isEmpty()) {
                    System.out.println();
                    cuvinte.add(cuvantNou);
                    cuvantTerminal = true;
                } else {

                    Productie productieRandom = productiiAplicabile.get(regulaRandom.nextInt(productiiAplicabile.size()));
                    for (int j = 0; j < cuvantNou.length(); j++) {
                        for (int k = 0; k < productieRandom.getMembruStang().length(); k++)
                            if (productieRandom.getMembruStang().charAt(k) == cuvantNou.charAt(j)) {
                                indiciPozitii.add(j);
                            }
                    }

                    int indicePozitieRandom = indiciPozitii.get(pozitieRandom.nextInt(indiciPozitii.size()));
                    if (indicePozitieRandom != 0)
                        cuvantNou = cuvantNou.substring(0, indicePozitieRandom - productieRandom.getMembruStang().length() + 1) + productieRandom.getMembruDrept() + cuvantNou.substring(indicePozitieRandom + productieRandom.getMembruStang().length());
                    else
                        cuvantNou = cuvantNou.replaceFirst(productieRandom.getMembruStang(), productieRandom.getMembruDrept());
                    System.out.print(" ->  " + cuvantNou);
                }
            }
            while (!cuvantTerminal);
        }
        System.out.println();
        System.out.println("Cuvintele distincte sunt: " + cuvinte);
    }

    public void ruleaza() {
        if (verificare()) {
            afisare();
            citire();
            generare();
        } else
            afisareModel();
    }

    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("lfc/TemaGramatici/gramatica.txt");
        gramatica.ruleaza();
    }

}
