package pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.claims;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
	private String apellidoPaterno;
	private String esPrimerLogin;
	private String dni;
	private String nombres;
	private String apellidoMaterno;
}
