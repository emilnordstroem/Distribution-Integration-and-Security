package lektion1Traade.opgave02;

import java.util.ArrayList;
import java.util.List;

public class FletteSortering {
    // den liste der skal sorteres skal vaere global for de rekursive kald
    //den rekursive metode der implementere del-loes og kombiner skabelonen
    public void mergesort(List<Integer> list, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            Thread traadLeft = new Traad(this, list, left, middle);
            Thread traadRight = new Traad(this, list, middle + 1, right);

            traadLeft.start();
            traadRight.start();

            try {
                traadLeft.join();
                traadRight.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(list, left, middle, right);
        }
    }
    private void merge(List<Integer> list, int low, int middle, int high) {
        List<Integer> temp = new ArrayList<Integer>();
        int i = low;
        int j = middle + 1;
        while (i <= middle && j <= high) {
            if (list.get(i).compareTo(list.get(j)) <= 0) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }
        while (i <= middle) {
            temp.add(list.get(i));
            i++;
        }
        while (j <= high) {
            temp.add(list.get(j));
            j++;
        }
        for (int k = 0; k < temp.size(); k++) {
            list.set(low + k, temp.get(k));
        }
    }
}