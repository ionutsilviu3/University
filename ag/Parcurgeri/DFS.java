package ag.Parcurgeri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import static ag.Parcurgeri.Utils.afisareVector;

public class DFS {

    public static void main(String[] args) {

        int n;
        int s;
        LinkedList<Integer>[] adjList;
        try (Scanner sc = new Scanner(new File("ag/Parcurgeri/graf.txt"))) {
            //System.out.print("Introduceti n: ");
            n = sc.nextInt();
            int ni;
            //System.out.print("Introduceti s: ");
            s = sc.nextInt();
            adjList = new LinkedList[n];

            for (int i = 0; i < n; i++) {
                adjList[i] = new LinkedList<>();
                //System.out.println("Nr de succesori al nodului " + i);
                ni = sc.nextInt();
                //System.out.println("Introduceti succesorii: ");
                for (int j = 0; j < ni; j++)
                    adjList[i].add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("EROARE FISIER :(");
            throw new RuntimeException(e);
        }

        Stack<Integer> V = new Stack<>();
        ArrayList<Integer> U = new ArrayList<>();
        ArrayList<Integer> W = new ArrayList<>();

        V.add(s);

        int[] t1 = new int[n];
        int[] t2 = new int[n];
        int[] p = new int[n];
        int t = 1;
        t1[s] = 1;

        for (int i = 0; i < n; i++) {
            if (i != s)
                U.add(i);
            p[i] = -1;
        }

        while (W.size() < n) {
            while (!V.isEmpty()) {
                int x = V.peek();
                int y = -1;
                boolean contains = false;
                for (int i = 0; i < adjList[x].size(); i++) {

                    if (U.contains(adjList[x].get(i))) {
                        y = adjList[x].get(i);
                        V.add(y);
                        U.remove(Integer.valueOf(y));
                        p[y] = x;
                        t1[y] = ++t;
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    V.pop();
                    W.add(x);
                    t2[x] = ++t;
                    break;
                }
            }
            if (!U.isEmpty()) {
                s = U.remove(0);
                V.push(s);
                U.remove(0);
                t++;
                t1[s] = t;
            }
        }

        System.out.println("W = " + W);
        System.out.println("U = " + U);
        System.out.print("T1 =  ");
        Utils.afisareVector(t1);

        System.out.print("T2 =  ");
        Utils.afisareVector(t2);


    }
}
