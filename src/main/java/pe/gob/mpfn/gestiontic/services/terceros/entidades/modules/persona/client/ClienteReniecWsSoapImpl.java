package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

import lombok.RequiredArgsConstructor;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt.JwtAuthToken;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MayorPorDniRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MayorPorDniResponse;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MenorPorDniRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.PorNombresRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.Reniecpersona;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.components.reniec.ReniecConstantes;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.components.reniec.ReniecCredentials;

@RequiredArgsConstructor
public class ClienteReniecWsSoapImpl extends WebServiceGatewaySupport implements ClienteReniecWsSoap {

	private final ReniecCredentials credentials;

	private final ReniecConstantes constantes;

	private final JwtAuthToken jwtAuthToken;

	@Override
	public List<Reniecpersona> mayorPorDni(MayorPorDniRequest request) {
		setCredentials(request);
		setConstantes(request);
		setAuthUser(request);
		setOtrosAttributos(request);
		MayorPorDniResponse response = (MayorPorDniResponse) getWebServiceTemplate()
				.marshalSendAndReceive(getDefaultUri(), request, message -> {
					((SoapMessage) message).setSoapAction("http://imp.ws.pb.reniec.ws.web.mpfn.gob.pe/#mayorPorDni");
				});

		if (constantes.getCodExito().equals(response.getElReturn().getCodigo())) {
			throw new ClienteReniecWsSoapException("Error en la consulta, el servicio devolvió el código "
					+ response.getElReturn().getCodigo() + " y el mensaje " + response.getElReturn().getMensaje());
		}

		return response.getElReturn().getListaPersonas();
	}

	private void setAuthUser(MayorPorDniRequest request) {
		request.setUsuario(
				Optional.ofNullable(request.getUsuario()).orElse(jwtAuthToken.getPayload().getUsuario().getUsuario()));
		request.setClienteusuario(Optional.ofNullable(request.getClienteusuario())
				.orElse(jwtAuthToken.getPayload().getUsuario().getDniFiscal()));
		request.setClienteip(
				Optional.ofNullable(request.getClienteip()).orElse(jwtAuthToken.getPayload().getUsuario().getIp()));
	}

	private void setConstantes(MayorPorDniRequest request) {
		request.setClientepc(Optional.ofNullable(request.getClientepc()).orElse(constantes.getClientePc()));
		request.setClientemac(Optional.ofNullable(request.getClientemac()).orElse(constantes.getClienteMac()));
		request.setClientevrs(Optional.ofNullable(request.getClientevrs()).orElse(constantes.getClienteVersion()));
		request.setHttphost(Optional.ofNullable(request.getHttphost()).orElse(constantes.getHttpHost()));
		request.setConpdf(Optional.ofNullable(request.getConpdf()).orElse(constantes.getPdf()));
	}

	private void setCredentials(MayorPorDniRequest request) {
		request.setSistema(Optional.ofNullable(request.getSistema()).orElse(credentials.getCodSistema()));
		request.setReniecjwt(Optional.ofNullable(request.getReniecjwt()).orElse(credentials.getTokenJwt()));
		request.setCoddistfiscal(
				Optional.ofNullable(request.getCoddistfiscal()).orElse(credentials.getCodDistritoFiscal()));
		request.setCoddependencia(
				Optional.ofNullable(request.getCoddependencia()).orElse(credentials.getCodDependencia()));
	}

	private void setOtrosAttributos(MayorPorDniRequest request) {
		PropertyDescriptor[] arrAttrs = BeanUtils.getPropertyDescriptors(request.getClass());
		Stream.of(arrAttrs).forEach(beanDesc -> {
			try {
				Object value = beanDesc.getReadMethod().invoke(request);
				if (value == null) {
					beanDesc.getWriteMethod().invoke(request, "");
				}
			} catch (Exception ex) {
				throw new ClienteReniecWsSoapException("Error in setOtrosAttributos: " + beanDesc, ex);
			}
		});
	}

	@Override
	public List<Reniecpersona> porNombres(PorNombresRequest request) {
		return null;
	}

	@Override
	public List<Reniecpersona> menorPorDni(MenorPorDniRequest request) {
		return null;
	}

	static class ClienteReniecWsSoapException extends RuntimeException {
		private static final long serialVersionUID = 5330253725789761041L;

		public ClienteReniecWsSoapException(String message) {
			super(message);
		}

		public ClienteReniecWsSoapException(String message, Throwable cause) {
			super(message, cause);
		}

	}

}
