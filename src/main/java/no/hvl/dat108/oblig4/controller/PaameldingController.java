package no.hvl.dat108.oblig4.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.DeltagerSkjema;
import no.hvl.dat108.oblig4.model.Feilmeldinger;
import no.hvl.dat108.oblig4.service.DeltagerDbService;
import no.hvl.dat108.oblig4.service.LogginnService;
import no.hvl.dat108.oblig4.service.PassordService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaameldingController {
	
	/*
	 * GET /paamelding - Viser påmeldingsskjema vhja av aktuelt view
	 */
    //TODO - Annoteringen for å ta i mot requesten
    public String henteSkjema() {

        //TODO - Skriv innholdet i denne metoden.
    }
    
	/*
	 * POST /paamelding - Tar i mot innsendt påmeldingsskjema, dvs:
	 *  - Sjekker at all input er gyldig og at ikke allerede påmeldt
	 *  - Lagrer ny deltager i databasen
	 *  - Logger inn
	 *  - Går (redirecter) til påmeldingsbekreftelse
	 */
    //TODO - Annoteringen for å ta i mot requesten
    public String meldePaa(HttpServletRequest request, 
    		@Valid @ModelAttribute("deltager") DeltagerSkjema skjema,
			BindingResult bindingResult) {

    	/* For å gjøre controller-metoden kortere, kan selve valideringen
    	 * skilles ut i en egen hjelpemetode (se nederst). Denne returnerer en
    	 * feilmelding om noe er galt, og null hvis alt er OK. */
    	String feilmelding = meldingFraInputValidering(skjema, bindingResult);

        //TODO - Skriv resten av innholdet i denne metoden.
    }

	/*
	 * GET /paameldt - Viser kvittering for påmelding vhja view
	 *
	 * Krever at man er innlogget.
     * Hvis ikke bruker er innlogget, skal det gjøres er redirect til /innlogging
     * med en feilmelding satt som en flash-attributt.
     */
    //TODO - Annoteringen for å ta i mot requesten
    public String hentePaameldt(HttpServletRequest request, RedirectAttributes ra) {

        //TODO - Skriv innholdet i denne metoden.
    }
    
	/*
	 * Basic validering av feltene fornavn, etternavn, mobil, passord og kjonn gjøres ihht.
	 * annoteringene i DeltagerSkjema.
	 * 
	 * Feilhåndtering av repetert passord kan gjøres som nedenfor.
	 * Feilhåndtering av om bruker med mobil allerede er påmeldt kan gjøres som nedenfor.
	 * 
	 * PS! Det er ikke et krav om tilbakemelding på individuelle felt med feil.
	 * Altså: Det er greit nok med melding at deltager allerede er påmeldt,
	 * og/eller generell feilmelding om ugyldige data i skjema.
	 */
	private String meldingFraInputValidering(DeltagerSkjema skjema, BindingResult bindingResult) {
		String melding = null;    	
    	if (bindingResult.hasErrors()) {
    		melding = Feilmeldinger.GENERELL_BINDING_MLD;
    		//Kunne her hentet ut melding(er) fra bindingResult i stedet.
    		
    	} else if (!skjema.getPassord().equals(skjema.getPassordRepetert())) {
    		melding = Feilmeldinger.FEIL_REPETERT_PASSORD_MLD;
    		
    	} else if (DeltagerDbService.erDeltagerPaameldt(skjema.getMobil())) {
    		melding = Feilmeldinger.ALLEREDE_PAAMELDT_MLD;
    	}
		return melding;
	}
    
}
