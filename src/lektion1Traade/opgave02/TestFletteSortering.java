package lektion1Traade.opgave02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFletteSortering {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random();
        // Without threads: 1045
        // With threads: x
        for (int i = 0; i < 1000; i++) {
            list.add(Math.abs(r.nextInt() % 10000));
        }

        FletteSortering sort = new FletteSortering();

        long l1, l2;
        l1 = System.nanoTime();
        sort.mergesort(list, 0, list.size() - 1);
        l2 = System.nanoTime();

        System.out.println(list);
        System.out.println("Koeretiden var " + (l2 - l1) / 1000000);

    }
}
