package lektion1Traade.opgave01;

public class Traad2 extends Thread {
    private final Resource resource;

    public Traad2(Resource resource) {
        this.resource = resource;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (resource.getStringResource() != null) {
                System.out.println(resource.getStringResource());
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
