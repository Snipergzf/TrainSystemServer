package com.train.main;

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
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import org.apache.log4j.Logger;
import com.train.Util.CommonUtil;
import com.train.View.ConfigAerialParamFrame;
import com.train.View.InsertConnectNodeFrame;
import com.train.communication.ComWorker;
import com.train.config.Config;
import com.train.dao.DataEntityDao;
import com.train.dao.UserEntityDao;
import com.train.logUtil.MyLog;
import com.train.model.DataEntity;
import com.train.model.UserEntity;

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
	private JTextArea serverConsole;
	private JScrollPane jScrollPane;
	private UserEntityDao userEntityDao;
	private DataEntityDao dataEntityDao;
	private static String STATUS = "INIT";
	private static String WINDOW = "ONLINE";
	private Thread refreshThread;
	
	public void init() throws Exception {
		initNetWork();
		newUtil();
		refreshThread = new Thread(new auto_refresh());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputHint.setText("   请输入查询IP  ");
		inputHint.setFont(new Font("宋体", Font.BOLD, 12));
		inputField.addKeyListener(new MyAdapter());
		inputField.setFont(new Font("宋体", Font.PLAIN, 12));
		queryButton.addActionListener(new inputListener());
		refreshButton.addActionListener(new refreshListener());
		onlineButton.addActionListener(new onLineListener());
		personalButton.addActionListener(new personalListener());
		connSelectButton.addActionListener(new connSelectListener());
		connSelectButton.setFocusable(false);
		aerialParamButton.addActionListener(new configParamListener());
		aerialParamButton.setFocusable(false);
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

		frame.setSize(1200, 900);
		//set frame to be created in center
		int[] xy = CommonUtil.getCenterXY(frame);    
        frame.setLocation(xy[0], xy[1]);
		frame.setVisible(true);
		queryUsers();
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
					MyLog myLog = new MyLog(ipaddress);
					executor.execute(new ComWorker(clientSocket, myLog.getLogger(),
							ipaddress));
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

	public void queryUsers() throws Exception {
		STATUS = "QUERYUSERS";
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
			presentUserEntity(users);
		}
	}

	public void queryUser() throws Exception {
		STATUS = "QUERYUSER";
		String inputString = inputField.getText().trim();
		if (WINDOW.equals("ONLINE")) {
			if (inputString != null && !inputString.equals("")) {
				serverConsole.setText("\n\t");
				serverConsole.append("IP地址" + "\t\t");
				serverConsole.append("上线时间" + "\t\t\t");
				serverConsole.append("在线情况" + "\t\t");
				serverConsole.append("连接情况" + "\t\t");
				serverConsole.append("连接对象" + "\t\t");
				serverConsole.append("操作次数" + "\t");
				serverConsole.append("\n");

				List<UserEntity> users = userEntityDao.queryUser(inputString);
				if (users != null && !users.isEmpty()) {
					presentUserEntity(users);
				} else if (users == null || users.isEmpty()) {
					serverConsole.setText("\n\t找不到该IP地址对应的设备");
				}
			} else {
				queryUsers();
			}
		}else {//PERSON
			if (inputString != null && !inputString.equals("")) {
				DataEntity query = dataEntityDao.queryEntity(inputString);
				if (query != null && query.getiPAddress() != null) {
					serverConsole.setText("\n");
					presentDataEntity(query);
				}else if(query == null || query.getiPAddress() == null) {
					serverConsole.setText("\n\t找不到该IP地址对应的设备");
				}
			}else {
				serverConsole.setText("\n");
			}
		}
	}
	
	public void presentUserEntity(List<UserEntity> users) {
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
	}
	
	public void presentDataEntity(DataEntity dataEntity){
		serverConsole.append("\t设备编号:\t"+dataEntity.getMC_deviceId()+"\n");
		serverConsole.append("\t群路速率:\t"+dataEntity.getMC_groupRate()+"\n");
		serverConsole.append("\t群路接口:\t"+dataEntity.getMC_groupInterface()+"\n");
		serverConsole.append("\t群路时钟:\t"+dataEntity.getMC_groupClock()+"\n");
		serverConsole.append("\t视频速率:\t"+dataEntity.getMC_videoRate()+"\n");
		serverConsole.append("\t视频发钟:\t"+dataEntity.getMC_videoSendClock()+"\n");
		serverConsole.append("\t视频收钟:\t"+dataEntity.getMC_videoReceivedClock()+"\n");
		serverConsole.append("\t线路接口:\t"+dataEntity.getMD_interfaceType()+"\n");
		serverConsole.append("\t时钟选择:\t"+dataEntity.getVC_clock()+"\n");
		serverConsole.append("\t线路速率:\t"+dataEntity.getVC_rate()+"\n");
		serverConsole.append("\t编码格式:\t"+dataEntity.getVC_codeFormat()+"\n");
		serverConsole.append("\t图像格式:\t"+dataEntity.getVC_imageFormat()+"\n");
		serverConsole.append("\t帧率:\t\t"+dataEntity.getVC_frameRateValues()+"\n");
		serverConsole.append("\t音频参数:\t"+dataEntity.getVC_audioParameValues()+"\n");
		serverConsole.append("\t同步数据:\t"+dataEntity.getVC_synData()+"\n");
		serverConsole.append("\t调制发数据速率:\t"+dataEntity.getMD_modemSendDataRate()+"\n");
		serverConsole.append("\t调制扰码方式:\t"+dataEntity.getMD_modemScrambleType()+"\n");
		serverConsole.append("\t调制差分编码:\t"+dataEntity.getMD_modemDifferEncode()+"\n");
		serverConsole.append("\t调制RS编码:\t"+dataEntity.getMD_modemRSCode()+"\n");
		serverConsole.append("\t调制卷积编码:\t"+dataEntity.getMD_modemConvoluCode()+"\n");
		serverConsole.append("\t调制方式:\t"+dataEntity.getMD_modemType()+"\n");
		serverConsole.append("\t载波输出:\t"+dataEntity.getMD_modemCarrierOutput()+"\n");
		serverConsole.append("\t调制发载波频率:\t"+dataEntity.getMD_modemSendCarrierFrequence()+"\n");
		serverConsole.append("\t解调收数据速率:\t"+dataEntity.getMD_deModemReceiveDataRate()+"\n");
		serverConsole.append("\t解调解扰方式:\t"+dataEntity.getMD_deModemDescrambleType()+"\n");
		serverConsole.append("\t解调差分译码:\t"+dataEntity.getMD_deModemDifferEncode()+"\n");
		serverConsole.append("\t解调RS译码:\t"+dataEntity.getMD_deModemRSDecode()+"\n");
		serverConsole.append("\t解调卷积译码:\t"+dataEntity.getMD_deModemConvoluDecode()+"\n");
		serverConsole.append("\t解调方式:\t"+dataEntity.getMD_deModemType()+"\n");
		serverConsole.append("\t解调收载波频率:\t"+dataEntity.getMD_deModemReceiveCarrierFrequence()+"\n");
		serverConsole.append("\t成帧类型:\t"+dataEntity.getMD_frameType()+"\n");
		serverConsole.append("\t载波参数:\t"+dataEntity.getMD_frameParam()+"\n");
		serverConsole.append("\t成帧发时钟相位:\t"+dataEntity.getMD_frameSClockPhase()+"\n");
		serverConsole.append("\t成帧发勤务接口:\t"+dataEntity.getMD_frameSServiceInterface()+"\n");
		serverConsole.append("\t成帧发数据时钟:\t"+dataEntity.getMD_frameSDataClock()+"\n");
		serverConsole.append("\t解帧类型:\t"+dataEntity.getMD_deframeType()+"\n");
		serverConsole.append("\t解帧发时钟相位:\t"+dataEntity.getMD_deframeSClockPhase()+"\n");
		serverConsole.append("\t解帧收勤务接口:\t"+dataEntity.getMD_deframeRServiceInterface()+"\n");
		serverConsole.append("\t接口类型:\t"+dataEntity.getMD_interfaceType()+"\n");
		serverConsole.append("\t接口码型:\t"+dataEntity.getMD_interfaceCodeType()+"\n");
	}
	

	public class inputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				queryUser();
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
		}
	}

	public class MyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					queryUser();
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
	
	public class connSelectListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new InsertConnectNodeFrame(frame, logger);
			frame.setEnabled(false);
		}
	}
	
	public class configParamListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new ConfigAerialParamFrame(frame, logger);
			frame.setEnabled(false);
		}
		
	}
	
	public void refresh() {
		try {
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
		} catch (Exception e2) {
			logger.error(e2.getMessage());
		}
	}
	
	public class auto_refresh implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(15*1000);
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
				queryUser();
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
				queryUser();
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
