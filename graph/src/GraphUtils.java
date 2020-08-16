

import java.util.*;

public class GraphUtils {
	
	/*
	 * Implement the following methods.
	 */


	public static int minDistance(Graph graph, String src, String dest) {
		if(graph == null || src == null || dest == null) {
			return -1;
		}
		if(!graph.containsNode(src) || !graph.containsNode(dest)){
			return -1;
		}
		graph.paths.clear();
		graph.dfps(src, dest);
		Set<LinkedList<String>> paths = graph.paths;
		if(paths.isEmpty()) {
			return -1;
		} else {
			int shortest = -1;
			for(LinkedList<String> p : paths) {
				if(shortest == -1) {
					shortest = p.size();
				} else {
					shortest = Math.min(p.size(), shortest);
				}
			}
			return shortest - 1;
		}
	}

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		if(graph == null || src == null ) {
			return null;
		}
		if(!graph.containsNode(src)){
			return null;
		}
		if(distance < 1){
			return null;
		}
		graph.elements.clear();
		graph.level = distance;
		graph.dis(src);
		Set<String> elements = graph.elements;
		elements.remove(src);
		return elements;
	}

	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if(g == null || values == null){
			return false;
		}
		if(values.get(0) != values.get(values.size() - 1)) { return false;}
		Map<String, Set<Edge>> ad = g.adjacencySets;
		Set<String> elements = ad.keySet();
		for(int i = 0; i<values.size() - 1; i++) {
			String s1 = values.get(i);
			String s2 = values.get(i + 1);
			if(elements.contains(s1)) {
				if(!g.getNodeNeighbors(s1).contains(s2)) {
					return false;
				}
				elements.remove(s1);
			} else {
				return false;
			}
		}
		if(!elements.isEmpty()) {return false;}
		return true;
	}
	
}
