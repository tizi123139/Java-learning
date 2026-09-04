package org.example;

import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .apiKey("API-KEY")
                .modelName("qwen-plus")
                .logRequests(true)
                .logResponses(true)
                .build();

        String result = model.chat("你是谁");
        System.out.println(result);
        System.out.println( "Hello World!" );
    }
}
