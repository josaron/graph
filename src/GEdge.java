public class GEdge
{
    private GNode origin;
    private GNode destination;
    private double weight;
    private GEdgeType edgeType;
    
    /**
     * Constructor
     * @param origin
     * @param dest
     * @param weight
     */
    public GEdge(GNode origin, GNode dest, double weight) {
    	this.origin = origin;
    	destination = dest;
    	this.weight = weight;
    }

    /**
     * Get the edge's weight
     * @return the weight
     */
	public double getWeight() {
		return weight;
	}

	/**
	 * Set the edge's weight
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Get the origin of edge
	 * @return origin
	 */
	public GNode getOrigin() {
		return origin;
	}

	/**
	 * Get the destination of the edge
	 * @return destination
	 */
	public GNode getDestination() {
		return destination;
	}
    
	/**
	 * Get the edge type
	 * @return the edge type
	 */
    public GEdgeType getEdgeType() {
    	return edgeType;
    }
    
    /**
     * Set the edge type
     * @param type
     */
    public void setEdgeType(GEdgeType type) {
    	edgeType = type;
    }
}
