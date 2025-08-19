package lektion1Traade.opgave01;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Resource resource = new Resource("");

        Thread insaetThread = new Traad1(resource);
        Thread printThread = new Traad2(resource);

        insaetThread.start();
        printThread.start();

    }

}
