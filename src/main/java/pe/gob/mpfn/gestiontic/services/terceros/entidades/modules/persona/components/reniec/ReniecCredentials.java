package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.components.reniec;

import lombok.Data;

@Data
public class ReniecCredentials {
	private String tokenJwt;
	private String codSistema;
	private String codDependencia;
	private String codDistritoFiscal;
}
