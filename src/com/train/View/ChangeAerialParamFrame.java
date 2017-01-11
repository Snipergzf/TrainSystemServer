package com.train.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import com.train.Util.CommonUtil;
import com.train.config.Config;
import com.train.dao.AerialEntityDao;
import com.train.model.AerialEntity;

public class ChangeAerialParamFrame extends JFrame {
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
	private JTextField f1;
	private JTextField f2;
	private JComboBox<String> f3;
	private JComboBox<String> f4;
	private JComboBox<String> f5;
	private JTextField f6;
	private JTextField f7;
	private Logger logger;
	private ChangeAerialParamFrame changeFrame;
	private ConfigAerialParamFrame lastFrame;
	private JButton comfirm;
	private JPanel jp3;
	private JPanel jp4;
	private JPanel jp5;
	private JPanel jp6;
	private JPanel jp7;
	private JPanel jp8;
	private JPanel jp9;
	private JPanel jp10;
	private JPanel jp11;
	private JPanel jp12;
	private JPanel jp13;
	private JPanel jp14;
	private JPanel jp15;
	private JPanel jp16;
	private JPanel jp17;
	private JPanel jp18;
	private GridBagLayout gb;
	private GridBagConstraints s1;
	private GridBagConstraints s2;
	private String aerialName = "";
	private String sateLongitude = "";
	private String aeWorkFre = "";
	private String aePolarization = "";
	private String reWorkStatus = "";
	private String reOffsetFre = "";
	private String reFre = "";
	private AerialEntity lastEntity;
	private AerialEntityDao aerialEntityDao;

	public ChangeAerialParamFrame(ConfigAerialParamFrame lastFrame,
			Logger logger, AerialEntity lastEntity) {
		super("添加天线参数");
		this.changeFrame = this;
		this.changeFrame.setResizable(false);
		this.changeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lastFrame = lastFrame;
		this.logger = logger;
		this.lastEntity = lastEntity;
		init();
		gb = new GridBagLayout();
		s1 = new GridBagConstraints();
		s2 = new GridBagConstraints();
		changeFrame.setLayout(gb);
		s1.fill = GridBagConstraints.BOTH;
		s2.fill = GridBagConstraints.BOTH;

		changeFrame.add(l1);
		changeFrame.add(jp12);
		changeFrame.add(f1);
		changeFrame.add(jp3);

		changeFrame.add(l2);
		changeFrame.add(jp13);
		changeFrame.add(f2);
		changeFrame.add(jp4);

		changeFrame.add(l3);
		changeFrame.add(jp14);
		changeFrame.add(f3);
		changeFrame.add(jp5);

		changeFrame.add(l4);
		changeFrame.add(jp15);
		changeFrame.add(f4);
		changeFrame.add(jp6);

		changeFrame.add(l5);
		changeFrame.add(jp16);
		changeFrame.add(f5);
		changeFrame.add(jp7);

		changeFrame.add(l6);
		changeFrame.add(jp17);
		changeFrame.add(f6);
		changeFrame.add(jp8);

		changeFrame.add(l7);
		changeFrame.add(jp18);
		changeFrame.add(f7);
		changeFrame.add(jp9);
		changeFrame.add(jp10);
		changeFrame.add(comfirm);
		changeFrame.add(jp11);

		s1.gridwidth = 1;
		s1.weightx = 0;
		s1.weighty = 0;
		s2.gridwidth = 0;
		s2.weightx = 0;
		s2.weighty = 0;

		gb.setConstraints(l1, s1);
		gb.setConstraints(f1, s2);
		gb.setConstraints(jp3, s2);
		gb.setConstraints(l2, s1);
		gb.setConstraints(f2, s2);
		gb.setConstraints(jp4, s2);
		gb.setConstraints(l3, s1);
		gb.setConstraints(f3, s2);
		gb.setConstraints(jp5, s2);
		gb.setConstraints(l4, s1);
		gb.setConstraints(f4, s2);
		gb.setConstraints(jp6, s2);
		gb.setConstraints(l5, s1);
		gb.setConstraints(f5, s2);
		gb.setConstraints(jp7, s2);
		gb.setConstraints(l6, s1);
		gb.setConstraints(f6, s2);
		gb.setConstraints(jp8, s2);
		gb.setConstraints(l7, s1);
		gb.setConstraints(f7, s2);
		gb.setConstraints(jp9, s2);
		gb.setConstraints(jp10, s1);
		gb.setConstraints(comfirm, s1);
		gb.setConstraints(jp11, s2);

		f1.setText(lastEntity.getAerialName());
		f2.setText(lastEntity.getSateLongitude());
		f3.setSelectedItem(lastEntity.getAeWorkFre());
		f4.setSelectedItem(lastEntity.getAePolarization());
		f5.setSelectedItem(lastEntity.getReWorkStatus());
		f6.setText(lastEntity.getReOffsetFre());
		f7.setText(lastEntity.getReFre());

		changeFrame.setSize(300, 300);
		// set frame to be created in center
		int[] xy = CommonUtil.getCenterXY(changeFrame);
		changeFrame.setLocation(xy[0], xy[1]);
		changeFrame.setVisible(true);
	}

