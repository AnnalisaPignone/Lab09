
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="cmbStati"
    private ComboBox<Country> cmbStati; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	String supporto= txtAnno.getText(); 
    	int anno= Integer.parseInt(supporto); 
    	this.model.creaGrafo(anno);
    	String supporto2= this.model.getConfinanti(); 
    	txtResult.setText(supporto2); 
    	int a = this.model.getComponentiConnesse(); 
    	txtResult.appendText("Componenti connesse: "+a); 
    	List<Country> vertici= new ArrayList (this.model.getVertici()); 
    	cmbStati.setItems(FXCollections.observableArrayList(vertici));
    }
    

    @FXML
    void doStatiRaggiungibili(ActionEvent event) {
    	Set <Country> daStampare = new HashSet(this.model.getComponenteConnessa2(cmbStati.getValue())); 
    	txtResult.setText("Stati raggiungibili: \n"); 
    	txtResult.appendText(daStampare.toString()); 
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    }
}
