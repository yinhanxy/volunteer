package com.topstar.volunteer.util;

import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;
import java.util.Properties;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.entity.SigarInfoEntity;
import com.topstar.volunteer.exception.TPSException;

/**
 * sigar获取信息工具类
 */
public class SigarUtils {

	private static final Logger logger = LoggerFactory.getLogger(SigarUtils.class);

	private static Sigar sigar = new Sigar();

	/**
	 * 获取sigar实体
	 */
	public static Sigar getInstance() {
		if (null == sigar) {
			synchronized (sigar) {
				if (null == sigar) {
					sigar = new Sigar();
				}
			}
		}
		return sigar;
	}

	/**
	 * 获取服务器的信息列表
	 * @return 服务器信息列表
	 * @throws TPSException
	 * @throws UnknownHostException
	 * @throws SigarException
	 */
	public static List<SigarInfoEntity> getSysInfo() throws TPSException {
		List<SigarInfoEntity> sysInfoList = new ArrayList<SigarInfoEntity>();
		try {
			// 系统配置属性
			Properties sysProps = System.getProperties();
			// java对ip封装的对象
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			// 物理内存信息  
			Mem mem = sigar.getMem(); 
			//磁盘信息
			FileSystem fslist[]= getInstance().getFileSystemList();
			long totalDisk = 0;
			for (int i = 0; i < fslist.length; i++) {
				FileSystem fs = fslist[i];
				FileSystemUsage usage = null;
				switch (fs.getType()) {
				case 2: // TYPE_LOCAL_DISK : 本地硬盘
					usage = getInstance().getFileSystemUsage(fs.getDirName());
					totalDisk += usage.getTotal();
					break;
				default:
					break;
				}
			}
			//磁盘总量
			double TotalDisk = (double)totalDisk / 1024L / 1024 ;
			// 内存总量  
		    double TotalMem = (double)mem.getTotal() / 1024L / 1024 / 1024;
		    DecimalFormat df = new DecimalFormat("#.00");
		    String Total=df.format(TotalMem)+"GB";
		    String TotalDis=df.format(TotalDisk)+"GB";
			String cpuNum = String.valueOf(getInstance().getCpuInfoList().length);
			String sysInfo=sysProps.getProperty("os.name")+" "+sysProps.getProperty("os.version")+" "+sysProps.getProperty("os.arch");
			String userInfo=sysProps.getProperty("user.name")+"("+addr.getHostAddress()+")";
			sysInfoList.add(new SigarInfoEntity(userInfo, "主机名"));// 用户的账户名称
			sysInfoList.add(new SigarInfoEntity(sysInfo, "操作系统"));// 操作系统的信息
			sysInfoList.add(new SigarInfoEntity(Total, "内存总量"));// 内存总量
			sysInfoList.add(new SigarInfoEntity(TotalDis, "磁盘总量"));// 磁盘总量
			sysInfoList.add(new SigarInfoEntity(cpuNum, "CPU个数"));
			sysInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.version"), "java运行时环境版本"));
			sysInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.home"), "java的安装路径"));
			sysInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.io.tmpdir"), "临时文件路径"));
		
		} catch (UnknownHostException e) {
			throw new TPSException("java对ip封装的对象不正确", e);
		} catch (SigarException e) {
			throw new TPSException("获取CPU信息不正确", e);
		}
		return sysInfoList;
	}

	/**
	 * 获取cpu使用率
	 * @return SigarInfoEntity类型CPU使用率
	 * @throws TPSException
	 */
	public static SigarInfoEntity getCpuPerc() throws TPSException {
		CpuPerc cpu;
		SigarInfoEntity sf = null;
		try {
			cpu = getInstance().getCpuPerc();
			sf = new SigarInfoEntity(String.valueOf(cpu.getCombined() * 100), "CPU使用率");
		} catch (SigarException e) {
			throw new TPSException("获取CPU使用率不正确", e);
		}
		return sf;
	}

	/**
	 * 获取cpu使用率
	 * @return int类型CPU使用率
	 * @throws TPSException
	 */
	public static int getCpuPercNum() throws TPSException {
		CpuPerc cpu;
		int sf = 0;
		try {
			cpu = getInstance().getCpuPerc();
			sf = Integer.valueOf((int) (cpu.getCombined() * 100));
		} catch (SigarException e) {
			throw new TPSException("获取CPU使用率不正确", e);
		}
		return sf;
	}

	/**
	 * 获取内存使用率
	 * @return SigarInfoEntity类型内存使用率
	 * @throws TPSException
	 */
	public static SigarInfoEntity getMemoryPerc() throws TPSException {
		Mem mem;
		SigarInfoEntity sf = null;
		try {
			mem = getInstance().getMem();

			long total = mem.getTotal();
			long use = mem.getUsed();
			double memoryPerc = (double) use / total;
			sf = new SigarInfoEntity(String.valueOf(memoryPerc * 100), "内存使用率");
		} catch (SigarException e) {
			throw new TPSException("获取内存使用率不正确", e);
		}
		return sf;
	}

	/**
	 * 获取内存使用率
	 * @return int类型内存使用率
	 * @throws TPSException
	 */
	public static int getMemoryPercNum() throws TPSException {
		Mem mem;
		int sf = 0;
		try {
			mem = getInstance().getMem();

			long total = mem.getTotal();
			long use = mem.getUsed();
			double memoryPerc = (double) use / total;
			sf = Integer.valueOf((int) (memoryPerc * 100));
		} catch (SigarException e) {
			throw new TPSException("获取内存使用率不正确", e);
		}
		return sf;
	}

	/**
	 * 获取磁盘使用率
	 * @return SigarInfoEntity类型磁盘使用率
	 * @throws TPSException
	 */
	public static SigarInfoEntity getFilePerc() throws TPSException {
		FileSystem fslist[];
		SigarInfoEntity sf = null;
		try {
			fslist = getInstance().getFileSystemList();
			long total = 0;
			long use = 0;
			for (int i = 0; i < fslist.length; i++) {
				FileSystem fs = fslist[i];
				FileSystemUsage usage = null;
				switch (fs.getType()) {
				case 2: // TYPE_LOCAL_DISK : 本地硬盘
					usage = getInstance().getFileSystemUsage(fs.getDirName());
					total += usage.getTotal();
					use += usage.getUsed();
					break;
				default:
					break;
				}
			}
			double filePerc = (double) use / total;
			sf = new SigarInfoEntity(String.valueOf(filePerc * 100), "磁盘使用率");
		} catch (SigarException e) {
			throw new TPSException("获取磁盘使用率不正确", e);
		}
		return sf;
	}

	/**
	 * 获取磁盘使用率
	 * @return int类型磁盘使用率
	 * @throws TPSException
	 */
	public static int getFilePercNum() throws TPSException {
		FileSystem fslist[];
		int sf = 0;
		try {
			fslist = getInstance().getFileSystemList();
			long total = 0;
			long use = 0;
			for (int i = 0; i < fslist.length; i++) {
				FileSystem fs = fslist[i];
				FileSystemUsage usage = null;
				switch (fs.getType()) {
				case 2: // TYPE_LOCAL_DISK : 本地硬盘
					logger.info("devName: " + fs.getDevName() + ",type：" + fs.getType());
					usage = getInstance().getFileSystemUsage(fs.getDirName());
					total += usage.getTotal();
					use += usage.getUsed();
					break;
				default:
					break;
				}
			}
			double filePerc = (double) use / total;
			sf = Integer.valueOf((int) (filePerc * 100));
		} catch (SigarException e) {
			throw new TPSException("获取磁盘使用率不正确", e);
		}
		return sf;
	}
}