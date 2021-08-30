package pe.gob.mpfn.gestiontic.services.terceros.entidades.delegados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.service.PersonaService;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.api.EntidadApiDelegate;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.EntidadRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.EntidadResponse;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.PersonaNatural;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.TipoDocIdentidad;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntidadApiDelegadoImpl implements EntidadApiDelegate {

	private final PersonaService personaService;

	@Override
	public ResponseEntity<EntidadResponse> getEntidad(TipoDocIdentidad tipoDoc, String numDoc, Boolean esMenor) {
		EntidadResponse entidadResponse = new EntidadResponse();
		if (TipoDocIdentidad.DNI == tipoDoc) {
			PersonaNatural personaNatural = personaService.getPersona(tipoDoc, numDoc, esMenor);
			entidadResponse.setPersonaNatural(personaNatural);
		}
		return ResponseEntity.ok(entidadResponse);
	}

	@Override
	public ResponseEntity<List<EntidadResponse>> getEntidades(EntidadRequest entidadRequest) {
		List<EntidadResponse> lstResponse = new ArrayList<>();
		log.debug("TipoDoc: {}", entidadRequest.getTipoDoc());
		log.debug("NumDoc: {}", entidadRequest.getNumDoc());
		return ResponseEntity.ok(lstResponse);
	}

}
