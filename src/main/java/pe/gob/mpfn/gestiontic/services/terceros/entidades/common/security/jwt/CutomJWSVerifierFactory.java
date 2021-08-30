package pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt;

import java.security.Key;

import javax.crypto.SecretKey;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyTypeException;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;

public class CutomJWSVerifierFactory extends DefaultJWSVerifierFactory {

	@Override
	public JWSVerifier createJWSVerifier(JWSHeader header, Key key) throws JOSEException {
		JWSVerifier verifier;

		if (DESVerifier.SUPPORTED_ALGORITHMS.contains(header.getAlgorithm())) {
			if (!(key instanceof SecretKey)) {
				throw new KeyTypeException(SecretKey.class);
			}

			SecretKey macKey = (SecretKey)key;

			verifier = new DESVerifier(macKey);
			
			return verifier;
		}
		
		return super.createJWSVerifier(header, key);
	}
}
