package org.chat.chatai.ai;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.chat.chatai.service.ChatAIService;
import org.chat.chatai.tools.InterviewQuestionTool;
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

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Resource
    private StreamingChatModel streamingChatModel;


    @Bean
    public ChatAIService chatAIService() {
        //设置会话10条
        MessageWindowChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);
//        return AiServices.create(ChatAIService.class,qwenChatModel);
        return AiServices.builder(ChatAIService.class)
                .chatModel(qwenChatModel)
                .streamingChatModel(streamingChatModel)
                .chatMemory(memory) //会话记忆
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)) //根据memoryId分组会话记忆
                .contentRetriever(contentRetriever) //rag增强
                .tools(new InterviewQuestionTool()) // 工具调用
                .toolProvider(mcpToolProvider) //mcp 调用别人封装好的工具
                .build();
    }
}
