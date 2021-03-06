import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GraphTest {

    public static void main(String[] args) {
    	GraphTest tester = new GraphTest();
    	//tester.minQTest();
    	//tester.dijkstraTest();
    	//tester.BFSTest();
    	//tester.DFSTest();
    	tester.artiPointsTest();
    	//tester.biconnectedTest();
    }
    
    private void biconnectedTest() {
    	System.out.println("Biconnected Components Test");
    	ALGraph graph = new ALGraph();
    	// Create the nodes
    	GNode a = graph.createNode("a");
    	GNode b = graph.createNode("b");
    	GNode c = graph.createNode("c");
    	GNode d = graph.createNode("d");
    	GNode e = graph.createNode("e");
    	GNode f = graph.createNode("f");
    	GNode g = graph.createNode("g");
    	GNode h = graph.createNode("h");
    	GNode i = graph.createNode("i");
    	GNode j = graph.createNode("j");
    	GNode k = graph.createNode("k");
    	GNode l = graph.createNode("l");
    	GNode m = graph.createNode("m");
    	GNode n = graph.createNode("n");
    	// Create the edges
    	a.addEdge(b, 1);
    	a.addEdge(c, 1);
    	b.addEdge(a, 1);
    	b.addEdge(d, 1);
    	c.addEdge(a, 1);
    	c.addEdge(d, 1);
    	d.addEdge(b, 1);
    	d.addEdge(c, 1);
    	d.addEdge(e, 1);
    	e.addEdge(d, 1);
    	e.addEdge(f, 1);
    	f.addEdge(j, 1);
    	f.addEdge(e, 1);
    	j.addEdge(i, 1);
    	j.addEdge(f, 1);
    	j.addEdge(g, 1);
    	j.addEdge(k, 1);
    	g.addEdge(j, 1);
    	k.addEdge(j, 1);
    	k.addEdge(h, 1);
    	k.addEdge(n, 1);
    	h.addEdge(k, 1);
    	n.addEdge(k, 1);
    	n.addEdge(m, 1);
    	m.addEdge(n, 1);
    	m.addEdge(i, 1);
    	m.addEdge(l, 1);
    	l.addEdge(i, 1);
    	l.addEdge(m, 1);
    	i.addEdge(l, 1);
    	i.addEdge(m, 1);
    	i.addEdge(j, 1);
    	// Add the nodes
    	graph.addNode(a);
    	graph.addNode(b);
    	graph.addNode(c);
    	graph.addNode(d);
    	graph.addNode(e);
    	graph.addNode(f);
    	graph.addNode(g);
    	graph.addNode(h);
    	graph.addNode(i);
    	graph.addNode(j);
    	graph.addNode(k);
    	graph.addNode(l);
    	graph.addNode(m);
    	graph.addNode(n);
    	
    	graph.biconnectedComponents();
    }
    
    private void artiPointsTest() {
    	System.out.println("Articulation Points Test:");
    	ALGraph graph = new ALGraph();
    	// Create the nodes
    	GNode a = graph.createNode("a");
    	GNode b = graph.createNode("b");
    	GNode c = graph.createNode("c");
    	GNode d = graph.createNode("d");
    	GNode e = graph.createNode("e");
    	GNode f = graph.createNode("f");
    	GNode g = graph.createNode("g");
    	// Create the edges
    	a.addEdge(b, 1);
    	a.addEdge(d, 1);
    	b.addEdge(a, 1);
    	b.addEdge(c, 1);
    	c.addEdge(b, 1);
    	c.addEdge(d, 1);
    	c.addEdge(g, 1);
    	g.addEdge(c, 1);
    	d.addEdge(a, 1);
    	d.addEdge(c, 1);
    	d.addEdge(e, 1);
    	d.addEdge(f, 1);
    	f.addEdge(d, 1);
    	f.addEdge(e, 1);
    	e.addEdge(f, 1);
    	e.addEdge(d, 1);
    	// Add nodes to the graph
    	graph.addNode(a);
    	graph.addNode(b);
    	graph.addNode(c);
    	graph.addNode(d);
    	graph.addNode(e);
    	graph.addNode(f);
    	graph.addNode(g);
    	
    	graph.findArtPoints(c);
    }
    
    private void DFSTest() {
    	System.out.println("Depth First Search Test");
    	ALGraph graph = new ALGraph();
    	// Create the nodes
    	GNode u = graph.createNode("u");
    	GNode v = graph.createNode("v");
    	GNode w = graph.createNode("w");
    	GNode x = graph.createNode("x");
    	GNode y = graph.createNode("y");
    	GNode z = graph.createNode("z");
    	// Create the edges
    	u.addEdge(x, 1);
    	u.addEdge(v, 1);
    	v.addEdge(y, 1);
    	w.addEdge(y, 1);
    	w.addEdge(z, 1);
    	x.addEdge(v, 1);
    	y.addEdge(x, 1);
    	z.addEdge(z, 1);
    	// Add nodes to the graph
    	graph.addNode(u);
    	graph.addNode(v);
    	graph.addNode(w);
    	graph.addNode(x);
    	graph.addNode(y);
    	graph.addNode(z);
    	
    	graph.DFS();
    	
    	System.out.println("Expected:");
    	System.out.println("Node u: Discovery- 1.0, Finish- 8.0"
    			+ "\nNode v: Discovery- 2.0, Finish- 7.0"
    			+ "\nNode w: Discovery- 9.0, Finish- 12.0"
    			+ "\nNode x: Discovery- 4.0, Finish- 5.0"
    			+ "\nNode y: Discovery- 3.0, Finish- 6.0"
    			+ "\nNode z: Discovery- 10.0, Finish- 11.0");
    	
    	System.out.println("Actual:");
    	for (GNode node : graph.getNodes()) {
    		String nodeName = node.getData() + "";
    		String discovery = node.getDiscoveryTime() + "";
    		String finish = node.getFinishTime() + "";
    		System.out.println("Node " + nodeName + ": "
    				+ "Discovery- " + discovery + ", Finish- " + finish); 
    	}
    	System.out.println();
    	System.out.println("Edge Types:");
    	for (GNode node : graph.getNodes()) {
    		for (GEdge edge : node.getEdges()) {
    			String origin = edge.getOrigin().getData().toString();
    			String dest = edge.getDestination().getData().toString();
    			GEdgeType type = edge.getEdgeType();
    			System.out.println(origin + " -> " + dest + ": " + type);
    		}
    	}
    	System.out.println();
    }

    private void BFSTest() {
    	System.out.println("Breadth First Search Test");
    	ALGraph graph = new ALGraph();
    	// Create the nodes
    	GNode r = graph.createNode("r");
    	GNode s = graph.createNode("s");
    	GNode t = graph.createNode("t");
    	GNode u = graph.createNode("u");
    	GNode v = graph.createNode("v");
    	GNode w = graph.createNode("w");
    	GNode x = graph.createNode("x");
    	GNode y = graph.createNode("y");
    	// Create the edges
    	r.addEdge(s, 1);
    	r.addEdge(v, 1);
    	v.addEdge(r, 1);
    	s.addEdge(r, 1);
    	s.addEdge(w, 1);
    	w.addEdge(s, 1);
    	w.addEdge(t, 1);
    	w.addEdge(x, 1);
    	t.addEdge(w, 1);
    	t.addEdge(x, 1);
    	t.addEdge(u, 1);
    	x.addEdge(w, 1);
    	x.addEdge(t, 1);
    	x.addEdge(u, 1);
    	x.addEdge(y, 1);
    	u.addEdge(t, 1);
    	u.addEdge(x, 1);
    	u.addEdge(y, 1);
    	y.addEdge(u, 1);
    	y.addEdge(x, 1);
    	// Add nodes to the graph
    	graph.addNode(r);
    	graph.addNode(s);
    	graph.addNode(t);
    	graph.addNode(u);
    	graph.addNode(v);
    	graph.addNode(w);
    	graph.addNode(x);
    	graph.addNode(y);
    	
    	graph.BFS(s);
    	
    	System.out.println("Expected:");
    	System.out.println("Distance of node r: 1.0 "
    			+ "\nDistance of node s: 0.0 "
    			+ "\nDistance of node t: 2.0 "
    			+ "\nDistance of node u: 3.0"
    			+ "\nDistance of node v: 2.0"
    			+ "\nDistance of node w: 1.0"
    			+ "\nDistance of node x: 2.0"
    			+ "\nDistance of node y: 3.0");
    	
    	System.out.println("Actual:");
    	for (GNode node : graph.getNodes()) {
    		String nodeName = node.getData().toString();
    		String distance = node.getDistance() + "";
    		System.out.println("Distance of node " + nodeName + ": " + distance);
    	}
    	System.out.println();
    }
    
    private void dijkstraTest() {
    	System.out.println("Dijkstra's Algorithm Test");
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
    	
    	System.out.println("Expected:");
    	System.out.println("Node s: 0.0"
    			+ "\nNode t: 8.0 "
    			+ "\nNode x: 9.0 "
    			+ "\nNode y: 5.0 "
    			+ "\nNode z: 7.0");
    	System.out.println();
    	
    	System.out.println("Actual:");
    	ArrayList<GNode> nodes = graph.getNodes();
    	for (GNode node : nodes) {
    		String nodeName = node.getData().toString();
    		System.out.println("Node " + nodeName + ": " + node.getDistance());
    	}
    	System.out.println();
    }
    
    private void minQTest() {
    	System.out.println("Min Queue Test");
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
