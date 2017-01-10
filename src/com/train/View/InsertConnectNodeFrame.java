package com.train.View;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import com.train.dao.AerialEntityDao;
import com.train.dao.IpEntityDao;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class InsertConnectNodeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private InsertConnectNodeFrame connFrame;
	private JButton comfirm;
	private JPanel left;
	private JPanel right;
	private IpEntityDao ipEntityDao;
	private AerialEntityDao aerialEntityDao;
	private HashMap<JComboBox<String>, Integer> search;
	private ArrayList<Integer> InitData;
	private Logger logger;
	private GridBagConstraints s;
	private GridBagLayout gb;
	private JFrame mainFrame;

	public InsertConnectNodeFrame(JFrame mainFrame, Logger logger) {
		super("配置连接对象");
		this.connFrame = this;
		this.connFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame = mainFrame;
		this.logger = logger;
		init();
		gb = new GridBagLayout();
		s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;
		connFrame.setLayout(gb);

		addComponent(l1, f1, la1, l2, f2, la17, ae1);
		addComponent(l3, f3, la2, l4, f4, la18, ae2);
		addComponent(l5, f5, la3, l6, f6, la19, ae3);
		addComponent(l7, f7, la4, l8, f8, la20, ae4);
		addComponent(l9, f9, la5, l10, f10, la21, ae5);
		addComponent(l11, f11, la6, l12, f12, la22, ae6);
		addComponent(l13, f13, la7, l14, f14, la23, ae7);
		addComponent(l15, f15, la8, l16, f16, la24, ae8);
		addComponent(l17, f17, la9, l18, f18, la25, ae9);
		addComponent(l19, f19, la10, l20, f20, la26, ae10);
		addComponent(l21, f21, la11, l22, f22, la27, ae11);
		addComponent(l23, f23, la12, l24, f24, la28, ae12);
		addComponent(l25, f25, la13, l26, f26, la29, ae13);
		addComponent(l27, f27, la14, l28, f28, la30, ae14);
		addComponent(l29, f29, la15, l30, f30, la31, ae15);
		addComponent(l31, f31, la16, l32, f32, la32, ae16);
		connFrame.add(left);
		connFrame.add(comfirm);
		connFrame.add(right);

		setConstrains(l1, f1, la1, l2, f2, la17, ae1);
		setConstrains(l3, f3, la2, l4, f4, la18, ae2);
		setConstrains(l5, f5, la3, l6, f6, la19, ae3);
		setConstrains(l7, f7, la4, l8, f8, la20, ae4);
		setConstrains(l9, f9, la5, l10, f10, la21, ae5);
		setConstrains(l11, f11, la6, l12, f12, la22, ae6);
		setConstrains(l13, f13, la7, l14, f14, la23, ae7);
		setConstrains(l15, f15, la8, l16, f16, la24, ae8);
		setConstrains(l17, f17, la9, l18, f18, la25, ae9);
		setConstrains(l19, f19, la10, l20, f20, la26, ae10);
		setConstrains(l21, f21, la11, l22, f22, la27, ae11);
		setConstrains(l23, f23, la12, l24, f24, la28, ae12);
		setConstrains(l25, f25, la13, l26, f26, la29, ae13);
		setConstrains(l27, f27, la14, l28, f28, la30, ae14);
		setConstrains(l29, f29, la15, l30, f30, la31, ae15);
		setConstrains(l31, f31, la16, l32, f32, la32, ae16);

		s.gridwidth = 4;
		gb.setConstraints(left, s);
		s.gridwidth = 1;
		gb.setConstraints(comfirm, s);
		s.gridwidth = 0;
		gb.setConstraints(right, s);

		connFrame.setSize(600, 600);
		connFrame.setVisible(true);
	}

	public void init() {
		try {
			search = new HashMap<>();
			String[] selectItems = { "无", "1号主机", "2号主机", "3号主机", "4号主机",
					"5号主机", "6号主机", "7号主机", "8号主机", "9号主机", "10号主机", "11号主机",
					"12号主机", "13号主机", "14号主机", "15号主机", "16号主机", "17号主机",
					"18号主机", "19号主机", "20号主机", "21号主机", "22号主机", "23号主机",
					"24号主机", "25号主机", "26号主机", "27号主机", "28号主机", "29号主机",
					"30号主机", "31号主机", "32号主机" };
			connFrame.setResizable(false);
			comfirm = new JButton("更新配对");
			comfirm.addActionListener(new UpdateListener());
			left = new JPanel();
			right = new JPanel();
			ipEntityDao = new IpEntityDao();
			aerialEntityDao = new AerialEntityDao();
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
			ArrayList<String> aerialBox_tmp = new ArrayList<>();
			aerialBox_tmp.add("无");
			ArrayList<String> res = aerialEntityDao.query();
			if (res != null && !res.isEmpty()) {
				for (String string : res) {
					aerialBox_tmp.add(string);
				}
			}
			String[] aerialBox = new String[aerialBox_tmp.size()];
			aerialBox = aerialBox_tmp.toArray(aerialBox);
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
			InitData = ipEntityDao.queryConn();
			if (InitData != null) {
				int size = InitData.size();
				if (size >= 2) {
					initView(f1, f2, InitData.get(0), InitData.get(1), ae1);
				}
				if (size >= 4) {
					initView(f3, f4, InitData.get(2), InitData.get(3), ae2);
				}
				if (size >= 6) {
					initView(f5, f6, InitData.get(4), InitData.get(5), ae3);
				}
				if (size >= 8) {
					initView(f7, f8, InitData.get(6), InitData.get(7), ae4);
				}
				if (size >= 10) {
					initView(f9, f10, InitData.get(8), InitData.get(9), ae5);
				}
				if (size >= 12) {
					initView(f11, f12, InitData.get(10), InitData.get(11), ae6);
				}
				if (size >= 14) {
					initView(f13, f14, InitData.get(12), InitData.get(13), ae7);
				}
				if (size >= 16) {
					initView(f15, f16, InitData.get(14), InitData.get(15), ae8);
				}
				if (size >= 18) {
					initView(f17, f18, InitData.get(16), InitData.get(17), ae9);
				}
				if (size >= 20) {
					initView(f19, f20, InitData.get(18), InitData.get(19), ae10);
				}
				if (size >= 22) {
					initView(f21, f22, InitData.get(20), InitData.get(21), ae11);
				}
				if (size >= 24) {
					initView(f23, f24, InitData.get(22), InitData.get(23), ae12);
				}
				if (size >= 26) {
					initView(f25, f26, InitData.get(24), InitData.get(25), ae13);
				}
				if (size >= 28) {
					initView(f27, f28, InitData.get(26), InitData.get(27), ae14);
				}
				if (size >= 30) {
					initView(f29, f30, InitData.get(28), InitData.get(29), ae15);
				}
				if (size >= 32) {
					initView(f31, f32, InitData.get(30), InitData.get(31), ae16);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			mainFrame.setEnabled(true);
			this.connFrame.dispose();
		}
	}

	private class SelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> tmp = (JComboBox<String>) e.getSource();
			int index = tmp.getSelectedIndex();
			if (!search.containsValue(index)
					|| (search.containsKey(tmp) && search.get(tmp) == index)) {
				search.put(tmp, index);
			} else if (search.containsValue(index)) {
				int res = JOptionPane.showConfirmDialog(null, "选择冲突,是否强制改变",
						"选择冲突", JOptionPane.YES_NO_OPTION);
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
			boolean result = updateData();
			if (result) {// 更新成功
				JOptionPane.showMessageDialog(connFrame, "更新成功", "更新成功",
						JOptionPane.INFORMATION_MESSAGE);
				mainFrame.setEnabled(true);
				connFrame.dispose();
			} else {// 更新不成功
				JOptionPane.showMessageDialog(connFrame, "更新不成功，请查看日志检查具体错误",
						"更新失败", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean updateData() {
		boolean ret = true;
		try {
			ipEntityDao.truncateConn(); // 清空所有连接对象，严格地讲这个操作应该与后面的更新操作一起写成一个过程，保持原子性，可以留作优化；
			ipEntityDao.truncateAerialNum(); // 清空所有连接对对应的卫星参数序号，严格地讲这个操作应该与后面的更新操作一起写成一个过程，保持原子性，可以留作优化；
			int tmp1 = f1.getSelectedIndex();
			int tmp2 = f2.getSelectedIndex();
			String selectItem = (String) ae1.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f3.getSelectedIndex();
			tmp2 = f4.getSelectedIndex();
			selectItem = (String) ae2.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f5.getSelectedIndex();
			tmp2 = f6.getSelectedIndex();
			selectItem = (String) ae3.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f7.getSelectedIndex();
			tmp2 = f8.getSelectedIndex();
			selectItem = (String) ae4.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f9.getSelectedIndex();
			tmp2 = f10.getSelectedIndex();
			selectItem = (String) ae5.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f11.getSelectedIndex();
			tmp2 = f12.getSelectedIndex();
			selectItem = (String) ae6.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f13.getSelectedIndex();
			tmp2 = f14.getSelectedIndex();
			selectItem = (String) ae7.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f15.getSelectedIndex();
			tmp2 = f16.getSelectedIndex();
			selectItem = (String) ae8.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f17.getSelectedIndex();
			tmp2 = f18.getSelectedIndex();
			selectItem = (String) ae9.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f19.getSelectedIndex();
			tmp2 = f20.getSelectedIndex();
			selectItem = (String) ae10.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f21.getSelectedIndex();
			tmp2 = f22.getSelectedIndex();
			selectItem = (String) ae11.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f23.getSelectedIndex();
			tmp2 = f24.getSelectedIndex();
			selectItem = (String) ae12.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f25.getSelectedIndex();
			tmp2 = f26.getSelectedIndex();
			selectItem = (String) ae13.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f27.getSelectedIndex();
			tmp2 = f28.getSelectedIndex();
			selectItem = (String) ae14.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f29.getSelectedIndex();
			tmp2 = f30.getSelectedIndex();
			selectItem = (String) ae15.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);

			tmp1 = f31.getSelectedIndex();
			tmp2 = f32.getSelectedIndex();
			selectItem = (String) ae16.getSelectedItem();
			updateConn(tmp1, tmp2, selectItem);
		} catch (Exception e) {
			ret = false;
			logger.error(e.getMessage());
		}
		return ret;
	}

	private void initView(JComboBox<String> conn1, JComboBox<String> conn2,
			int index1, int index2, JComboBox<String> aerial)
			throws SQLException {
		conn1.setSelectedIndex(index1);
		conn2.setSelectedIndex(index2);
		aerial.setSelectedItem(ipEntityDao.queryAerialName(index1));
	}

	private void updateConn(int tmp1, int tmp2, String selectItem)
			throws Exception {
		if (tmp1 != 0 && tmp2 != 0) {
			ipEntityDao.insertConnectNode(tmp1, tmp2);
			if (!selectItem.equals("无")) {
				int tmp3 = aerialEntityDao.queryId(selectItem);
				if (tmp3 != -1) {
					ipEntityDao.updateAerialNum(tmp1, tmp3);
					ipEntityDao.updateAerialNum(tmp2, tmp3);
				}
			}
		}
	}

	private void addComponent(JLabel comp1, JComboBox<String> comp2,
			JLabel comp3, JLabel comp4, JComboBox<String> comp5, JLabel comp6,
			JComboBox<String> comp7) {
		connFrame.add(comp1);
		connFrame.add(comp2);
		connFrame.add(comp3);
		connFrame.add(comp4);
		connFrame.add(comp5);
		connFrame.add(comp6);
		connFrame.add(comp7);
	}

	public void setConstrains(JLabel comp1, JComboBox<String> comp2,
			JLabel comp3, JLabel comp4, JComboBox<String> comp5, JLabel comp6,
			JComboBox<String> comp7) {
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		gb.setConstraints(comp1, s);
		gb.setConstraints(comp2, s);
		gb.setConstraints(comp3, s);
		gb.setConstraints(comp4, s);
		gb.setConstraints(comp5, s);
		gb.setConstraints(comp6, s);
		s.gridwidth = 0;
		gb.setConstraints(comp7, s);
	}
}
