import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ALGraph
{
    private ArrayList<GNode> nodes;
    private Stack<GEdge> stack;
    
    /**
     * Constructor
     */
    public ALGraph() {
    	nodes = new ArrayList<GNode>();
    }
    
    /**
     * Create a new GNode
     * @param data
     * @return The new GNode
     */
    public GNode createNode(Object data) {
    	GNode node = new GNode(data);
    	return node;
    }
    
    /**
     * Add an already instantiated GNode to the graph.
     * @param data
     */
    public void addNode(GNode node) {
    	nodes.add(node);
    }
    
    /**
     * Return a set of all the nodes in the graph.
     * @param the set of nodes
     */
    public ArrayList<GNode> getNodes() {
    	return nodes;
    }
    
    /**
     * Perform a Breadth First Search
     * @param node the node from which to begin the BFS
     */
    public void BFS(GNode origin) {
    	for (GNode node : nodes) {
    		node.setColor(Color.white);
    		node.setDistance(-1);
    		node.setPrev(null);
    	}
    	origin.setColor(Color.gray);
    	origin.setDistance(0);
    	origin.setPrev(null);
    	
    	Queue<GNode> myQ=new LinkedList<GNode>(); // Use a LinkedList as a Queue
    	myQ.add(origin);
    	
    	while (!myQ.isEmpty()) {
    		GNode currentNode = myQ.poll();
    		ArrayList<GEdge> edges = currentNode.getEdges();
    		for (GEdge edge : edges) {
    			GNode neighbor = edge.getDestination();
    			if (neighbor.getColor() == Color.white) {
    				neighbor.setColor(Color.gray);
    				neighbor.setDistance(currentNode.getDistance() + 1);
    				neighbor.setPrev(currentNode);
    				myQ.add(neighbor);
    			}
    		}
    		currentNode.setColor(Color.black);
    	}
    }
    
    /**
     * Perform a Depth First Search
     * @param the origin
     */
    public void DFS() {
    	for (GNode node : nodes) {
    		node.setColor(Color.white);
    		node.setPrev(null);
    	}
    	int time = 0;
    	for (GNode node : nodes) {
    		if (node.getColor() == Color.white) {
    			time = DFSVisit(node, time);
    		}
    	}
    }
    
    /**
     * Helper method for DFS
     * @param the node from which to run the DFS
     */
    private int DFSVisit(GNode node, int t) {
    	int time = t + 1;
    	node.setDiscoveryTime(time);
    	node.setColor(Color.gray);
    	ArrayList<GEdge> edges = node.getEdges();
    	for (GEdge edge : edges) {
    		GNode neighbor = edge.getDestination();
    		if (neighbor.getColor() == Color.gray) {
    			edge.setEdgeType(GEdgeType.BACK);
    		}
    		else if (neighbor.getColor() == Color.black){
    			if (inSameTree(node, neighbor)) {
    				edge.setEdgeType(GEdgeType.FORWARD);
    			}
    			else {
    				edge.setEdgeType(GEdgeType.CROSS);
    			}
    		}
    		else if (neighbor.getColor() == Color.white) {
    			edge.setEdgeType(GEdgeType.TREE);
    			neighbor.setPrev(node);
    			time = DFSVisit(neighbor, time);
    		}     		
    	}
    	node.setColor(Color.black);
    	time++;
    	node.setFinishTime(time);
    	return time;
    }
    
    /**
     * Helper method for Depth First Search.
     * Check if two nodes are in the same tree.
     * This is used to find whether and edge leading to a black colored 
     * destination is a forward or cross edge.
     * @param x One GNode
     * @param y The other GNode
     * @return Whether in the same tree
     */
    private boolean inSameTree(GNode x, GNode y) {
    	ArrayList<GNode> xTree = new ArrayList<GNode>();
    	GNode xParent = x;
    	GNode yParent = y;
    	while (xParent != null) {
    		xTree.add(xParent);
    		xParent = xParent.getPrev();
    	}
    	while (yParent != null) {
    		if (xTree.contains(yParent)) {
    			return true;
    		}
    		yParent = yParent.getPrev();
    	}
    	return false;
    }
    
    /**
     * Perform Dijkstra's shortest path algorithm
     * @param origin
     */
    public void dijkstra(GNode n) {
    	initializeSingleSource(n);
    	ArrayList<GNode> finishedNodes = new ArrayList<GNode>();
    	Comparator<GNode> distanceComparator = new NodeDistanceComparator();
    	PriorityQueue<GNode> minDistanceQ = new PriorityQueue<GNode>(nodes.size(), distanceComparator);
    	for (GNode node : nodes) {
    		minDistanceQ.add(node);
    	}
    	
    	GNode node;
    	boolean atOrigin = true;
    	while (minDistanceQ.size() > 0) {
    		if (atOrigin) {
    			node = n;
    			minDistanceQ.remove(node);
    			atOrigin = false;
    		}
    		else {
    			node = minDistanceQ.poll();
    		}
    		finishedNodes.add(node);
    		ArrayList<GEdge> edges = node.getEdges();
    		for (GEdge edge : edges) {
    			// Relax
    			double edgeWeight = edge.getWeight();
    			GNode neighbor = edge.getDestination();
    			if (node.getDistance() + edgeWeight < neighbor.getDistance()) {
    	    		neighbor.setDistance(node.getDistance() + edgeWeight);
    	    		neighbor.setPrev(node);
    	    		// Resort the queue to account for the changed distances
    	    		minDistanceQ.remove(neighbor);
    	    		minDistanceQ.add(neighbor);
    	    	}
    		}
    	}
    }
    
    /**
     * Helper method for Dijkstra's algorithm.
     * Initialize the graph.
     * @param origin 
     */
    private void initializeSingleSource(GNode origin) {
    	for (GNode node : nodes) {
    		node.setDistance(Double.MAX_VALUE);
    		node.setPrev(null);
    	}
    	origin.setDistance(0);
    }
    
    /**
     * Find the biconnected components in the graph.
     */
    public void biconnectedComponents() {
    	int count = 0;
    	stack = new Stack<GEdge>();
    	for (GNode node : nodes) {
    		node.setVisited(false);
    		node.setPrev(null);
    	}
    	for (GNode node : nodes) {
    		if (!node.isVisited()) {
    			biconnectedDFSVisit(node, count);
    		}
    	}
    }
    
    /**
     * Helper method for biconnectedComponents().
     * @param the node from which to perform the DFS Visit.
     * @param the count of the discovery time.
     * @return the new discovery time
     */
    private int biconnectedDFSVisit(GNode node, int c) {
    	node.setVisited(true);
    	int count = c++;
    	node.setDiscoveryTime(count);
    	node.setLow(node.getDiscoveryTime());
    	ArrayList<GEdge> edges = node.getEdges();
    	for (GEdge edge : edges) {
    		GNode neighbor = edge.getDestination();
    		if (!neighbor.isVisited()) {
    			stack.push(edge);
    			neighbor.setPrev(node);
    			count = biconnectedDFSVisit(neighbor, count);
    			if (neighbor.getLow() >= node.getDiscoveryTime()) {
    				printComponent(edge);
    			}
    			node.setLow(Math.min(node.getLow(), neighbor.getLow()));
    		}
    		else if (node.getPrev() != neighbor && 
    				neighbor.getDiscoveryTime() < node.getDiscoveryTime()) {
    			// 'edge' is a back edge from 'node' to its ancestor 'neighbor'
    			stack.push(edge);
    			node.setLow(Math.min(node.getLow(), neighbor.getDiscoveryTime()));
    		}
    	}
    	return count;
    }
    
    /**
     * Helper method for biconnectedComponents().
     * Print the edges in a biconnected component.
     * @param edge
     */
    private void printComponent(GEdge edge) {
    	System.out.println("New Biconnected Component Found:");
    	GEdge popped = stack.pop();
    	while (popped != edge) {
    		System.out.println(popped.toString());
    		popped = stack.pop();
    	}
    	System.out.println(popped.toString());
    }
    
    /**
     * Find the articulation points in the graph.
     * @param node
     */
    public void findArtPoints(GNode node) {
    	int count = 0;
    	assignDiscovery(node, count);
    	assignLow(node);
    }
    
    /**
     * Helper method for findArtPoints().
     * Assign the discovery time for each node.
     * @param node
     * @param count
     */
    private void assignDiscovery(GNode node, int count) {
    	count++;
    	node.setDiscoveryTime(count);
    	node.setVisited(true);
    	ArrayList<GEdge> edges = node.getEdges();
    	for (GEdge edge : edges) {
    		GNode neighbor = edge.getDestination();
    		if (!neighbor.isVisited()) {
    			neighbor.setPrev(node);
    			assignDiscovery(neighbor, count);
    		}
    	}
    }
    
    /**
     * Helper method for findArtPoints().
     * Assign the low for each node and print articulation points.
     * @param node
     */
    private void assignLow(GNode node) {
    	node.setLow(node.getDiscoveryTime());
    	ArrayList<GEdge> edges =  node.getEdges();
    	for (GEdge edge : edges) {
    		GNode neighbor = edge.getDestination();
    		if (neighbor.getDiscoveryTime() > node.getDiscoveryTime()) {
    			// Forward edge
    			assignLow(neighbor);
    			if (neighbor.getLow() >= node.getDiscoveryTime()) {
    				System.out.println(node.getData() + " is an articulation point.");
    			}
    			node.setLow(Math.min(node.getLow(), neighbor.getLow()));
    		}
    		else if (node.getPrev() != neighbor) {
    			// Back edge
    			node.setLow(Math.min(node.getLow(), neighbor.getDiscoveryTime()));
    		}
    	}
    }
    
    
    
}