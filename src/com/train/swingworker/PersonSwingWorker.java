package com.train.swingworker;

import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.train.Util.CommonUtil;
import com.train.View.table.ServerPersonTable;
import com.train.config.Config;
import com.train.dao.DataEntityDao;

public class PersonSwingWorker extends SwingWorker<DefaultTableModel, Void>{
	
	private JScrollPane jScrollPane;
	private DataEntityDao dataEntityDao;
	private String queryString;
	private ServerPersonTable serverPersonTable;
	private String[][] rowData;
	private DefaultTableModel tableModel;
	
	public PersonSwingWorker(JScrollPane jScrollPane,
			DataEntityDao dataEntityDao, String queryString) {
		this.jScrollPane = jScrollPane;
		this.dataEntityDao = dataEntityDao;
		this.queryString = queryString;
	}

	@Override
	protected DefaultTableModel doInBackground() throws Exception {
		rowData = CommonUtil.loadServerPersonTableData(dataEntityDao.queryEntity(queryString));
		tableModel = new DefaultTableModel(rowData, Config.personTitle);
		return tableModel;
	}

	@Override
	protected void done() {
		serverPersonTable = new ServerPersonTable(tableModel);
		serverPersonTable.setEnabled(false);
		jScrollPane.setViewportView(serverPersonTable);
	}

}
