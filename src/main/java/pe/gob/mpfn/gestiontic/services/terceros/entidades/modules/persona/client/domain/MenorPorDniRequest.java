package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenorPorDniRequest {

	private String apoderado;

	private String dni;

	private String tipVinculo;

	private String sistema;

	private String usuario;

	private String reniecjwt;

	private String clienteusuario;

	private String clientepc;

	private String clienteip;

	private String clientemac;

	private String clientevrs;

	private String coddistfiscal;

	private String coddependencia;

	private String idunico;

	private String idUsuario;

	private String uniqueid;

	private String httphost;

	private String httpuseragent;

	private String remoteport;

	private String requestmethod;

	private String parent;

	private String platform;

	private String platformversion;

	private String win;

	private String browser;

	private String typebrowser;

	private String observacion;

	private String conpdf;
}
