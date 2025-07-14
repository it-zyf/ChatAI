package org.chat.chatai;

import dev.langchain4j.service.Result;
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

    @Test
    public void chatTranslate(){
        //基础版本
        ChatAIService.Report report = chatAIService.chatTranslate("你好我叫张三,我会 1.打篮球，2.打羽毛球，3.打乒乓球");
        System.out.println(report);
        //如果基础版本不理想，可以在请求的时候就设置参数
//        ResponseFormat responseFormat = ResponseFormat.builder()
//                .type(JSON)
//                .jsonSchema(JsonSchema.builder()
//                        .name("Person")
//                        .rootElement(JsonObjectSchema.builder()
//                                .addStringProperty("name")
//                                .addIntegerProperty("age")
//                                .addNumberProperty("height")
//                                .addBooleanProperty("married")
//                                .required("name", "age", "height", "married")
//                                .build())
//                        .build())
//                .build();
//        ChatRequest chatRequest = ChatRequest.builder()
//                .responseFormat(responseFormat)
//                .messages(UserMessage.from("123"))
//                .build();
    }

    @Test
    void chatWithRag() {
        Result<String> result = chatAIService.chatWithRag("怎么学习 Java？有哪些常见面试题？");
        System.out.println(result.content());
        System.out.println(result.sources());
    }


    @Test
    void chatWithTools() {
        String result = chatAIService.chatMemory("有哪些常见的计算机网络面试题？");
        System.out.println(result);
    }


    @Test
    void chatWithMcp() {
        String result = chatAIService.chatMemory("什么是编程导航？");
        System.out.println(result);
    }


    @Test
    void chatWithGuardrail() {
        String result = chatAIService.chatMemory("kill the game");
        System.out.println(result);
    }



}
