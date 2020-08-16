

public class DirectedGraph extends Graph {
	
	public DirectedGraph() {
		super();
	}
	
	@Override
	public boolean addEdge(String source, String destination) {
		addNode(source); // only adds if node not already in graph.
		addNode(destination);
		boolean addEdgeSuccess = addEdgeFromTo(source, destination);
		if (addEdgeSuccess) {
			numEdges++;
		}
		return addEdgeSuccess;	
	}

	@Override
	public boolean removeEdge(String source, String destination) {
		if (!containsNode(source) || !containsNode(destination)) {
			return false;
		}
		boolean removeEdgeSuccess = removeEdgeFromTo(source, destination);
		if (removeEdgeSuccess) {
			numEdges--;
		}
		return removeEdgeSuccess;	
	}
	

	
}
