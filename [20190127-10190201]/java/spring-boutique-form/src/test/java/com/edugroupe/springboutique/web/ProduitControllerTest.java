package com.edugroupe.springboutique.web;

// les fonction mockito pour faux objet
import static org.mockito.Mockito.*;
// les fonctions de mockmvc pour simuler et tester des requette reponse
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// des assertions permettant de vérifier du contenu json
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.edugroupe.springboutique.metier.Produit;
import com.edugroupe.springboutique.repositories.ProduitRepository;

@SpringBootTest
@AutoConfigureMockMvc // equivalent a @EnableSpringDataWebSupport et webmvc
public class ProduitControllerTest {

	
	
	/*
	 * on va utiliser MockMvc 
	 * 	-> simuler l'environnement web pour tester un controller
	 * 
	 * @AutoConfigureMockMvc va nous permettre de tester un controller
	 * spring mvc dans un "faux" environnement web simulé
	 *  -> tres utile pour les test unitaires
	 * 
	 * on pourra envoyer des requette http "virtuelle" à notre
	 * controller, et inspecter le réponse qu'il retourne
	 * comme du json par exemple
	 * tout cela sans réellement démarrer de serveur web
	 * 
	 *  			+----MockMovc-----------------+
	 *  			|		<-- http <--		  |	<-- envoie requette virtuelle
	 *  mock <->	|   ProduitController		  |
	 *  			|		--> json, etc -->     | --> verification réponse renvoyee
	 *  			+-----------------------------+
	 * 
	 */
	@Autowired private MockMvc mockMvc;
	// j'injecte un "faux" repository
	@MockBean
	private ProduitRepository produitRepository;

	private Page<Produit> getSampleProduitPage1() {
		return new PageImpl<Produit>(new ArrayList<>(Arrays.asList(
								new Produit(1, "test1", 11.99, 0.75),
								new Produit(2, "test2", 15.99, 0.35),
								new Produit(3, "test3", 8.99, 0.5),
								new Produit(4, "test4", 12.99, 1.0))),
				PageRequest.of(0, 10), 4);
	}
	
	@Test
	@DisplayName("test de requette get vers la liste")
	public void testListe() throws Exception {
		when(produitRepository.findAll(any(Pageable.class)))
							  .thenReturn(getSampleProduitPage1());
		
		mockMvc.perform(get("/produits/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.content", hasSize(4)))
				.andExpect(jsonPath("$.size", is(10)));
		
		verify(produitRepository, times(1)).findAll(any(Pageable.class));
							  
	}
	
	@Test
	@DisplayName("test de la requette get pour un produit d'id 1")
	public void testFindProduitById() throws Exception {
		when(produitRepository.findById(1))
					.thenReturn(Optional.of(new Produit(1, "sachimi thon", 12.50, 0.150)));
	
		mockMvc.perform(get("/produits/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.nom", is("sachimi thon")))
				.andExpect(jsonPath("$.prix", is(12.50)))
				.andExpect(jsonPath("$.poids", is(0.150)));
		
		verify(produitRepository, times(1)).findById(1);
	}
	
	
	@Test
	@DisplayName("test de post pour creer un produit")
	public void testCreateProduit() throws Exception {
		when(produitRepository.save(any(Produit.class)))
							.thenReturn(new Produit(4, "mochi glace framboise", 3.99, 0.15));
		
		String produitJson =  
				" {   \"id\": 0,\n" + 
				"    \"nom\": \"mochi glace framboise\",\n" + 
				"    \"prix\": 3.99,\n" + 
				"    \"poids\": 0.15 }";
		
		mockMvc.perform(post("/produits").content(produitJson)
										 .contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(4)))
				.andExpect(jsonPath("$.nom", is("mochi glace framboise")))
				.andExpect(jsonPath("$.prix", is(3.99)))
				.andExpect(jsonPath("$.poids", is(0.15)));
		
		verify(produitRepository, times(1)).save(any(Produit.class));
		
	}
	
	@Test
	@DisplayName("test de retour 404(not found) si produit inconnu")
	public void testProduitNotFound() throws Exception {
		when(produitRepository.findById(12))
							  .thenReturn(Optional.empty());
		
		mockMvc.perform(get("/produits/12"))
			 	.andExpect(status().isNotFound());
		
		verify(produitRepository, times(1)).findById(12);
	}
	
	
	@Test
	@DisplayName("test de post pour creer un produit invalide (id > 0)")
	public void testCreateProduitKo() throws Exception {
	
		String produitJson =  
				" {   \"id\": 4,\n" + 
				"    \"nom\": \"mochi glace framboise\",\n" + 
				"    \"prix\": 3.99,\n" + 
				"    \"poids\": 0.15 }";
		
		mockMvc.perform(post("/produits").content(produitJson)
										 .contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
		
		verify(produitRepository, never()).save(any(Produit.class));
		
	}
	
	
}
