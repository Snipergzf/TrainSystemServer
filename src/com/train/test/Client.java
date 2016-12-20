package com.train.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.json.JSONObject;

import com.train.config.Config;


public class Client {
	private Socket socket;
	private JFrame frame;
	private JTextField chatInput;
	private JTextArea chatRoom;
	private JButton send;
	private PrintWriter pWriter;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	
	public void go() {
		frame = new JFrame("Client GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		chatInput = new JTextField(20);
		chatRoom = new JTextArea(6,20);
		chatRoom.setLineWrap(true);
		chatRoom.setWrapStyleWord(true);
		chatRoom.setEditable(false);
		
		JScrollPane jScrollPane = new JScrollPane(chatRoom);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		send = new JButton("send");
		send.addActionListener(new sendListener());
		mainPanel.add(chatInput);
		mainPanel.add(send);
		setUpNetWorking();
		
		Thread thread = new Thread(new getInput());
		thread.start();
		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.getContentPane().add(jScrollPane, BorderLayout.NORTH);
		frame.setSize(300,400);
		frame.setVisible(true);
	}
	public void setUpNetWorking() {
		try {
			socket = new Socket("127.0.0.1", Config.ServerPort);
			pWriter = new PrintWriter(socket.getOutputStream());
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			bufferedReader = new BufferedReader(inputStreamReader);
			System.out.println("networking established");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public class getInput implements Runnable {
		@Override
		public void run() {
			String message = "";
			try{
				while ((message = bufferedReader.readLine())!= null) {
					if(!message.equals("")){
						JSONObject inJsonObject = new JSONObject(message);
						if (chatRoom.getText().trim().equals("")){
							chatRoom.setText(inJsonObject.getString("result"));
						}else {
							chatRoom.setText(chatRoom.getText()+"\n"+inJsonObject.getString("result"));
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public class sendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String input = chatInput.getText().trim();
			if(!input.equals("")){
				try {
					JSONObject outJsonObject = new JSONObject();
					outJsonObject.put("name", input);
					outJsonObject.put("phone", 12345465);
					pWriter.println(outJsonObject.toString());
					pWriter.flush();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				chatInput.setText("");
				chatInput.requestFocus();
			}
		}
	}
	public static void main(String[] args) {
		Client client = new Client();
		client.go();
	}
	
	
}











