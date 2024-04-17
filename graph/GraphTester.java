package graph;

 

public class GraphTester {

	private static DiGraph graph;
	
	public static void main(String[] args) {
	

		
		graph = new DiGraphImpl();
		
		//add nodes
		GraphNode nodeA = new GraphNode("A");
		GraphNode nodeB = new GraphNode("B");
		GraphNode nodeC = new GraphNode("C");
		GraphNode nodeD = new GraphNode("D");
		GraphNode nodeE = new GraphNode("E");
		GraphNode nodeF = new GraphNode("F");
		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);
		
		//add edges
		graph.addEdge(nodeA, nodeB, 1);
		graph.addEdge(nodeB, nodeC, 2);
		graph.addEdge(nodeC, nodeD, 3);
		graph.addEdge(nodeD, nodeE, 4);
		graph.addEdge(nodeE, nodeF, 5);
		
		//describe
		List<GraphNode> adjacentNodes = graph.getAdjacentNodes(nodeB);
		System.out.println("Adjacent nodes of B: ");
		for(GraphNode node : adjacentNodes) {
			System.out.println(node.getValue());
		}
		List<GraphNode> adjacentNodes1 = graph.getAdjacentNodes(nodeE);
		System.out.println("Adjacent nodes of E: ");
		for(GraphNode node : adjacentNodes1) {
			System.out.println(node.getValue());
			
		//test nodes are adjacent
		System.out.println("Are A and B adjacent? " + graph.nodesAreAdjacent(nodeA, nodeB));
		System.out.println("Are C and F adjacent? " + graph.nodesAreAdjacent(nodeC, nodeF));
	
		//test remove edge
		graph.removeEdge(nodeA, nodeB);
			
		//test reachablity
		System.out.println("Is C reachable from A? " + graph.nodeIsReachable(nodeA, nodeC));
		System.out.println("Is D reachable from E? " + graph.nodeIsReachable(nodeD, nodeE));
		
		
		//test hasCycles
		System.out.println("Does the graph have cycles? " + graph.hasCycles());	
		
		//test fewest hops
		System.out.println("Fewest hops from A to C: " + graph.fewestHops(nodeA, nodeC));
		System.out.println("Fewest hops from A to F: " + graph.fewestHops(nodeA, nodeF));
		
		//test shortest path
		System.out.println("Shortest path from A to C: " + graph.shortestPath(nodeA, nodeC));
		System.out.println("Shortest path from D to F: " + graph.shortestPath(nodeD, nodeF));
		
		
	}
	
}
