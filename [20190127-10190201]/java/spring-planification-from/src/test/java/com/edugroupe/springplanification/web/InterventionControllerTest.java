package com.edugroupe.springplanification.web;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atMost;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.edugroupe.springplanification.repositories.InterventionRepository;
import com.edugroupe.springplanification.metier.Intervenant;
import com.edugroupe.springplanification.metier.Intervention;
import com.edugroupe.springplanification.repositories.IntervenantRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class InterventionControllerTest {
	
	
	@MockBean private InterventionRepository interventionRepository;
	@MockBean private IntervenantRepository intervenantRepository;
	
	@Autowired private MockMvc mockMvc;

	private static LocalDate currentDate;
	
	@BeforeAll
	public static void initDate() {
		currentDate = LocalDate.now();
	}
	
	private List<Intervenant> getSampleIntervenants() {
		return Arrays.asList(
					new Intervenant(1, "mario", "mario@plombier.com", new HashSet<>()),
					new Intervenant(2, "lara", "laracroft@demolition.com", new HashSet<>())
				);
		
	}
	private List<Intervention> getSampleInterventions() {
		List<Intervenant> intervenants = getSampleIntervenants();
		return Arrays.asList(
				new Intervention(1, "reparer porte toilettes",
						currentDate.plusDays(1),
						9, 11, "edugroupe", intervenants.get(0)),
				new Intervention(2, "recuperer idole maya",
						currentDate.plusDays(1),
						9, 18, "edugroupe", intervenants.get(1)),
				new Intervention(3, "braconnage equitable",
						currentDate.plusDays(2),
						13, 15, "edugroupe", intervenants.get(1)),
				new Intervention(4, "sauver la princesse",
						currentDate.plusDays(2),
						12, 16, "edugroupe", intervenants.get(0)),
				new Intervention(5, "explorer un autre chateaux",
						currentDate.plusDays(2),
						17, 18, "edugroupe", intervenants.get(0)));
	}
	
	@Test
	@DisplayName("test de liste normale avec pagination")
	public void testFindAllOk() throws Exception {
		
		when(interventionRepository.findAll(any(Pageable.class)))
									.thenReturn(new PageImpl<>(getSampleInterventions(),
											PageRequest.of(0,  10), 5));
		
		mockMvc.perform(get("/interventions"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.content",hasSize(5)))
				.andExpect(jsonPath("$.content[0].description", is("reparer porte toilettes")));
		
		verify(interventionRepository, times(1)).findAll(any(Pageable.class));
	}
	
	@Test
	@DisplayName("test de la liste des intervention de mario")
	public void testFinByIntervenantOk() throws Exception {
		when(intervenantRepository.existsById(1))
							.thenReturn(true);
		when(interventionRepository.findByIntervenantId(eq(1), any(Pageable.class)))
							.thenReturn(
								new PageImpl<>(getSampleInterventions()
												.stream().filter(i -> i.getIntervenant().getId() == 1)
												.collect(Collectors.toList()),
											PageRequest.of(0,  10), 3));
		
		mockMvc.perform(get("/interventions/intervenant/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.content",hasSize(3)))
				.andExpect(jsonPath("$.content[1].description", is("sauver la princesse")));
		
		verify(intervenantRepository, times(1)).existsById(1);
		verify(interventionRepository, times(1)).findByIntervenantId(eq(1),any(Pageable.class));
		
	}

	@Test
	@DisplayName("test de la liste des intervention d'un intervenant inexistant KO")
	public void testFinByIntervenantKO() throws Exception {
		when(intervenantRepository.existsById(3))
							.thenReturn(false);
		
		mockMvc.perform(get("/interventions/intervenant/3"))
				.andExpect(status().isNotFound());
		
		verify(intervenantRepository, times(1)).existsById(3);
		verify(interventionRepository, never()).findByIntervenantId(anyInt(),any(Pageable.class));
		
	}
	
	@ParameterizedTest(name = "test de planification initale jour {0} debut {1} fin {2} codeRetour {4}")
	@CsvFileSource(resources = "/resources/testplanifmario.csv", numLinesToSkip = 1)
	public void testPlanificationCreateMario(int decalageJour,
											int heureDebut,
											int heureFin,
											int intervenantId,
											int codeRetour) throws Exception {
		
		LocalDate planifDate = currentDate.plusDays(decalageJour);
		StringBuilder sb = new StringBuilder()
				.append("{ \"id\" : 0, \"description\":\"desc\",")
				.append("\"dateIntervention\": \"")
						.append(planifDate.toString()).append("\",")
						.append("\"heureDebut\": ").append(heureDebut).append(",")
						.append("\"heureFin\": ").append(heureFin).append(",")
						.append("\"lieu\": \"edugroupe\" }");
						
		when(intervenantRepository.findById(eq(1)))
				.thenReturn(Optional.of(new Intervenant(intervenantId, "test", "test", null)));
		
		when(interventionRepository.findByIntervenantIdAndDateIntervention(
						eq(intervenantId), eq(planifDate)))
				.thenReturn(getSampleInterventions()
								.stream()
								.filter(i -> i.getIntervenant().getId() == intervenantId)
								.filter(i -> i.getDateIntervention().equals(planifDate))
								.collect(Collectors.toList()));
		
		when(interventionRepository.save(any(Intervention.class)))
									.thenReturn(
							new Intervention(6, "desc", planifDate, heureDebut, heureFin, "edugroupe", null));
		
	
		mockMvc.perform(post("/interventions")
						.content(sb.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.param("intervenantId", "" + intervenantId))
				.andExpect(status().is(codeRetour));
		
		verify(intervenantRepository, (times(1))).findById(intervenantId);
		verify(interventionRepository, atMost(1)).findByIntervenantIdAndDateIntervention(
				eq(intervenantId), eq(planifDate));
		verify(interventionRepository, atMost(1)).save(any(Intervention.class));
				
	}

	
	
	
}
