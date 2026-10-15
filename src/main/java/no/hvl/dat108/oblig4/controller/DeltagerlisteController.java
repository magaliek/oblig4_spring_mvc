package no.hvl.dat108.oblig4.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeltagerlisteController {
	
	/**
	 * GET /deltagerliste - Henter deltagerlisten og spiller ballen videre til
     * deltagerliste-viewet for visning.
     *
     * Krever at man er innlogget.
     * Hvis ikke bruker er innlogget, skal det gjøres er redirect til /innlogging
     * med en feilmelding satt som en flash-attributt.
	 */
    //TODO - Annoteringen for å ta i mot requesten
    public String henteDeltagerliste(HttpServletRequest request, Model model,
									 RedirectAttributes ra) {

        //TODO - Skriv innholdet i denne metoden.
    }
}
