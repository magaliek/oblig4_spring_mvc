package no.hvl.dat108.oblig4.service;

import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/*
  DatatypeConverter er ikke lenger med direkte i JDK 9+ !!!

  Vi kan likevel hente den inn ved å si fra i pom.xml:
	<dependency>
		<groupId>jakarta.xml.bind</groupId>
		<artifactId>jakarta.xml.bind-api</artifactId>
	</dependency>
*/

public class PassordService {

	private final static String DEFAULT_SALT = "ABCD";

    /**
     * Lager et hashet passord for passordKlartekst ved å bruke DEFAULT_SALT.
     *
     * PS! Ikke optimalt å bruke DEAFULT_SALT, men gjort slik for å forenkle
     * oppgaven.
     */
    public static String lagHashetPassord(String passordKlartekst) {
        return hashMedSalt(passordKlartekst, DEFAULT_SALT);
    }

    /**
	 * Sjekker om et passord matcher et hashet passord tidligere generert av
     * lagHashetPassord(...).
	 * 
	 * @param passord - Passord som skal sjekkes
	 * @param hashetPassord - Det "lagrete" hashede passordet
	 * @return om passordet matcher
	 */
	public static boolean erPassordKorrekt(
			String passord, String hashetPassord) {
		
		if (passord == null || hashetPassord == null) { //Burde validert skikkelig!!
			throw new IllegalArgumentException();
		}
		return hashetPassord.equals(lagHashetPassord(passord));
	}
	
    /**
     * @return et tilfeldig generert 16 bytes salt ved å bruke SHA1PRNG
     */
    private static String genererTilfeldigSalt() {
        SecureRandom sr;
        byte[] salt = new byte[16];
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return DatatypeConverter.printHexBinary(salt);
    }

    /**
     * Genererer en kryptografisk hash for gitt passord og salt.
     *
     * Algoritmen som brukes er PBKDF2WithHmacSHA1
     * 	- PBKDF2: Password-Based-Key-Derivation-Function
     *  - WithHmac: HMAC står for Keyed-Hash Message Authentication Code
     *  - SHA1: SHA1 hashing-algoritme
     *
     * Det itereres 1000 ganger.
     * Output fra denne algoritmen er 256 bits, dvs. 32 bytes.
     *
     * Se https://en.wikipedia.org/wiki/PBKDF2 for mer info om virkemåte.
     *
     * Til slutt omgjøres byte-tabellen til en HEX-streng på 64 tegn/siffer.
     *
     * @param passord
     * @param salt
     * @return en 64 tegn lang HEX-streng som representerer en 32 byte/256 bit hash.
     */
    private static String hashMedSalt(String passord, String salt) {

        if (passord == null || salt == null) { //Burde validert skikkelig!!
            throw new IllegalArgumentException();
        }

        char[] passchar = passord.toCharArray();
        byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);

        byte[] keyhash = null;
        try {
            PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            keyhash = skf.generateSecret(pks).getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return DatatypeConverter.printHexBinary(keyhash);
    }

}
