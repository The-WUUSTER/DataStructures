package node;

import java.util.*;

/**
 * Created by Edmond Wu on 9/12/2016.
 */
public class GraphNode<T> extends Node<T> {

    private HashSet<GraphNode<T>> neighbors;

    /**
     * Node for a graph
     * @param value data
     */
    public GraphNode(T value) {
        super(value);
        neighbors = new HashSet<>();
    }

    /**
     * Returns the node's list of neighbors
     * @return hashset of node's neighbors
     */
    public HashSet<GraphNode<T>> getNeighbors() {
        return neighbors;
    }
}
