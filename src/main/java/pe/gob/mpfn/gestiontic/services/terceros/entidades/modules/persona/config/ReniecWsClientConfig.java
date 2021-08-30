package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import lombok.RequiredArgsConstructor;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt.JwtAuthToken;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.ClienteReniecWsSoap;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.ClienteReniecWsSoapImpl;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MayorPorDniRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MayorPorDniResponse;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.components.reniec.ReniecWsSoapProperties;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.Reniecpersona;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.Reniecresultado;

@Configuration
@EnableConfigurationProperties(ReniecWsSoapProperties.class)
@RequiredArgsConstructor
public class ReniecWsClientConfig {

	private final ReniecWsSoapProperties reniecWsSoapProperties;
	
	private final JwtAuthToken jwtAuthToken;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Reniecresultado.class, Reniecpersona.class, MayorPorDniRequest.class,
				MayorPorDniResponse.class);

		return marshaller;
	}

	@Bean
	public WebServiceMessageSender reniecMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		httpComponentsMessageSender
				.setConnectionTimeout((int) reniecWsSoapProperties.getConnectionTimeoutMs().toMillis());
		httpComponentsMessageSender.setReadTimeout((int) reniecWsSoapProperties.getReceiveTimeoutMs().toMillis());
		httpComponentsMessageSender.setHttpClient(reniecHttpClient());
		return httpComponentsMessageSender;
	}

	private CloseableHttpClient reniecHttpClient() {
		return HttpClientBuilder.create()
				.addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor()).build();
	}

	@Bean
	public ClienteReniecWsSoap clienteReniecWsSoap(Jaxb2Marshaller reniecMarshaller,
			WebServiceMessageSender reniecMessageSender) {
		ClienteReniecWsSoapImpl wsClient = new ClienteReniecWsSoapImpl(reniecWsSoapProperties.getCredentials(),
				reniecWsSoapProperties.getConstantes(), jwtAuthToken);
		wsClient.setDefaultUri(reniecWsSoapProperties.getEndpointUri());
		wsClient.setMarshaller(reniecMarshaller);
		wsClient.setUnmarshaller(reniecMarshaller);
		wsClient.setMessageSender(reniecMessageSender);
		return wsClient;
	}
}
