/*
 * Copyright (c) 2018 koory1st
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package Http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public final class HttpUtil implements Serializable {

    public static String postWebserviceXml(String url, String request, String soapAction) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {

            StringEntity s = new StringEntity(request, "UTF-8");
            post.setHeader("Content-Type","text/xml; charset=utf-8");
            if (soapAction.length() > 0) {
                post.setHeader("SOAPAction",soapAction);
            }
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "UTF-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                System.out.println("请求服务器成功，做相应处理");

            } else {

                System.out.println("请求服务端失败");

            }


        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return result;
    }
    public static String postJson(String url, JSONObject json) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";

        try {

            StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                System.out.println("请求服务器成功，做相应处理");

            } else {

                System.out.println("请求服务端失败");

            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return result;
    }

}
