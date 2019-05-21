package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.dizionariograph.db.WordDAO;



public class Model {
	
	//private WordDAO dao;
	
	private List<String> listWordsFixedLength;
	
	private Graph<String, DefaultEdge> grafo;
	Map<String, String>backVisit;



	public void createGraph(int numeroLettere) {
		this.grafo=new SimpleGraph<String, DefaultEdge>(DefaultEdge.class); //il grafo è non orientato
		
		//aggiungo i vertici
		WordDAO dao=new WordDAO();
		this.listWordsFixedLength=dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(this.grafo, this.listWordsFixedLength);
		
		//aggiungo gli archi
		for(String parola: grafo.vertexSet()) {
			for(String simile:grafo.vertexSet()) {
				int n=0;

				for(int i=0; i<numeroLettere; i++) {
					if(parola.charAt(i)!=simile.charAt(i)) {
						n++;
					}
				}
				if(n==1) {
					grafo.addEdge(parola, simile);
				}
			}
		}
		
		//System.err.println("createGraph -- TODO");
	}

	/**
	 * Visualizza tutte le parole direttamente connesse a quella inserita.
	 * @param parolaInserita
	 * @return
	 */
	public List<String> displayNeighbours(String parolaInserita) {
		/*List<String> risultato=new ArrayList<String>();
		
		backVisit=new HashMap<>();
		
		//creo l'iteratore
		GraphIterator<String, DefaultEdge>iterator=new DepthFirstIterator<>(this.grafo, parolaInserita);
		
		//aggiungo all'iteratore il listener
		iterator.addTraversalListener(new Model.EdgeListenerParoleSimili());
		
		backVisit.put(parolaInserita, null);//tutti i nodi hanno un padre, tranne la radice che ne ha zero
		
		while(iterator.hasNext()) {
			risultato.add(iterator.next());
		}
		
		return risultato;
		
		//System.err.println("displayNeighbours -- TODO");
		//return new ArrayList<String>();*/
		
		if(parolaInserita.length()!=listWordsFixedLength.get(0).length()) {
			throw new RuntimeException("La parola inserita ha un numero di lettere diverso dal numero inserito\n");
		}
		List<String>paroleVicine=new ArrayList<String>();
		paroleVicine.addAll(Graphs.neighborListOf(grafo, parolaInserita));
		
		return paroleVicine;
	}

	/**
	 * cercare il vertice col grado massimo
	 * @return
	 */
	public String findMaxDegree(String parolaInserire) {
		//System.err.println("findMaxDegree -- TODO");
		//return -1;
	/*Set<String>vertici=grafo.vertexSet();
	int maxDegree=0;
	String vertexMaxDegree=null;
	for(String vertice: vertici) {
		int vertexDegree=grafo.degreeOf(vertice);
		if(vertexDegree>=maxDegree) {
			maxDegree=vertexDegree;
			vertexMaxDegree=vertice;}
	}
	return maxDegree;*/
		List<String>vicini=Graphs.neighborListOf(grafo, parolaInserire);
		Set<String>vertici=grafo.vertexSet();
		int maxDegree=0;
		String vMaxDegree=null;
		for(String parola: vertici) {
			if(vicini.contains(parola)) {
			  int pDegree=grafo.degreeOf(parola);
			  if(pDegree>=maxDegree) {
				  maxDegree=pDegree;
				  vMaxDegree=parola;
			  }
			}
		}
		
		return maxDegree+" "+vMaxDegree;
	
		
		
	}

	/**
	 * mi serve per visualizzare la stampa del grafo nel TestModel.
	 * @return
	 */
	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}
	

	private class EdgeListenerParoleSimili implements TraversalListener<String, DefaultEdge>{

		@Override
		public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
			
		}

		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
			
		}

		@Override
		public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
			String sourceVertex=grafo.getEdgeSource(e.getEdge());
			String targetVertex=grafo.getEdgeTarget(e.getEdge()); 
			
			if(!backVisit.containsKey(targetVertex)&& backVisit.containsKey(sourceVertex)) {
				backVisit.put(targetVertex, sourceVertex);
			}else if(!backVisit.containsKey(sourceVertex)&& backVisit.containsKey(targetVertex)) {
				backVisit.put(sourceVertex, targetVertex);
			}
		}

		@Override
		public void vertexFinished(VertexTraversalEvent<String> arg0) {
			
		}

		@Override
		public void vertexTraversed(VertexTraversalEvent<String> arg0) {
			
		}
		
	}

	
}
