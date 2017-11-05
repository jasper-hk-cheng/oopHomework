package oop.handler;

import java.lang.reflect.Method;

import oop.javabean.HandlerMapping;

public class HandlerFactory {

	public static Handler create(String key) {

		try {
			String handlerName = HandlerMapping.getHandlerNameWith(key);
			String handlerClazzName = new StringBuffer().append("oop.handler.impl.").append(handlerName).toString();
			Method getInstanceMethod = Class.forName(handlerClazzName).getMethod("getInstance");
			return (Handler) getInstanceMethod.invoke(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
