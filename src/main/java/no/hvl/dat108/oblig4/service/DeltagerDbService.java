package no.hvl.dat108.oblig4.service;

import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.repository.DeltagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DeltagerDbService {

    @Autowired
    DeltagerRepository deltagerDb;

    /**
     * @param mobil
     * @return En deltager med gitt mobilnummer, null hvis ingen.
     */
    public Deltager finnDeltagerMedMobil(String mobil) {
        return deltagerDb.findById(mobil).orElse(null);
    }

    /**
     * @return Alle deltagerne, sortert stigende på fullt navn.
     */
    public List<Deltager> finnAlleSortertPaaFulltNavn() {
        return deltagerDb.findAll().stream().sorted(Deltager.paaFulltNavn).toList();
    }

    /**
     * @param mobil
     * @return Om deltager med gitt mobil er påmeldt
     */
    public boolean erDeltagerPaameldt(String mobil) {
        return deltagerDb.existsById(mobil);
    }

    /**
     * Lagrer (melder på) en deltager ved å legge til i listen.
     * @param deltager
     */
    public void lagreDeltager(Deltager deltager) {
        if (!deltagerDb.existsById(deltager.getMobil())) {
            deltagerDb.save(deltager);
        }
    }

    public List<Deltager> findAll() {
        return deltagerDb.findAll();
    }
}
