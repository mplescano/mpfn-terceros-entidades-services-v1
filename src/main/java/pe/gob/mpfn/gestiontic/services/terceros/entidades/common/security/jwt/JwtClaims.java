package pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.claims.UsuarioClaim;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtClaims implements Serializable {

	private static final long serialVersionUID = -664526132074879127L;
	
	private String sub;
	private String iss;
	private String ip;
	private UsuarioClaim usuario;
}
