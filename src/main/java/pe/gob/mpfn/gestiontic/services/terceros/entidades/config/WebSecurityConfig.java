package pe.gob.mpfn.gestiontic.services.terceros.entidades.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.claims.UsuarioClaim;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt.CutomJWSVerifierFactory;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt.JwtAuthToken;
import pe.gob.mpfn.gestiontic.services.terceros.entidades.common.security.jwt.JwtClaims;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final ObjectMapper objectMapper;

	WebSecurityConfig(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests(requests ->
				requests
					.antMatchers("/entidad/**").authenticated()
					.anyRequest().permitAll()
			)
			// .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.oauth2ResourceServer(t -> {
				// t.accessDeniedHandler(null);
				// t.authenticationEntryPoint(null);
				// t.jwt(null);
				t.jwt();
			})
			;
		// @formatter:on
	}

	@Bean
	JwtDecoder jwtDecoder() {
		String keyString = "a1b2c3z28";
		SecretKey key = new SecretKeySpec(keyString.getBytes(), "DES");
		NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS512)
				.jwtProcessorCustomizer(t -> t.setJWSVerifierFactory(new CutomJWSVerifierFactory())).build();
		decoder.setClaimSetConverter(MappedJwtClaimSetConverter.withDefaults(
				ImmutableMap.of(UsuarioClaim.JWT_CLAIM_NAME, source -> objectMapper.convertValue(source, UsuarioClaim.class))));

		return decoder;
	}

	@Bean
	@RequestScope
	JwtAuthToken jwtAuthToken() {
		JwtAuthenticationToken currentUser = (JwtAuthenticationToken) SecurityContextHolder.getContext()
				.getAuthentication();
		Jwt jwt = currentUser.getToken();
		JwtClaims payload = new JwtClaims(jwt.getSubject(), jwt.getIssuer().toString(), jwt.getClaimAsString("ip"),
				jwt.getClaim(UsuarioClaim.JWT_CLAIM_NAME));
		return new JwtAuthToken(jwt.getTokenValue(), payload);
	}
}
