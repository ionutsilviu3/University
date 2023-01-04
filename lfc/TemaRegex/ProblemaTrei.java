package TemaRegex;

import java.util.Scanner;

public class ProblemaTrei {

    private Scanner scanner;
    private String parola;
    private String regexParolaStrong;
    private String raspunsReiterare;

    public ProblemaTrei()
    {
        scanner = new Scanner(System.in);
        parola = "";
        raspunsReiterare = "";
        regexParolaStrong = "^(?=.*[A-Z])(?=.*[0-9][0-9])(?=.*[#$%])(?=.*[a-zA-Z]).{10,}$";
    }

    private void citireParola()
    {
        System.out.println();
        System.out.println("Introduceti o parola, pentru a verifica daca acesta este strong sau slaba:");
        parola = scanner.nextLine();
    }
    private void verificare()
    {
        if (RegexUtils.isMatching(regexParolaStrong, parola))
            System.out.println("Parola introdusa este strong.");
        else
            System.out.println("Parola introdusa NU este strong!");

    }
    private String decizieReiterare()
    {
        System.out.println();
        System.out.println("Doriti sa mai incercati inca o parola ? ( Orice raspuns in afara de \"Nu\", va mai rula inca o data problema.");
        return scanner.nextLine();
    }

    public void rezolvare()
    {
        while (!raspunsReiterare.equalsIgnoreCase("nu")) {
            citireParola();
            verificare();
            raspunsReiterare = decizieReiterare();
        }
        //scanner.close();
    }
}
