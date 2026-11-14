package no.hvl.dat108.oblig4.dto;

import no.hvl.dat108.oblig4.model.Deltager;

public record DeltagerResponseDTO(
        String mobil, String fornavn, String etternavn, String kjonn) {

    public DeltagerResponseDTO(Deltager d) {
        this(d.getMobil(), d.getFornavn(), d.getEtternavn(), d.getKjonn());
    }
}
