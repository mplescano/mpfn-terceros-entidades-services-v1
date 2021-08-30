package pe.gob.mpfn.gestiontic.services.terceros.entidades.modules.persona.service;

import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.PersonaNatural;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.specification.model.TipoDocIdentidad;

public interface PersonaService {

	PersonaNatural getPersona(TipoDocIdentidad tipoDoc, String numDoc, Boolean esMenor);
	
}
