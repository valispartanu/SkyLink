package DroneController;

public class Drone {
    int[] vector = new int[3];
    //destinatie, sursa, pozitie, altitudine
    int d, s, p, alt;
    int[] path;
    DroneStatus status;
    int fuel, capacity;
    int consumption; // mpkwh

    public Drone(){
        fuel = 100;
        consumption = 1;
        d = 0;
        s = 0;
        p = 0; // hub
        alt = 0;
        status = DroneStatus.SLEEPING;
    }

    public Drone(int[] vector, int d, int s, int p, int alt, int[] path, DroneStatus status, int fuel, int capacity, int consumption) {
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
    }

    public int[] getVector() {
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

    public void setVector(int[] vector) {
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
