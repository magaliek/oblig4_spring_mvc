package no.hvl.dat108.oblig4.controller;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.oblig4.service.DeltagerDbService;
import no.hvl.dat108.oblig4.service.LogginnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import no.hvl.dat108.oblig4.model.Feilmeldinger;

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
    @GetMapping("/deltagerliste")
    public String henteDeltagerliste(HttpServletRequest request, Model model,
									 RedirectAttributes ra) {

        if (!LogginnService.erBrukerLoggetInn(request)) {
			ra.addFlashAttribute("feilmelding", Feilmeldinger.KREVER_INNLOGGET_MLD);
			return "redirect:/innlogging";
		}

		model.addAttribute("deltagere", DeltagerDbService.finnAlleSortertPaaFulltNavn());
		model.addAttribute("Innloggetbruker", request.getSession().getAttribute("Innloggetbruker"));
		return "deltagerliste";
    }
}
