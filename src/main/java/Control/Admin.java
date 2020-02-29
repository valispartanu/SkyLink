package Control;

import DroneController.Drone;
import Map.Graph;
import Map.Node;
import Map.NodeDesc;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Admin {

    private List<Drone> drones;
    private static Admin instance;

    public static Admin getInstance(){
        if(instance == null)
            instance = new Admin();
        return instance;
    }

    public Admin(){
        drones = new ArrayList<>();
        importDrones();
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void importDrones() {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/drones.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                //System.out.println(Arrays.toString(values));
                drones.add(new Drone(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(drones.size());
    }

    public void importNodes(){
        Graph graph = Graph.getGraph();
        int id=0;
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/nodes.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                //System.out.println(Arrays.toString(values));
                if(values[2].equals("P"))
                    graph.addNode(new Node(Double.parseDouble(values[0]), Double.parseDouble(values[1]), id, NodeDesc.PICKUP));
                if(values[2].equals("H"))
                    graph.addNode(new Node(Double.parseDouble(values[0]), Double.parseDouble(values[1]), id, NodeDesc.HUB));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
