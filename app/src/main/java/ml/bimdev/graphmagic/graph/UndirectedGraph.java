package ml.bimdev.graphmagic.graph;

import android.support.annotation.NonNull;

import java.util.Iterator;

public class UndirectedGraph extends Graph {

    @NonNull
    @Override
    public Iterator<Vertex> iterator() {
        return vertexList.iterator();
    }
}
