package com.train.config;

/**
 * @author gzf Configure file
 */
public class Config {
	public static final String URL = "jdbc:mysql://localhost:3306/trainsystem?verifyServerCertificate=false&useSSL=false";
	public static final String CharSet = "UTF-8";
	public static final String UserName = "root";
	public static final String Passwd = "gzfisme";
	public static final int ServerPort = 2333;
	public static final int CorePoolSize = 32; // 服务器线程池大小
	public static final int MaximumPoolSize = 32; // 服务器端最大的线程数，用于流量过大时应急线程最大数
	public static final int KeepAliveTime = 60 * 60 * 60; // 当客户端无数据向服务器发送超过KeepAliveTime的时间后该线程就会断掉，直到线程数为0
	public static final int CHECK = 1;
	public static final int UPDATE = 2;
	public static final int HEART = 4;
	
	// 1 视频编解码器
	// VC_interfaceType
	public static String[] InterSet_LineInterfaceValues = new String[] {
			"Rs-422接口", "V.35接口", "E1接口" };
	// VC_clock
	public static String[] InterSet_ClockValues = new String[] { "收时钟", "内时钟",
			"外时钟" };
	// VC_rate
	public static String[] InterSet_LineRateValues = new String[] { "64kb/s",
			"128kb/s", "192kb/s", "256kb/s", "320kb/s", "384kb/s", "512kb/s",
			"640kb/s", "768kb/s", "960kb/s", "1280kb/s", "1536kb/s", "1920kb/s" };
	// VC_codeFormat
	public static String[] EncodingFormatValues = new String[] { "H.261",
			"MPEG4" };
	// VC_imageFormat
	public static String[] PictureFormatValues = new String[] { "CIF", "QCIF" };
	// VC_frameRateValues
	public static String[] FrameRateValues = new String[] { "25帧/s", "12.5帧/s",
			"8.3帧/s", "6.25帧/s" };
	// VC_audioParameValues
	public static String[] ParameSet_AudioParameValues = new String[] { "无",
			"G.721 32k", "G.728 16k", "G.711 56k", "G.721+G.728" };
	// VC_synData
	public static String[] ParameSet_SynDataValues = new String[] { "无",
			"16kb/s", "32kb/s", "64kb/s", "128kb/s" };

	// 2 多功能复接器

	// MC_deviceId 范围为1-9 本身就是int型

	/* MC_groupRate */
	public static String[] GroupRateSettingValues = new String[] { "2048K",
			"1024K", "512K" };
	/* MC_groupInterface */
	public static String[] GroupInferSettingValues = new String[] { "G7.03",
			"RS422", "V.35" };
	/* MC_groupClock */
	public static String[] GroupClockSettingValues = new String[] { "收钟", "内钟",
			"外钟" };
	/* MC_videoRate */
	public static String[] SynRate = new String[] { "2.4K", "4.8K", "9.6K",
			"19.2K", "64K", "128K", "192K", "256K", "384K", "512k" };
	/* MC_videoSendClock */
	public static String[] SendClock = new String[] { "上沿", "下沿" };
	/* MC_videoReceivedClock */
	public static String[] ReceivedClock = new String[] { "上沿", "下沿" };

	// 3 调制解调器
	// MD_modemSendDataRate 范围为64-2048 本身为int型

	// MD_modemScrambleType
	public static String[] Scrambler = { "ON", "OFF" };
	// MD_modemDifferEncode
	public static String[] DifferEncoding = { "ON", "OFF" };
	// MD_modemRSCode
	public static String[] RSEncoding = { "ON", "OFF" };
	// MD_modemConvoluCode
	public static String[] ConvolutionEncoding = { "1/2", "2/3", "3/4", "7/8" };
	// MD_modemType
	public static String[] ModulationType = { "BPSK S", "BPSK NS", "QPSK S",
			"QPSK NS", "8PSK S", "8PSK NS" };
	// MD_modemCarrierOutput
	public static String[] CarrierWave = { "N", "Y" };
	// MD_modemSendCarrierFrequence 范围为52-88MHz、104-167MHz 本身为int型

	// MD_deModemReceiveDataRate 64-2048

	// MD_deModemDescrambleType
	public static String[] DemodulateScrambler = { "ON", "OFF" };
	// MD_deModemDifferEncode
	public static String[] DemodulateDifferEncoding = { "ON", "OFF" };
	// MD_deModemRSDecode
	public static String[] RSDecode = { "ON", "OFF" };
	// MD_deModemConvoluDecode
	public static String[] ConvolutionDecode = { "1/2", "2/3", "3/4", "7/8" };
	// MD_deModemType
	public static String[] DemodulationType = { "BPSK S", "BPSK NS", "QPSK S",
			"QPSK NS", "8PSK S", "8PSK NS" };
	// MD_deModemReceiveCarrierFrequence 范围为52-88MHz、104-167MHz 本身为int型

	// MD_frameType
	public static String[] FramingType = { "BYPS", "IBS", "IDR" };
	// MD_inCarrierOutput
	public static String[] InterfaceCarrierWaveOutput = { "N", "Y" };
	// MD_frameSClockPhase
	public static String[] ClockPhase = { "INV", "NOR" };
	// MD_frameSServiceInterface
	public static String[] ServiceInterface = { "AUDIO", "DATA" };
	// MD_frameSDataClock
	public static String[] DataClock = { "TTC", "TXC", "RXC" };

	// MD_deframeType
	public static String[] DeframingType = { "BYPS", "IBS", "IDR" };
	// MD_deframeSClockPhase
	public static String[] DeframClockPhase = { "INV", "NOR" };
	// MD_deframeRServiceInterface
	public static String[] DeframeServiceInterface = { "DATA", "AUDIO" };
	// MD_interfaceType
	public static String[] InterfaceType = { "RS422", "V.35", "G.703B",
			"G.703VB" };
	// MD_interfaceCodeType
	public static String[] InterfaceCodeType = { "AMI", "HDB3" };
}
