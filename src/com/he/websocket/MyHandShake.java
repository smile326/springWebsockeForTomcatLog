package com.he.websocket;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 *@function：实现获取上传的参数，然后设置到socke 的session中
 *@parameter:
 *@return：
 *@date：2016-9-10 上午11:15:03
 *@author:Administrator
 *@notice:
 */
public class MyHandShake implements HandshakeInterceptor{
	
	
	protected static final Logger LOG = Logger.getLogger(MyHandShake.class);


	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		
	}

	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		try {
			if (request instanceof ServletServerHttpRequest) {
				ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
				// 标记用户
				String uid = servletRequest.getServletRequest().getParameter("uid");
				LOG.warn("servlet request uid is :" + uid);
				if (uid != null) {
					attributes.put("uid", uid);
				} else {
					return false;
				}
			}
			LOG.info("websocket handshake success");
			return true;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.warn(sw.toString());
			LOG.warn("websocket 的handshake出现异常");
			return false;
		}
	}

}
