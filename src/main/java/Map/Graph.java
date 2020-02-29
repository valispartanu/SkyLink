package Map;

public class Graph {

    private static Graph graph;
    public static Graph getGraph(){
        if(graph == null){
            graph = new Graph();
        }
        return graph;
    }

    public Graph(){

    }

}
