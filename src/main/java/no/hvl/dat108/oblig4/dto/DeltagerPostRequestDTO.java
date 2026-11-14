package no.hvl.dat108.oblig4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.Feilmeldinger;

import static no.hvl.dat108.oblig4.model.DeltagerSkjema.*;

public record DeltagerPostRequestDTO(

        @NotNull
        @Pattern(regexp=(STRING_START + A_DIGIT + EIGHT_TIMES + STRING_END),
                message= Feilmeldinger.UGYLDIG_MOBIL_MLD)

        @NotBlank
        @Pattern(regexp=STRING_START+A_DIGIT+EIGHT_TIMES+STRING_END, message = Feilmeldinger.UGYLDIG_MOBIL_MLD)
        String mobil,
        @NotBlank
        @Pattern(regexp=STRING_START+ANY_CHARACTER+FOUR_OR_MORE_TIMES+STRING_END, message=Feilmeldinger.UGYLDIG_PASSORD_MLD)
        String passord, //passord i klartekst
        @NotBlank
        @Pattern(regexp = STRING_START+A_CAPITAL_LETTER+A_LETTER_SPACE_OR_HYPHEN+ONE_TO_19_TIMES+STRING_END, message=Feilmeldinger.UGYLDIG_FORNAVN_MLD)
        String fornavn,
        @NotBlank
        @Pattern(regexp = STRING_START+A_CAPITAL_LETTER+A_LETTER_SPACE_OR_HYPHEN+ONE_TO_19_TIMES+STRING_END, message=Feilmeldinger.UGYLDIG_ETTERNAVN_MLD)
        String etternavn,
        @NotBlank
        @Pattern(regexp = STRING_START+MANN_ELLER_KVINNE+STRING_END, message = Feilmeldinger.UGYLDIG_KJONN_MLD)
        String kjonn) {

    public Deltager lagDeltager(String hashetPassord) {
        return new Deltager(mobil, hashetPassord, fornavn, etternavn, kjonn);
    }
}