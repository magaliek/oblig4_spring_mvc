package no.hvl.dat108.oblig4;

import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.repository.DeltagerRepository;
import no.hvl.dat108.oblig4.service.DeltagerDbService;
import no.hvl.dat108.oblig4.service.PassordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class Oblig4SpringmvcLosning1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @InjectMocks
    PassordService passordService;

    @InjectMocks
    DeltagerDbService deltagerDbService;

    @Mock
    DeltagerRepository rp;

    @Test
    void passordTest(){
        String passord = "passord";
        String feilp = "feilp";
        String hashetpass = passordService.lagHashetPassord(passord);

        assertTrue(passordService.erPassordKorrekt(passord, hashetpass));
        assertFalse(passordService.erPassordKorrekt(feilp, hashetpass));
    }

    @Test
    void finnAlleTest() {
        Deltager[] deltagere = {
                new Deltager("12345678", "123momdadbro", "Ramaggie", "Balash", "Kvinne"),
                new Deltager("12345679", "123momdadbro", "Amaggie", "Calash", "Kvinne"),
                new Deltager("12345673", "123momdadbro", "Acoggie", "Alash", "Kvinne")
        };

        when(rp.findAll()).thenReturn(Arrays.asList(deltagere));

        Deltager[] deltagereSortert = {
                new Deltager("12345673", "123momdadbro", "Acoggie", "Alash", "Kvinne"),
                new Deltager("12345679", "123momdadbro", "Amaggie", "Calash", "Kvinne"),
                new Deltager("12345678", "123momdadbro", "Ramaggie", "Balash", "Kvinne")
        };

        assertArrayEquals(deltagereSortert, deltagerDbService.finnAlleSortertPaaFulltNavn().toArray());

    }
}
