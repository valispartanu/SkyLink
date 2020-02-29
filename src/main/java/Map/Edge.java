package Map;

import static java.util.Collections.swap;

public class Edge {

    private Node s, d;
    private double cost;

    public Edge(Node s, Node d, int cost) {
        this.s = s;
        this.d = d;
        this.cost = cost;
    }

    public Edge(Edge e){
        this.s = e.getS();
        this.d = e.getD();
        this.cost = e.getCost();
    }

    public Edge(Node s, Node d) {
        this.s = s;
        this.d = d;
    }

    Edge swapNodes(){
        Node aux = s;
        s = d;
        d = aux;
        return this;
    }

    public Node getS() {
        return s;
    }

    public Node getD() {
        return d;
    }

    public double getCost() {
        return cost;
    }


}
