import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class GraphTest {

    public static void main(String[] args) {
    	GraphTest tester = new GraphTest();
    	tester.minQTest();
    	tester.dijkstraTest();
    }
    
    private void dijkstraTest() {
    	System.out.println("Dijkstra's Algorithm Test:");
    	ALGraph graph = new ALGraph();
    	// Create the nodes
    	GNode s = new GNode("s");
    	GNode t = new GNode("t");
    	GNode x = new GNode("x");
    	GNode y = new GNode("y");
    	GNode z = new GNode("z");
    	// Create the edges
    	s.addEdge(t, 10);
    	s.addEdge(y, 5);
    	t.addEdge(x, 1);
    	t.addEdge(y, 2);
    	x.addEdge(z, 4);
    	y.addEdge(t, 3);
    	y.addEdge(x, 9);
    	y.addEdge(z, 2);
    	z.addEdge(x, 6);
    	z.addEdge(s, 7);
    	// Add nodes to the graph
    	graph.addNode(s);
    	graph.addNode(t);
    	graph.addNode(x);
    	graph.addNode(y);
    	graph.addNode(z);
    	
    	s.setDistance(10);
    	graph.initializeSingleSource(s);
    	s.setDistance(-1);
    	
    	//graph.dijkstra(s);
    	
    	/*HashSet<GNode> nodes = graph.getNodes();
    	for (GNode node : nodes) {
    		String nodeName = node.getData().toString();
    		System.out.println("Node " + nodeName + ": " + node.getDistance());
    	}*/
    	System.out.println("Node s: " + s.getDistance());
    	System.out.println("Node t: " + t.getDistance());
    	System.out.println("Node x: " + x.getDistance());
    	System.out.println("Node y: " + y.getDistance());
    	System.out.println("Node z: " + z.getDistance());
    	
    	System.out.println();
    }
    
    private void minQTest() {
    	System.out.println("Min Queue Test:");
    	// create nodes and distances
    	GNode n5 = new GNode("Hello");
    	n5.setDistance(5);
    	GNode n2 = new GNode("Hell");
    	n2.setDistance(2);
    	GNode n4 = new GNode("Helo");
    	n4.setDistance(4);
    	GNode n7 = new GNode("Hllo");
    	n7.setDistance(7);
    	GNode n3 = new GNode("ello");
    	n3.setDistance(3);
    	
    	// add nodes to set
    	HashSet<GNode> set = new HashSet<GNode>();
    	set.add(n5);
    	set.add(n2);
    	set.add(n4);
    	set.add(n7);
    	set.add(n3);
    	
    	Comparator<GNode> comparator = new NodeDistanceComparator();
    	PriorityQueue<GNode> minQ = new PriorityQueue<GNode>(set.size(), comparator);
    	for (GNode node : set) {
    		minQ.add(node);
    	}
    	
    	//reorder to account for changed element
    	n2.setDistance(10);
    	minQ.remove(n2);
    	minQ.add(n2);
    	
    	while(minQ.size() > 0) {
    		System.out.println(minQ.poll().getDistance() + "");
    	}
    	
    	System.out.println();
    }
}
