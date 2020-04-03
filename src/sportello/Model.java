package sportello;

import java.util.ArrayList;

public class Model {
	
	private Utenti cc1 = new Utenti("Rossi", "Mario", "1234", 5000.00);
	private Utenti cc2 = new Utenti("Verdi", "Luigi", "4321", 600.00);
	private Utenti cc3 = new Utenti("Guidi", "Guido", "0000", 11000.00);
	
	private ArrayList<Utenti> cc = new ArrayList<Utenti>();
	
	public int iCC;
	public boolean ccTrovato = false;
	

	public Model() {
		cc.add(cc1);
		cc.add(cc2);
		cc.add(cc3);
	}
	
	
	public void Exit() {
		iCC = -1;
	}
	
	
	/**
	 * Trova l'index del contocorrente abbinato al pin e lo salva in una variabile
	 * @param p PIN
	 */
	public boolean getConto(String p) {
		for (Utenti u : cc) {
			if (p.equals(u.getPin())) {
				iCC = cc.indexOf(u);
				ccTrovato = true;
				break;
			}
		}
		return ccTrovato;
	}
	
	
	/**
	 * aggiunge al saldo il valore del versamento e retun true
	 * @param vers double
	 */
	public boolean Versamento(double vers) {
		if (ccTrovato) {
			double operazione = cc.get(iCC).getSaldo() + vers;
			cc.get(iCC).setSaldo(operazione);
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * preleva il valore desiderato se disponile e lo sottrae dal saldo
	 * @param prel double
	 * @return true se operazione avvenuta con successo o false se saldo non disponibile
	 */
	public boolean Prelievo(double prel) {
		boolean success = false;
		if (ccTrovato) {
			if (prel<=cc.get(iCC).getSaldo()) {
				double operazione = cc.get(iCC).getSaldo() - prel;
				cc.get(iCC).setSaldo(operazione);
				success = true;
			}
		}
		return success;
	}
	
	
	/**
	 * Pin deve contenere solo numeri
	 * @param pw il pin da controllare
	 * @return
	 */
	public boolean controlloPin(String pw) {
		boolean correct = true;
		char[] pwa = pw.toCharArray();
		for (char t : pwa) {
			if (Character.isLetter(t)) {
				correct = false;
			}
		}
		return correct;
	}
	
	
	/**
	 * cognome e nome in una sola string
	 * @return
	 */
	public String getIntestatario() {
		String intestC = cc.get(iCC).getCognome();
		String intestN = cc.get(iCC).getNome();
		String intest = intestC + " " + intestN;
		return intest;
	}
	
	
	/**
	 * Mostra il saldo
	 * @return saldo
	 */
	public double getSaldo() {
		return cc.get(iCC).getSaldo();
	}
	
}
