package ml.bimdev.graphmagic.graph;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    protected List<Vertex> neighbors = new ArrayList<>();
    public float x, y;

    public Vertex() {

    }

    public Vertex(Vertex... neighbors) {
        this.neighbors.addAll(Arrays.asList(neighbors));
    }

    public int deg() {
        return neighbors.size();
    }

    public boolean isAdjacent(Vertex other) {
        return neighbors.contains(other);
    }

    @Override
    public int compareTo(@NonNull Vertex o) {
        return Integer.compare(neighbors.size(), o.deg());
    }
}
