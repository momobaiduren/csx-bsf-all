package com.yh.csx.bsf.cat.remote;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.internal.AbstractMessage;
import com.dianping.cat.message.internal.NullMessage;
import com.yh.csx.bsf.cat.CatProperties;
import com.yh.csx.bsf.core.util.LogUtils;
import lombok.val;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: chejiangyi
 * @version: 2019-08-30 11:10
 **/
public class CatCrossFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		String requestURI = request.getRequestURI();

		val root = request.getHeader(Cat.Context.ROOT);
		val parent = request.getHeader(Cat.Context.PARENT);
		val child = request.getHeader(Cat.Context.CHILD);
		String type = "PigeonService";
		if (root == null || parent == null || child == null) {
			type = "URL";
		}

		Transaction t = Cat.newTransaction(type, requestURI);

		try {
			Cat.Context context = new CatRemoteContext();
			context.addProperty(Cat.Context.ROOT, root);
			context.addProperty(Cat.Context.PARENT, parent);
			context.addProperty(Cat.Context.CHILD, child);
			Cat.logRemoteCallServer(context);
			if ("PigeonService".equals(type)) {
				this.createProviderCross(request, t);
			}
			this.logRequestClientInfo(request, type);
			this.logRequestPayload(request, type);
			this.logProject(request, type);
			filterChain.doFilter(req, resp);
			t.setStatus(Transaction.SUCCESS);
			
		} catch (Exception e) {
			LogUtils.warn(CatCrossFilter.class, CatProperties.Project, "CatCrossFilter error", e);
			Event event = Cat.newEvent("HTTP_CAT_ERROR", requestURI);
			event.setStatus(e);
			completeEvent(event);
			t.addChild(event);
			t.setStatus(e.getClass().getSimpleName());
			throw e;
		} finally {
			t.complete();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	/**
	 * 串联provider端消息树
	 *
	 * @param request
	 * @param t
	 */
	private void createProviderCross(HttpServletRequest request, Transaction t) {
		// clientName
		Event crossAppEvent = Cat.newEvent("PigeonService.app", request.getHeader("application.name"));
		// clientIp
		Event crossServerEvent = Cat.newEvent("PigeonService.client", request.getRemoteAddr());
		crossAppEvent.setStatus(Event.SUCCESS);
		crossServerEvent.setStatus(Event.SUCCESS);
		completeEvent(crossAppEvent);
		completeEvent(crossServerEvent);
		t.addChild(crossAppEvent);
		t.addChild(crossServerEvent);
	}

	private void completeEvent(Event event) {
		if (event != NullMessage.EVENT) {
			AbstractMessage message = (AbstractMessage) event;
			message.setCompleted(true);
		}
	}

	private void logRequestClientInfo(HttpServletRequest req, String type) {
		StringBuilder sb = new StringBuilder(1024);
		String ip;
		String ipForwarded = req.getHeader("x-forwarded-for");
		if (ipForwarded == null) {
			ip = req.getRemoteAddr();
		} else {
			ip = ipForwarded;
		}

		sb.append("IPS=").append(ip);
		sb.append("&VirtualIP=").append(req.getRemoteAddr());
		sb.append("&Server=").append(req.getServerName());
		sb.append("&Referer=").append(req.getHeader("referer"));
		sb.append("&Agent=").append(req.getHeader("user-agent"));
		sb.append("&Time=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
		Cat.logEvent(type, type + ".Server", "0", sb.toString());
	}

	private void logRequestPayload(HttpServletRequest req, String type) {
		StringBuilder sb = new StringBuilder(256);
		sb.append(req.getScheme().toUpperCase()).append('/');
		sb.append(req.getMethod()).append(' ').append(req.getRequestURI());
		String qs = req.getQueryString();
		if (qs != null) {
			sb.append('?').append(qs);
		}

		Cat.logEvent(type, type + ".Method", "0", sb.toString());
	}

	private void logProject(HttpServletRequest req, String type) {
		Cat.logEvent(type, type + ".Project", "0", Cat.getManager().getDomain());
	}
}
