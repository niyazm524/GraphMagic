package ml.bimdev.graphmagic.graphview.graphs;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import ml.bimdev.graphmagic.graph.UndirectedGraph;
import ml.bimdev.graphmagic.graph.Vertex;
import ml.bimdev.graphmagic.graphview.Visible;

public class VisibleUndirectedGraph extends UndirectedGraph implements Visible {
    Random random = new Random();
    Paint paint;

    public VisibleUndirectedGraph() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        for(Vertex v : vertexList) {
            canvas.drawCircle(v.x, v.y, 40, paint);
        }
    }

    @Override
    public void addVertex(Vertex vertex) {
        vertex.x = random.nextInt(SIZE-50)+50;
        vertex.y = random.nextInt(SIZE-50)+50;
        super.addVertex(vertex);

    }
}
