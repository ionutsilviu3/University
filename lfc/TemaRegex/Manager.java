package TemaRegex;

import java.util.Scanner;

public class Manager {
    Scanner scanner;
    ProblemaUnu problemaUnu;
    ProblemaDoi problemaDoi;
    ProblemaTrei problemaTrei;

    public Manager() {
        problemaUnu = new ProblemaUnu();
        problemaDoi = new ProblemaDoi("lfc/TemaRegex/cuvinteProblemaDoi.txt");
        problemaTrei = new ProblemaTrei();
    }

    private void selecteazaProblema(int raspuns)
    {
        switch (raspuns)
        {
            case 1:
                problemaUnu.rezolvare();
                asteptareInput();
                break;
            case 2:
                problemaDoi.rezolvare();
                asteptareInput();
                break;
            case 3:
                problemaTrei.rezolvare();
                asteptareInput();
                break;
            case -1:
                System.out.println("O zi frumoasa!");
                System.exit(-1);
            default:
                System.out.println("Input invalid, incercati inca o data!");
                break;
        }
    }
    private void afiseazaOptiuni()
    {
        System.out.println();
        System.out.println("Selectati o problema ( introduceti doar numarul problemei )");
        System.out.println();
        System.out.println("1.Verificarea daca un numar este intreg sau real.");
        System.out.println("2.Problema cuvintelor peste vocabularul {a,b,c d}.");
        System.out.println("3.Verficarea daca o parola este strong sau nu.");
        System.out.println();
        System.out.println("Daca doriti sa terminati programul, introduceti \"-1\":");
    }
    private void asteptareInput()
    {
        System.out.println();
        System.out.println("Introduceti orice pentru a reveni la meniul de la inceput.");
        String inputTemp = scanner.next();
    }
    public void ruleaza()
    {
        scanner = new Scanner(System.in);
        while(true)
        {
            afiseazaOptiuni();
            selecteazaProblema(scanner.nextInt());
        }
    }
}
