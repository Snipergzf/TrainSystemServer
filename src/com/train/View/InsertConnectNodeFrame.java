package com.train.View;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import com.train.dao.IpEntityDao;

import sun.text.normalizer.VersionInfo;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class InsertConnectNodeFrame {
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	private JLabel l7;
	private JLabel l8;
	private JLabel l9;
	private JLabel l10;
	private JLabel l11;
	private JLabel l12;
	private JLabel l13;
	private JLabel l14;
	private JLabel l15;
	private JLabel l16;
	private JLabel l17;
	private JLabel l18;
	private JLabel l19;
	private JLabel l20;
	private JLabel l21;
	private JLabel l22;
	private JLabel l23;
	private JLabel l24;
	private JLabel l25;
	private JLabel l26;
	private JLabel l27;
	private JLabel l28;
	private JLabel l29;
	private JLabel l30;
	private JLabel l31;
	private JLabel l32;

	private JLabel la1;
	private JLabel la2;
	private JLabel la3;
	private JLabel la4;
	private JLabel la5;
	private JLabel la6;
	private JLabel la7;
	private JLabel la8;
	private JLabel la9;
	private JLabel la10;
	private JLabel la11;
	private JLabel la12;
	private JLabel la13;
	private JLabel la14;
	private JLabel la15;
	private JLabel la16;
	private JLabel la17;
	private JLabel la18;
	private JLabel la19;
	private JLabel la20;
	private JLabel la21;
	private JLabel la22;
	private JLabel la23;
	private JLabel la24;
	private JLabel la25;
	private JLabel la26;
	private JLabel la27;
	private JLabel la28;
	private JLabel la29;
	private JLabel la30;
	private JLabel la31;
	private JLabel la32;

	private JComboBox<String> f1;
	private JComboBox<String> f2;
	private JComboBox<String> f3;
	private JComboBox<String> f4;
	private JComboBox<String> f5;
	private JComboBox<String> f6;
	private JComboBox<String> f7;
	private JComboBox<String> f8;
	private JComboBox<String> f9;
	private JComboBox<String> f10;
	private JComboBox<String> f11;
	private JComboBox<String> f12;
	private JComboBox<String> f13;
	private JComboBox<String> f14;
	private JComboBox<String> f15;
	private JComboBox<String> f16;
	private JComboBox<String> f17;
	private JComboBox<String> f18;
	private JComboBox<String> f19;
	private JComboBox<String> f20;
	private JComboBox<String> f21;
	private JComboBox<String> f22;
	private JComboBox<String> f23;
	private JComboBox<String> f24;
	private JComboBox<String> f25;
	private JComboBox<String> f26;
	private JComboBox<String> f27;
	private JComboBox<String> f28;
	private JComboBox<String> f29;
	private JComboBox<String> f30;
	private JComboBox<String> f31;
	private JComboBox<String> f32;
	
	private JComboBox<String> ae1;
	private JComboBox<String> ae2;
	private JComboBox<String> ae3;
	private JComboBox<String> ae4;
	private JComboBox<String> ae5;
	private JComboBox<String> ae6;
	private JComboBox<String> ae7;
	private JComboBox<String> ae8;
	private JComboBox<String> ae9;
	private JComboBox<String> ae10;
	private JComboBox<String> ae11;
	private JComboBox<String> ae12;
	private JComboBox<String> ae13;
	private JComboBox<String> ae14;
	private JComboBox<String> ae15;
	private JComboBox<String> ae16;
	private JFrame connFrame;
	private JButton comfirm;
	private JPanel left;
	private JPanel right;
	private IpEntityDao ipEntityDao;
	private HashMap<JComboBox<String>, Integer> search;
	private ArrayList<Integer> InitData;
	private Logger logger;

	public InsertConnectNodeFrame(Logger logger) {
		this.logger = logger;
		init();
		GridBagLayout gb = new GridBagLayout();
		connFrame.setLayout(gb);
		addComponent(l1, f1, la1, l2, f2, la17, ae1);
		addComponent(l3, f3, la2, l4, f4, la18, ae2);
		addComponent(l5, f5, la3, l6, f6, la19, ae3);
		addComponent(l7, f7, la4, l8, f8, la20, ae4);
		addComponent(l9, f9, la6, l10, f10, la21, ae5);
		
		connFrame.add(l11);
		connFrame.add(f11);
		connFrame.add(la6);
		connFrame.add(l12);
		connFrame.add(f12);
		connFrame.add(la22);
		connFrame.add(l13);
		connFrame.add(f13);
		connFrame.add(la7);
		connFrame.add(l14);
		connFrame.add(f14);
		connFrame.add(la23);
		connFrame.add(l15);
		connFrame.add(f15);
		connFrame.add(la8);
		connFrame.add(l16);
		connFrame.add(f16);
		connFrame.add(la24);
		connFrame.add(l17);
		connFrame.add(f17);
		connFrame.add(la9);
		connFrame.add(l18);
		connFrame.add(f18);
		connFrame.add(la25);
		connFrame.add(l19);
		connFrame.add(f19);
		connFrame.add(la10);
		connFrame.add(l20);
		connFrame.add(f20);
		connFrame.add(la26);
		connFrame.add(l21);
		connFrame.add(f21);
		connFrame.add(la11);
		connFrame.add(l22);
		connFrame.add(f22);
		connFrame.add(la27);
		connFrame.add(l23);
		connFrame.add(f23);
		connFrame.add(la12);
		connFrame.add(l24);
		connFrame.add(f24);
		connFrame.add(la28);
		connFrame.add(l25);
		connFrame.add(f25);
		connFrame.add(la13);
		connFrame.add(l26);
		connFrame.add(f26);
		connFrame.add(la29);
		connFrame.add(l27);
		connFrame.add(f27);
		connFrame.add(la14);
		connFrame.add(l28);
		connFrame.add(f28);
		connFrame.add(la30);
		connFrame.add(l29);
		connFrame.add(f29);
		connFrame.add(la15);
		connFrame.add(l30);
		connFrame.add(f30);
		connFrame.add(la31);
		connFrame.add(l31);
		connFrame.add(f31);
		connFrame.add(la16);
		connFrame.add(l32);
		connFrame.add(f32);
		connFrame.add(la32);
		connFrame.add(left);
		connFrame.add(comfirm);
		connFrame.add(right);

		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;

		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(l1, s);
		gb.setConstraints(f1, s);
		gb.setConstraints(l2, s);
		gb.setConstraints(f2, s);
		s.gridwidth = 0;
		gb.setConstraints(la17, s);

		s.gridwidth = 1;
		gb.setConstraints(l3, s);
		gb.setConstraints(f3, s);
		gb.setConstraints(l4, s);
		// s.gridwidth = 0;
		gb.setConstraints(f4, s);
		s.gridwidth = 0;
		gb.setConstraints(la18, s);

		s.gridwidth = 1;
		gb.setConstraints(l5, s);
		gb.setConstraints(f5, s);
		gb.setConstraints(l6, s);
		s.gridwidth = 0;
		gb.setConstraints(f6, s);

		s.gridwidth = 1;
		gb.setConstraints(l7, s);
		gb.setConstraints(f7, s);
		gb.setConstraints(l8, s);
		s.gridwidth = 0;
		gb.setConstraints(f8, s);

		s.gridwidth = 1;
		gb.setConstraints(l9, s);
		gb.setConstraints(f9, s);
		gb.setConstraints(l10, s);
		s.gridwidth = 0;
		gb.setConstraints(f10, s);

		s.gridwidth = 1;
		gb.setConstraints(l11, s);
		gb.setConstraints(f11, s);
		gb.setConstraints(l12, s);
		s.gridwidth = 0;
		gb.setConstraints(f12, s);

		s.gridwidth = 1;
		gb.setConstraints(l13, s);
		gb.setConstraints(f13, s);
		gb.setConstraints(l14, s);
		s.gridwidth = 0;
		gb.setConstraints(f14, s);

		s.gridwidth = 1;
		gb.setConstraints(l15, s);
		gb.setConstraints(f15, s);
		gb.setConstraints(l16, s);
		s.gridwidth = 0;
		gb.setConstraints(f16, s);

		s.gridwidth = 1;
		gb.setConstraints(l17, s);
		gb.setConstraints(f17, s);
		gb.setConstraints(l18, s);
		s.gridwidth = 0;
		gb.setConstraints(f18, s);

		s.gridwidth = 1;
		gb.setConstraints(l19, s);
		gb.setConstraints(f19, s);
		gb.setConstraints(l20, s);
		s.gridwidth = 0;
		gb.setConstraints(f20, s);

		s.gridwidth = 1;
		gb.setConstraints(l21, s);
		gb.setConstraints(f21, s);
		gb.setConstraints(l22, s);
		s.gridwidth = 0;
		gb.setConstraints(f22, s);

		s.gridwidth = 1;
		gb.setConstraints(l23, s);
		gb.setConstraints(f23, s);
		gb.setConstraints(l24, s);
		s.gridwidth = 0;
		gb.setConstraints(f24, s);

		s.gridwidth = 1;
		gb.setConstraints(l25, s);
		gb.setConstraints(f25, s);
		gb.setConstraints(l26, s);
		s.gridwidth = 0;
		gb.setConstraints(f26, s);

		s.gridwidth = 1;
		gb.setConstraints(l27, s);
		gb.setConstraints(f27, s);
		gb.setConstraints(l28, s);
		s.gridwidth = 0;
		gb.setConstraints(f28, s);

		s.gridwidth = 1;
		gb.setConstraints(l29, s);
		gb.setConstraints(f29, s);
		gb.setConstraints(l30, s);
		s.gridwidth = 0;
		gb.setConstraints(f30, s);

		s.gridwidth = 1;
		gb.setConstraints(l31, s);
		gb.setConstraints(f31, s);
		gb.setConstraints(l32, s);
		s.gridwidth = 0;
		gb.setConstraints(f32, s);

		s.gridwidth = 2;
		gb.setConstraints(left, s);
		s.gridwidth = 1;
		gb.setConstraints(comfirm, s);
		s.gridwidth = 0;
		gb.setConstraints(right, s);

		connFrame.setSize(600, 600);
		connFrame.setVisible(true);
	}

	public void init() {
		search = new HashMap<>();
		String[] selectItems = { "无", "1号主机", "2号主机", "3号主机", "4号主机", "5号主机", "6号主机", "7号主机", "8号主机", "9号主机", "10号主机",
				"11号主机", "12号主机", "13号主机", "14号主机", "15号主机", "16号主机", "17号主机", "18号主机", "19号主机", "20号主机", "21号主机",
				"22号主机", "23号主机", "24号主机", "25号主机", "26号主机", "27号主机", "28号主机", "29号主机", "30号主机", "31号主机", "32号主机" };
		connFrame = new JFrame("配置连接对象");
		comfirm = new JButton("更新配对");
		comfirm.addActionListener(new UpdateListener());
		left = new JPanel();
		right = new JPanel();
		ipEntityDao = new IpEntityDao();
		l1 = new JLabel("对象:");
		l2 = new JLabel("对象:");
		l3 = new JLabel("对象:");
		l4 = new JLabel("对象:");
		l5 = new JLabel("对象:");
		l6 = new JLabel("对象:");
		l7 = new JLabel("对象:");
		l8 = new JLabel("对象:");
		l9 = new JLabel("对象:");
		l10 = new JLabel("对象:");
		l11 = new JLabel("对象:");
		l12 = new JLabel("对象:");
		l13 = new JLabel("对象:");
		l14 = new JLabel("对象:");
		l15 = new JLabel("对象:");
		l16 = new JLabel("对象:");
		l17 = new JLabel("对象:");
		l18 = new JLabel("对象:");
		l19 = new JLabel("对象:");
		l20 = new JLabel("对象:");
		l21 = new JLabel("对象:");
		l22 = new JLabel("对象:");
		l23 = new JLabel("对象:");
		l24 = new JLabel("对象:");
		l25 = new JLabel("对象:");
		l26 = new JLabel("对象:");
		l27 = new JLabel("对象:");
		l28 = new JLabel("对象:");
		l29 = new JLabel("对象:");
		l30 = new JLabel("对象:");
		l31 = new JLabel("对象:");
		l32 = new JLabel("对象:");

		SelectListener selectListener = new SelectListener();
		f1 = new JComboBox<>(selectItems);
		f2 = new JComboBox<>(selectItems);
		f3 = new JComboBox<>(selectItems);
		f4 = new JComboBox<>(selectItems);
		f5 = new JComboBox<>(selectItems);
		f6 = new JComboBox<>(selectItems);
		f7 = new JComboBox<>(selectItems);
		f8 = new JComboBox<>(selectItems);
		f9 = new JComboBox<>(selectItems);
		f10 = new JComboBox<>(selectItems);
		f11 = new JComboBox<>(selectItems);
		f12 = new JComboBox<>(selectItems);
		f13 = new JComboBox<>(selectItems);
		f14 = new JComboBox<>(selectItems);
		f15 = new JComboBox<>(selectItems);
		f16 = new JComboBox<>(selectItems);
		f17 = new JComboBox<>(selectItems);
		f18 = new JComboBox<>(selectItems);
		f19 = new JComboBox<>(selectItems);
		f20 = new JComboBox<>(selectItems);
		f21 = new JComboBox<>(selectItems);
		f22 = new JComboBox<>(selectItems);
		f23 = new JComboBox<>(selectItems);
		f24 = new JComboBox<>(selectItems);
		f25 = new JComboBox<>(selectItems);
		f26 = new JComboBox<>(selectItems);
		f27 = new JComboBox<>(selectItems);
		f28 = new JComboBox<>(selectItems);
		f29 = new JComboBox<>(selectItems);
		f30 = new JComboBox<>(selectItems);
		f31 = new JComboBox<>(selectItems);
		f32 = new JComboBox<>(selectItems);

		f1.addActionListener(selectListener);
		f2.addActionListener(selectListener);
		f3.addActionListener(selectListener);
		f4.addActionListener(selectListener);
		f5.addActionListener(selectListener);
		f6.addActionListener(selectListener);
		f7.addActionListener(selectListener);
		f8.addActionListener(selectListener);
		f9.addActionListener(selectListener);
		f10.addActionListener(selectListener);
		f11.addActionListener(selectListener);
		f12.addActionListener(selectListener);
		f13.addActionListener(selectListener);
		f14.addActionListener(selectListener);
		f15.addActionListener(selectListener);
		f16.addActionListener(selectListener);
		f17.addActionListener(selectListener);
		f18.addActionListener(selectListener);
		f19.addActionListener(selectListener);
		f20.addActionListener(selectListener);
		f21.addActionListener(selectListener);
		f22.addActionListener(selectListener);
		f23.addActionListener(selectListener);
		f24.addActionListener(selectListener);
		f25.addActionListener(selectListener);
		f26.addActionListener(selectListener);
		f27.addActionListener(selectListener);
		f28.addActionListener(selectListener);
		f29.addActionListener(selectListener);
		f30.addActionListener(selectListener);
		f31.addActionListener(selectListener);
		f32.addActionListener(selectListener);

		la1 = new JLabel(" 连接 ");
		la2 = new JLabel(" 连接 ");
		la3 = new JLabel(" 连接 ");
		la4 = new JLabel(" 连接 ");
		la5 = new JLabel(" 连接 ");
		la6 = new JLabel(" 连接 ");
		la7 = new JLabel(" 连接 ");
		la8 = new JLabel(" 连接 ");
		la9 = new JLabel(" 连接 ");
		la10 = new JLabel(" 连接 ");
		la11 = new JLabel(" 连接 ");
		la12 = new JLabel(" 连接 ");
		la13 = new JLabel(" 连接 ");
		la14 = new JLabel(" 连接 ");
		la15 = new JLabel(" 连接 ");
		la16 = new JLabel(" 连接 ");
		la17 = new JLabel("天线参数选择：");
		la18 = new JLabel("天线参数选择：");
		la19 = new JLabel("天线参数选择：");
		la20 = new JLabel("天线参数选择：");
		la21 = new JLabel("天线参数选择：");
		la22 = new JLabel("天线参数选择：");
		la23 = new JLabel("天线参数选择：");
		la24 = new JLabel("天线参数选择：");
		la25 = new JLabel("天线参数选择：");
		la26 = new JLabel("天线参数选择：");
		la27 = new JLabel("天线参数选择：");
		la28 = new JLabel("天线参数选择：");
		la29 = new JLabel("天线参数选择：");
		la30 = new JLabel("天线参数选择：");
		la31 = new JLabel("天线参数选择：");
		la32 = new JLabel("天线参数选择：");
		String[] aerialBox = {"无"};
		//查询天线参数
		ae1 = new JComboBox<>(aerialBox);
		ae2 = new JComboBox<>(aerialBox);
		ae3 = new JComboBox<>(aerialBox);
		ae4 = new JComboBox<>(aerialBox);
		ae5 = new JComboBox<>(aerialBox);
		ae6 = new JComboBox<>(aerialBox);
		ae7 = new JComboBox<>(aerialBox);
		ae8 = new JComboBox<>(aerialBox);
		ae9 = new JComboBox<>(aerialBox);
		ae10 = new JComboBox<>(aerialBox);
		ae11 = new JComboBox<>(aerialBox);
		ae12 = new JComboBox<>(aerialBox);
		ae13 = new JComboBox<>(aerialBox);
		ae14 = new JComboBox<>(aerialBox);
		ae15 = new JComboBox<>(aerialBox);
		ae16 = new JComboBox<>(aerialBox);
		
		
		
		try {
			InitData = ipEntityDao.queryConn();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (InitData != null) {
			int size = InitData.size();
			if (size >= 2) {
				f1.setSelectedIndex(InitData.get(0));
				f2.setSelectedIndex(InitData.get(1));
			}
			if (size >= 4) {
				f3.setSelectedIndex(InitData.get(2));
				f4.setSelectedIndex(InitData.get(3));
			}
			if (size >= 6) {
				f5.setSelectedIndex(InitData.get(4));
				f6.setSelectedIndex(InitData.get(5));
			}
			if (size >= 8) {
				f7.setSelectedIndex(InitData.get(6));
				f8.setSelectedIndex(InitData.get(7));
			}
			if (size >= 10) {
				f9.setSelectedIndex(InitData.get(8));
				f10.setSelectedIndex(InitData.get(9));
			}
			if (size >= 12) {
				f11.setSelectedIndex(InitData.get(10));
				f12.setSelectedIndex(InitData.get(11));
			}
			if (size >= 14) {
				f13.setSelectedIndex(InitData.get(12));
				f14.setSelectedIndex(InitData.get(13));
			}
			if (size >= 16) {
				f15.setSelectedIndex(InitData.get(14));
				f16.setSelectedIndex(InitData.get(15));
			}
			if (size >= 18) {
				f17.setSelectedIndex(InitData.get(16));
				f18.setSelectedIndex(InitData.get(17));
			}
			if (size >= 20) {
				f19.setSelectedIndex(InitData.get(18));
				f20.setSelectedIndex(InitData.get(19));
			}
			if (size >= 22) {
				f21.setSelectedIndex(InitData.get(20));
				f22.setSelectedIndex(InitData.get(21));
			}
			if (size >= 24) {
				f23.setSelectedIndex(InitData.get(22));
				f24.setSelectedIndex(InitData.get(23));
			}
			if (size >= 26) {
				f25.setSelectedIndex(InitData.get(24));
				f26.setSelectedIndex(InitData.get(25));
			}
			if (size >= 28) {
				f27.setSelectedIndex(InitData.get(26));
				f28.setSelectedIndex(InitData.get(27));
			}
			if (size >= 30) {
				f29.setSelectedIndex(InitData.get(28));
				f30.setSelectedIndex(InitData.get(29));
			}
			if (size >= 32) {
				f31.setSelectedIndex(InitData.get(30));
				f32.setSelectedIndex(InitData.get(31));
			}
		}
	}

	private class SelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> tmp = (JComboBox<String>) e.getSource();
			int index = tmp.getSelectedIndex();
			if (!search.containsValue(index) || (search.containsKey(tmp) && search.get(tmp) == index)) {
				search.put(tmp, index);
			} else if (search.containsValue(index)) {
				int res = JOptionPane.showConfirmDialog(null, "选择冲突,是否强制改变", "选择冲突", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					for (JComboBox<String> jComboBox : search.keySet()) {
						if (search.get(jComboBox) == index) {
							search.put(jComboBox, 0);
							jComboBox.setSelectedIndex(0);
							break;
						}
					}
					search.put(tmp, index);
				} else {
					tmp.setSelectedIndex(0);
				}
			}
		}
	}

	private class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = updateData2();
			if (result) {// 更新成功
				JOptionPane.showMessageDialog(connFrame, "更新成功", "更新成功", JOptionPane.INFORMATION_MESSAGE);
				connFrame.dispose();
			} else {// 更新不成功
				JOptionPane.showMessageDialog(connFrame, "更新不成功，请查看日志检查具体错误", "更新失败", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean updateData2() {
		boolean ret = true;
		try {
			ipEntityDao.truncateConn();
			if (f1.getSelectedIndex() != 0 && f2.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f1.getSelectedIndex(), f2.getSelectedIndex());
			}
			if (f3.getSelectedIndex() != 0 && f4.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f3.getSelectedIndex(), f4.getSelectedIndex());
			}
			if (f5.getSelectedIndex() != 0 && f6.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f5.getSelectedIndex(), f6.getSelectedIndex());
			}
			if (f7.getSelectedIndex() != 0 && f8.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f7.getSelectedIndex(), f8.getSelectedIndex());
			}
			if (f9.getSelectedIndex() != 0 && f10.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f9.getSelectedIndex(), f10.getSelectedIndex());
			}
			if (f11.getSelectedIndex() != 0 && f12.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f11.getSelectedIndex(), f12.getSelectedIndex());
			}
			if (f13.getSelectedIndex() != 0 && f14.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f13.getSelectedIndex(), f14.getSelectedIndex());
			}
			if (f15.getSelectedIndex() != 0 && f16.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f15.getSelectedIndex(), f16.getSelectedIndex());
			}
			if (f17.getSelectedIndex() != 0 && f18.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f17.getSelectedIndex(), f18.getSelectedIndex());
			}
			if (f19.getSelectedIndex() != 0 && f20.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f19.getSelectedIndex(), f20.getSelectedIndex());
			}
			if (f21.getSelectedIndex() != 0 && f22.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f21.getSelectedIndex(), f22.getSelectedIndex());
			}
			if (f23.getSelectedIndex() != 0 && f24.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f23.getSelectedIndex(), f24.getSelectedIndex());
			}
			if (f25.getSelectedIndex() != 0 && f26.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f25.getSelectedIndex(), f26.getSelectedIndex());
			}
			if (f27.getSelectedIndex() != 0 && f28.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f27.getSelectedIndex(), f28.getSelectedIndex());
			}
			if (f29.getSelectedIndex() != 0 && f30.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f29.getSelectedIndex(), f30.getSelectedIndex());
			}
			if (f31.getSelectedIndex() != 0 && f32.getSelectedIndex() != 0) {
				ipEntityDao.insertConnectNode(f31.getSelectedIndex(), f32.getSelectedIndex());
			}
		} catch (Exception e) {
			ret = false;
			logger.error(e.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private void addComponent(JLabel comp1, JComboBox<String> comp2, JLabel comp3, JLabel comp4, JComboBox<String> comp5, JLabel comp6, JComboBox<String> comp7) {
		connFrame.add(comp1);
		connFrame.add(comp2);
		connFrame.add(comp3);
		connFrame.add(comp4);
		connFrame.add(comp5);
		connFrame.add(comp6);
		connFrame.add(comp7);
	}

}
