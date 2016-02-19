package com.test.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


//http接口请求类
public class sendUrl {
	//private static ArrayList<List<Map<String, Object>>>[] listret = new ArrayList[6];

	public sendUrl() {
		
	}
	// 实现get方法调用
	public String sendGet(String url,	String param) {
		String result = "";
		BufferedReader in = null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 创建URL对象
			URL realUrl = new URL(url + "?" + param);
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
				conn.setRequestProperty("Cookie", "here");
				param = param.substring(param.indexOf("&") + 1, param.length());
			} else if (param.contains("cookie")) {
				param = param.substring(param.indexOf("&") + 1, param.length());
			}
			// 初始化请求头
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.connect();

			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			result = in.readLine();
			// System.out.println(result);
			try {
				result = new String(result.getBytes(), "GBK");
				result = decodeUnicode(result);
				// System.out.println(result);
			} catch (UnsupportedEncodingException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			return result;
		} catch (Exception e) {
			System.out.println("发送get请求失败！" + e);
			e.printStackTrace();
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

	// 实现post请求发包
	public String sendPost(String url,	String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
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
				conn.setRequestProperty("Cookie", "here");
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			result = reader.readLine();

			try {
				result = new String(result.getBytes(), "utf-8");
				result = decodeUnicode(result);
			} catch (Exception e1) {
			}
			return result;

		} catch (Exception e) {
			 System.out.println("发送post请求失败！\n" + e);
			 e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
		}

		 System.out.println(result);
		//jsonPase(result, 1, false);

		return null;
	}

	public static String decodeUnicode(String str) {	//解码类
		Charset set = Charset.forName("UTF-16");
		Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
		Matcher m = p.matcher(str);
		int start = 0;
		int start2 = 0;
		StringBuffer sb = new StringBuffer();
		while (m.find(start)) {
			start2 = m.start();
			if (start2 > start) {
				String seg = str.substring(start, start2);
				sb.append(seg);
			}
			String code = m.group(1);
			int i = Integer.valueOf(code, 16);
			byte[] bb = new byte[4];
			bb[0] = (byte) ((i >> 8) & 0xFF);
			bb[1] = (byte) (i & 0xFF);
			ByteBuffer b = ByteBuffer.wrap(bb);
			sb.append(String.valueOf(set.decode(b)).trim());
			start = m.end();
		}
		start2 = str.length();
		if (start2 > start) {
			String seg = str.substring(start, start2);
			sb.append(seg);
		}
		return sb.toString();
	}
}