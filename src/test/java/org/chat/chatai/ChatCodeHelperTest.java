package org.chat.chatai;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.chat.chatai.ai.ChatCodeHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author zyf
 * @date 2025/7/11 11:29
 */
@SpringBootTest
class ChatCodeHelperTest {
    @Resource
    private ChatCodeHelper chatCodeHelper;

    @Test
    public void chat() {
        String helloWorld = chatCodeHelper.chat("hello world");
        System.out.println(helloWorld);
    }

    @Test
    void chatWithUserMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("解释一下这个图标"),
                ImageContent.from("https://www.codefather.cn/logo.png")

        );

        String result = chatCodeHelper.chatWithUserMessage(userMessage);
        System.out.println(result);
    }

    @Test
    void chatWithSystemMessage() {
        String result = chatCodeHelper.chatWithSystemMessage("最近心情不是很好");
        System.out.println(result);
    }
}