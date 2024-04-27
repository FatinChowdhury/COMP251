import java.util.*; 

public class A3Q2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int num_of_vertices = sc.nextInt();
		Graph g = new Graph(num_of_vertices);
		LinkedList<Integer> buffer;
		for (int i=0; i<num_of_vertices; i++) {
			int id = sc.nextInt();
			g.addNode(id);
			int neighbors = sc.nextInt();
			int neighbor = 0;
			buffer = new LinkedList<Integer>();
			for (int j=0; j<neighbors; j++) {
				neighbor = sc.nextInt();
				buffer.add(neighbor);
			}
			g.addEdges(id, buffer);
		}
		sc.close();
		F1(g);
		System.out.println(Arrays.toString(g.issue1.toArray()));
		System.out.println(Arrays.toString(g.issue2.toArray()));
	}

	public static void F1(Graph g) {
        // Assuming 0 is the starting node (Notre Dame Island)
        int startNode = 0;
        for (Integer node : g.order) {
            if (node == startNode) continue; // Skip the starting node as it's always reachable

            boolean[] visited = new boolean[g.num_nodes];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startNode);
            visited[startNode] = true;

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                if (currentNode == node) {
                    break; // Found a path to the node, no issue
                }

                LinkedList<Integer> neighbors = g.adj.get(currentNode);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (!visited[neighbor]) {
                            queue.add(neighbor);
                            visited[neighbor] = true;
                        }
                    }
                }
            }

            if (!visited[node]) {
                g.issue2.add(node); // Node cannot be reached from Notre Dame Island
            }
        }

        // Check if Notre Dame Island can reach all nodes
        for (Integer node : g.order) {
            if (node == startNode) continue; // Skip the starting node as it's always reachable

            boolean[] visited = new boolean[g.num_nodes];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(node);
            visited[node] = true;

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                if (currentNode == startNode) {
                    break; // Found a path to the starting node, no issue
                }

                LinkedList<Integer> neighbors = g.adj.get(currentNode);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (!visited[neighbor]) {
                            queue.add(neighbor);
                            visited[neighbor] = true;
                        }
                    }
                }
            }

            if (!visited[startNode]) {
                g.issue1.add(startNode); // Notre Dame Island cannot be reached from the node
            }
        }
    }
}

class Graph{
	public int num_nodes;
	public Hashtable<Integer, LinkedList<Integer>> adj;
	public LinkedList<Integer> order;
	public LinkedList<Integer> issue1;
	public LinkedList<Integer> issue2;
	
	public Graph(int num_vertices) {
		this.num_nodes = num_vertices;
		adj = new Hashtable<Integer, LinkedList<Integer>>();
		order = new LinkedList<Integer>();
		issue1 = new LinkedList<Integer>();
		issue2 = new LinkedList<Integer>();
	}
	public void addEdges(int u, LinkedList<Integer> vs) {
		this.adj.put(u, vs);
	}
	public void addNode(int u) {
		order.add(u);
		adj.put(u, new LinkedList<Integer>());
	}
	public LinkedList<Integer> get_Order(){
		return this.order;
	}	
}



