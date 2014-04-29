import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;

public class GNode
{
    private Object data;
    private ArrayList<GEdge> edges;
    private double distance;  // For path-finding algorithms
    private GNode prev;   // For path-finding algorithms
    private Color color;  // For DFS and BFS
    private double discoveryTime; // For DFS
    private double finishTime; // For DFS
    
    /**
     * Constructor
     * @param data
     */
    public GNode(Object data) {
    	this.data = data;
    	edges = new ArrayList<GEdge>();
    }
    
    /**
     * Return the HashSet of this node's edges
     * @return the edges
     */
    public ArrayList<GEdge> getEdges() {
    	return edges;
    }
    
    public void addEdge(GNode dest, double weight) {
    	GEdge newEdge = new GEdge(this, dest, weight);
    	edges.add(newEdge);
    }
    
    public void setData(Object data) {
    	this.data = data;
    }
    
    public Object getData() {
    	return data;
    }
    
    /**
     * Set the Node's color
     * @param color
     */
    public void setColor(Color col) {
    	color = col;
    }
    
    /**
     * Get the Node's color
     * @return color
     */
    public Color getColor() {
    	return color;
    }
    
    /**
     * Set the Node's distance
     * @param distance
     */
    public void setDistance(double dist) {
    	distance = dist;
    }
    
    /**
     * Get the Node's distance
     * @return the distance
     */
    public double getDistance() {
    	return distance;
    }
    
    /**
     * Set the predecessor
     * @param predecessor
     */
    public void setPrev(GNode pre) {
    	prev = pre;
    }
    
    /**
     * Get the predecessor
     * @return the predecessor
     */
    public GNode getPrev() {
    	return prev;
    }
    
    /**
     * Set the discovery time
     * @param discovery time
     */
    public void setDiscoveryTime(int time) {
    	discoveryTime = time;
    }
    
    /**
     * Get the discovery time
     * @return the discovery time
     */
    public double getDiscoveryTime() {
    	return discoveryTime;
    }
    
    /**
     * Set the finish time
     * @param finish time
     */
    public void setFinishTime(int time) {
    	finishTime = time;
    }
    
    /**
     * Get the finish time
     * @return the finish time
     */
    public double getFinishTime() {
    	return finishTime;
    }
}