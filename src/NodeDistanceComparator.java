import java.util.Comparator;

/**
 * Comparator for finding the shorter distance between two nodes.
 * @author josaron
 */
public class NodeDistanceComparator implements Comparator<GNode> {
	@Override
	public int compare(GNode x, GNode y) {
		if (x.getDistance() < y.getDistance()) {
			return -1;
		}
		if (x.getDistance() > y.getDistance()) {
			return 1;
		}
		return 0;
	}
}
