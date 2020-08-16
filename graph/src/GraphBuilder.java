

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphBuilder {
		
	public static DirectedGraph buildDirectedGraph(String filename) {
		DirectedGraph dg = new DirectedGraph();
		buildGraph(dg, filename);
		return dg;
	}
	
	public static UndirectedGraph buildUndirectedGraph(String filename) {
		UndirectedGraph ug = new UndirectedGraph();
		buildGraph(ug, filename);
		return ug;
	}
	
	protected static void buildGraph(Graph graph, String filename) {
		try (BufferedReader br = new BufferedReader(
				new FileReader(filename))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	// process the line.
		    	String[] edge = line.split(" ");
		    	if (edge.length < 2) 
		    		continue;
		    	String source = edge[0];
		    	String destination = edge[1];

		    	graph.addEdge(source, destination);
		    	//System.out.println("added edge from " + sourceNode.getElement() + " to " + destinationNode.getElement());
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
