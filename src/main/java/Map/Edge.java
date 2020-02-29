package Map;

public class Edge {

    Node n1, n2;
    int cost;

    public Edge(Node n1, Node n2, int cost) {
        this.n1 = n1;
        this.n2 = n2;
        this.cost = cost;
    }

    public Edge(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
}
