package model;

import java.util.ArrayList;

public class Corso {
	private String nomeCorso;
	private ArrayList<String> studenti = new ArrayList();
	
	public Corso(){}
	
	public Corso(String nomeCorso, ArrayList<String> studenti) {
		this.nomeCorso = nomeCorso;
		this.studenti = studenti;
	}
	
	public String getNomeCorso() {
		return nomeCorso;
	}
	
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	
	public ArrayList<String> getStudenti() {
		return studenti;
	}
	
	public void setStudenti(ArrayList<String> studenti) {
		this.studenti = studenti;
	}
	
	
	

}
