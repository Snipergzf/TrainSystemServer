package com.train.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import com.train.Util.CommonUtil;
import com.train.config.Config;
import com.train.dao.AerialEntityDao;
import com.train.model.AerialEntity;


public class ConfigAerialParamFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConfigAerialParamFrame configFrame;
	private JFrame mainFrame;
	private Logger logger;
	private JButton add;
	private JButton delete;
	private JButton change;
	private MyJTable table;
	private DefaultTableModel tableModel;
	private ListSelectionModel listSelectionModel;
	private AerialEntityDao aerialEntityDao;
	private ArrayList<AerialEntity> rowDataArrayList;
	private String[][] rowData;
	private JScrollPane tablePane;
	private JSplitPane splitPane;
	private JPanel tableContainer;
	private JPanel bottomHalf;
	private int selectRowIndex = -1;

	public ConfigAerialParamFrame(JFrame mainFrame, Logger logger) {
		super("配置天线参数");
		this.configFrame = this;
		this.configFrame.setResizable(false);
		this.configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame = mainFrame;
		this.logger = logger;
		init();
		configFrame.setSize(800, 600);
		int[] xy = CommonUtil.getCenterXY(configFrame);
		configFrame.setLocation(xy[0], xy[1]);
		configFrame.setVisible(true);
	}

	private void init() {
		try {
			aerialEntityDao = new AerialEntityDao();
			rowDataArrayList = aerialEntityDao.queryEntityList();
			loadData(rowDataArrayList);
			// 创建各个空间对象
			add = new JButton("添加");
			delete = new JButton("删除");
			change = new JButton("修改");
			tableModel = new DefaultTableModel(rowData, Config.paramNames);
			table = new MyJTable(tableModel);
			listSelectionModel = table.getSelectionModel();
			listSelectionModel
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setDefaultRenderer(Object.class, new TableCellStyle());

			tablePane = new JScrollPane(table);
			splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			add(splitPane, BorderLayout.CENTER);

			// add tablePane to tableContainer
			tableContainer = new JPanel(new GridLayout(1, 1));
			tableContainer.setBorder(BorderFactory.createTitledBorder("卫星参数组"));
			tableContainer.add(tablePane);
			tableContainer.setMinimumSize(new Dimension(450, 500));
			tableContainer.setPreferredSize(new Dimension(450, 500));

			// add button to bottomHalf Panel
			bottomHalf = new JPanel();
			bottomHalf.add(add);
			bottomHalf.add(change);
			bottomHalf.add(delete);
			bottomHalf.setPreferredSize(new Dimension(450, 100));

			// add tableContainer and bottomHalf Panel to splitPane
			splitPane.add(tableContainer);
			splitPane.add(bottomHalf);

			// add splitPane to frame
			configFrame.add(splitPane);

			add.addActionListener(new AddParamsListener());
			delete.addActionListener(new DeleteParamsListener());
			change.addActionListener(new ChangeParamsListener());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	private void loadData(ArrayList<AerialEntity> rowDataArrayList) {
		if (rowDataArrayList != null && !rowDataArrayList.isEmpty()) {
			rowData = new String[rowDataArrayList.size()][8];
			for (int i = 0; i < rowDataArrayList.size(); i++) {
				String[] tmp = new String[8];
				tmp[0] = Integer.toString(i + 1);
				tmp[1] = rowDataArrayList.get(i).getAerialName();
				tmp[2] = rowDataArrayList.get(i).getSateLongitude();
				tmp[3] = rowDataArrayList.get(i).getAeWorkFre();
				tmp[4] = rowDataArrayList.get(i).getAePolarization();
				tmp[5] = rowDataArrayList.get(i).getReWorkStatus();
				tmp[6] = rowDataArrayList.get(i).getReOffsetFre();
				tmp[7] = rowDataArrayList.get(i).getReFre();
				rowData[i] = tmp;
			}
		} else {
			rowData = new String[0][8];
		}
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			mainFrame.setEnabled(true);
			configFrame.dispose();
		}
	}

	private class AddParamsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddAerialParamFrame(configFrame, logger);
			configFrame.setEnabled(false);
		}
	}

	private class DeleteParamsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				selectRowIndex = table.getSelectedRow();
				if (selectRowIndex == -1) {
					JOptionPane.showMessageDialog(configFrame, "请选择要删除行",
							"删除不成功", JOptionPane.ERROR_MESSAGE);
				} else {
					String aerialName = (String) tableModel.getValueAt(
							selectRowIndex, 1);
					aerialEntityDao.delete(aerialName);
					tableModel.removeRow(selectRowIndex);
				}
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
	}

	private class ChangeParamsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				selectRowIndex = table.getSelectedRow();
				if (selectRowIndex == -1) {
					JOptionPane.showMessageDialog(configFrame, "请选择要修改行",
							"修改不成功", JOptionPane.ERROR_MESSAGE);
				} else {
					String aerialName = (String) tableModel.getValueAt(
							selectRowIndex, 1); 
					new ChangeAerialParamFrame(configFrame, logger, aerialEntityDao.query(aerialName));
					configFrame.setEnabled(false);
				}
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
	}
	
	public void addRow(AerialEntity aerialEntity) {
		String[] rowValues = new String[8];
		rowValues[0] = Integer.toString(tableModel.getRowCount() + 1);
		rowValues[1] = aerialEntity.getAerialName();
		rowValues[2] = aerialEntity.getSateLongitude();
		rowValues[3] = aerialEntity.getAeWorkFre();
		rowValues[4] = aerialEntity.getAePolarization();
		rowValues[5] = aerialEntity.getReWorkStatus();
		rowValues[6] = aerialEntity.getReOffsetFre();
		rowValues[7] = aerialEntity.getReFre();
		tableModel.addRow(rowValues);// 将响应后的事件进行处理
	}

	public void changeRow(AerialEntity aerialEntity) {
		if (selectRowIndex != -1) {
			tableModel.setValueAt(aerialEntity.getAerialName(), selectRowIndex,
					1);
			tableModel.setValueAt(aerialEntity.getSateLongitude(),
					selectRowIndex, 2);
			tableModel.setValueAt(aerialEntity.getAeWorkFre(), selectRowIndex,
					3);
			tableModel.setValueAt(aerialEntity.getAePolarization(),
					selectRowIndex, 4);
			tableModel.setValueAt(aerialEntity.getReWorkStatus(),
					selectRowIndex, 5);
			tableModel.setValueAt(aerialEntity.getReOffsetFre(),
					selectRowIndex, 6);
			tableModel.setValueAt(aerialEntity.getReFre(), selectRowIndex, 7);
		}
	}
}