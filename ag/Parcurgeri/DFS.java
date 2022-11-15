package ag.Parcurgeri;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import static ag.Parcurgeri.Utils.afisareVector;

public class DFS {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceti n: ");
        int n = sc.nextInt();
        System.out.print("Introduceti s: ");
        int s = sc.nextInt();
        LinkedList<Integer>[] adjList = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<>();
            System.out.println("Nr de succesori al nodului " + i);
            int ni = sc.nextInt();
            System.out.println("Introduceti succesorii: ");
            for (int j = 0; j < ni; j++)
                adjList[i].add(sc.nextInt());
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
            p[i] = -1;
        }
        while(W.size() != n)
        {
        while (!V.isEmpty()) {
            int x = V.firstElement();
            int y = -1;
            for (int i = 0; i < adjList[x].size(); i++) {
                if (U.contains(adjList[x].get(i))) {
                    y = adjList[x].get(i);
                    p[y] = x;
                    t1[y] = ++t;
                }
                else {
                    V.remove(x);
                    W.add(x);
                    t2[x] = ++t;
                    break;
                }
            }
        }
        if(!U.isEmpty())
        {
        s = U.get(0);
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
