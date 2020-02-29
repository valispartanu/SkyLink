package DroneController;

import Client.Request;
import Map.Graph;
import Map.Node;

import java.time.Duration;
import java.time.LocalDateTime;

public class Drone {
    private double[] vector = new double[3];
    //destinatie, sursa, pozitie, altitudine
    private int d, s, p, alt;
    private int x, y;
    private int[] path;
    private DroneStatus status;
    private int fuel, capacity;
    private int consumption;
    private double speed;

    LocalDateTime lastUpdated;
    LocalDateTime arrival;

    Request r;
    Node start, finish;

    public Drone(){
        fuel = 100;
        consumption = 1;
        d = 0;
        s = 0;
        p = 0; // hub
        alt = 0;
        status = DroneStatus.SLEEPING;
        speed = 0.001;
    }

    public Drone(double[] vector, int d, int s, int p, int alt, int[] path, DroneStatus status, int fuel, int capacity, int consumption, double speed) {
        this.vector = vector;
        this.d = d;
        this.s = s;
        this.p = p;
        this.alt = alt;
        this.path = path;
        this.status = status;
        this.fuel = fuel;
        this.capacity = capacity;
        this.consumption = consumption;
        this.speed = 1;
        x = 0;
        y = 0;
    }

    public void updateDestination(Node d){
        finish = d;
        double mag = Graph.calculateDistance(x, y, d.getX(), d.getY());
        vector[0] = ((d.getX() - x)/mag)*speed;
        vector[1] = ((d.getY() - y)/mag)*speed;
    }

    public void updateRequest(Request r){
        this.r = r;
        start = r.getStart();
        updateDestination(r.getFinish());
    }

    //TODO arrival

    public void updatePos(){
        long diff = Math.abs(Duration.between(LocalDateTime.now(), lastUpdated).toSeconds());
        x += (vector[0] * diff);
        y += (vector[1] * diff);
        alt += (vector[2] * diff);
        lastUpdated = LocalDateTime.now();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
