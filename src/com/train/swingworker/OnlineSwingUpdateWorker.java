package com.train.swingworker;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.train.Util.CommonUtil;
import com.train.View.table.ServerMainTable;
import com.train.config.Config;
import com.train.dao.UserEntityDao;

public class OnlineSwingUpdateWorker extends
		SwingWorker<DefaultTableModel, Void> {

	private String[][] rowData;
	private DefaultTableModel tableModel;
	private ServerMainTable serverMainTable;
	private UserEntityDao userEntityDao;
	private String queryString;

	public OnlineSwingUpdateWorker(ServerMainTable serverMainTable,
			DefaultTableModel tableModel, UserEntityDao userEntityDao,
			String queryString) {
		this.serverMainTable = serverMainTable;
		this.tableModel = tableModel;
		this.userEntityDao = userEntityDao;
		this.queryString = queryString;
	}

	@Override
	protected DefaultTableModel doInBackground() throws Exception {
		rowData = CommonUtil.loadServerMainTableData(userEntityDao
				.queryUser(queryString));
		tableModel.setDataVector(rowData, Config.onLineTitles);
		return tableModel;
	}

	@Override
	protected void done() {
		serverMainTable.setModel(tableModel);
		serverMainTable.updateUI();
	}

}
