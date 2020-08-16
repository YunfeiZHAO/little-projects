

import java.util.*;

public abstract class Graph {
	protected Map<String, Set<Edge>> adjacencySets;
	protected int numNodes;
	protected int numEdges;
	protected Set<String> marked = new HashSet<>();

	protected Set<LinkedList<String>> paths = new HashSet<LinkedList<String>>();
	protected LinkedList<String> path = new LinkedList<String>();
	protected Set<String> elements = new HashSet<String>();
	protected int level;

	public abstract boolean addEdge(String node1, String node2);
	public abstract boolean removeEdge(String node1, String node2);

	public Graph() {
		adjacencySets = new HashMap<String, Set<Edge>>();
		numNodes = 0;
		numEdges = 0;
	}
	
	
	public boolean addNode(String newNode) {
		if (newNode == null || containsNode(newNode)) {
			return false;
		}
		Set<Edge> newAdjacencySet = new HashSet<Edge>();
		adjacencySets.put(newNode, newAdjacencySet);
		numNodes++;
		return true;
	}
	
	public Set<String> getNodeNeighbors(String node) {
		if (!containsNode(node)) {
			return null;
		}
		Set<Edge> nodeEdges = adjacencySets.get(node);
		Set<String> nodeNeighbors= new HashSet<String>();
		for (Edge e : nodeEdges) {
			String neighbor = e.getDestination();
			nodeNeighbors.add(neighbor);
		}
		return nodeNeighbors;
	}
	
		
	protected boolean addEdgeFromTo(String source,
			String destination) {
		Edge newEdge = new Edge(source, destination);
		Set<Edge> sourceEdges = adjacencySets.get(source);
		if (!sourceEdges.contains(newEdge)) {
			sourceEdges.add(newEdge);
			return true;
		}
		return false;
	}
	
	protected boolean removeEdgeFromTo(String source,
			String destination) {
		Edge toRemove = new Edge(source, destination);
		Set<Edge> sourceEdges = adjacencySets.get(source);
		if (sourceEdges.contains(toRemove)) {
			sourceEdges.remove(toRemove);
			return true;
		}
		return false;
	}
	
	
	public int getNumNodes() {
		return numNodes;
	}
	
	public int getNumEdges() {
		return numEdges;
	}
	
	public Set<String> getAllNodes() {
		return adjacencySets.keySet();
	}
	
	public Set<Edge> getNodeEdges(String node) {
		if (!containsNode(node)) {
			return null;
		}
		return adjacencySets.get(node);
	}
	
	public boolean containsNode(String node) {
		return adjacencySets.containsKey(node);
	}
	
	/*
	 * Breadth-First Search implementation
	 */
	public boolean bfs(String start, String elementToFind) {
		marked.clear();
		if (!containsNode(start)) {
				return false;
		}
		if (start.equals(elementToFind)) {
			return true;
		}
		Queue<String> toExplore = new LinkedList<String>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			String current = toExplore.remove();
			for (String neighbor : getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.equals(elementToFind)) {
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}

		}
		return false;
	}
	
	/*
	 * Depth-First Search implementation using recursion
	 */
	public boolean dfs(String start, String elementToFind) {
		if (!containsNode(start)) {
			return false;
		}	
		marked.clear();
		return doDfs(start, elementToFind);
	}
	
	private boolean doDfs(String start, String elementToFind) {
		if (start.equals(elementToFind)) {
			return true;
		} else {
			marked.add(start);
			for (String neighbor : getNodeNeighbors(start)) {
				if (!marked.contains(neighbor) && 
				    dfs(neighbor, elementToFind))
	             return true;
			}
			return false;
		}
	}

	/*
	 * Depth-First Search implementation using recursion for all possible paths
	 */
	public void dfps(String start, String elementToFind) {
		if (!containsNode(start)) {
			return;
		}
		path.addLast(start);
		doDfps(start, elementToFind);
		path.removeLast();
	}

	private void doDfps(String start, String elementToFind) {
		if (start.equals(elementToFind)) {
			paths.add(new LinkedList<String>(path));
		} else {
			for (String neighbor : getNodeNeighbors(start)) {
				if (!path.contains(neighbor)){
					dfps(neighbor, elementToFind);
				}
			}
			return;
		}
	}
	/*
	 * Depth-First Search implementation using recursion for all elements in a certain distance
	 */
	public void dis(String start) {
		if (!containsNode(start)) {
			return;
		}
		level = level - 1;
		if(level >= 0) {
			for (String neighbor : getNodeNeighbors(start)) {
				if (!elements.contains(neighbor)) {
					elements.add(neighbor);
				}
				dis(neighbor);
				level = level + 1;
			}
		}
	}
}
