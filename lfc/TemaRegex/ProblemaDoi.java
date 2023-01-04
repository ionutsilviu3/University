package TemaRegex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProblemaDoi {

    private String regexCerintaA, regexCerintaB, regexCerintaC;

    private String adresaFisier;
    private int contorCuvinteCerintaA;
    ArrayList<String> cuvinte, cuvinteDupaVerificare;
    ArrayList<Integer> indexCuvinteB, indexCuvinteC;
    private String cuvantConcatenat;

    public ProblemaDoi(String adresaFisier) {
        resetareDate(adresaFisier);
    }

    private void resetareDate(String adresaFisier)
    {
        this.adresaFisier = adresaFisier;
        regexCerintaA = "^(aa)+[^a].*";
        regexCerintaB = "^b.*b$";
        regexCerintaC = "^[a-d]+$";
        cuvinte = new ArrayList<>();
        cuvinteDupaVerificare = new ArrayList<>();
        indexCuvinteB = new ArrayList<>();
        indexCuvinteC = new ArrayList<>();
        contorCuvinteCerintaA = 0;
        cuvantConcatenat = "";
    }

    private void citireDinFisier() {
        try (Scanner scanner = new Scanner(new File(adresaFisier))) {
            while (scanner.hasNext()) {
                cuvinte.add(scanner.nextLine());
            }
        } catch (
                FileNotFoundException fnfe) {
            System.out.println("Eroare la citirea fisierului. Fisierul nu a fost gasit!");
            System.exit(-1);
        }
    }

    public void afisareCuvinte() {
        System.out.println("Cuvintele din fisier sunt: ");
        for (String cuvant : cuvinte)
            System.out.println(cuvant);

    }

    private void verificareCuvinte() {
        for (int index = 0; index < cuvinte.size(); index++) {
            String cuvant = cuvinte.get(index);

            if (RegexUtils.isMatching(regexCerintaA, cuvant)) {
                contorCuvinteCerintaA++;
            }

            if (RegexUtils.isMatching(regexCerintaB, cuvant)) {
                indexCuvinteB.add(index);
            }
            if (!RegexUtils.isMatching(regexCerintaC, cuvant)) {
                indexCuvinteC.add(index);
                cuvantConcatenat = cuvantConcatenat.concat(cuvant);
            }
        }
    }

    private void adaugareCuvinteDupaVerificare() {
        for (int indexCuvant = 0; indexCuvant < cuvinte.size(); indexCuvant++) {
            String cuvant = cuvinte.get(indexCuvant);
            if (!indexCuvinteB.contains(indexCuvant) && !indexCuvinteC.contains(indexCuvant)) {
                cuvinteDupaVerificare.add(cuvant);
            }
            if (indexCuvinteB.contains(indexCuvant)) {
                cuvinteDupaVerificare.add(String.valueOf(cuvant.length()));
            }

        }
        cuvinteDupaVerificare.add(cuvantConcatenat);
        System.out.println("In fisierul dumneavoastra exista " + contorCuvinteCerintaA + " cuvinte care incep cu un numar par de \"a\"-uri.");
    }

    private void rescrieFisier() {

        File file = new File(adresaFisier);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
            for (String cuvant : cuvinteDupaVerificare)
                fileWriter.write(cuvant + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void rezolvare() {
        citireDinFisier();
        afisareCuvinte();
        verificareCuvinte();
        adaugareCuvinteDupaVerificare();
        rescrieFisier();
        resetareDate(adresaFisier);
    }

}
