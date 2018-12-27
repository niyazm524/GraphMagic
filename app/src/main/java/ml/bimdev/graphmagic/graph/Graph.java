package ml.bimdev.graphmagic.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Graph implements Iterable<Vertex> {
    protected List<Vertex> vertexList;

    Graph() {
        vertexList = new ArrayList<>();
    }

    public Vertex getVertex(int id) {
        return vertexList.get(id);
    }

    public boolean hasVertex(Vertex v) {
        return vertexList.contains(v);
    }

    public void addVertex(Vertex v) {
        vertexList.add(v);
    }

    public void sortVerticles() {
        Collections.sort(vertexList);
    }
}
