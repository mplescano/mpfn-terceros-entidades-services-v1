package pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.claims;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioClaim {
	
	public static final String JWT_CLAIM_NAME = "usuario";
	
	private String estado;
	private String ip;
	private String usuario;
	private Info info;
	private String codDependencia;
	private String dependencia;
	private String codDespacho;
	private String sede;
	private String despacho;
	private String codCargo;
	private String codSede;
	private String cargo;
	private String codDistritoFiscal;
	private String distritoFiscal;
	private String dniFiscal;
	private String direccion;
	private String fiscal;
	private String correoFiscal;
	private String codJerarquia;
	private String codCategoria;
	private String codEspecialidad;
	private String ubigeo;
	private String distrito;
	private String correo;
	private String telefono;
}
