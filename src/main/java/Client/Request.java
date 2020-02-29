package Client;

import Map.Graph;
import Map.Node;

public class Request {
    Node start, finish;
    int capacity;

    public Request(double x1, double y1, double x2, double y2, int capacity){
        start = Graph.getGraph().getClosestPickupNode(x1, y1);
        finish = Graph.getGraph().getClosestPickupNode(x2, y2);
        this.capacity = capacity;
    }

    public Node getStart() {
        return start;
    }

    public Node getFinish() {
        return finish;
    }

    public int getCapacity() {
        return capacity;
    }
}
