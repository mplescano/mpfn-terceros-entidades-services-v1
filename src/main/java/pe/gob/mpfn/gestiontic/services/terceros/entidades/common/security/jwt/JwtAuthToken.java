package pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtAuthToken implements Serializable {

	private static final long serialVersionUID = 5533525790102450732L;

	private String token;

	private JwtClaims payload;
}
