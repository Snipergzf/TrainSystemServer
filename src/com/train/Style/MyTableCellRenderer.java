package com.train.Style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if ((row & 1) == 0) {
			setBackground(new Color(0xC6C3C3));
		} else {
			setBackground(new Color(0xFFFFFF));
		}
		//设置行高
		table.setRowHeight(35);
		//设置字体
		table.setFont(new Font("宋体", Font.BOLD, 16));
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}

	public MyTableCellRenderer() {
		setHorizontalAlignment(CENTER);
	}
}
