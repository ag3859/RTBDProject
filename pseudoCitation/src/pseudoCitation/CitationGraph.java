package pseudoCitation;

/**
 * Class that builds on the existing citation graph using the given paper/author
 * as a starting point
 * @author ansa
 *
 */
public class CitationGraph {

	// Functions to build graph will keep building until no new authors are found
	//i.e. until the entire connected component that includes that author has been
	//added to the graph
	/**
	 * Function to build graph based on author
	 */
	//buildGraphAuthor(String authorname,ArrayList <ArrayList <String>>existingGraph)
	/* Takes an authorname as input, and searches for it in the existing graph.on
	 * If found nothing to be done, else starts searching the database and adding 
	 * to the existing graph*/
	
	
	/**
	 * Function to build graph based on paper
	 */
	//buildGraphPaper(String papername,ArrayList <ArrayList <String>>existingGraph)
	/* Takes an papername as input, and searches for it's authors in the existing graph.
	 * If found nothing to be done, else starts searching the database and adding 
	 * to the existing graph*/
	
	
	/**
	 * Function to read in graph from text file and put into arraylist
	 */
	//readGraph(String inputfilename)
	/*Graph will be an arraylist of arraylist<integer>*/
	/*Each author will be assigned his unique id*/
	
	/**
	 * Count the citation distance between two authors
	 */
	//getCitationDistance(author1,author2)
	//Returns distance if found both authors, -1 otherwise
	//will implement a DFS for this
	
}
