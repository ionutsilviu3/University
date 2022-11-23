package ag.Parcurgeri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BFS {

    public static void main(String[] args) throws FileNotFoundException {

        try (Scanner sc = new Scanner(new File("ag/Parcurgeri/graf.txt"))) {

            Queue<Integer> V = new LinkedList<>();
            ArrayList<Integer> U = new ArrayList<>();
            ArrayList<Integer> W = new ArrayList<>();

            //System.out.print("Introduceti numarul de noduri: ");
            int n = sc.nextInt();
            int ni;
            LinkedList<Integer>[] adjList = new LinkedList[n];
            //System.out.print("Introduceti nodul sursa: ");
            int s = sc.nextInt();
            int l[] = new int[n];
            int p[] = new int[n];

            for (int i = 0; i < n; i++) {
                adjList[i] = new LinkedList<>();
                //System.out.println("Numarul de succesori ai nodului " + i);
                ni = sc.nextInt();
                //System.out.println("Introduceti succesorii: ");
                for (int j = 0; j < ni; j++) {
                    adjList[i].add(sc.nextInt());
                }
            }

            V.add(s);

            for (int i = 0; i < n; i++) {
                if (i != s) {
                    U.add(i);
                    l[i] = Integer.MAX_VALUE;
                }
                p[i] = -1;
            }

            while (!V.isEmpty()) {
                int x = V.poll();
                int y = -1;
                for (int i = 0; i < adjList[x].size(); i++) {
                    if (U.contains(adjList[x].get(i))) {
                        y = adjList[x].get(i);
                        U.remove(Integer.valueOf(y));
                        V.add(y);
                        p[y] = x;
                        l[y] = l[x] + 1;
                    }
                }
                W.add(x);
            }

            System.out.println("W = " + W);
            System.out.println("U = " + U);

            System.out.print("o = ");
            Utils.afisareVector(l);


            System.out.print("p = ");
            Utils.afisareVector(p);

        }

    }
}