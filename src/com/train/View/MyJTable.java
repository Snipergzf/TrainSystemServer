package com.train.View;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyJTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyJTable(DefaultTableModel tbModel) {
		super(tbModel);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}