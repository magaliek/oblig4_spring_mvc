package no.hvl.dat108.oblig4.model;

public class Feilmeldinger {

	//Disse hører til deklarativ validering av påmeldingsskjema 
	public static final String UGYLDIG_FORNAVN_MLD = "Må være 2-20 tegn og starte med stor bokstav"; 
	public static final String UGYLDIG_ETTERNAVN_MLD = "Etternavn må være 2-20 tegn, og første tegn en stor bokstav. Ingen mellomrom"; 
	public static final String UGYLDIG_MOBIL_MLD = "Må være akkurat 8 siffer"; 
	public static final String UGYLDIG_PASSORD_MLD = "Passord må være minst 4 tegn. Alle tegn er lov"; 
	public static final String UGYLDIG_KJONN_MLD = "Du må krysse av for mann eller kvinne";
	
	//Disse hører til (ekstra) programmatisk validering av påmeldingsskjema 
	public static final String GENERELL_BINDING_MLD = "Påmeldingsdetaljer er ugyldige";
	public static final String FEIL_REPETERT_PASSORD_MLD = "Repetert passord må være likt passord"; 
	public static final String ALLEREDE_PAAMELDT_MLD = "En deltager med dette nummeret er allerede påmeldt";
	
	//Disse er meldinger når man blir omdirigert til loginn-siden
	public static final String KREVER_INNLOGGET_MLD = "Forespørselen din krever at du er innlogget";
	public static final String UGYLDIG_BRUKERNAVN_PASSORD_MLD = "Ugyldig brukernavn og/eller passord";
	public static final String UTLOGGET_MLD = "Du er logget ut";
}
