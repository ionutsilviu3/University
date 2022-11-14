package TemaGramatici;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Gramatica {



    String adresaFisier;

    ArrayList<String> vN, vT;
    String S;
    ArrayList<Productie> P;
    int n;
    Random random;
    Scanner scanner;
    HashSet<String> cuvinte;

    public Gramatica() {
    }

    void citireFisier() {
        try (Scanner scanner =new Scanner(new File("lfc/TemaGramatici/"+adresaFisier+".txt"))){
            if (scanner.hasNext())
                vN.add(scanner.nextLine());
            if (scanner.hasNext())
                vT.add(scanner.nextLine());
            while (scanner.hasNext()) {
                String[] productiile = scanner.nextLine().split("\\s+");
                P.add(new Productie(productiile[0], productiile[1]));
            }
        } catch(
                FileNotFoundException fnfe){
            System.out.println("Eroare la citirea fisierului. Fisierul nu a fost gasit!");
        }
    }

    public void citire()
    {
        System.out.print("Cate cuvinte doriti sa fie generate ? ");
        n = scanner.nextInt();
    }

    public boolean validare()
    {
        return true;
    }

    public void afisare()
    {

    }

    public void generare()
    {
        while(cuvinte.size() < n)
        {

        }
    }


}
