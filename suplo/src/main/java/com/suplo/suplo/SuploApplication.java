package com.suplo.suplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorestore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@SpringBootApplication
public class SuploApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuploApplication.class, args);
	}

}

@Configuration
class AppConfig {
	@Bean
	VectoreStore vectorestore(EmbeddingClien embeddingClient){
		return new SimpleVectoreStore(embeddingClient);
	}
}
