package com.jinyu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/22 18:43
 */
public class Graph {
    //region private & constructor
    private List<String> vertexList;
    private int[][] adjacencyMatrix;
    private int edgesNum;
    private boolean[] isAccess;

    public Graph(int vertexNum){
        adjacencyMatrix = new int[vertexNum][vertexNum];
        vertexList = new ArrayList<>(vertexNum);
        edgesNum = 0;
    }
    //endregion

    /**
     * 添加一个顶点
     * @param vertex 顶点
     */
    public void addVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加一个顶点的集合
     * @param vertexList 顶点集合
     */
    public void addVertexes(List<String> vertexList){
        this.vertexList.addAll(vertexList);
    }

    /**
     * 添加边
     * @param var0 顶点0
     * @param var1 顶点1
     * @param weight 权值
     */
    public void addEdge(int var0, int var1, int weight) {
        adjacencyMatrix[var0][var1] = weight;
        adjacencyMatrix[var1][var0] = weight;
        edgesNum++;
    }

    /**
     * 得到顶点总数
     * @return 顶点总数
     */
    public int getVertexNum(){
        return vertexList.size();
    }

    /**
     * 得到边的总数
     * @return 边的总数
     */
    public int getEdgesNum(){
        return edgesNum;
    }

    /**
     * 根据索引得到顶点名
     * @param index 索引
     * @return 顶点名
     */
    public String getVertexByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 得到权值
     * @param var0 顶点0
     * @param var1 顶点1
     * @return var0 和 var1 之间的权值
     */
    public int getWeight(int var0, int var1){
        return adjacencyMatrix[var0][var1];
    }

    /**
     * 打印邻接矩阵
     */
    public void printAdjacencyMatrix(){
        for (int[] i : adjacencyMatrix) {
            System.out.println(Arrays.toString(i));
        }
    }

    //region DFS
    /**
     * 得到第一个邻接顶点的索引
     * @param index 原顶点索引
     * @return 邻接顶点索引
     */
    private int getFirstNeighborWeight(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (adjacencyMatrix[index][i] != 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到邻接顶点的下一个邻接顶点的索引
     * @param var0 原顶点索引
     * @param var1 邻接顶点索引
     * @return 邻接顶点的下一个邻接顶点索引
     */
    private int getNeighborNextNeighborWeight(int var0, int var1){
        //i为var1的邻接顶点的邻接顶点
        for (int i = var1 + 1; i < vertexList.size(); i++) {
            if (adjacencyMatrix[var0][i] != 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * DFS
     * @param isAccess 顶点是否被访问的数组
     * @param index 顶点索引
     */
    private void depthFirstSearch(boolean[] isAccess, int index){
        System.out.print(getVertexByIndex(index));
        if (index < isAccess.length - 1) {
            System.out.print(" -> ");
        }
        isAccess[index] = true;

        int fnw = getFirstNeighborWeight(index);
        while (fnw != -1){
            if (!isAccess[fnw]) {
                depthFirstSearch(isAccess, fnw);
            }
            fnw = getNeighborNextNeighborWeight(index, fnw);
        }
    }

    /**
     * DFS
     */
    public void depthFirstSearch(){
        isAccess = new boolean[getVertexNum()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isAccess[i]) {
                depthFirstSearch(isAccess, i);
            }
        }
        System.out.println();
    }
    //endregion

    //region BFS
    private void breadthFirstSearch(boolean[] isAccess, int index){
        //队列头索引
        int u;
        //邻接顶点索引
        int w;
        LinkedList<Integer> linkedList = new LinkedList<>();
        //index已访问
        System.out.print(getVertexByIndex(index));
        if (index < isAccess.length - 1) {
            System.out.print(" -> ");
        }
        isAccess[index] = true;
        //index入队
        linkedList.addLast(index);

        while (!linkedList.isEmpty()){
            u = linkedList.removeFirst();
            w = getFirstNeighborWeight(u);
            while (w != -1){
                if (!isAccess[w]){
                    //w已访问
                    System.out.print(getVertexByIndex(w));
                    if (w < isAccess.length - 1) {
                        System.out.print(" -> ");
                    }
                    isAccess[w] = true;
                    //w入队
                    linkedList.addLast(w);
                }
                w = getNeighborNextNeighborWeight(u, w);
            }
        }

    }

    public void breadthFirstSearch(){
        isAccess = new boolean[getVertexNum()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isAccess[i]){
                breadthFirstSearch(isAccess, i);
            }
        }
        System.out.println();
    }
    //endregion
}
