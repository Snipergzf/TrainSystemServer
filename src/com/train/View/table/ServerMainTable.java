package com.train.View.table;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.train.Style.MyTableCellRenderer;
import com.train.Util.CommonUtil;
import com.train.config.Config;
import com.train.dao.UserEntityDao;
import com.train.model.UserEntity;

public class ServerMainTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[][] rowData;
	private DefaultTableModel tableModel;
	private ServerMainTable serverMainTable;
	private UserEntityDao userEntityDao;
	
	public ServerMainTable(DefaultTableModel tbModel) {
		super(tbModel);
		this.tableModel = tbModel;
		this.serverMainTable = this;
		this.serverMainTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.serverMainTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
	}

	
	public ServerMainTable(DefaultTableModel tbModel, UserEntityDao userEntityDao) {
		super(tbModel);
		this.tableModel = tbModel;
		this.serverMainTable = this;
		this.userEntityDao = userEntityDao;
		this.serverMainTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.serverMainTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public void presentUserEntity(List<UserEntity> users) {
		rowData = CommonUtil.loadServerMainTableData(users);
		tableModel.setDataVector(rowData, Config.onLineTitles);
		serverMainTable.setModel(tableModel);
		serverMainTable.updateUI();
	}

	public void queryUsers() throws Exception {
		presentUserEntity(userEntityDao.queryUser());
	}
	
	public void queryUser(String input) throws Exception {
		if (input != null && !input.equals("")) {
			presentUserEntity(userEntityDao.queryUser(input));
		} else {
			queryUsers();
		}
	}
}