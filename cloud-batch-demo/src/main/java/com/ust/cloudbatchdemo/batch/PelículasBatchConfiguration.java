package com.ust.cloudbatchdemo.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.ust.cloudbatchdemo.models.Película;

@Configuration
@EnableBatchProcessing
public class PelículasBatchConfiguration extends DefaultBatchConfigurer {

	@Bean
	public Job películasJob(JobBuilderFactory jobBuilderFactory, Step step) {
		return jobBuilderFactory.get("peliculasJob1").start(step).build();
	}

	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory,
			ItemReader<Película> reader, ItemProcessor<Película, Película> processor,
			ItemWriter<Película> writer) {
		return stepBuilderFactory.get("step1")
				.<Película, Película>chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

	@Bean
	ItemReader<Película> reader() {
		return new FlatFileItemReaderBuilder<Película>().name("peliculasItemReader")
				.resource(new FileSystemResource("src/main/resources/peliculas.csv"))
				.delimited().delimiter(";")
				.names(new String[] {"titulo", "genero", "estudio", "puntuacion", "year"})
				.targetType(Película.class)
				.build();
	}

	@Bean
	ItemProcessor<Película, Película> processor() {
		return new ItemProcessor<Película, Película>() {

			@Override
			public Película process(Película item) throws Exception {
				//item.setTitulo(item.getTitulo().toUpperCase());
				return item;
			}
		};
	}

	@Bean
	ItemWriter<Película> writer(DataSource ds) {
		return new JdbcBatchItemWriterBuilder<Película>()
				.dataSource(ds)
				.sql(
						"INSERT INTO peliculas(TITULO, GENERO, ESTUDIO, PUNTUACION, YEAR) VALUES (:titulo, :genero, :estudio, :puntuacion, :year)")
				.beanMapped()
				.build();
	}

	/**
	 * Overriding JobRepository in order to not persist Job data in tables, but using a Map instead
	 * @return
	 * @throws Exception
	 */
	@Override
	protected JobRepository createJobRepository() throws Exception {
		MapJobRepositoryFactoryBean fb = new MapJobRepositoryFactoryBean();
		fb.afterPropertiesSet();
		return fb.getObject();
	}
}