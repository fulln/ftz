package me.fulln.ftz.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @AUthor: fulln
 * @Description: http工具类
 * @Date : Created in  12:50  2018/5/12.
 */
@Slf4j
public class httpUtil {



    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String DEFAULT_HTTP_CHARSET = "UTF-8";

    private static final String DEFAULT_HTTP_CONTENT = "application/x-www-form-urlencoded";

    /**
     * 根据api地址和参数生成请求URL
     *
     * @param url
     * @param params
     * @return
     */
    public static String getUrlWithQueryString(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            // 过滤空的key
            if (value == null) {
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }

        return builder.toString();
    }


    /**
     * 进行URL编码
     *
     * @param input
     * @return
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }

    /**
     * 用httpclient 发送post请求   调取接口用  设置请求头
     *
     * @param url               请求地址
     * @param requestHeadParams
     * @param requestParams
     * @return
     */
    public static String ClientPostHeadRequest(String url, Map<String, String> requestHeadParams, Map<String, String> requestParams) {

        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;

        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(6000)
                .setConnectTimeout(6000)
                .build();//设置请求和传输超时时间

        post.setConfig(requestConfig);
        post.addHeader("User-Agent", USER_AGENT);
        requestHeadParams.forEach(post::addHeader);

        List<BasicNameValuePair> params = new ArrayList<>();
        requestParams.forEach(
                (k, v) -> params.add(new BasicNameValuePair(k, v))
        );


        try {
            post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_HTTP_CHARSET));
            //HttpResponse
            httpResponse = httpClient.execute(post);

            int code = httpResponse.getStatusLine().getStatusCode();
            if (code != 200) {
                log.error("url访问出错误:" + code);
                return null;
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IO出现错误:" + e.getMessage());
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("IO文件关闭出现异常:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 用httpclient 发送post请求   调取接口用
     *
     * @param url
     * @return
     */
    public static String ClientPostRequest(String url, Map<String, Object> requestParams) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
//        BufferedReader reader = null;
//        StringBuilder response = new StringBuilder();

        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(6000)
                .setConnectTimeout(6000)
                .build();//设置请求和传输超时时间

        post.setConfig(requestConfig);
        post.addHeader("User-Agent", USER_AGENT);

        List params = new ArrayList();
        if (requestParams != null) {
            for (Object o : requestParams.entrySet()) {
                Entry<String, String> en = (Entry) o;
                String key = en.getKey();
                String value = en.getValue();
                if (value != null) {
                    params.add(new BasicNameValuePair(key, value));
                }
            }
        }

        try {
            post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_HTTP_CHARSET));
            /**HttpResponse*/
            httpResponse = httpClient.execute(post);

            int code = httpResponse.getStatusLine().getStatusCode();
            if (code != 200) {
                log.error("url访问出错误:" + code);
                return null;
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IO出现错误:" + e.getMessage());
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("IO文件关闭出现异常:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 用httpclient  发送get请求
     *
     * @return
     */
    public static String ClientGetRequest(String url) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 执行get请求.
        HttpGet httpget = new HttpGet(url);
        try (
                //将将要抛出异常的部分放入括号实现无论当前 异常结束  都会被正确的关闭
                CloseableHttpResponse response = httpclient.execute(httpget)) {
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用httpClient  发送get请求 发送用户名密码登录验证  基于cookie    基于证书
     * 标准拟浏览器访问
     */
    public static String ClientGetFullRequest(String url) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("localhost", 8083),
                //用户验证
                new UsernamePasswordCredentials("username", "password"));
        //cookie验证
        CookieStore cookieStore = new BasicCookieStore();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .setMaxConnPerRoute(100)
                .setDefaultCookieStore(cookieStore)
                .build();
        HttpGet httpget = new HttpGet(url);

        try (CloseableHttpResponse response = httpclient.execute(httpget)) {
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            List<Cookie> cookies = cookieStore.getCookies();

            for (Cookie c : cookies
            ) {
                System.out.println("Local cookie: " + c);
            }

            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 用httpClient  post请求 http  登录 基于cookie 基于证书  返回页面
     */
    public static String ClientPostFullRequest(String url, String userName, String pass) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        BufferedReader reader = null;
        StringBuilder Hresponse = new StringBuilder();

        HttpHost ha = new HttpHost(url);
        credsProvider.setCredentials(
                new AuthScope(ha),
                //用户验证
                new UsernamePasswordCredentials(userName, pass));
        //cookie验证
        CookieStore cookieStore = new BasicCookieStore();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .setMaxConnPerRoute(100)
                .setDefaultCookieStore(cookieStore)
                .build();

        HttpPost post = new HttpPost(url);
        post.addHeader("User-Agent", USER_AGENT);
        post.addHeader("Content-Type", DEFAULT_HTTP_CONTENT);

        try (CloseableHttpResponse response = httpclient.execute(post)) {

            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                Hresponse.append(inputLine);
            }
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Hresponse.toString();
    }




}
