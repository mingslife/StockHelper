package com.mingslife.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingslife.task.StockWatchingTask;

@WebServlet(name = "stockServlet", urlPatterns = "/servlet/stock")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("{\"status\":\"normal\"}");
		out.flush();
		out.close();
		
		Thread stockWatchingThread = new Thread(new StockWatchingTask("http://hq.sinajs.cn/list=sz002352"));
//		Thread stockWatchingThread = new Thread(new StockWatchingTask("http://localhost:8080/StockHelper/index.jsp"));
		stockWatchingThread.start();
	}
}
