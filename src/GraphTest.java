import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GraphTest {

    public static void main(String[] args) {
    	GraphTest tester = new GraphTest();
    	//tester.minQTest();
    	tester.dijkstraTest();
    }
    
    private void checkInitializeSingleSource() {
    	
    }
    
    private void dijkstraTest() {
    	System.out.println("Dijkstra's Algorithm Test:");
    	ALGraph graph = new ALGraph();
    	// Create the nodes
    	GNode s = graph.createNode("s");
    	GNode t = graph.createNode("t");
    	GNode x = graph.createNode("x");
    	GNode y = graph.createNode("y");
    	GNode z = graph.createNode("z");
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
    	
    	graph.dijkstra(s);
    	
    	ArrayList<GNode> nodes = graph.getNodes();
    	for (GNode node : nodes) {
    		String nodeName = node.getData().toString();
    		System.out.println("Node " + nodeName + ": " + node.getDistance());
    	}
    	System.out.println();
    }
    
    private void minQTest() {
    	System.out.println("Min Queue Test:");
    	ALGraph graph = new ALGraph();
    	// create nodes and distances
    	GNode n5 = graph.createNode("Hello");
    	n5.setDistance(5);
    	GNode n2 = graph.createNode("Hell");
    	n2.setDistance(2);
    	GNode n4 = graph.createNode("Helo");
    	n4.setDistance(4);
    	GNode n7 = graph.createNode("Hllo");
    	n7.setDistance(7);
    	GNode n3 = graph.createNode("ello");
    	n3.setDistance(3);
    	
    	// add nodes to set
    	ArrayList<GNode> list = new ArrayList<GNode>();
    	list.add(n5);
    	list.add(n2);
    	list.add(n4);
    	list.add(n7);
    	list.add(n3);
    	
    	Comparator<GNode> comparator = new NodeDistanceComparator();
    	PriorityQueue<GNode> minQ = new PriorityQueue<GNode>(list.size(), comparator);
    	for (GNode node : list) {
    		minQ.add(node);
    	}
    	
    	//reorder to account for changed element
    	n2.setDistance(10);
    	minQ.remove(n2);
    	minQ.add(n2);
    	n7.setDistance(1);
    	minQ.remove(n7);
    	minQ.add(n7);
    	
    	while(minQ.size() > 0) {
    		System.out.println(minQ.poll().getDistance() + "");
    	}
    	System.out.println();
    }
}
