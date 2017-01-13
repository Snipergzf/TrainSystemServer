package com.train.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import com.train.Util.CommonUtil;
import com.train.View.ConfigAerialParamFrame;
import com.train.View.InsertConnectNodeFrame;
import com.train.View.table.ServerMainTable;
import com.train.View.table.ServerPersonTable;
import com.train.communication.ComWorker;
import com.train.config.Config;
import com.train.dao.DataEntityDao;
import com.train.dao.UserEntityDao;
import com.train.logUtil.MyLog;

/**
 * @author gzf
 * 
 */

public class Server {
	private static Logger logger;
	private ServerSocket serverSocket;
	private ThreadPoolExecutor executor;
	private JFrame frame;
	private JPanel j1;
	private JButton personalButton;
	private JButton onlineButton;
	private JButton connSelectButton;
	private JButton aerialParamButton;
	private JLabel inputHint;
	private JTextField inputField;
	private JButton queryButton;
	private JButton refreshButton;
	private JScrollPane jScrollPane;
	private UserEntityDao userEntityDao;
	private DataEntityDao dataEntityDao;
	private static String WINDOW = "ONLINE";
	private Thread refreshThread;
	private ServerMainTable serverMainTable;
	private ServerPersonTable serverPersonTable;
	private String[][] rowData;
	private DefaultTableModel tableModel;
	private Font textFont;

	public void init() throws Exception {
		initNetWork();
		newUtil();
		textFont = new Font("宋体", Font.BOLD, 12);
		refreshThread = new Thread(new auto_refresh());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputHint.setText("   请输入查询IP  ");
		inputHint.setFont(textFont);
		inputField.addKeyListener(new MyAdapter());
		inputField.setFont(textFont);
		queryButton.setFont(textFont);
		queryButton.addActionListener(new inputListener());
		refreshButton.setFont(textFont);
		refreshButton.addActionListener(new refreshListener());
		onlineButton.setFont(textFont);
		onlineButton.addActionListener(new onLineListener());
		personalButton.setFont(textFont);
		personalButton.addActionListener(new personalListener());
		connSelectButton.setFont(textFont);
		connSelectButton.addActionListener(new connSelectListener());
		connSelectButton.setFocusable(false);
		aerialParamButton.setFont(textFont);
		aerialParamButton.addActionListener(new configParamListener());
		aerialParamButton.setFocusable(false);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		GridBagLayout gb = new GridBagLayout();
		frame.setLayout(gb);
		frame.add(onlineButton);
		frame.add(personalButton);
		frame.add(connSelectButton);
		frame.add(aerialParamButton);
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
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(connSelectButton, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(aerialParamButton, s);
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

		frame.setSize(1400, 900);
		frame.setMinimumSize(new Dimension(600, 450));
		// set frame to be created in center
		int[] xy = CommonUtil.getCenterXY(frame);
		frame.setLocation(xy[0], xy[1]);
		frame.setVisible(true);
		refreshThread.start();
	}

	public void initNetWork() {
		try {
			serverSocket = new ServerSocket(Config.ServerPort);
			executor = new ThreadPoolExecutor(Config.CorePoolSize,
					Config.MaximumPoolSize, Config.KeepAliveTime,
					TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(12));
			executor.allowCoreThreadTimeOut(true);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void newUtil() {

		try {
			userEntityDao = new UserEntityDao();
			dataEntityDao = new DataEntityDao();
			frame = new JFrame("训练系统后台监控");
			j1 = new JPanel();
			onlineButton = new JButton("在线情况");
			personalButton = new JButton("个人操作");
			connSelectButton = new JButton("配对选择");
			aerialParamButton = new JButton("天线参数设置");
			inputHint = new JLabel();
			inputField = new JTextField();
			queryButton = new JButton("查询");
			refreshButton = new JButton("刷新");
			// 初始化主界面onlineTable
			rowData = CommonUtil.loadServerMainTableData(userEntityDao
					.queryUser());
			tableModel = new DefaultTableModel(rowData, Config.onLineTitles);
			serverMainTable = new ServerMainTable(tableModel, userEntityDao);
			// 初始化主界面personTable,但不立马显示
			serverPersonTable = new ServerPersonTable(new DefaultTableModel(
					new String[0][4], Config.personTitle), dataEntityDao);
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(serverMainTable);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
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
					MyLog myLog = new MyLog(ipaddress);
					executor.execute(new ComWorker(clientSocket, myLog
							.getLogger(), ipaddress));
				} catch (Exception e) {
					userEntityDao.deleteUser(ipaddress);
					logger.error(e.getMessage());
					logger.info(ipaddress + " has closed");
					clientSocket.close();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			serverSocket.close();
			logger.info("Server Exit.");
		}
	}

	// 查询按钮的监听器
	public class inputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (WINDOW.equals("ONLINE")) {
					serverMainTable.queryUser(inputField.getText().trim());
				} else {// PERSON
					serverPersonTable.queryUser(inputField.getText().trim());
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
		}
	}

	//查询输入框监听器
	public class MyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					if (WINDOW.equals("ONLINE")) {
						serverMainTable.queryUser(inputField.getText().trim());
					} else {// PERSON
						serverPersonTable.queryUser(inputField.getText().trim());
					}
				} catch (Exception e1) {
					logger.error(e1.getMessage());
				}
			}
		}
	}

	public class refreshListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			refresh();
		}
	}

	public class connSelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new InsertConnectNodeFrame(frame, logger);
			frame.setEnabled(false);
		}
	}

	public class configParamListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new ConfigAerialParamFrame(frame, logger);
			frame.setEnabled(false);
		}

	}

	public void refresh() {
		try {
			if (WINDOW.equals("ONLINE")) {
				serverMainTable.queryUser(inputField.getText().trim());
			} else {// PERSON
				serverPersonTable.queryUser(inputField.getText().trim());
			}
		} catch (Exception e2) {
			logger.error(e2.getMessage());
		}
	}

	public class auto_refresh implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(10 * 1000);
					refresh();
				}
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}
	}

	public class personalListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				WINDOW = "PERSON";
				serverPersonTable.queryUser(inputField.getText().trim());
				jScrollPane.setViewportView(serverPersonTable);
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
		}
	}

	public class onLineListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				WINDOW = "ONLINE";
				serverMainTable.queryUser(inputField.getText().trim());
				jScrollPane.setViewportView(serverMainTable);
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}

		}
	}

	public static void main(String[] args) {
		logger = Logger.getLogger(Server.class);
		logger.info("Server initializing...");
		Server server = new Server();
		try {
			server.init();
			server.go();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
