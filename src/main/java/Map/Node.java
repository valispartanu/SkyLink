package Map;

public class Node {

    int x, y; // pos on the image

    int id;

    NodeDesc s;



    public Node(int x, int y, int id, NodeDesc s) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.s = s;
    }
}
