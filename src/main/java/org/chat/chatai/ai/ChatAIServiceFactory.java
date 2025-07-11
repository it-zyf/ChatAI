package org.chat.chatai.ai;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.chat.chatai.service.ChatAIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyf
 * @date 2025/7/11 13:52
 */
@Configuration
public class ChatAIServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Bean
    public ChatAIService chatAIService() {
        //设置会话10条
        MessageWindowChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);
//        return AiServices.create(ChatAIService.class,qwenChatModel);
        return AiServices.builder(ChatAIService.class)
                .chatModel(qwenChatModel)
                .chatMemory(memory) //会话记忆
                .chatMemoryProvider(memoryId->MessageWindowChatMemory.withMaxMessages(10)) //根据memoryId分组会话记忆
                .build();
    }
}
