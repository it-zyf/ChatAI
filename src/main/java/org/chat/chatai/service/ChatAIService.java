package org.chat.chatai.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import java.util.List;

/**
 * @author zyf
 * @date 2025/7/11 13:48
 */
//@AiService 注解创建为一个服务
public interface ChatAIService {
    /**
     * 系统预设
     * @param userMassage 用户信息
     * @return AI回复
     */
//    @SystemMessage(value = "你是一个英语翻译，我给出中文你需要翻译成英文")
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMassage);

    /**
     * 会话记忆
     * @param userMassage 用户信息
     * @return AI回复
     */
    String chatMemory(String userMassage);

    /**
     * 会话记忆区分
     * @param memoryId 区分标识
     * @param userMessage 用户信息
     * @return AI回复
     */
    String chatMemoryId(@MemoryId String memoryId, @UserMessage String userMessage);

    /**
     * 结构化输出
     *
     */
    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatTranslate(String userMassage);

    record Report(String name, List<String> suggestions) {
    }


    /**
     * rag知识库
     * @param userMessage 用户信息
     * @return AI回复
     * 中新增方法，在原本的返回类型外封؜装一层 Result 类，就可以获得封装后结果，从中能够获取到 RAG 引用的源文档、؜以及 Token 的消耗情况
     */
    Result<String> chatWithRag(String userMessage);


}
