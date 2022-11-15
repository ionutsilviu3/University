package ag.Parcurgeri;

import java.util.*;

public class BFS {

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

        Queue<Integer> V = new LinkedList<>();
        ArrayList<Integer> U = new ArrayList<>();
        ArrayList<Integer> W = new ArrayList<>();
        V.add(s);

        int[] l = new int[n];
        int[] p = new int[n];
        l[s] = 0;

        for (int i = 0; i < n; i++) {
            p[i] = -1;
        }
        while(W.size() != n)
        {
            while (!V.isEmpty()) {
                int x = V.peek();
                int y = -1;
                for (int i = 0; i < adjList[x].size(); i++) {
                    if (U.contains(adjList[x].get(i))) {
                        y = adjList[x].get(i);
                        p[y] = x;
                        l[y] = l[x] + 1;
                    }
                    else {
                        V.remove(x);
                        W.add(x);
                        break;
                    }
                }
            }
            if(!U.isEmpty())
            {
                s = U.get(0);
                V.add(s);
                U.remove(0);
                l[s] = s;
            }
        }
        System.out.println("W = " + W);
        System.out.println("U = " + U);
        System.out.print("L =  ");
        Utils.afisareVector(l);


    }

}
