package sportello;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

public class sportelloController {
	
	Model model;
	public boolean cartaInserita = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button B1;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button B4;

    @FXML
    private TextArea menu;

    @FXML
    private TextArea screen;

    @FXML
    private Label labelpin;

    @FXML
    private PasswordField pinfield;

    @FXML
    private Button btok;

    @FXML
    private Button cartaButton;

    @FXML
    private Button soldi;

    @FXML
    void doCarta(ActionEvent event) {
    	if (!cartaInserita) {
    		//simula inserimento carta bancomat
    		labelpin.setVisible(true);
        	pinfield.setVisible(true);
        	btok.setVisible(true);
        	cartaButton.setText("Rimuovi Carta");
        	cartaInserita = true;
        	
        	//visualizzazione screen
        	screen.setText("Inserisci il PIN\n\n\t |  |\n\t |  |\n\tV V");
    	} else if (cartaInserita) {
    		//simula rimozione carta bancomat
    		cartaInserita = false;
    		labelpin.setVisible(false);
        	pinfield.setVisible(false);
        	btok.setVisible(false);
        	cartaButton.setText("Inserisci Carta");
        	
        	//visualizzazione screen
        	screen.setText("Benvenuto allo sportello automatico di\n\nBANCA SOLDIBELLI\n\ninserisci la carta e digita il PIN");
    	}
    }

    @FXML
    void dook(ActionEvent event) {
    	if (pinfield.getLength()==4) {
    		if (model.controlloPin(pinfield.getText())) {
    			if (model.getConto(pinfield.getText())) {
    				//conto trovato
    				screen.setText("Benvenuto " + model.getIntestatario());
    			} else {
    				//conto non trovato
    				screen.setText("Nessun contocorrente trovato");
    	    		pinfield.clear();
    			}
    		} else {
    			//pin formalmente errato
    			screen.setText("PIN Errato\n\nInserire il PIN corretto di 4 numeri");
        		pinfield.clear();
    		}
    		
    	} else {
    		//pin formalmente errato
    		screen.setText("PIN Errato\n\nInserire il PIN corretto di 4 numeri");
    		pinfield.clear();
    	}
    }

    @FXML
    void pressB1(ActionEvent event) {

    }

    @FXML
    void pressB2(ActionEvent event) {

    }

    @FXML
    void pressB3(ActionEvent event) {

    }

    @FXML
    void pressB4(ActionEvent event) {

    }

    @FXML
    void ritiraSoldi(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert B1 != null : "fx:id=\"B1\" was not injected: check your FXML file 'sportello.fxml'.";
        assert B2 != null : "fx:id=\"B2\" was not injected: check your FXML file 'sportello.fxml'.";
        assert B3 != null : "fx:id=\"B3\" was not injected: check your FXML file 'sportello.fxml'.";
        assert B4 != null : "fx:id=\"B4\" was not injected: check your FXML file 'sportello.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'sportello.fxml'.";
        assert screen != null : "fx:id=\"screen\" was not injected: check your FXML file 'sportello.fxml'.";
        assert labelpin != null : "fx:id=\"labelpin\" was not injected: check your FXML file 'sportello.fxml'.";
        assert pinfield != null : "fx:id=\"pinfield\" was not injected: check your FXML file 'sportello.fxml'.";
        assert btok != null : "fx:id=\"btok\" was not injected: check your FXML file 'sportello.fxml'.";
        assert cartaButton != null : "fx:id=\"cartaButton\" was not injected: check your FXML file 'sportello.fxml'.";
        assert soldi != null : "fx:id=\"soldi\" was not injected: check your FXML file 'sportello.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}

}
