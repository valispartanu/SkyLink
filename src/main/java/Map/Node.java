package Map;

import java.util.*;

public class Node {

    private double x, y; // pos on the image

    private int id;

    private NodeDesc s;

    private Map<Node, Edge> edges;
    private List<Node> children;

    public Node(double x, double y, int id, NodeDesc s) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.s = s;
        edges = new HashMap<Node, Edge>();
        children = new ArrayList<>();
    }

    void addChild(Node x){
        children.add(x);
        edges.put(x, new Edge(this, x));
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodeDesc getS() {
        return s;
    }

    public void setS(NodeDesc s) {
        this.s = s;
    }

    public Map<Node, Edge> getEdges() {
        return edges;
    }

    public void setEdges(Map<Node, Edge> edges) {
        this.edges = edges;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
