package com.edugroupe.springcontact.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.edugroupe.springcontact.metier.Contact;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	// notre item reader
	@Bean
	public FlatFileItemReader<Contact> reader() {
		return new FlatFileItemReaderBuilder<Contact>()
					.name("contactItemreader")
					.resource(new ClassPathResource("contacts.csv"))
					.delimited().delimiter(",")
								.names(new String[] {"prenom", "nom", "email"})
								.fieldSetMapper(new BeanWrapperFieldSetMapper<Contact>() {{
									setTargetType(Contact.class);
								}})
					.build();
	}
	
	@Bean
	public ContactProcessor processor() {
		return new ContactProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Contact> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Contact>()
					.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
					.sql("INSERT INTO contacts (nom, prenom, email) VALUES (:nom, :prenom, :email)")
					.dataSource(dataSource)
					.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<Contact> writer) {
		return stepBuilderFactory
						.get("step1")
						.<Contact, Contact>chunk(5)
						.reader(reader())
						.processor(processor())
						.writer(writer)
						.build();
	}
	
	@Bean
	public Job importUserJob(Step step1) {
		return jobBuilderFactory.get("importuserJob")
				.flow(step1)
				.end()
				.build();
	}
	
	
}
