package no.hvl.dat108.oblig4.model;

import java.util.Comparator;

public class Deltager {
    
    private static final String MANN_SYMBOL = "&#9794;";    // Unicode, symbol for mann
    private static final String KVINNE_SYMBOL = "&#9792;";  // Unicode, symbol for kvinne

    //Denne kan brukes hvis man ønsker sortering på full navn
    public static final Comparator<Deltager> paaFulltNavn 
    		= Comparator.comparing(Deltager::getFulltNavn); 
    
    //---
    
    private String mobil;
    private String hashetPassord;
    private String fornavn;
    private String etternavn;
    private String kjonn;
    
    //---
    
    public Deltager() {}
    
    public Deltager(String mobil, String hashetPassord, String fornavn, String etternavn, String kjonn) {
		this.mobil = mobil;
		this.hashetPassord = hashetPassord;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.kjonn = kjonn;
	}

    //Forenkler oppsett av testdata i enhetstest
    public Deltager(String fornavn, String etternavn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		//Resten blir null
	}
    
	//---
    
    public String getMobilFormatert() {
        return getMobil().substring(0,3) + " " + getMobil().substring(3,5)
                + " " + getMobil().substring(5,8);
    }

    public String getFulltNavn() {
        return getFornavn() + " " + getEtternavn();
    }
    
    public String getKjonnsymbol() {
        return (getKjonn().equals("Mann") ? MANN_SYMBOL : KVINNE_SYMBOL);
    }
    
    //---
    
	public String getMobil() {
        return mobil;
    }
    public void setMobil(String mobil) {
    	this.mobil = mobil;
    }
    public String getHashetPassord() {
		return hashetPassord;
	}
    public String getFornavn() {
        return fornavn;
    }
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
    public String getEtternavn() {
        return etternavn;
    }
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }
    public String getKjonn() {
        return kjonn;
    }
    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Deltager deltager = (Deltager) o;
        return mobil.equals(deltager.mobil);
    }

    @Override
    public int hashCode() {
        return mobil.hashCode();
    }
}
