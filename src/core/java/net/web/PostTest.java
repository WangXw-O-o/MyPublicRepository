package core.java.net.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>一个测试发送web请求的客户端</p>
 * @author WangXwO_o
 */
public class PostTest {

    public static void main(String[] args) throws IOException {
        //Cookie设置，重定向时cookie信息也会被正确发送
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        Map<Object, Object> nameValuePairs = new HashMap<>();
        //测试向访问百度首页
        String result = doPost(new URL("https://www.baidu.com/"), nameValuePairs, null, -1);
        System.out.println(result);
    }

    public static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, int redirects) throws IOException {
        //打开连接
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置代理
        if (userAgent != null) {
            connection.setRequestProperty("User-Agent", userAgent);
        }
        //取消自动重定向
        if (redirects >= 0) {
            connection.setInstanceFollowRedirects(false);
        }
        //建立输出连接
        connection.setDoOutput(true);
        try (
                //获取输出流
                var out = new PrintWriter(connection.getOutputStream())
        ) {
            //输出参数
            boolean first = true;
            for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    out.print("&");
                }
                String name = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(name);
                out.print(value);
                out.print(URLEncoder.encode(value, StandardCharsets.UTF_8));
            }
        }
        String encoding = connection.getContentEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }
        //手动重定向
        if (redirects > 0) {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    URL base = connection.getURL();
                    //断开连接
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }
            }
        } else if (redirects == 0) {
            throw new IOException("Too Many redirects");
        }
        StringBuilder response = new StringBuilder();
        try (var in = new Scanner(connection.getInputStream(), encoding)) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        } catch (IOException e) {
            InputStream err = connection.getErrorStream();
            if (err == null) {
                throw e;
            }
            try (var in = new Scanner(err)) {
                response.append(in.nextLine());
                response.append("\n");
            }
        }
        return response.toString();
    }
}