	private void init() {
		aerialEntityDao = new AerialEntityDao();
		comfirm = new JButton("确认");
		comfirm.addActionListener(new changeListener());
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		jp8 = new JPanel();
		jp9 = new JPanel();
		jp10 = new JPanel();
		jp11 = new JPanel();
		jp12 = new JPanel();
		jp13 = new JPanel();
		jp14 = new JPanel();
		jp15 = new JPanel();
		jp16 = new JPanel();
		jp17 = new JPanel();
		jp18 = new JPanel();
		l1 = new JLabel("卫星参数名");
		l2 = new JLabel("卫星经度");
		l3 = new JLabel("天线工作频段");
		l4 = new JLabel("天线极化方式");
		l5 = new JLabel("接收机工作状态");
		l6 = new JLabel("接收机频偏");
		l7 = new JLabel("接收机频率");
		f1 = new JTextField();
		f2 = new JTextField();
		f3 = new JComboBox<String>(Config.workFreStrings);
		f4 = new JComboBox<String>(Config.polarizationStrings);
		f5 = new JComboBox<String>(Config.workStatusStrings);
		f6 = new JTextField();
		f7 = new JTextField();

	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			lastFrame.setEnabled(true);
			changeFrame.dispose();
		}
	}

	private class changeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				aerialName = f1.getText().trim();
				sateLongitude = f2.getText().trim();
				aeWorkFre = (String) f3.getSelectedItem();
				aePolarization = (String) f4.getSelectedItem();
				reWorkStatus = (String) f5.getSelectedItem();
				reOffsetFre = f6.getText().trim();
				reFre = f7.getText().trim();
				if (aerialName.equals("") || sateLongitude.equals("")
						|| aeWorkFre.equals("") || aePolarization.equals("")
						|| reWorkStatus.equals("") || reOffsetFre.equals("")
						|| reFre.equals("")) {
					JOptionPane.showMessageDialog(changeFrame, "请填写完整。",
							"信息不完整", JOptionPane.ERROR_MESSAGE);
				} else {
					AerialEntity change = new AerialEntity();
					change.setId(lastEntity.getId());
					change.setAerialName(aerialName);
					change.setSateLongitude(sateLongitude + "°");
					change.setAeWorkFre(aeWorkFre);
					change.setAePolarization(aePolarization);
					change.setReWorkStatus(reWorkStatus);
					change.setReOffsetFre(reOffsetFre);
					change.setReFre(reFre);
					if (aerialEntityDao.update(change)) {// 更新成功
						JOptionPane.showMessageDialog(changeFrame, "修改成功",
								"修改成功", JOptionPane.INFORMATION_MESSAGE);
						lastFrame.setEnabled(true);
						lastFrame.changeRow(change);
						changeFrame.dispose();
					} else {// 更新不成功
						JOptionPane.showMessageDialog(changeFrame,
								"请检查参数组名称是否重复，具体原因请查看系统日志。", "修改不成功",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
				JOptionPane.showMessageDialog(changeFrame,
						"请检查参数组名称是否重复，具体原因请查看系统日志。", "添加不成功",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
