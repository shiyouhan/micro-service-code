package top.syhan.cosumer.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * @description:
 * @author: syhan
 * @create: 2022-08-30
 **/

@RestController
public class ConsumerController {
    private final String SERVICE_URL = "http://localhost:8080/api";

    @Resource
    private RestTemplate restTemplate;

    private WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_URL)
            .build();

    @GetMapping("/httpClientTest")
    public String httpClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            // 判断状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(result);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            return "请求成功!";
        }
    }

    @GetMapping("/restTemplateTest")
    public String restTemplateTest() {
        System.out.println(restTemplate.getForObject(SERVICE_URL + "/hello", String.class));
        return restTemplate.getForObject(SERVICE_URL + "/hello" + "", String.class);
    }

    @GetMapping("/webClientTest")
    private String webClientTest() {
        Mono<String> mono = webClient.get().uri("/hello").retrieve().bodyToMono(String.class);
        mono.subscribe(System.out::println);
        return "请求成功！";
    }

}