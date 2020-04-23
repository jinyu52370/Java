package com.jinyu.graph;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/22 19:00
 */
public class GraphTest {
    @Test
    public void test(){
        int vertexNum = 5;
        Graph graph = new Graph(vertexNum);
        graph.addVertexes(Arrays.asList("A", "B", "C", "D", "E"));
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);

        graph.printAdjacencyMatrix();

        graph.depthFirstSearch();
        graph.breadthFirstSearch();
    }
}
