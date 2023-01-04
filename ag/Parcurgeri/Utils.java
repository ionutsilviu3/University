package ag.Parcurgeri;

import java.util.LinkedList;
import java.util.Scanner;

public class Utils {
    static LinkedList<Integer>[] populareListaDeAdiacenta(int numarDeNoduri, Scanner scanner, boolean citireDeLaTastatura)
    {
        LinkedList<Integer>[] adjList = new LinkedList[numarDeNoduri];
        for (int i = 0; i < numarDeNoduri; i++) {
            adjList[i] = new LinkedList<>();
            if(citireDeLaTastatura == true)
            System.out.println("Numarul de succesori ai nodului " + i);
            int numarDeMuchii = scanner.nextInt();
            if(citireDeLaTastatura == true)
            System.out.println("Introduceti succesorii: ");
            for (int j = 0; j < numarDeMuchii; j++) {
                adjList[i].add(scanner.nextInt());
            }
        }
        return adjList;
    }

    static LinkedList<Pair>[] populareListaDeAdiacentaWeighted(int numarDeNoduri, Scanner scanner)
    {
        LinkedList<Pair>[] adjList = new LinkedList[numarDeNoduri];
        for (int i = 0; i < numarDeNoduri; i++) {
            adjList[i] = new LinkedList<>();
            int numarDeMuchii = scanner.nextInt();
            for (int j = 0; j < numarDeMuchii; j++) {
                adjList[i].add(new Pair(scanner.nextInt(), scanner.nextInt()));
            }
        }
        return adjList;
    }
    static void afisareVector(int[] array)
    {
        System.out.print("{ ");
        for(int i = 0; i < array.length; i++)
        {
            if(i == array.length - 1)
                System.out.print(array[i] + " }");
            else
                System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    static void drum(int s, int y, int[] p) {
        if (s == y) {
            System.out.println(s);
        } else {
            if (p[y] == -1) {
                System.out.println("Nu exista drum.");
            } else {
                drum(s, p[y], p);
                System.out.println(y);
            }
        }
    }
}
