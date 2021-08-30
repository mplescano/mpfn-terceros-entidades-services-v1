package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.components.reniec;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mpfn.support.cloud.client-web-soap.reniec")
public class ReniecWsSoapProperties {

	private String wsdlUri;
	private String serviceName;
	private String endpointUri;
	private String targetName;
	private String packagePath;
	private Duration connectionTimeoutMs;
	private Duration receiveTimeoutMs;
	private ReniecCredentials credentials;
	private ReniecConstantes constantes;
}
