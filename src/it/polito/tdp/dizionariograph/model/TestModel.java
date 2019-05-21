package it.polito.tdp.dizionariograph.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.createGraph(4);
		//System.out.println(model.getGrafo());
		//System.out.println(String.format("**Grafo creato**\n"));
		//System.out.format("creati %d vertici e %d archi \n", model.getGrafo().vertexSet().size(), model.getGrafo().edgeSet().size());
		
		//System.out.println("\n******************************\n");
		String parolaDaInserire="casa";
		List<String> vicini = model.displayNeighbours(parolaDaInserire);
		System.out.printf("Neighbours di %s: " + vicini.toString() + "\n", parolaDaInserire );
		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree(parolaDaInserire));
	}

}
