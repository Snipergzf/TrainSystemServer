package com.train.View.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConfigAeParamTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigAeParamTable(DefaultTableModel tbModel) {
		super(tbModel);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}