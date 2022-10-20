package core.java.net.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //获取默认配置的客户端
//        HttpClient httpClient = HttpClient.newHttpClient();
        //通过构建器构建包含配置消息的客户端
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        //POST请求的时候需要使用体发布器
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString("");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://fanyi.baidu.com/#en/zh/build"))
                .header("Content-Type", "text/html; charset=utf-8")
                .GET()
                .build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }

}
