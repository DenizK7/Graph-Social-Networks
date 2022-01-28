import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
/*The code doesn't run very slowly, but it does take a while.
 *  The reason for this was the effect of the functions I wrote,
 *   but the main reason was that I created the vertex and edge as 2 separate classes and called them as an object them in the graph class. 
 *   Because, instead of Vertex and Edge classes,
 *    when I opened hashmap and ArrayList directly in the graph class, there was a big change in its speed.
 *  However, since I think that a code written in this way is more correct and healthy, I decided to send it like this.
 */
	public static void main(String[] args) {
		Graph graph = new Graph();
		System.out.println("Deniz Küçükkara 2019510128 \n");
		String path_facebook="facebook_social_network.txt";
		String path_karate="karate_club_network.txt";
		String header = "->Karate  ";
		System.out.println(".txt name\tHigher Betweenness\t\tHigher Closeness\n");
		txt_To_Graph(path_karate, graph, header);
		System.out.println();
		graph = new Graph();
		 header = "->Facebook";
		txt_To_Graph(path_facebook, graph, header);
		
	}
	public static void txt_To_Graph(String path, Graph graph, String header) {
		File file=new File(path);
		Scanner reader=null;
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Wrong File Location.");
		}
		while (reader.hasNextLine()) {
			String[] splitLine=reader.nextLine().split(" ");
			graph.addEdge(splitLine[0], splitLine[1]);
		}		
		System.out.print(header);
		graph.find_All_Paths();
		System.out.print("\t");
		graph.Betweennness_Result_Calc();
		System.out.print("\t");
		graph.Closeness_Result_Calc();
	}
}
