package com.train.Util;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;

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
}