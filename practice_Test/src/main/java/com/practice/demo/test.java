package com.practice.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Controller
@Slf4j
public class test {
	private static final ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(test.class);
	private static final org.apache.logging.log4j.Logger logger1 = LogManager.getLogger(test.class);
	private static final java.util.logging.Logger logger2 = java.util.logging.Logger.getLogger(test.class.getName());
	
	@GetMapping("/")
	public String test(Model model) {
		System.out.println("HelloController hello Start...");
		model.addAttribute("data", "Server...");	// server...라는 것을 data로 보냄
		
		logger.info("info111111111111111111111111111");
		logger.debug("debug111111111111111111111111");
		logger.error("error!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.warn("wanr!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.trace("trace!!!!!!!!!!!!!!!!!!!!!!!");
		
		logger1.info("info111111111111111111111111111");
		logger1.debug("debug111111111111111111111111");
		logger1.error("error!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger1.warn("wanr!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger1.trace("trace!!!!!!!!!!!!!!!!!!!!!!!");
		logger1.fatal("ttttttttttttt");
		
		// 로깅 수준 설정
		logger2.finer("finer222222222222222"); // debug
		logger2.fine("fine2222222222222222"); // debug
		logger2.config("config222222222222"); // info
		logger2.info("info2222222222222222222");
		logger2.warning("warning22222222222222");
		logger2.severe("severe222222222222");
		
		return "staticTest.html";
	}
	
	
}
