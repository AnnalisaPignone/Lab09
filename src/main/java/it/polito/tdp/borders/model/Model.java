package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	Map <Integer, Country> idMap; 
	BordersDAO dao= new BordersDAO();  
	private Graph<Country,DefaultEdge> grafo; 
	List<Country> vertici; 
	List<Border> borders;
	

	public Model() {
		idMap= new HashMap<Integer, Country>(); 
		dao.loadAllCountries(idMap); 	
	}
	
	public void creaGrafo(int anno) {
	this.grafo=new SimpleGraph<>(DefaultEdge.class);
	vertici= new ArrayList(dao.getVertici(anno, idMap)); 
	Graphs.addAllVertices(this.grafo, vertici); 
	borders=dao.getBorders(anno, idMap); 
	for (Border bo: borders) {
	Graphs.addEdgeWithVertices(this.grafo, bo.getC1(), bo.getC2()); 
	}
	
	System.out.println("Grafo creato!"); 
	System.out.println("#VERTICI: "+this.grafo.vertexSet().size()); 
	System.out.println("#ARCHI: "+this.grafo.edgeSet().size());
	}
	
	public List<Country> getVertici(){
		return vertici; 
	}
	

	public String getConfinanti() {
		String stampa=""; 
		for(Country c: vertici) {
			stampa= stampa+ c.getName()+ " "+ Graphs.neighborListOf(this.grafo, c).size()+ "\n"; 
		}
		return stampa; 
	}
	
	public int getComponentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		return ci.connectedSets().size();  
	}
	
	public Set<Country> getComponenteConnessa(Country vertice){
		Set<Country> visitati= new HashSet<>(); 
		DepthFirstIterator <Country, DefaultEdge> it= new DepthFirstIterator  <Country, DefaultEdge>(this.grafo, vertice);
		while(it.hasNext()) {
			visitati.add(it.next()); 
		}
		return visitati; 
 	}
	
	public Set<Country> getComponenteConnessa2(Country vertice){
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		Set<Country> visitati= new HashSet<>(); 
		visitati= ci.connectedSetOf(vertice);
		return visitati; 
	}
	

	
}


