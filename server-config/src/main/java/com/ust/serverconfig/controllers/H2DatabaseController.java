package com.ust.serverconfig.controllers;

//import java.sql.SQLException;
//
//import org.h2.tools.Server;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class H2DatabaseController {
//
//	private static final String[] args = new String[] {"-tcpPort", "8008", "-tcpAllowOthers", "true"};
//	private Server server;
//	
//	@RequestMapping("/start")
//	public String startServer() {
//		try {
//			server = Server.createTcpServer(args).start();
//			return "Se ha iniciado la base de datos";
//		} catch (SQLException e) {
//			return "No se ha iniciado la base de datos";
//		}
//	}
//	
//	@RequestMapping("/stop")
//	public String stopServer() {
//		if (server.isRunning(false)) {
//			server.stop();
//			return "Se ha cerrado la base de datos";
//		}
//		
//		return "La base de datos ya se encuentra cerrada";
//	}
//}
