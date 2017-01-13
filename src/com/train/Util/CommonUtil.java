package com.train.Util;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;

import com.train.model.UserEntity;

public class CommonUtil {

	// This method is used to calculate the coordinates of the inputFrame which
	// needed to be create in center
	public static int[] getCenterXY(JFrame inputFrame) {
		int[] ret = new int[2];
		Dimension windowSize = inputFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		ret[0] = centerPoint.x - windowSize.width / 2;
		ret[1] = centerPoint.y - windowSize.height / 2;
		return ret;
	}

	// This method is used to check whether a input string is made up by digit
	public static boolean checkDigit(String input) {
		for (char c : input.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	public static String[][] loadServerMainTableData(List<UserEntity> rowDataArrayList) {
		String[][] rowData;
		if (rowDataArrayList != null && !rowDataArrayList.isEmpty()) {
			rowData = new String[rowDataArrayList.size()][7];
			for (int i = 0; i < rowDataArrayList.size(); i++) {
				String[] tmp = new String[7];
				tmp[0] = Integer.toString(i + 1);
				tmp[1] = rowDataArrayList.get(i).getIpaddress();
				tmp[2] = rowDataArrayList.get(i).getCurrentlogin().toString().substring(0, 19);
				tmp[3] = "在线";
				if (rowDataArrayList.get(i).getOnline() == 0) {
					tmp[3] = "不在线";
				}
				tmp[4] = "未连通";
				if (rowDataArrayList.get(i).getConnectstatus() == 1) {
					tmp[4] = "已连通";
				}
				tmp[5] = rowDataArrayList.get(i).getConnectwith();
				tmp[6] = String.valueOf(rowDataArrayList.get(i).getOperationcount());
				rowData[i] = tmp;
			}
		} else {
			rowData = new String[0][7];
		}
		return rowData;
	}
}