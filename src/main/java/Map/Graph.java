package Map;

import Control.Admin;

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

    public void addNode(Node n){
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

        System.out.println(x + " " + y);

        if(nodes.size() == 0)
            Admin.getInstance().importNodes();
        Node min = nodes.get(0);
        double d = calculateDistance(x, y, min.getX(), min.getY());
        System.out.println("dist nod 1: " + calculateDistance(x, y, nodes.get(0).getX(), nodes.get(0).getY()));
        System.out.println("dist nod 2: " + calculateDistance(x, y, nodes.get(1).getX(), nodes.get(1).getY()));
        for (Node n : nodes) {
            //System.out.println("nod: " + n.getX() + " " + n.getY() );
            if(n.getS() == NodeDesc.PICKUP) {
                if (calculateDistance(x, y, n.getX(), n.getY()) < d) {
                    d = calculateDistance(x, y, n.getX(), n.getY());
                    min = n;
                }
            }
        }
        return min;
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2){
        return (sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
    }

    public Node getClosestHub(Node x){
        if(nodes.size() == 0)
            Admin.getInstance().importNodes();
        Node min = nodes.get(0);
        double d = calculateDistance(x.getX(), x.getY(), min.getX(), min.getY());
        //System.out.println("dist nod 1: " + calculateDistance(x, nodes.get(0).getX(), y, nodes.get(0).getY()));
        //System.out.println("dist nod 2: " + calculateDistance(x, nodes.get(1).getX(), y, nodes.get(1).getY()));
        for (Node n : nodes) {
            System.out.println("nod: " + n.getX() + " " + n.getY() );
            if(n.getS() == NodeDesc.HUB) {
                if (calculateDistance(x.getX(), x.getY(), n.getX(), n.getY()) < d) {
                    d = calculateDistance(x.getX(), x.getY(), n.getX(), n.getY());
                    min = n;
                }
            }
        }
        return min;
    }



}