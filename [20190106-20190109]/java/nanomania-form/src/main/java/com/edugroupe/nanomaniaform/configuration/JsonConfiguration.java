package com.edugroupe.nanomaniaform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.rest.core.config.EnumTranslationConfiguration;
import org.springframework.data.rest.core.config.MetadataConfiguration;
import org.springframework.data.rest.core.config.ProjectionDefinitionConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.edugroupe.nanomaniaform.metier.Editeur;
import com.edugroupe.nanomaniaform.metier.Genre;
import com.edugroupe.nanomaniaform.metier.JeuxVideo;
import com.edugroupe.nanomaniaform.metier.PlateForme;


@Configuration
public class JsonConfiguration implements RepositoryRestConfigurer {

	// objet permettant de gerer les projections par exemple dans les controlleurs
	@Bean
	public SpelAwareProxyProjectionFactory projectionFactory() {
		return new SpelAwareProxyProjectionFactory();
	}
	
	
	// on peut ici "forcer" spring data rest à renvoyer l'id des entité dans l'api automatique
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(JeuxVideo.class, Genre.class, Editeur.class, PlateForme.class);
	}
	
	


}
