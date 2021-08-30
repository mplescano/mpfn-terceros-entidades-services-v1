package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.ClienteReniecWsSoap;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MayorPorDniRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.Reniecpersona;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.service.PersonaService;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.PersonaNatural;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.TipoDocIdentidad;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

	private final ClienteReniecWsSoap clienteReniecWsSoap;

	@Override
	public PersonaNatural getPersona(TipoDocIdentidad tipoDoc, String numDoc, Boolean esMenor) {
		if (TipoDocIdentidad.DNI == tipoDoc && Boolean.FALSE.equals(esMenor)) {
			MayorPorDniRequest request = new MayorPorDniRequest();
			request.setDni(numDoc);
			List<Reniecpersona> lstPersonas = clienteReniecWsSoap.mayorPorDni(request);
			Reniecpersona reniecpersona = lstPersonas.get(0);

			PersonaNatural personaNatural = new PersonaNatural();

			personaNatural.nombres(reniecpersona.getNombres()).apellidoPaterno(reniecpersona.getApePaterno())
					.apellidoMaterno(reniecpersona.getApeMaterno())
					.direccionResidencia(reniecpersona.getDescDireccionCompleta())
					.codUbigeoResidencia(reniecpersona.getCodUbigDepartamentoDom()
							+ reniecpersona.getCodUbigProvinciaDom() + reniecpersona.getCodUbigDistritoDom())
					.descUbigeoResidencia(reniecpersona.getDescDepartamentoDom() + "-"
							+ reniecpersona.getDescProvinciaDom() + "-" + reniecpersona.getDescDistritoDom())
					.codPaisResidencia(reniecpersona.getCodUbigPaisDom())
					.descPaisResidencia(reniecpersona.getDescPaisDom())

					.codUbigeoNacimiento(reniecpersona.getCodUbigDepartamentoNac()
							+ reniecpersona.getCodUbigProvinciaNac() + reniecpersona.getCodUbigDistritoNac())
					.descUbigeoNacimiento(reniecpersona.getDescDepartamentoNac() + "-"
							+ reniecpersona.getDescProvinciaNac() + "-" + reniecpersona.getDescDistritoNac())
					.codPaisNacimiento(reniecpersona.getCodUbigPaisNac())
					.descPaisNacimiento(reniecpersona.getDescPaisNac());

			personaNatural.setEsMenor(Boolean.FALSE);
			Optional.ofNullable(reniecpersona.getFechaNac()).ifPresent(strFechaNac -> personaNatural
					.setFechaNacimiento(LocalDate.parse(strFechaNac, DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
			return personaNatural;
		} else if (TipoDocIdentidad.DNI == tipoDoc && Boolean.TRUE.equals(esMenor)) {

		}

		return null;
	}

}
