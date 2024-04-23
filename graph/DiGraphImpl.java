package graph;

import java.util.ArrayList;
import java.util.List;
 
//test comment another and another
public class DiGraphImpl implements DiGraph{

	private List<GraphNode> nodeList = new ArrayList<>();
	@Override
	public Boolean addNode(GraphNode node) {
		if(!nodeList.contains(node)) { 
			nodeList.add(node);
			return true;
		}
		return false;
	}
	@Override
	public Boolean removeNode(GraphNode node) {
		if(nodeList.contains(node)) {
			nodeList.remove(node);
			for(GraphNode otherNode : nodeList) {
				otherNode.removeNeighbor(node);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		if(nodeList.contains(node)) {
			node.setValue(newNodeValue);
			return true;
		}
		return false;
	}
	
	@Override 
	public String getNodeValue(GraphNode node) {
		return node.getValue();
	}
	
	@Override 
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		if(nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			fromNode.addNeighbor(toNode, weight);
			return true;
		}
		return false;
	}
	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		if(nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			return fromNode.removeNeighbor(toNode);
		
			}
		return false;
	}
	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		if(nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			Integer currentWeight = fromNode.getDistanceToNeighbor(toNode);
			if(currentWeight != null) {
				fromNode.addNeighbor(toNode, newWeight);
				return true;
			}
	}
	return false;
	}
	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		if(nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			return fromNode.getDistanceToNeighbor(toNode);
		}
		return null;
	}
	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node){
		if(nodeList.contains(node)) {
			return node.getNeighbors();
		}
		return new ArrayList<>();
	}
	
	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		if(nodeList.contains(fromNode) && nodeList.contains(toNode)) {
			return fromNode.getDistanceToNeighbor(toNode) != null;
		}
		return false;
	}
	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		return false;
	}
	@Override
	public Boolean hasCycles() {
		return false;
	}
	@Override
    public List<GraphNode> getNodes() {
        return nodeList;
    }

    @Override
    public GraphNode getNode(String nodeValue) {
        for (GraphNode node : nodeList) {
            if (node.getValue().equals(nodeValue)) {
                return node;
            }
        }
        return null;
    }

public int fewestHops(GraphNode fromNode, GraphNode toNode) {
        if(fromNode == null || toNode == null || !nodeList.contains(fromNode) || !nodeList.contains(toNode)) {
        	return -1;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();
        Map<GraphNode, Integer> hops = new HashMap<>();
        
        queue.add(fromNode);
        hops.put(fromNode, 0);
        
        while(!queue.isEmpty()) {
        	GraphNode currentNode = queue.poll();
        	visited.add(currentNode);
        	
        	if(currentNode == toNode) {
        		return hops.get(currentNode);
        	}
        	for(GraphNode neighbor : currentNode.getNeighbors()) {
        		if(!visited.contains(neighbor)) {
        			queue.add(neighbor);
        			hops.put(neighbor, hops.get(currentNode) + 1);
        		}
        	}
        }
        return -1;
    }

    @Override
    public int shortestPath(GraphNode fromNode, GraphNode toNode) {
        if(fromNode == null || toNode == null || !nodeList.contains(fromNode) || !nodeList.contains(toNode)) {
        	return -1;
        }
        Map<GraphNode, Integer> distances = new HashMap<>();
        Map<GraphNode, GraphNode> previousNodes = new HashMap<>();
        Set<GraphNode> unvisitedNodes = new HashSet<>();
        
        for(GraphNode node : nodeList) {
        	distances.put(node, Integer.MAX_VALUE);
        	previousNodes.put(node, null);
        	unvisitedNodes.add(node);
        }
        distances.put(fromNode, 0);
        
        while(!unvisitedNodes.isEmpty()) {
        	GraphNode currentNode = null;
        	int minDistance = Integer.MAX_VALUE;
        	
        	for(GraphNode node : unvisitedNodes) {
        		if(distances.get(node) < minDistance) {
        			minDistance = distances.get(node);
        			currentNode = node;
        		}
        }
        
        if(currentNode == null)
        	break;
        	
        unvisitedNodes.remove(currentNode);
        
        for(GraphNode neighbor : currentNode.getNeighbors()) {
        	int newDistance = distances.get(currentNode) + currentNode.getDistanceToNeighbor(neighbor);
        	if(newDistance < distances.get(neighbor)) {
        		distances.put(neighbor, newDistance);
        		previousNodes.put(neighbor, currentNode);
        	}
        }
        }
        
    
    	List<GraphNode> shortestPath = new ArrayList<>();
    	GraphNode current = toNode;
    	while(current != null) {
    		shortestPath.add(current);
    		current = previousNodes.get(current);
    	}
    	Collections.reverse(shortestPath);
    	return shortestPath.size() -1;
}
}
	
	


	
	
