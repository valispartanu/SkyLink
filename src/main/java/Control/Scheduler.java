package Control;

import Client.Request;
import DroneController.Drone;
import DroneController.DroneStatus;
import Map.Graph;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    public boolean hasFuel(){
        return true;
    }

    private Drone pickClosestAvailableDrone(Request r){
        Drone min = null;
        double mindist = 9007199254740991.0;
        for(Drone d : Admin.getInstance().getDrones()){
            d.updatePos();
            if(Graph.calculateDistance(r.getStart().getX(), r.getStart().getY(), d.getX(), d.getY()) < mindist && hasFuel() && d.getStatus() == DroneStatus.SLEEPING){
                mindist = Graph.calculateDistance(r.getStart().getX(), r.getStart().getY(), d.getX(), d.getY());
                min = d;
            }
        }
        System.out.println("dist" + mindist);
        return min;
    }

    public Drone assignDrone(Request r){
        Drone d = pickClosestAvailableDrone(r);
        d.updateRequest(r);
        d.setStatus(DroneStatus.TOPICKUP);
        return d;
    }
}