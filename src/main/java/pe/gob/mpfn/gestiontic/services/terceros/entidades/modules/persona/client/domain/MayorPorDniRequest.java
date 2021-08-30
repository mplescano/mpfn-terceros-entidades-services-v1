package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mayorPorDni")
public class MayorPorDniRequest {

    private String dni;

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

    @XmlElement(name = "id_usuario")
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
