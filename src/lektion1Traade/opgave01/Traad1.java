package lektion1Traade.opgave01;

import java.util.Scanner;

public class Traad1 extends Thread {
    private final Resource resource;

    public Traad1(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        Scanner inputFraTastatur = new Scanner(System.in);
        while (true) {
            String message = inputFraTastatur.next();
            resource.setStringResource(message);
        }
    }
}
