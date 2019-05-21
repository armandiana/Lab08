package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.tree.ExpandVetoException;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtFieldNumeroLettere;

    @FXML
    private TextField txtFieldParolaDaCercare;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnTrovaGradoMax;

    @FXML
    private TextArea txtAreaRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	try {
    		Integer numeroLettere=Integer.parseInt(this.txtFieldNumeroLettere.getText());
    		this.model.createGraph(numeroLettere);
    		this.txtAreaRisultato.appendText("Grafo creato: " +this.model.getGrafo()+"\n");
    		
    	}catch(NumberFormatException e) {
    		this.txtAreaRisultato.appendText("\nSi è verificato un erroe di formato, riprova.");
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtAreaRisultato.clear();
    	this.txtFieldNumeroLettere.clear();
    	this.txtFieldParolaDaCercare.clear();
    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	String parola=this.txtFieldParolaDaCercare.getText();
    	if(parola!=null||parola!=" ") {
    		this.txtAreaRisultato.appendText(this.model.findMaxDegree(parola)+"\n");
    	}

    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	String parola=this.txtFieldParolaDaCercare.getText();
    	if((parola!=null||parola!=" ")&&(parola.length()==Integer.parseInt(this.txtFieldNumeroLettere.getText()))) {
    		this.txtAreaRisultato.appendText(this.model.displayNeighbours(parola).toString()+"\n");
    	}else
    		this.txtAreaRisultato.appendText("ERRORE\n");

    }

    @FXML
    void initialize() {
        assert txtFieldNumeroLettere != null : "fx:id=\"txtFieldNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtFieldParolaDaCercare != null : "fx:id=\"txtFieldParolaDaCercare\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtAreaRisultato != null : "fx:id=\"txtAreaRisultato\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
    
}
