package Control;

import DroneController.Drone;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    List<Drone> drones;
    private static Admin instance;

    public static Admin getInstance(){
        if(instance == null)
            instance = new Admin();
        return instance;
    }

    public Admin(){
        drones = new ArrayList<>();
    }

}
