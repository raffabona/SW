package com.rieti.sample.listener;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SampleTable
 *
 */
@WebListener
public class SampleTable implements ServletContextListener {

	private static final HashMap<Integer, String> table = new HashMap<Integer, String>();

	/**
	 * Default constructor.
	 */
	public SampleTable() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) 
	{
		table.clear();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		table.put(1, "aaa");
		table.put(2, "bbb");
		table.put(3, "ccc");
	}

	public static String delete(Integer key) {
		synchronized (table) {
			return table.remove(key);
		}
	}

	public static boolean insert(Integer key, String value) {
		synchronized (table) {
			if (table.containsKey(key)) {
				return false;
			}
			table.put(key, value);
		}
		return true;
	}

	public static String select(Integer key) {
		synchronized (table) {
			return table.get(key);
		}
	}

}
