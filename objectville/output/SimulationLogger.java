package objectville.output;

public class SimulationLogger {
    public void logTickStart(int tick) {
        System.out.println("Tick " + tick);
    }

    public void logService(String type, int r, int c, String service) {
        System.out.println(type + " at (" + r + "," + c + ") received " + service + " service");
    }

    public void logUtility(String type, int r, int c, int amount, String utility) {
        System.out.println(type + " at (" + r + "," + c + ") received " + amount + " " + utility);
    }

    public void logResource(String type, int r, int c, int amount, String resource) {
        System.out.println(type + " at (" + r + "," + c + ") received " + amount + " " + resource);
    }

    public void logProduction(String type, int r, int c, int amount, String resource) {
        System.out.println(type + " at (" + r + "," + c + ") generated " + amount + " " + resource);
    }

    public void logLevelChange(String type, int r, int c, int oldL, int newL) {
        if (newL > oldL) {
            System.out.println(type + " at (" + r + "," + c + ") levels up from " + oldL + " to " + newL);
        } else {
            System.out.println(type + " at (" + r + "," + c + ") levels down from " + oldL + " to " + newL);
        }
    }
}
