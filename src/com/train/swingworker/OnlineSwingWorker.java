package com.train.swingworker;

import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.train.Util.CommonUtil;
import com.train.View.table.ServerMainTable;
import com.train.config.Config;
import com.train.dao.UserEntityDao;

public class OnlineSwingWorker extends SwingWorker<DefaultTableModel, Void>{
	private String[][] rowData;
	private DefaultTableModel tableModel;
	private ServerMainTable serverMainTable;
	private UserEntityDao userEntityDao;
	private String queryString;
	private JScrollPane jScrollPane;
	
	public OnlineSwingWorker(JScrollPane jScrollPane,
			UserEntityDao userEntityDao, String queryString) {
		this.jScrollPane = jScrollPane;
		this.userEntityDao = userEntityDao;
		this.queryString = queryString;
	}

	@Override
	protected DefaultTableModel doInBackground() throws Exception {
		rowData = CommonUtil.loadServerMainTableData(userEntityDao.queryUser(queryString));
		tableModel = new DefaultTableModel(rowData, Config.onLineTitles);
		return tableModel;
	}

	@Override
	protected void done() {
		serverMainTable = new ServerMainTable(tableModel);
		serverMainTable.setEnabled(false);
		jScrollPane.setViewportView(serverMainTable);
	}
	
}
