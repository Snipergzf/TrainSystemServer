package com.train.View.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.train.Style.MyTableCellRenderer;
import com.train.Util.CommonUtil;
import com.train.config.Config;
import com.train.dao.DataEntityDao;
import com.train.model.DataEntity;

public class ServerPersonTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[][] rowData;
	private DefaultTableModel tableModel;
	private ServerPersonTable serverPersonTable;
	private DataEntityDao dataEntityDao;
	
	public ServerPersonTable(DefaultTableModel tbModel){
		super(tbModel);
		this.tableModel = tbModel;
		this.serverPersonTable = this;
		this.serverPersonTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.serverPersonTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
	}
	
	public ServerPersonTable(DefaultTableModel tbModel, DataEntityDao dataEntityDao) {
		super(tbModel);
		this.tableModel = tbModel;
		this.serverPersonTable = this;
		this.dataEntityDao = dataEntityDao;
		this.serverPersonTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.serverPersonTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public void presentDataEntity(DataEntity dataEntity) {
		rowData = CommonUtil.loadServerPersonTableData(dataEntity);
		tableModel.setDataVector(rowData, Config.personTitle);
		serverPersonTable.setModel(tableModel);
		serverPersonTable.updateUI();
	}
	
	public void queryUser(String input) throws Exception {
		if (input != null && !input.equals("")) {
			presentDataEntity(dataEntityDao.queryEntity(input));
		} else {
			return;
		}
	}
}