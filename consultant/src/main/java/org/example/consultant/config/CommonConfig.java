package org.example.consultant.config;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.example.consultant.aiservice.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonConfig {

    @Autowired
    private OpenAiChatModel model;

    @Autowired
    private ChatMemoryStore redisChatMemoryStore;

    @Autowired
    private EmbeddingModel embeddingModel;

//    @Autowired
//    private RedisEmbeddingStore redisEmbeddingStore;

//    @Bean
//    public ConsultantService consultantService(){
//        ConsultantService consultantService = AiServices.builder(ConsultantService.class)
//                .chatLanguageModel(model)
//                .chatMemoryProvider(chatMemoryProvider())
//                .build();
//        return consultantService;
//    }

    @Bean
    public ChatMemory chatMemory(){
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
        return memory;
    }

    @Bean
    public ChatMemoryProvider  chatMemoryProvider(){
        ChatMemoryProvider  chatMemoryProvider = new ChatMemoryProvider(){
            @Override
            public ChatMemory get(Object memoryId){
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(20)
                        .chatMemoryStore(redisChatMemoryStore)
                        .build();
            }
        };
        return chatMemoryProvider;

    }

    @Bean
    public EmbeddingStore store(){
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content",new ApachePdfBoxDocumentParser());
        InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();

        DocumentSplitter ds = DocumentSplitters.recursive(500,100);
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .documentSplitter(ds)
                .embeddingModel(embeddingModel)
                .build();
        ingestor.ingest(documents);
        return store;
    }

    @Bean
    //构建向量数据库检索对象
    public ContentRetriever  contentRetriever(EmbeddingStore store){
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(store)
                .minScore(0.5)
                .maxResults(3)
                .embeddingModel(embeddingModel)
                .build();
    }
}
