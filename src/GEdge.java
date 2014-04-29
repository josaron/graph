public class GEdge
{
    private GNode origin;
    private GNode destination;
    private double weight;
    
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public GNode getOrigin() {
		return origin;
	}

	public GNode getDestination() {
		return destination;
	}
    
    
}
