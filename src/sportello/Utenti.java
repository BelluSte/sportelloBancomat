package sportello;

public class Utenti {
	
	private String cognome;
	private String nome;
	private String pin;
	private double saldo;
	
	
	/**
	 * Genera un nuovo utente ed il relatico conto corrente
	 * @param cognome String
	 * @param nome String
	 * @param pin int
	 * @param saldo double
	 */
	public Utenti(String cognome, String nome, String pin, double saldo) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.pin = pin;
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getPin() {
		return pin;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
}
