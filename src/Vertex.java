import java.util.ArrayList;

public class Vertex {
	private String name;
	private ArrayList<Edge> edges;
	private Vertex parent;
	private boolean isVisited;
	private int betweenness;
	private int closeness;
	public Vertex(String name) {//edgeleri arraylistte depoluyor
		this.name = name;
		edges = new ArrayList<Edge>();
		parent = null;
		isVisited = false;
		betweenness =0;
		closeness = 0;
	}

	public double getBetweenness() {
		return betweenness;
	}

	public void increaseBetweenness() {
		betweenness ++;
	}

	public double getCloseness() {
		return closeness;
	}

	public void arrangeCloseness(int count) {
		closeness += count;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void visit() {
		isVisited = true;
	}
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}
	public void default_values(){
		isVisited = false;
		parent = null;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

}
