

public class Edge {
	private final String source;
	private final String destination;
	
	public Edge(String source, String destination) {
		this.source = source;
		this.destination = destination;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge otherEdge = (Edge) obj;
		String otherSource = otherEdge.getSource();
		String otherDest = otherEdge.getDestination();

		return (otherSource.equals(source)
			 && otherDest.equals(destination));
	}
	
	@Override
	public int hashCode() {
		return source.hashCode() + destination.hashCode();
	}
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	

}
