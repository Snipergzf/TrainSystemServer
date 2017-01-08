package com.train.View;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

public class ConfigAerialParam {
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	private JTextField f1;
	private JTextField f2;
	private JTextField f3;
	private JTextField f4;
	private JTextField f5;
	private JTextField f6;
	private Logger logger;
	private JFrame configFrame;
	private JButton comfirm;
	private JButton add;
	private JButton delete;
	private JButton load;
	private JPanel jp1;

	public ConfigAerialParam(Logger logger) {
		this.logger = logger;
		GridBagLayout gb = new GridBagLayout();
		configFrame.setLayout(gb);
		configFrame.add(load);
		configFrame.add(add);
		configFrame.add(delete);
		configFrame.add(jp1);

		init();
	}

	private void init() {
		configFrame = new JFrame("配置天线参数");
		comfirm = new JButton("确认");
		add = new JButton("添加");
		delete = new JButton("删除");
		load = new JButton("加载");
		jp1 = new JPanel();
		l1 = new JLabel("1");
		l2 = new JLabel("2");
		l3 = new JLabel("3");
		l4 = new JLabel("4");
		l5 = new JLabel("5");
		l6 = new JLabel("6");
		f1 = new JTextField();
		f2 = new JTextField();
		f3 = new JTextField();
		f4 = new JTextField();
		f5 = new JTextField();
		f6 = new JTextField();

	}

}
