package no.hvl.dat108.oblig4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * Denne klassen representerer påmeldingsskjema med de reglene
 * vi har satt for hva som er gyldige data.
 * 
 * Vi kan bruke automatisk Data Binding og Bean Validation på denne 
 * i Controlleren.
 * 
 * Det er deretter mulig å opprette et Deltager-objekt fra denne med
 * metoden lagDeltager().
 */
public class DeltagerSkjema {

    /*
     * Her er noen konstanter som kan brukes til å "bygge" regulære uttrykk.
     * De må ikke brukes, men kan kanskje være til hjelp.
     */
    public static final String STRING_START = "^";
    public static final String STRING_END = "$";
    public static final String A_LETTER_SPACE_OR_HYPHEN = "[a-zA-ZæøåÆØÅ -]";
    public static final String ANY_CHARACTER = ".";
    public static final String A_CAPITAL_LETTER = "[A-ZÆØÅ]";
    public static final String A_DIGIT = "[0-9]";
    public static final String MANN_ELLER_KVINNE = "(mann|kvinne)";
	public static final String FOUR_OR_MORE_TIMES = "{4,}";
    public static final String ONE_TO_19_TIMES = "{1,19}";
    public static final String EIGHT_TIMES = "{8}";

    @NotBlank
	@Pattern(regexp = STRING_START+A_CAPITAL_LETTER+A_LETTER_SPACE_OR_HYPHEN+ONE_TO_19_TIMES+STRING_END, message=Feilmeldinger.UGYLDIG_FORNAVN_MLD)
	private String fornavn;

    @NotBlank
	@Pattern(regexp = STRING_START+A_CAPITAL_LETTER+A_LETTER_SPACE_OR_HYPHEN+ONE_TO_19_TIMES+STRING_END, message=Feilmeldinger.UGYLDIG_ETTERNAVN_MLD)
	private String etternavn;

    @NotBlank
	@Pattern(regexp=STRING_START+A_DIGIT+EIGHT_TIMES+STRING_END, message = Feilmeldinger.UGYLDIG_MOBIL_MLD)
	private String mobil;

    @NotBlank
	@Pattern(regexp=STRING_START+ANY_CHARACTER+FOUR_OR_MORE_TIMES+STRING_END, message=Feilmeldinger.UGYLDIG_PASSORD_MLD)
	private String passord;
    
    //Denne valideres programmatisk i PaameldingController
	private String passordRepetert;

    @NotBlank
	@Pattern(regexp = STRING_START+MANN_ELLER_KVINNE+STRING_END, message = Feilmeldinger.UGYLDIG_KJONN_MLD)
	private String kjonn;
	
	//---
    
    /**
     * Lager og returnerer et deltagerobjekt (/-entitet) basert på 
     * dataene i dette deltagerskjemaet.
     * 
     * Parameteren passord inneholder skal inneholde et ferdig hashet passord !
     * Det er valgt å gjøre hashing av passord utenfor Deltagerskjema-klassen for
     * å holde den "plain".
     */
    public Deltager lagDeltager(String hashetPassord) {
    	return new Deltager(mobil, hashetPassord, fornavn, etternavn, kjonn);
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
	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public String getPassord() {
		return passord;
	}
	public void setPassord(String passord) {
		this.passord = passord;
	}
	public String getPassordRepetert() {
		return passordRepetert;
	}
	public void setPassordRepetert(String passordRepetert) {
		this.passordRepetert = passordRepetert;
	}
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

}
