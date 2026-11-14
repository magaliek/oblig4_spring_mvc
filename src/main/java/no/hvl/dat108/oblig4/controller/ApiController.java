package no.hvl.dat108.oblig4.controller;

import jakarta.validation.Valid;
import no.hvl.dat108.oblig4.dto.DeltagerPostRequestDTO;
import no.hvl.dat108.oblig4.dto.DeltagerResponseDTO;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.service.ApiNokkelService;
import no.hvl.dat108.oblig4.service.DeltagerDbService;
import no.hvl.dat108.oblig4.service.PassordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/deltagere")
public class ApiController {

    @Autowired
    private DeltagerDbService deltagerDbService;

    @Autowired
    private ApiNokkelService apiNokkelService;

    @Autowired
    private PassordService passordService;

    @GetMapping
    public ResponseEntity<?> deltagere(
            @RequestHeader(value="Api-Key", required = false) String token
    ) {
        if (!apiNokkelService.erNokkelGyldig(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Api-Key mangler eller er ugyldig");
        }

        List<DeltagerResponseDTO> deltagere = deltagerDbService.findAll().stream().map(DeltagerResponseDTO::new).toList();
        return ResponseEntity.ok(deltagere);
    }

    @GetMapping("/{mobil}")
    public ResponseEntity<?> deltagerMedMobil(
            @PathVariable String mobil, @RequestHeader(value="Api-Key", required = false) String token
    ) {
        if (!apiNokkelService.erNokkelGyldig(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Api-Key mangler eller er ugyldig");
        }

        Deltager d = deltagerDbService.finnDeltagerMedMobil(mobil);

        if (d != null) {
            DeltagerResponseDTO dto = new DeltagerResponseDTO(d);
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> postDeltager(
            @RequestHeader(value="Api-Key", required = false) String token,
            @Valid @RequestBody DeltagerPostRequestDTO dto) {

        if (!apiNokkelService.erNokkelGyldig(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Api-Key mangler eller er ugyldig");
        }

        if (deltagerDbService.finnDeltagerMedMobil(dto.mobil()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Det finnes allerede en deltager med mobilnummer " + dto.mobil());
        }

        String hash = passordService.lagHashetPassord(dto.passord());

        deltagerDbService.lagreDeltager(dto.lagDeltager(hash));
        URI url = URI.create("/api/deltagere/" + dto.mobil());
        return ResponseEntity.created(url).body("Deltager er meldt på");
    }
}