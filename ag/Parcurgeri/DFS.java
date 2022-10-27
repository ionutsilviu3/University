package ag.Parcurgeri;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

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

        int[] tu = new int[n];
        int[] td = new int[n];
        int[] p = new int[n];
        int t = 1;
        tu[s] = 1;

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
                    break;
                }
            }
            if (y != -1) {
                U.remove(Integer.valueOf(y));
                V.add(y);
                p[y] = x;
                tu[y] = ++t;
            } else {
                V.remove(Integer.valueOf(x));
                W.add(x);
            }

        }
        if(!U.isEmpty())
    {
        int x = 0;
        V.clear();
        V.add(x);
        U.remove(x);

    }
    }
        System.out.println(W);
        System.out.println(U);
    }
}
