package it.unibo.generics.graph.impl;

import it.unibo.generics.graph.api.Graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GraphImpl<N> implements Graph<N>{
    private Map<N,Set<N>> data = new HashMap<>();


    public void addNode(N node) {
        if (node!=null && !this.data.containsKey(node)) {
            this.data.put(node, new HashSet<>());
        }
    }

    public void addEdge(N source, N target) {
        if (this.data.containsKey(source) && this.data.containsKey(target)) {
            Set<N> near = linkedNodes(source);
            near.add(target);
            this.data.put(source,near);
        }
    }

    public Set<N> nodeSet() {
        return new HashSet<>(this.data.keySet());
    }

    public Set<N> linkedNodes(N node) {
        return new HashSet<>(this.data.get(node));
    }

    public List<N> getPath(N source, N target) {
        if (!this.data.containsKey(source) || !this.data.containsKey(target)) {
            return Collections.emptyList();
        }

        Set<N> visited = new HashSet<>();
        Queue<N> queue = new LinkedList<>();
        Map<N,N> backward = new HashMap<>();
        List<N> path = new LinkedList<>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            N currentNode = queue.poll();

            if (currentNode.equals(target)) {
                for (N n = target; n != null; n = backward.get(n)) {
                    path.add(0,n);
                }
                break;
            }

            for (N adiacent : linkedNodes(currentNode)) {
                if (!visited.contains(adiacent)) {
                    visited.add(adiacent);
                    queue.add(adiacent);
                    backward.put(adiacent, currentNode);

                }
            }
        }
        
        return path;


    }






}
