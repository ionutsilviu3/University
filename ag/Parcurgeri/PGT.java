package ag.Parcurgeri;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
public class PGT {

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceti n: ");
        int n = sc.nextInt();
        System.out.print("Introduceti s: ");
        int s = sc.nextInt();
        LinkedList<Integer>[] adjList = new LinkedList[n];
        Random intR = new Random();

        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<>();
            System.out.println("Nr de succesori al nodului " + i);
            int ni = sc.nextInt();
            System.out.println("Introduceti succesorii: ");
            for (int j = 0; j < ni; j++)
                adjList[i].add(sc.nextInt());
        }

        ArrayList<Integer> V = new ArrayList<>();
        ArrayList<Integer> U = new ArrayList<>();
        ArrayList<Integer> W = new ArrayList<>();

        V.add(s);

        int[] o = new int[n];
        int[] p = new int[n];
        int k = 1;
        o[s] = 1;

        for (int i = 0; i < n; i++) {
            p[i] = -1;
        }
        while(W.size() != n)
        {
        while (!V.isEmpty()) {
            int x = V.get(intR.nextInt(V.size()));
            int y = -1;
            for (int i = 0; i < adjList[x].size(); i++) {
                if (U.contains(adjList[x].get(i))) {
                    y = adjList[x].get(i);
                    break;
                }
            }
            if (y != -1) {
                U.remove(Integer.valueOf(y));
                V.add(y);
                p[y] = x;
                o[y] = ++k;
            } else {
                V.remove(Integer.valueOf(x));
                W.add(x);
            }

        }
        if(!U.isEmpty())
    {
        int x = U.get(intR.nextInt(U.size()));
        V.clear();
        V.add(x);
        U.remove(x);
        k++;
        o[x] = k;
    }
    }
        System.out.println(W);
        System.out.println(U);

        for (int i = 0; i < n; i++)
            System.out.print(o[i] + " ");
        System.out.println();
        for (int i = 0; i < n; i++)
            System.out.print(p[i] + " ");
        drum(0, 5, p);
    }
}
