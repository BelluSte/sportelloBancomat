package sportello;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Model {
	
	private ArrayList<Utenti> cc;
	
	public int iCC;
	public boolean ccTrovato = false;
	
	FileInputStream fis;
	ObjectInputStream ois;
	FileOutputStream fos;
	ObjectOutputStream oos;
	
	@SuppressWarnings("unchecked")
	public Model() {
		try {
			fis = new FileInputStream("D:\\Stefano\\Documents\\Java\\SportelloBancomat\\src\\sportello\\filefolder\\save.txt");
			ois = new ObjectInputStream(fis);
			
			cc = (ArrayList<Utenti>) ois.readObject();
			
			ois.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void Exit() {
		
		try {
			iCC = -1;
			
			fos = new FileOutputStream("D:\\Stefano\\Documents\\Java\\SportelloBancomat\\src\\sportello\\filefolder\\save.txt");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(cc);
			
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
