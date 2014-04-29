import java.awt.Color;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ALGraph
{
    private HashSet<GNode> nodes;
    
    /**
     * Constructor
     */
    public ALGraph() {
    	nodes = new HashSet<GNode>();
    }
    
    /**
     * Add a new Node to the graph
     * @param data
     */
    public void addNode(Object data) {
    	GNode node = new GNode(data);
    	nodes.add(node);
    }
    
    /**
     * Return a set of all the nodes in the graph.
     * I only use this method for testing.
     * @param the set of nodes
     */
    public HashSet<GNode> getNodes() {
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
    		HashSet<GEdge> edges = currentNode.getEdges();
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
    			DFSVisit(node, time);
    		}
    	}
    }
    
    /**
     * Helper method for DFS
     * @param the node from which to run the DFS
     */
    private void DFSVisit(GNode node, int t) {
    	int time = t + 1;
    	node.setDiscoveryTime(time);
    	node.setColor(Color.gray);
    	HashSet<GEdge> edges = node.getEdges();
    	for (GEdge edge : edges) {
    		GNode neighbor = edge.getDestination();
    		if (neighbor.getColor() == Color.white) {
    			neighbor.setPrev(node);
    			DFSVisit(neighbor, time);
    		}
    	}
    	node.setColor(Color.black);
    	time++;
    	node.setFinishTime(time);
    }
    
    /**
     * Perform Dijkstra's shortest path algorithm
     * @param origin
     */
    public void dijkstra(GNode n) {
    	initializeSingleSource(n);
    	HashSet<GNode> finishedNodes = new HashSet<GNode>();
    	Comparator<GNode> distanceComparator = new NodeDistanceComparator();
    	PriorityQueue<GNode> minDistanceQ = new PriorityQueue<GNode>(nodes.size(), distanceComparator);
    	for (GNode node : nodes) {
    		minDistanceQ.add(node);
    	}
    	
    	while (minDistanceQ.size() > 0) {
    		GNode node = minDistanceQ.poll();
    		finishedNodes.add(node);
    		HashSet<GEdge> edges = node.getEdges();
    		for (GEdge edge : edges) {
    			// Relax
    			double edgeWeight = edge.getWeight();
    			GNode neighbor = edge.getDestination();
    			if (neighbor.getDistance() < 0 || // Is the delimiter value
    					node.getDistance() + edgeWeight < neighbor.getDistance()) {
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
    public void initializeSingleSource(GNode origin) {
    	for (GNode node : nodes) {
    		node.setDistance(-1);
    		node.setPrev(null);
    	}
    	origin.setDistance(0);
    }

}