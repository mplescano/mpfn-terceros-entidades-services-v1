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
@XmlRootElement(name = "mayorPorDniResponse"/*, namespace = "http://imp.ws.pb.reniec.ws.web.mpfn.gob.pe/"*/)
public class MayorPorDniResponse {

	@XmlElement(name = "return")
	private Reniecresultado elReturn;
}
