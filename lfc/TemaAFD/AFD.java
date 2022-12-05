package TemaAFD;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AFD {
    private static HashSet<String> stari = new HashSet<>();
    private static ArrayList<Character> sigma = new ArrayList<>();
    private static ArrayList<Delta> delta = new ArrayList<>();
    private static String stareInitiala;
    private static ArrayList<String> stariFinale = new ArrayList<>();
    private static String cuvant;
    private static String adresaFisier;
    private static Scanner scanner = new Scanner(System.in);
    private static String lambda;

    public AFD(String adresaFisier, String lambda) {
        this.adresaFisier = adresaFisier;
        this.lambda = lambda;
    }

    static void citireDinFisier() {
        try (Scanner scanner = new Scanner(new File(adresaFisier))) {
            if (scanner.hasNext())
                stari.addAll(List.of(scanner.nextLine().split("\\s+")));
            if (scanner.hasNext())
                for (String s : scanner.nextLine().split("\\s+"))
                    sigma.add(s.charAt(0));
            if (scanner.hasNext())
                stareInitiala = scanner.nextLine();
            if (scanner.hasNext())
                stariFinale.addAll(List.of(scanner.nextLine().split("\\s+")));
            while (scanner.hasNext()) {
                String[] d = scanner.nextLine().split("\\s+");
                delta.add(new Delta(d[0], d[1].charAt(0), d[2]));
            }
        } catch (
                FileNotFoundException fnfe) {
            System.out.println("Eroare la citirea fisierului. Fisierul nu a fost gasit!");
            System.exit(-1);
        }
    }

    static void citire() {
        System.out.println();

        System.out.print("Introduceti un cuvant: ");
        cuvant = scanner.next();
        for (char caracterDinCuvant : cuvant.toCharArray())
            if (!sigma.contains(caracterDinCuvant)) {
                System.out.println("Cuvant invalid! ");
                citire();
            }
    }

    static boolean validare() {

        for (Delta tranzitie : delta) {
            if (!stari.contains(tranzitie.getQ())) {
                System.out.println("Q din tranzitia "+ tranzitie + " nu este inclusa in Stari");
                return false;
            }
            if (!sigma.contains(tranzitie.getA())) {
                System.out.println("A din tranzitia " + tranzitie + " nu este inclusa in Sigma");
                return false;
            }
            if (!stari.contains(tranzitie.getP())) {
                if (!tranzitie.getP().equals(lambda)) {
                    System.out.println("P din tranzitia " + tranzitie + " nu este inclusa in Stari");
                    return false;
                }
            }
        }
        if (!stari.contains(stareInitiala)) {
            System.out.println("Starea initiala nu este inclusa in Stari");
            return false;
        }
        for (String stareFinala : stariFinale)
            if (!stari.contains(stareFinala)) {
                System.out.println("Starea finala nu este inclusa in Stari");
                return false;
            }
        return true;
    }

    static void verificare() {
        String stareActuala = stareInitiala;
        for (char caracterDinCuvant : cuvant.toCharArray()) {
            boolean blocaj = false;
            for (Delta tranzizie : delta)
                if (tranzizie.getQ().equals(stareActuala)) {
                    if (tranzizie.getA() == caracterDinCuvant) {
                        blocaj = true;
                        System.out.println(tranzizie);
                        stareActuala = tranzizie.getP();
                        break;
                    }
                }
            if (blocaj == false) {
                System.out.println("Cuvantul a generat un blocaj! ");
                return;
            }
        }
        if (stariFinale.contains(stareActuala)) {
            System.out.println("Cuvant a fost acceptat!");
        } else
            System.out.println("Cuvantul a fost respins! :(");
    }

    static void afisare() {
        System.out.println("\n" + "Automatul dumneavoastra finit determinist contine urmatoarele:"+"\n");
        System.out.println("Stari: " + stari);
        System.out.println("Sigma: " + sigma);
        System.out.println("Delta: ");
        for(Delta tranzitii : delta)
        {
            System.out.println(tranzitii);
        }
        System.out.println();
        System.out.println("Starea initiala: " + stareInitiala + "\n");
        System.out.println("Starile finale: " + stariFinale);
    }

    static void rulare() {
        citireDinFisier();
        if (validare())
            do {
                afisare();
                citire();
                verificare();
                System.out.println("\n" + "Doriti sa mai introduceti un cuvant ? ( Orice alt raspunsReiterare in afara de \"Da\" inseamna Nu ): ");
                String raspunsReiterare = scanner.next();
                if (!raspunsReiterare.equalsIgnoreCase("Da")) {
                    System.out.println("O zi frumoasa!");
                    return;
                }
            }
            while (true);

    }

    public static void main(String[] args) {
        AFD afd = new AFD("lfc/TemaAFD/afd.txt", "#");
        afd.rulare();
    }

}
