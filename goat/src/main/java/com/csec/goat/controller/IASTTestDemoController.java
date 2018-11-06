package com.csec.goat.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * IAST 服务端接口测试
 *
 * @Author: mickle
 * @Create: 2018/8/18 16:23
 */
@RestController
@RequestMapping(path = "/iast")
public class IASTTestDemoController {
	private static Logger logger = Logger.getLogger(httprequestController.class);

	private AtomicInteger mirrorCount = new AtomicInteger(0);


	@GetMapping(value = "/mirror")
	public Object mirrorTest() throws IOException {
		int index = mirrorCount.addAndGet(1);
		if (index > 9) {
			return "[]";
		}

		Mirror[] mirrors = new Mirror[1];
		mirrors[0] = new Mirror(String.valueOf(index), "http://192.168.5.106:8090/bash.html?cmd=fconfig", "GET", new HashMap(),
				new HashMap());
		return mirrors;
	}

	static class Mirror {
		private String id;
		private String url;
		private String method;
		private Map header;
		private Map body;

		public Mirror(String id, String url, String method, Map header, Map body) {
			this.id = id;
			this.url = url;
			this.method = method;
			this.header = header;
			this.body = body;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public Map getHeader() {
			return header;
		}

		public void setHeader(Map header) {
			this.header = header;
		}

		public Map getBody() {
			return body;
		}

		public void setBody(Map body) {
			this.body = body;
		}
	}

	@PostMapping(value = "/pcap")
	public String issueTest(@RequestBody String s) throws IOException {
		logger.info(s);
		return "{\"result\":\"true\"}";
	}
}
