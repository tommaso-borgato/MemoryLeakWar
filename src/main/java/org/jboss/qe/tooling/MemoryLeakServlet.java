package org.jboss.qe.tooling;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet("/add")
public class MemoryLeakServlet extends HttpServlet {

	private static final Map<Long,String> memoryLeakMap = new HashMap<>();
	private static final String CANDIDATE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		StringBuilder sb = new StringBuilder ();
		Random random = new Random ();
		for (int i = 0; i < 9999999; i ++) {
			sb.append (CANDIDATE_CHARS.charAt (random.nextInt (CANDIDATE_CHARS
					.length ())));
		}
		Long key = new Date().getTime();
		memoryLeakMap.put(key,sb.toString());
		PrintWriter out = res.getWriter();
		System.out.println("Added entry " + key);
		out.println("Added entry " + key);
	}
}
