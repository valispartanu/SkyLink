package Control;

import Client.Request;
import DroneController.Drone;
import DroneController.DroneStatus;
import Map.Graph;
import org.springframework.stereotype.Component;
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
            if(Graph.calculateDistance(r.getStart().getX(), d.getX(), r.getStart().getY(), d.getY()) < mindist && hasFuel() && d.getStatus() != DroneStatus.INSERVICE){
                mindist = Graph.calculateDistance(r.getStart().getX(), d.getX(), r.getStart().getY(), d.getY());
                min = d;
            }
        }
        return min;
    }

    public Drone assignDrone(Request r){
        Drone d = pickClosestAvailableDrone(r);
        d.setStatus(DroneStatus.INSERVICE);
        d.updateRequest(r);
        return d;
    }
}