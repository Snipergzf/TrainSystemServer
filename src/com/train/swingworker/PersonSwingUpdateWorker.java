package com.train.swingworker;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.train.Util.CommonUtil;
import com.train.View.table.ServerPersonTable;
import com.train.config.Config;
import com.train.dao.DataEntityDao;

public class PersonSwingUpdateWorker extends
		SwingWorker<DefaultTableModel, Void> {

	private String[][] rowData;
	private DefaultTableModel tableModel;
	private ServerPersonTable serverPersonTable;
	private DataEntityDao dataEntityDao;
	private String queryString;

	public PersonSwingUpdateWorker(ServerPersonTable serverPersonTable,
			DefaultTableModel tableModel, DataEntityDao dataEntityDao,
			String queryString) {
		this.serverPersonTable = serverPersonTable;
		this.tableModel = tableModel;
		this.dataEntityDao = dataEntityDao;
		this.queryString = queryString;
	}

	@Override
	protected DefaultTableModel doInBackground() throws Exception {
		rowData = CommonUtil.loadServerPersonTableData(dataEntityDao
				.queryEntity(queryString));
		tableModel.setDataVector(rowData, Config.personTitle);
		return tableModel;
	}

	@Override
	protected void done() {
		serverPersonTable.setModel(tableModel);
		serverPersonTable.updateUI();
	}
}
