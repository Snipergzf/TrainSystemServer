package com.train.main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import com.train.communication.WorkerTwo;
import com.train.config.Config;
import com.train.dao.UserEntityDao;
import com.train.model.UserEntity;

/**
 * @author gzf
 * 
 */
public class Server {
	private Logger logger;
	private ServerSocket serverSocket;
	private ThreadPoolExecutor executor;
	private JFrame frame;
	private JPanel j1;
	private JButton personalButton;
	private JButton onlineButton;
	private JTextArea inputHint;
	private JTextField inputField;
	private JButton queryButton;
	private JButton refreshButton;
	private JTextArea serverConsole;
	private JScrollPane jScrollPane;
	private UserEntityDao userEntityDao;
	private static String STATUS = "INIT";

	public void init() {
		initNetWork();
		newUtil();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputHint.setText("请输入查询IP");
		inputHint.setFont(new Font("宋体", Font.BOLD, 12));
		inputHint.setMargin(new Insets(3, 4, 1, 2));
		inputField.addKeyListener(new MyAdapter());
		inputField.setFont(new Font("宋体", Font.PLAIN, 12));
		queryButton.addActionListener(new inputListener());
		refreshButton.addActionListener(new refreshListener());
		serverConsole.setLineWrap(false);
		serverConsole.setWrapStyleWord(true);
		serverConsole.setEditable(false);
		serverConsole.setFont(new Font("宋体", Font.BOLD, 12));
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		GridBagLayout gb = new GridBagLayout();
		frame.setLayout(gb);
		frame.add(onlineButton);
		frame.add(personalButton);
		frame.add(j1);
		frame.add(inputHint);
		frame.add(inputField);
		frame.add(queryButton);
		frame.add(refreshButton);
		frame.add(jScrollPane);
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;

		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(onlineButton, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(personalButton, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(j1, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(inputHint, s);
		s.gridwidth = 4;
		s.weightx = 1;
		s.weighty = 0;
		gb.setConstraints(inputField, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(queryButton, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(refreshButton, s);
		s.gridwidth = 7;
		s.weightx = 0;
		s.weighty = 1;
		gb.setConstraints(jScrollPane, s);

		frame.setSize(800, 600);
		frame.setVisible(true);
		queryUsers();
	}

	public void initNetWork() {
		logger = Logger.getLogger("TrainSystemServer");
		logger.info("Server initializing...");
		try {
			serverSocket = new ServerSocket(Config.ServerPort);
			executor = new ThreadPoolExecutor(Config.CorePoolSize,
					Config.MaximumPoolSize, Config.KeepAliveTime,
					TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(12));
			executor.allowCoreThreadTimeOut(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newUtil() {
		userEntityDao = new UserEntityDao();
		frame = new JFrame("训练系统后台监控");
		j1 = new JPanel();
		onlineButton = new JButton("在线情况");
		personalButton = new JButton("个人操作");
		inputHint = new JTextArea();
		inputField = new JTextField();
		queryButton = new JButton("查询");
		refreshButton = new JButton("刷新");
		serverConsole = new JTextArea();
		jScrollPane = new JScrollPane(serverConsole);
	}

	public void go() throws IOException {
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				String ipaddress = clientSocket.getInetAddress().toString()
						.substring(1);
				logger.info("get a connection: " + ipaddress);
				try {
					userEntityDao.addUser(ipaddress);
					executor.execute(new WorkerTwo(clientSocket, logger,
							ipaddress));
				} catch (Exception e) {
					userEntityDao.deleteUser(ipaddress);
					logger.severe(e.getMessage());
					logger.info(ipaddress + " has closed");
					clientSocket.close();
				}
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		} finally {
			serverSocket.close();
			logger.info("Server Exit.");
		}
	}

	public void queryUsers() {
		STATUS = "QUERYUSERS";
		try {
			List<UserEntity> users = userEntityDao.queryUser();
			serverConsole.setText("\n\t");
			serverConsole.append("IP地址" + "\t\t");
			serverConsole.append("上线时间" + "\t\t\t");
			serverConsole.append("在线情况" + "\t\t");
			serverConsole.append("连接情况" + "\t\t");
			serverConsole.append("连接对象" + "\t\t");
			serverConsole.append("操作次数" + "\t");
			serverConsole.append("\n");
			if (users != null && !users.isEmpty()) {
				for (UserEntity user : users) {
					String online;
					String connect;
					serverConsole.append("\t");
					serverConsole.append(user.getIpaddress() + "\t");
					if (user.getOnline() == 1) {
						online = "在线";
					} else {
						online = "不在线";
					}
					if (user.getConnectstatus() == 1) {
						connect = "连通";
					} else {
						connect = "不连通";
					}
					serverConsole.append(String.valueOf(user.getCurrentlogin())
							+ "\t");
					serverConsole.append(online + "\t\t");
					serverConsole.append(connect + "\t\t");
					serverConsole.append(user.getConnectwith() + "\t\t");
					serverConsole.append(user.getOperationcount() + "\t");
					serverConsole.append("\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void queryUser() {
		STATUS = "QUERYUSER";
		String inputString = inputField.getText().trim();
		if (inputString != null && !inputString.equals("")) {
			serverConsole.setText("\n\t");
			serverConsole.append("IP地址" + "\t\t");
			serverConsole.append("上线时间" + "\t\t\t");
			serverConsole.append("在线情况" + "\t\t");
			serverConsole.append("连接情况" + "\t\t");
			serverConsole.append("连接对象" + "\t\t");
			serverConsole.append("操作次数" + "\t");
			serverConsole.append("\n");
			try {
				List<UserEntity> users = userEntityDao.queryUser(inputString);
				if (users != null && !users.isEmpty()) {
					for (UserEntity user : users) {
						String online;
						String connect;
						serverConsole.append("\t");
						serverConsole.append(user.getIpaddress() + "\t");
						if (user.getOnline() == 1) {
							online = "在线";
						} else {
							online = "不在线";
						}
						if (user.getConnectstatus() == 1) {
							connect = "连通";
						} else {
							connect = "不连通";
						}
						serverConsole.append(String.valueOf(user
								.getCurrentlogin()) + "\t");
						serverConsole.append(online + "\t\t");
						serverConsole.append(connect + "\t\t");
						serverConsole.append(user.getConnectwith() + "\t\t");
						serverConsole.append(user.getOperationcount() + "\t");
						serverConsole.append("\n");
					}
				} else if (users == null || users.isEmpty()) {
					serverConsole.setText("\n\t找不到该IP地址对应的设备");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			queryUsers();
		}
	}

	public class inputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			queryUser();
		}
	}

	public class MyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				queryUser();
			}
		}
	}

	public class refreshListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (STATUS) {
			case "INIT":
				queryUsers();
				break;
			case "QUERYUSERS":
				queryUsers();
				break;
			case "QUERYUSER":
				queryUser();
				break;
			}
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.init();
		try {
			server.go();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
