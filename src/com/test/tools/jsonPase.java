package com.test.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class jsonPase {//暂未使用

	
	// 用json.jar开源的方法，使用递归方式将json结果解析成map结果。
		public void jsonPase(String str, int level, boolean flag) {
			JSONObject jsonObject = null;
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				if (str.indexOf("[") < str.indexOf("{"))
					flag = true;
				if (str.indexOf("{") > 0) {
					str = str.substring(str.indexOf("{"), str.length());
					// System.out.println(str);
				}
			} catch (Exception e) {
				System.out.println("截取字符串错误！");
			}
			try {
				// System.out.println(level);
				while (str.indexOf("}") > 2) {
					Map<String, Object> map_value = new HashMap<String, Object>();
					try {
						jsonObject = new JSONObject(str);
						Iterator<?> it = jsonObject.keys();
						// System.out.println(jsonObject.toString());
						while (it.hasNext()) {
							String key = String.valueOf(it.next());
							// System.out.println(key);
							String values = null;
							String value = null;
							try {
								if (value == null) {
									try {
										boolean dou = jsonObject.getBoolean(key);
										values = "bool[" + dou + "]";
										map_value.put(key, values);
										value = Boolean.toString(dou);
										// System.out.println(key+":" + values);
									} catch (Exception e1) {
										value = null;
									}
								}
								if (value == null) {
									try {
										String dou = jsonObject.getString(key);
										if (dou.contains(".")) {
											try {
												values = "double["
														+ Double.valueOf(dou) + "]";
												map_value.put(key, values);
												value = dou;
											} catch (Exception e2) {
												value = null;
											}
										} else {
											try {
												values = "int["
														+ Integer.valueOf(dou)
														+ "]";
												map_value.put(key, values);
												value = dou;
											} catch (Exception e3) {
												value = null;
											}
										}
										// System.out.println(key+":" + values);
									} catch (Exception e4) {
										value = null;
									}
								}
								if (value == null) {
									value = jsonObject.getString(key);
									if (value.indexOf("[") >= 0
											&& value.indexOf("[") < value
													.indexOf("{")) {
										// System.out.println(key+":" + value);
										values = "list"
												+ value.substring(
														value.indexOf("["),
														value.length());
										map_value.put(key, values);
										jsonPase(value, level + 1, true);
									} else {
										if (value.contains("{")) {
											// System.out.println("value:" + value);
											values = "string[" + value + "]";
											map_value.put(key, values);
											jsonPase(value, level + 1, false);
										} else {
											// System.out.println(key + "--" +
											// value);
											values = "string[" + value + "]";
											map_value.put(key, values);
										}
									}
								}

							} catch (JSONException e5) {
								// System.out.println(key + "-e-" + value);
								map_value.put(key, value);
							}
						}
						// System.out.println(map_value);
						list.add(map_value);
						// System.out.println("map:"+map_value);
						// System.out.println("list:"+list);
						if (!flag)
							break;
						str = str.substring(jsonObject.toString().length(),
								str.length());
						if (str.contains("{")) {
							// System.out.println("index:" + str.indexOf("{") +
							// "--len:"
							// + str.length() + "--str:" + str);
							if (str.indexOf("{") > 0) {
								str = str.substring(str.indexOf("{"), str.length());
							}
							jsonObject = new JSONObject(str);
							it = jsonObject.keys();
							// System.out.println(jsonObject);
							// System.out.println(str.indexOf("{"));
						} else {
							str = "";
						}
					} catch (Exception e6) {
						FrameDemo.str = "json汉字编码解析错误";
						break;
					}
				}
			} catch (Exception e) {
				// System.out.println("json 解析错误");
				e.printStackTrace();
				FrameDemo.str = "json 解析错误";
			}
			try {
				listret[level - 1].add(list);
			} catch (Exception e) {
				listret[level - 1].add(null);
			}
		}
	
}
