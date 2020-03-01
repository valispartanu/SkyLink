package Control;

import DroneController.Drone;
import Map.Graph;
import Map.Node;
import Map.NodeDesc;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class DroneRunner extends Thread{
    public void run() {
        try {
            while(true)
                for(Drone d:Admin.getInstance().getDrones())
                    d.updatePos();
        }
        catch (Exception e) {
            System.out.println ("Exception is caught");
            e.printStackTrace();
        }
    }
}

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

    public Drone getDroneById(int id) {return drones.get(id);}

    public void importDrones() {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/drones.csv"));) {
            String[] values = null;
            int id = 0;
            while ((values = csvReader.readNext()) != null) {
                //System.out.println(Arrays.toString(values));
                drones.add(new Drone(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), drones.size()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DroneRunner dr =  new DroneRunner();
        dr.start();
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
