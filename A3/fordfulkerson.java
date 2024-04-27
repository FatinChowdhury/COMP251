import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph) {
		ArrayList<Integer> path = new ArrayList<>();
		boolean[] visited = new boolean[graph.getNbNodes()];
		Stack<Integer> stack = new Stack<>();
	
		stack.push(source);
		visited[source] = true;
	
		while (!stack.isEmpty()) {
			Integer node = stack.pop();
			path.add(node);
	
			if (node == destination) {
				break;
			}
	
			for (Edge edge : graph.getEdges()) {
				if (edge.nodes[0] == node && !visited[edge.nodes[1]]) {
					stack.push(edge.nodes[1]);
					visited[edge.nodes[1]] = true;
				}
			}
		}
	
		return path;
	}



	public static String fordfulkerson(Integer source, Integer destination, WGraph graph){
		int maxFlow = 0;
		WGraph residualGraph = new WGraph(graph); // Create a copy of the graph to work with
		WGraph tempGraph = new WGraph(graph); // Temporary graph to keep track of the current flow
	
		while (true) {
			ArrayList<Integer> path = pathDFS(source, destination, residualGraph);
			if (path.size() == 0) {
				break; // No more augmenting paths, so we're done
			}
	
			int pathFlow = Integer.MAX_VALUE;
			for (int i = 0; i < path.size() - 1; i++) {
				Edge edge = residualGraph.getEdge(path.get(i), path.get(i + 1));
				if (edge != null) {
					pathFlow = Math.min(pathFlow, edge.weight);
				}
			}
	
			maxFlow += pathFlow;
	
			// Update the residual graph and the temporary graph
			for (int i = 0; i < path.size() - 1; i++) {
				residualGraph.setEdge(path.get(i), path.get(i + 1), residualGraph.getEdge(path.get(i), path.get(i + 1)).weight - pathFlow);
				residualGraph.setEdge(path.get(i + 1), path.get(i), residualGraph.getEdge(path.get(i + 1), path.get(i)).weight + pathFlow);
				tempGraph.setEdge(path.get(i), path.get(i + 1), tempGraph.getEdge(path.get(i), path.get(i + 1)).weight - pathFlow);
				tempGraph.setEdge(path.get(i + 1), path.get(i), tempGraph.getEdge(path.get(i + 1), path.get(i)).weight + pathFlow);
			}
		}
		
		return maxFlow;
	}
	

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }
}

