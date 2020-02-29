package Map;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Graph {

    private static Graph graph;

    public static Graph getGraph() {
        if (graph == null) {
            graph = new Graph();
        }
        return graph;
    }

    List<Node> nodes;
    List<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Node getNodeByID(int id) {
        for (Node n : nodes) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    void addPickUpPoint(Node n) {
        getNodeByID(n.getId()).setS(NodeDesc.PICKUP);
    }

    void addNode(Node n){
        nodes.add(n);
    }

    void addEdgeBidirectional(Edge e){
        edges.add(e);
        edges.add(new Edge(e).swapNodes());
        getNodeByID(e.getS().getId()).addChild(e.getD());
        getNodeByID(e.getD().getId()).addChild(e.getS());
    }

    void addEdgeUnidirectional(Edge e){
        edges.add(e);
        getNodeByID(e.getS().getId()).addChild(e.getD());
    }

    public Node getClosestPickupNode(double x, double y){

        Node min = nodes.get(0);
        double d = calculateDistance(x, min.getX(), y, min.getY());
        for (Node n : nodes) {
            if(n.getS() == NodeDesc.PICKUP) {
                if (calculateDistance(x, n.getX(), y, n.getY()) < d) {
                    d = calculateDistance(x, n.getX(), y, n.getY());
                    min = n;
                }
            }
        }
        return min;
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2){
        return sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    public Node getClosestHub(Node x){
        Node min = nodes.get(0);
        double d = calculateDistance(x.getX(), min.getX(), x.getY(), min.getY());
        for (Node n : nodes) {
            if(n.getS() == NodeDesc.HUB) {
                if (calculateDistance(x.getX(), n.getX(), x.getY(), n.getY()) < d) {
                    d = calculateDistance(x.getX(), n.getX(), x.getY(), n.getY());
                    min = n;
                }
            }
        }
        return min;
    }

}