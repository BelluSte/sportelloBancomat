package sportello;

import java.io.Serializable;

public class Utenti implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8069194360234139093L;


	@Override
	public String toString() {
		return "Utenti [cognome=" + cognome + ", nome=" + nome + ", pin=" + pin + ", saldo=" + saldo + "]";
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utenti other = (Utenti) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		return true;
	}
	
	
}
