import org.junit.Test;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

public class Test1 {
    @Test
    public void graphe(){
        Graph g = new DirectedGraph();
        g.addEdge("1", "2");
        g.addEdge("1", "5");
        g.addEdge("2", "4");
        g.addEdge("2", "5");
        g.addEdge("3", "4");
        g.addEdge("4", "5");
        g.addEdge("4", "6");
        System.out.println(g.dfs("1", "5"));
    }
    @Test
    public void graphe1(){
        Graph g = new DirectedGraph();
        g.addEdge("1", "2");
        g.addEdge("1", "3");
        g.addEdge("2", "1");
        g.addEdge("2", "3");
        g.addEdge("3", "1");
        g.addEdge("3", "2");
        System.out.println(g.dfs("1", "5"));
    }
    @Test
    public void l(){
       Set<LinkedList<String>> paths = new HashSet<LinkedList<String>>();
       LinkedList<String> path = new LinkedList<String>();
       path.add("a");
       LinkedList path1 = new LinkedList<String>(path);
       paths.add(path1);
       System.out.println(paths);
       path.add("b");
       System.out.println(paths);
    }
    @Test
    public void graphe2(){
        Graph g = new DirectedGraph();
        g.addEdge("0", "1");
        g.addEdge("0", "2");
        g.addEdge("0", "3");
        g.addEdge("0", "5");
        g.addEdge("1", "2");
        g.addEdge("1", "5");
        g.addEdge("2", "4");
        g.addEdge("2", "5");
        g.addEdge("3", "4");
        g.addEdge("4", "5");
        g.addEdge("4", "6");
        g.dfps("1", "5");
        System.out.println(g.paths);
    }

    @Test
    public void graphe3(){
        Graph g = new DirectedGraph();
        g.addEdge("0", "1");
        g.addEdge("0", "2");
        g.addEdge("0", "3");
        g.addEdge("0", "5");
        g.addEdge("1", "2");
        g.addEdge("1", "5");
        g.addEdge("2", "4");
        g.addEdge("2", "5");
        g.addEdge("3", "4");
        g.addEdge("4", "5");
        g.addEdge("4", "6");
        GraphUtils gu = new GraphUtils();
        g.dfps("1", "5");
        System.out.println(g.paths);
        System.out.println(gu.minDistance(g, "1","5"));
    }

    @Test
    public void dis(){
        Graph g = new DirectedGraph();
        g.addEdge("0", "1");
        g.addEdge("0", "2");
        g.addEdge("0", "3");
        g.addEdge("0", "5");
        g.addEdge("1", "2");
        g.addEdge("1", "5");
        g.addEdge("2", "4");
        g.addEdge("2", "5");
        g.addEdge("3", "4");
        g.addEdge("4", "5");
        g.addEdge("4", "6");
        GraphUtils gu = new GraphUtils();
        System.out.println(gu.nodesWithinDistance(g, "0", 3));
        gu.isHamiltonianPath(g, new LinkedList<String>());
    }

    @Test
    public void isHamiltonianPath(){
        Graph g = new DirectedGraph();
        g.addEdge("0", "1");
        g.addEdge("1", "2");
        g.addEdge("2", "3");
        g.addEdge("3", "4");
        g.addEdge("4", "5");
        g.addEdge("5", "6");
        g.addEdge("6", "0");

        GraphUtils gu = new GraphUtils();
        List<String> l = new LinkedList<String>();
        l.add("0");
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("5");
        l.add("6");
        l.add("0");

        System.out.println(gu.isHamiltonianPath(g, l));
    }


}



