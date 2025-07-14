package org.chat.chatai.config;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

//@Configuration
//@ConfigurationProperties(prefix = "langchain4j.community.dashscope.streaming-chat-model")
@Data
public class QwenChatModelConfig {

    private String modelName;

    private String apiKey;

    @Resource
    private ChatModelListener chatModelListener;

    @Bean
    public ChatModel myQwenChatModel() {
        return QwenChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .listeners(List.of(chatModelListener))
                .build();
    }
}
