package TemaRegex;

import java.util.Scanner;

public class ProblemaUnu {
    private Scanner scanner;
    private String numarIntrodus, raspunsReiterare;
    private String regexNumarIntreg,regexNumarReal;

    public ProblemaUnu()
    {
        numarIntrodus = "";
        raspunsReiterare = "";
        scanner = new Scanner(System.in);
        regexNumarIntreg = "[+-]?(0)|([1-9]+[0-9]*)";
        regexNumarReal = "[+-]?((0\\.[0-9]+)|([1-9]+[0-9]*\\.[0-9]+))";
    }
    private void citireNumar()
    {
        System.out.println();
        System.out.println("Introduceti un numar, pentru a verifica daca acesta este intreg sau real:");
        numarIntrodus = scanner.nextLine();
    }
    private void verificare()
    {
        if (RegexUtils.isMatching(regexNumarIntreg, numarIntrodus))
            System.out.println("Numarul este intreg!");
        else {

            if (RegexUtils.isMatching(regexNumarReal, numarIntrodus))
                System.out.println("Numarul este real!");
            else
                System.out.println("Input-ul este invalid!");
        }
    }
    
    private String decizieReiterare()
    {
        System.out.println();
        System.out.println("Doriti sa mai incercati ? ( Orice raspuns in afara de \"Nu\", va mai rula inca o data problema.");
        return scanner.nextLine();
    }   
    public void rezolvare()
    {
        while (!raspunsReiterare.equalsIgnoreCase("nu")) {
            citireNumar();
            verificare();
            raspunsReiterare = decizieReiterare();
        }
    }
    
    
}
