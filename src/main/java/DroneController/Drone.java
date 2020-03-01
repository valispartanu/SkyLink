package DroneController;

import Client.Request;
import Map.Graph;
import Map.Node;
import io.vavr.collection.Array;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static java.lang.Double.NaN;

public class Drone {
    private double[] vector = new double[3];
    //destinatie, sursa, pozitie, altitudine
    @Getter
    private int id;
    private int d, s, p, alt;
    private double x;
    private double y;
    private int[] path;
    private DroneStatus status;
    private int fuel, capacity;
    private int consumption;
    private double speed;

    LocalDateTime lastUpdated;
    LocalDateTime arrival;

    private Request r;
    private Node start, finish;

    public Drone(){
        fuel = 100;
        consumption = 1;
        d = 0;
        s = 0;
        p = 0; // hub
        alt = 0;
        status = DroneStatus.SLEEPING;
        speed = 0.000000000000001;
    }

    public Drone(double x, double y, int fuel, int capacity, int consumption, double speed, int id) {
        this.x = x;
        this.y = y;
        this.fuel = fuel;
        this.capacity = capacity;
        this.consumption = consumption;
        this.speed = speed;
        lastUpdated = LocalDateTime.now();
        this.status = DroneStatus.SLEEPING;
        this.id = id;
    }

    public void updateDestination(Node d){
        double mag = Graph.calculateDistance(x, y, d.getX(), d.getY());
        System.out.println(mag + " " + status);
//        System.out.println(mag + " " + x + " " + y + " | " + d.getX() + " " + d.getY());
        vector[0] = ((d.getX() - x)/mag)*speed;
        vector[1] = ((d.getY() - y)/mag)*speed;
        //System.out.println(Arrays.toString(vector));
    }

    public void updateRequest(Request r){
        //System.out.println("initial mag: "+Graph.calculateDistance(r.getStart().getX(), r.getFinish().getX(), r.getStart().getY(), r.getFinish().getY()));
        System.out.println(r.getStart().getX() + " " + r.getStart().getY() + " | " + r.getFinish().getX() + " " + r.getFinish().getY());
        this.r = r;
        start = r.getStart();
        updateDestination(r.getFinish());
    }

    boolean arrived(Node d){
        double mag = Graph.calculateDistance(x, y, d.getX(), d.getY());
        if(mag <= 0.003)
            return true;
        else return false;
    }

    public int getPercentage(){
        double dt = Graph.calculateDistance(r.getStart().getX(), r.getStart().getY(), r.getFinish().getX(), r.getFinish().getY());
        double dp = Graph.calculateDistance(x, y, r.getFinish().getX(), r.getFinish().getY());
        System.out.println(dt + " " + dp);
        return (int) (100-dp/dt*100.0);
    }

    public void updatePos(){

        //if(status != DroneStatus.SLEEPING && x!=NaN && status != DroneStatus.TOPICKUP)
//        if(id == 0 && status != DroneStatus.SLEEPING)
//            System.out.println("Drone " + id + " new position: "+ x + ", " + y + ", having status " + status);

        if(status == DroneStatus.TOPICKUP)
            if(arrived(r.getStart())){
                status = DroneStatus.INSERVICE;
                updateDestination(r.getFinish());
                System.out.println("Arrived at the pickup point" + x + " " + y);
            }
        if(status == DroneStatus.INSERVICE) {
            System.out.println(getPercentage() + "%");
            if (arrived(r.getFinish())) {
                status = DroneStatus.SLEEPING;
                updateDestination(r.getFinish());
                System.out.println("Arrived at the destination" + x + " " + y);
            }
        }
        if(status == DroneStatus.INSERVICE || status == DroneStatus.TOPICKUP) {
            double diff = Math.abs(Duration.between(LocalDateTime.now(), lastUpdated).toMillis()) / 1000.0;
            //System.out.println(vector[0]*diff);
            updateDestination(r.getFinish());
            x += (vector[0] * diff);
            y += (vector[1] * diff);
//            alt += (vector[2] * diff);
            lastUpdated = LocalDateTime.now();
        }

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double[] getVector() {
        return vector;
    }

    public int getD() {
        return d;
    }

    public int getS() {
        return s;
    }

    public int getP() {
        return p;
    }

    public int getAlt() {
        return alt;
    }

    public int[] getPath() {
        return path;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public int getFuel() {
        return fuel;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public void setPath(int[] path) {
        this.path = path;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}
