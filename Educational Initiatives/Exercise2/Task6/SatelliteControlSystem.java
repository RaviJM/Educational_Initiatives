import java.util.logging.Logger;
import java.util.*;

class Satellite {
    private String orientation;
    private boolean solarPanelsActive;
    private int dataCollected;
    private static final Logger logger = Logger.getLogger(Satellite.class.getName());

    public Satellite() {
        this.orientation = "North";
        this.solarPanelsActive = false;
        this.dataCollected = 0;
        logger.info("Satellite initialized. Orientation: North, Solar Panels: Inactive, Data Collected: 0");
    }

    public void rotate(String direction) {
        this.orientation = direction;
        logger.info("Satellite rotated to " + direction);
    }

    public void activatePanels() {
        this.solarPanelsActive = true;
        logger.info("Solar panels activated.");
    }

    public void deactivatePanels() {
        this.solarPanelsActive = false;
        logger.info("Solar panels deactivated.");
    }

    public void collectData() {
        if (solarPanelsActive) {
            this.dataCollected += 10;
            logger.info("Data collected. Total Data: " + this.dataCollected);
        } else {
            logger.warning("Cannot collect data. Solar panels are inactive.");
        }
    }

    public String getState() {
        return "Orientation: " + orientation + ", Solar Panels: " + (solarPanelsActive ? "Active" : "Inactive") + ", Data Collected: " + dataCollected;
    }
}

// Command Pattern
interface SatelliteCommand {
    void execute();
}

class RotateCommand implements SatelliteCommand {
    private Satellite satellite;
    private String direction;

    public RotateCommand(Satellite satellite, String direction) {
        this.satellite = satellite;
        this.direction = direction;
    }

    @Override
    public void execute() {
        satellite.rotate(direction);
    }
}

class ActivatePanelsCommand implements SatelliteCommand {
    private Satellite satellite;

    public ActivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.activatePanels();
    }
}

class DeactivatePanelsCommand implements SatelliteCommand {
    private Satellite satellite;

    public DeactivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.deactivatePanels();
    }
}

class CollectDataCommand implements SatelliteCommand {
    private Satellite satellite;

    public CollectDataCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.collectData();
    }
}

public class SatelliteControlSystem {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();

        SatelliteCommand rotateSouth = new RotateCommand(satellite, "South");
        SatelliteCommand rotateEast = new RotateCommand(satellite, "East");
        SatelliteCommand rotateWest = new RotateCommand(satellite, "West");
        SatelliteCommand rotateNorth = new RotateCommand(satellite, "North");

        SatelliteCommand activatePanels = new ActivatePanelsCommand(satellite);
        SatelliteCommand deactivatePanels = new DeactivatePanelsCommand(satellite);

        SatelliteCommand collectData = new CollectDataCommand(satellite);
        
        rotateSouth.execute();
        activatePanels.execute();
        collectData.execute();

        // System.out.println(satellite.getState());

        Scanner sc = new Scanner(System.in);

        System.out.println("############################   Enter Choice   ############################");
        System.out.println(">>>  0 - Exit");
        System.out.println(">>>  1 - Continue");

        String choice = sc.nextLine();

        while (!choice.equals("0")){
            System.out.println("############################   Enter Operation   ############################");
            System.out.println(">>>  rotate(direction)");
            System.out.println(">>>  activatepanels");
            System.out.println(">>>  deactivatepanels");
            System.out.println(">>>  collectdata");
            System.out.println();
            System.out.println(">>>  0 - Exit");

            choice = sc.nextLine();

            if (choice.startsWith("rotate")){
                if (choice.toLowerCase().equals("rotate(east)")) rotateEast.execute();
                else if (choice.toLowerCase().equals("rotate(west)")) rotateWest.execute();
                else if (choice.toLowerCase().equals("rotate(north)")) rotateNorth.execute();
                else if (choice.toLowerCase().equals("rotate(south)")) rotateSouth.execute();
                else{
                    System.out.println("Enter correct direction!");
                    //nothing
                }
            }

            else if (choice.toLowerCase().equals("activatepanels")) activatePanels.execute();
            else if (choice.toLowerCase().equals("deactivatepanels")) deactivatePanels.execute();
            else if (choice.toLowerCase().equals("collectdata")) collectData.execute();

            else if (choice.equals("0")){
                break;
            }

            else {
                System.out.println("Enter correct choice!");
            }
        }
    }
}
