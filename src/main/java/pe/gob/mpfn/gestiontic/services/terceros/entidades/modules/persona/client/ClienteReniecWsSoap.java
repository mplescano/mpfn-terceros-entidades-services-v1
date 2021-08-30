package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client;

import java.util.List;

import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MayorPorDniRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.MenorPorDniRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.PorNombresRequest;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.client.domain.Reniecpersona;

public interface ClienteReniecWsSoap {

	List<Reniecpersona> mayorPorDni(MayorPorDniRequest request);

	List<Reniecpersona> porNombres(PorNombresRequest request);

	List<Reniecpersona> menorPorDni(MenorPorDniRequest request);
}
