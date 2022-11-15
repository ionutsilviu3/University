package ag.Parcurgeri;

public class Utils {
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
}
