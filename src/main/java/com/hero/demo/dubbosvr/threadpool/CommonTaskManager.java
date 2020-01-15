package com.hero.demo.dubbosvr.threadpool;

import com.hero.demo.dubbosvr.common.SpringContext;
import com.hero.demo.dubbosvr.config.PropsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通用的线程池任务管理器
 * @author Mojianzhang
 *
 */
public class CommonTaskManager {
	
	private static CommonTaskManager instance = null;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonTaskManager.class);
	private static PropsConfig propsConfig = null;
	
	private final static int initialThreadSize = 3;
	private final static int maxThreadSize = 100;
	private final static int threadWaitingTimeoutMillis = 30000;
	private final static int backlogSize = 3000;
	
	private ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor( initialThreadSize, maxThreadSize, threadWaitingTimeoutMillis,
			TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(backlogSize));


	private static class HolderClass {
		private static final CommonTaskManager manager = new CommonTaskManager();
	}
	
	public static CommonTaskManager getInstance() {
		if(instance == null) {
			instance = HolderClass.manager;
			propsConfig = SpringContext.getBean(PropsConfig.class);
		}
		return instance;
	}


	/**
	 * 添加任务
	 * @param task
	 */
	public void addTask(Runnable task) {
		logger.info("[CommonTaskManager] Active Thread Count ->"+taskExecutor.getActiveCount()+"  maxThreadSize ->"+maxThreadSize);
		if (taskExecutor.getQueue().size() < backlogSize) {
			taskExecutor.execute(task);
		} else {
			logger.info("CommonTaskManager backlogSize has reach too limit!!");
		}
	}


	/**
	 * 停止任务
	 */
	public void stop() {
		taskExecutor.getQueue().clear();
		taskExecutor.shutdownNow();
		logger.info("<<< CommonTaskManager Executor shutdown >>>");
	}
}
