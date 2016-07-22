package com.test.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


//http接口请求类
public class sendUrl {
	//private static ArrayList<List<Map<String, Object>>>[] listret = new ArrayList[6];
	private MyX509TrustManager cacert = null;
	private String cookie;

	public sendUrl() {
		try {
			cacert = new MyX509TrustManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("获取证书失败！");
			e.printStackTrace();
		}
	}
	// 实现get方法调用
	public String sendGet(String url,	String param) {
		String result = "";
		InputStream in = null;
		try {
			TrustManager[] tm = { this.cacert };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 创建URL对象\
			URL realUrl;
			if(param.length()>0)
				realUrl = new URL(url + "?" + param);
			else
				realUrl = new URL(url);
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpURLConnection conn = null;
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			// 设置同时调用http和https协议
			try {
				conn = (HttpURLConnection) realUrl.openConnection();
				((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
			} catch (Exception e) {
				conn = (HttpURLConnection) realUrl.openConnection();
			}
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if (param.contains("cookie=1")) {
				conn.setRequestProperty("Cookie", this.cookie);
				param = param.substring(param.indexOf("&") + 1, param.length());
			} else {
				if (param.contains("cookie")) {
					param = param.substring(param.indexOf("&") + 1,
							param.length());
				}
			}
			// 初始化请求头
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.connect();

			in = conn.getInputStream();
			if (in != null) {    
	            result = convertStreamToString(in); 
	            //System.out.println("???");
	            //System.out.println(result);
				return result;
	        }
		} catch (Exception e) {
			System.out.println("发送get请求失败！" + e);
			//e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				// e2.printStackTrace();
			}
		}
		// System.out.println(result);
		//jsonPase(result, 1, false);

		return null;
	}
	
	/**
	 * 根据地址获得数据的字节流
	 * @param strUrl 网络连接地址
	 * @return
	 */
	public byte[] getImageFromNetByUrl(String url,String param){
		InputStream in = null;
		try {
			TrustManager[] tm = { this.cacert };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 创建URL对象\
			URL realUrl;
			if(param.length()>0)
				realUrl = new URL(url + "?" + param);
			else
				realUrl = new URL(url);
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpURLConnection conn = null;
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			// 设置同时调用http和https协议
			try {
				conn = (HttpURLConnection) realUrl.openConnection();
				((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
			} catch (Exception e) {
				conn = (HttpURLConnection) realUrl.openConnection();
			}
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if (param.contains("cookie=1")) {
				conn.setRequestProperty("Cookie", this.cookie);
				param = param.substring(param.indexOf("&") + 1, param.length());
			} else {
				if (param.contains("cookie")) {
					param = param.substring(param.indexOf("&") + 1,
							param.length());
				}
			}
			// 初始化请求头
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.connect();
			in = conn.getInputStream();//通过输入流获取图片数据
			byte[] btImg = readInputStream(in);//得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			System.out.println("log::error:图片下载时出错。");
			e.printStackTrace();
		}
		return null;
	}

	// 实现post请求发包
	public String sendPost(String url,	String param) {
		PrintWriter out = null;
		String result = "";
		try {
			TrustManager[] tm = { this.cacert };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 创建URL对象
			URL realUrl = new URL(url);
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpURLConnection conn = null;
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			// 设置同时调用http和https协议
			try {
				conn = (HttpURLConnection) realUrl.openConnection();
				((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
			} catch (Exception e) {
				conn = (HttpURLConnection) realUrl.openConnection();
			}
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if (param.contains("cookie=1")) {
				conn.setRequestProperty("Cookie", this.cookie);
				param = param.substring(param.indexOf("&") + 1, param.length());
			} else {
				if (param.contains("cookie")) {
					param = param.substring(param.indexOf("&") + 1,
							param.length());
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			InputStream reader = conn.getInputStream();

			if (reader != null) {    
	            result = convertStreamToString(reader); 
	            //System.out.println("???");
	            //System.out.println(result);
				return result;
	        }


		} catch (Exception e) {
			 System.out.println("发送post请求失败！\n" + e);
			 e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		// System.out.println(result);
		//jsonPase(result, 1, false);

		return null;
	}
	
	//编码转为utf-8
	public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
	/**
	 * @return the cookie
	 */
	public String getCookie() {
		return cookie;
	}
	/**
	 * @param cookie the cookie to set
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	/**
	 * 从输入流中获取数据
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}