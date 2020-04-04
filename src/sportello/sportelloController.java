package sportello;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class sportelloController {
	
	Model model;
	int fase = 0;
	/*
	 * fase 0 - carta non inserita
	 * fase 1 - carta inserita, richiesta pin
	 * fase 2 - accesso effettuato
	 * fase 3 - operazione salso
	 * fase 4 - operazione prelievo
	 * fase 5 - operazione versamento
	 */
	double importo;
	
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
    private TextField impfield;

    @FXML
    private Button btok;
    
    @FXML
    private Button cartaButton;

    @FXML
    private Button soldi;

    @FXML
    void doCarta(ActionEvent event) {
    	if (fase == 0) {
    		//simula inserimento carta bancomat
    		labelpin.setVisible(true);
        	pinfield.setVisible(true);
        	if (!labelpin.getText().equals("Inserisci PIN")) {
        		labelpin.setText("Inserisci PIN");
        	}
        	btok.setVisible(true);
        	cartaButton.setText("Rimuovi Carta");
        	fase = 1;
        	
        	//visualizzazione screen
        	screen.setText("Inserisci il PIN\n\n\t |  |\n\t |  |\n\tV V");
    	} else if (fase != 0) {
    		//simula rimozione carta bancomat
    		fase = 0;
    		labelpin.setVisible(false);
        	pinfield.setVisible(false);
        	btok.setVisible(false);
        	cartaButton.setText("Inserisci Carta");
        	menu.clear();
        	model.Exit();
        	
        	//visualizzazione screen
        	screen.setText("Benvenuto allo sportello automatico di\n\nBANCA SOLDIBELLI\n\ninserisci la carta e digita il PIN");
    	}
    }

    @FXML
    void dook(ActionEvent event) {
    	
    	if (fase == 1) {
    		
    		//pin
    		if (pinfield.getLength()==4) {
        		if (model.controlloPin(pinfield.getText())) {
        			if (model.getConto(pinfield.getText())) {
        				//conto trovato
        				fase = 2;
        				pinfield.clear();
        				pinfield.setVisible(false);
        				labelpin.setVisible(false);
        				btok.setVisible(false);
        				screen.setText("Benvenuto " + model.getIntestatario());
        				menu.setText("\nSALDO\n\nPRELIEVO\n\nVERSAMENTO");
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
    	
    	//Prelievo
    	else if (fase == 4) {
    			
    		try {
       			importo = Double.parseDouble(impfield.getText());
       			
       			if (model.Prelievo(importo)) {
       				soldi.setVisible(true);
       				impfield.clear();
       				impfield.setVisible(false);
       				btok.setVisible(false);
       				labelpin.setVisible(false);
       				screen.setText("Prelevare il contante");
       				menu.setText("");
       				fase = -1;
       			} else {
       				screen.setText("Credito insufficente");
       				impfield.clear();
       			}
       		} catch(NumberFormatException exp) {
       			screen.setText("Errore. Inserisci un importo corretto");
       			impfield.clear();
       		}
    	}	   		
    	
    	//Versamento
    	else if (fase == 5) {

        	try {
       			importo = Double.parseDouble(impfield.getText());
       			//versamento + verifica
       			if (model.Versamento(importo)) {
       				screen.setText("Operazione avvenuta con successo");
       				impfield.clear();
       				impfield.setVisible(false);
       				btok.setVisible(false);
       				labelpin.setVisible(false);
       			}
       		} catch(NumberFormatException exp) {
       			screen.setText("Errore. Inserisci un importo corretto");
       			impfield.clear();
       		}		
        	}
    }

        
    @FXML
    void pressB1(ActionEvent event) {
    	if (fase == 2) {
    		fase = 3;
    		
    		//view
    		menu.setText("\n\n\n\n\n\n\nIndietro");
    		screen.setText("Saldo disponibile: " + model.getSaldo());
    	}
    }

    @FXML
    void pressB2(ActionEvent event) {
    	//prelievo
    	if (fase == 2) {
    		fase = 4;
    		
    		//view
    		menu.setText("\n\n\n\n\n\n\nIndietro");
    		screen.setText("Inserisci l'importo che desideri prelevare\n\n\t |  |\n\t |  |\n\tV V");
    		labelpin.setVisible(true);
    		labelpin.setText("Preleva");
    		impfield.setVisible(true);
    		btok.setVisible(true);
    	}
    }

    @FXML
    void pressB3(ActionEvent event) {
    	//versamento
    	if (fase == 2) {
    		fase = 5;
    		
    		//view
    		menu.setText("\n\n\n\n\n\n\nIndietro");
    		screen.setText("Inserisci l'importo che desideri versare\n\n\t |  |\n\t |  |\n\tV V");
    		labelpin.setVisible(true);
    		labelpin.setText("Versa");
    		impfield.setVisible(true);
    		btok.setVisible(true);
    	}
    }

    @FXML
    void pressB4(ActionEvent event) {
    	// indietro
    	if (fase == 3 || fase == 4 || fase == 5) {
    		fase = 2;
    		
    		//view
    		screen.setText("Benvenuto " + model.getIntestatario());
			menu.setText("\nSALDO\n\nPRELIEVO\n\nVERSAMENTO");
    	}
    }

    @FXML
    void ritiraSoldi(ActionEvent event) {
    	screen.setText("Operazione avvenuta con successo");
    	menu.setText("\n\n\n\n\n\n\nIndietro");
    	soldi.setVisible(false);
    	fase = 4;
    	
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
        assert impfield != null : "fx:id=\"impfield\" was not injected: check your FXML file 'sportello.fxml'.";
        assert btok != null : "fx:id=\"btok\" was not injected: check your FXML file 'sportello.fxml'.";
        assert cartaButton != null : "fx:id=\"cartaButton\" was not injected: check your FXML file 'sportello.fxml'.";
        assert soldi != null : "fx:id=\"soldi\" was not injected: check your FXML file 'sportello.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}

}
