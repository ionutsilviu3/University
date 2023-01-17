package ag.Parcurgeri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CC {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Text.txt"));
        int n = sc.nextInt();
        int s = sc.nextInt();
        int A = sc.nextInt();
        System.out.println("n = " + n);
        ArrayList<Integer> V = new ArrayList<>();
        ArrayList<Integer> U = new ArrayList<>();
        ArrayList<Integer> W = new ArrayList<>();
        ArrayList<Integer> N = new ArrayList<>();
        LinkedList<Integer>[] adjList = new LinkedList[n];
        int nc = 1;
        V.add(s);
        N.add(s);

        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<>();
            System.out.print("Nr de succesori ai nodului " + (i + 1) + " : ");
            int ni = sc.nextInt();
            System.out.println(ni);
            System.out.print("Succesorii: ");
            for (int j = 0; j < ni; j++)
                adjList[i].add(sc.nextInt() - 1);
            System.out.println(adjList[i]);
        }

        for (int i = 0; i < n; i++) {
            if (i != s) {
                U.add(i);
            }
        }

        System.out.print("N= ");

        while (W.size() != n) {
            while (!V.isEmpty()) {
                int x = V.get(V.size() - 1);
                boolean contains = false;
                for (int i = 0; i < adjList[x].size(); i++) {
                    if (U.contains(adjList[x].get(i))) {
                        int y = adjList[x].get(i);
                        U.remove(Integer.valueOf(y));
                        V.add(y);
                        N.add(y);
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    V.remove(Integer.valueOf(x));
                    W.add(x + 1);
                }


            }
            if (!U.isEmpty()) {
                V.add(U.get(0));
                nc++;
                System.out.print(N+" ");
                N.clear();
                N.add(U.get(0));
                U.remove(0);
            }

        }
        System.out.println();
        System.out.println("W = " + W);
    }

}