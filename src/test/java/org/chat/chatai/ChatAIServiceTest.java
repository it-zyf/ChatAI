package org.chat.chatai;

import jakarta.annotation.Resource;
import org.chat.chatai.service.ChatAIService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zyf
 * @date 2025/7/11 14:04
 */
@SpringBootTest
public class ChatAIServiceTest {

    @Resource
    private ChatAIService chatAIService;

    @Test
    public void chat(){
        String result = chatAIService.chat("今天天气好热，好想睡觉");
        System.out.println(result);
    }

    @Test
    public void chatMemory(){
        String result = chatAIService.chatMemory("你好，我是国服李白");
        System.out.println(result);
        String result2 = chatAIService.chatMemory("今天天气怎么样");
        System.out.println(result2);
        String result3 = chatAIService.chatMemory("我是谁来着");
        System.out.println(result3);
    }

    @Test
    public void chatMemoryId(){
        String result = chatAIService.chatMemoryId("1","我是李白");
        System.out.println(result);
        String result2 = chatAIService.chatMemoryId("2","我是赵云");
        System.out.println(result2);
        String result3 = chatAIService.chatMemoryId("1","我是谁");
        System.out.println(result3);
    }

}
