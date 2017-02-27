package com.mingslife.task;

import java.io.File;
import java.io.FileReader;

import javax.servlet.ServletContext;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class StockWatchingTask implements Runnable {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

	private OkHttpClient client = new OkHttpClient();

	private ServletContext application;
	private String url;

	@Override
	public void run() {
		try {
			Request request = new Request.Builder().url(url).header("user-agent", USER_AGENT).build();
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String responseText = response.body().string();
				System.out.println(responseText);
				
				Context context = Context.enter();
				Scriptable scope = context.initStandardObjects();
				String result = "123";
//				Object returnValueObj = Context.javaToJS(returnValue, scope);
				Object resultObj = Context.javaToJS(result, scope);
				ScriptableObject.putProperty(scope, "result", resultObj);
				context.evaluateReader(scope, new FileReader(new File(application.getResource("WEB-INF/rule.js").getPath())), null, 1, null);
				System.out.println("result = JSON.stringify(format('" + responseText.replaceAll("\n", "") + "'));");
//				context.evaluateString(scope, "result = JSON.stringify(format('" + responseText + "'));", null, 1, null);
				context.evaluateString(scope, "result = test();", null, 1, null);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StockWatchingTask(ServletContext application, String url) {
		this.application = application;
		this.url = url;
	}
}
