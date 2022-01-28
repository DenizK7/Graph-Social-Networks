import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private HashMap<String, Vertex> vertices;
	private HashMap<String, Edge> edges;

	public Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
	}

	public void addEdge(String source, String destination) {

		if (edges.get(source + "-" + destination) == null && edges.get(destination + "-" + source) == null) {
			Vertex source_v, destination_v;

			if (vertices.get(source) == null) {
				source_v = new Vertex(source);
				vertices.put(source, source_v);
			} else
				source_v = vertices.get(source);

			if (vertices.get(destination) == null) {
				destination_v = new Vertex(destination);
				vertices.put(destination, destination_v);
			} else
				destination_v = vertices.get(destination);

			Edge edge = new Edge(source_v, destination_v, 1);
			Edge edge2=new Edge(destination_v,source_v,1);
			source_v.addEdge(edge);
			destination_v.addEdge(edge2);
			edges.put(source + "-" + destination, edge);
			edges.put(destination+"-"+source, edge2);
		} 
	}
	public void find_All_Paths() {
		ArrayList<String> names=new ArrayList<>(vertices.keySet());
		for (int i = 0; i < names.size(); i++) 
			for (int j = i+1; j < names.size(); j++) 
			getShortestPath(names.get(i), names.get(j));	
	}
	public void Betweennness_Result_Calc() {
		Vertex result = null;		
		for (Vertex vertices : vertices.values()) {
			if(result == null || vertices.getBetweenness()> result.getBetweenness())
				result = vertices;
		}
		System.out.print("Name --> "+result.getName() + " Value --> "+ result.getBetweenness());
	}
	public void Closeness_Result_Calc() {
		Vertex result = null;
		for (Vertex vertices : vertices.values()) {
			if(vertices.getCloseness()>1)
			if((result == null || (1/vertices.getCloseness())> (1/result.getCloseness())))				
				result = vertices;
		}
		System.out.println("Name --> "+result.getName() + " Value --> "+ (1/result.getCloseness()));
	}
	public void getShortestPath(String begin, String end) {
		vertexResetter();
		boolean check = false;
		int pathLength = 0;
		Queue<Vertex> vertexQueue=new LinkedList<>();
		Vertex originVertex=vertices.get(begin);
		Vertex endVertex=vertices.get(end);
         originVertex.visit();
         vertexQueue.add(originVertex);
         while (!vertexQueue.isEmpty()&& !check) {
 			Vertex frontVertex=vertexQueue.poll();
 			ArrayList<Edge> neighbors=frontVertex.getEdges();
 			for (int i = 0; i < neighbors.size(); i++) {
 				
 					if (!neighbors.get(i).getDestination().isVisited()) {
 						Vertex nextNeighbor=neighbors.get(i).getDestination();
 						nextNeighbor.visit();
 						nextNeighbor.setParent(frontVertex);
 						vertexQueue.add(nextNeighbor);
 					}
 					if (neighbors.get(i).getDestination().getName().equals(end)) { 						
 						pathLength=pathLength_and_Betweenness_Calc(endVertex);
 						originVertex.arrangeCloseness(pathLength);
 						endVertex.arrangeCloseness(pathLength);
 						check = true;
 						break;
 					}
 				
 			}
 		}        
         
} // end getShortestPath
	public int pathLength_and_Betweenness_Calc(Vertex endVertex) {
		int pathLength=0;
		Vertex tempVertex=endVertex;
		while (tempVertex.getParent()!=null) {		
			tempVertex.increaseBetweenness();
			tempVertex=tempVertex.getParent();
			pathLength++;
		}
		return pathLength;
	}
	public void vertexResetter() {
		for (Vertex vertices : vertices.values()) {
			vertices.default_values();
		}
	}

	public Iterable<Vertex> vertices() {
		return vertices.values();
	}

	public Iterable<Edge> edges() {
		return edges.values();
	}

	public int size() {
		return vertices.size();
	}

}
