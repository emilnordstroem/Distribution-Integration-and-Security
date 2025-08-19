package lektion1Traade.opgave02;

import java.util.List;

public class Traad extends Thread {
    private final FletteSortering fletteSortering;
    private final List<Integer> list;
    private final int left;
    private final int right;

    public Traad(FletteSortering fletteSortering, List<Integer> list, int left, int right) {
        this.fletteSortering = fletteSortering;
        this.list = list;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        fletteSortering.mergesort(list, left, right);
    }
}
