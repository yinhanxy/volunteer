package com.topstar.volunteer.jdbc;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.util.NullValue;

/**
 * 
 * Title: 数据库连接工具类<BR>
 * 数据库连接工具类 <BR>
 */
public class DBManagerProvider {

	private static Logger logger =LoggerFactory.getLogger(DBManagerProvider.class);

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() throws TPSException {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			throw new TPSException(e);
		}
	}

	public LinkedHashMap<String, Object> sqlExecuteQueryRow(String sql,String idColumn, LinkedHashMap<String, Object> params) throws SQLException,
			IOException, Exception {
		PageInfo<LinkedHashMap<String, Object>> page = sqlExecuteQuery(sql,idColumn, params, 0, 1);
		if (page.getTotal() > 0) {
			return (LinkedHashMap<String, Object>) page.getList().get(0);
		}
		return null;
	}

	public List<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql) throws TPSException {
		return sqlQuery(sql, null);
	}

	public PageInfo<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql,String idColumn, int pageIndex, int pageSize) throws TPSException {
		return sqlExecuteQuery(sql,idColumn, null,pageIndex,pageSize);
	}

	public List<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException {
		return sqlQuery(sql, params);
	}

	public PageInfo<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql,String idColumn, LinkedHashMap<String, Object> params, int pageIndex, int pageSize)
			throws TPSException {
		logger.debug("sql:"+sql);
		printParam(params);
		logger.debug("pageIndex:"+pageIndex);
		logger.debug("pageSize:"+pageSize);
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PageInfo<LinkedHashMap<String, Object>> page = new PageInfo<LinkedHashMap<String, Object>>();
		List<LinkedHashMap<String, Object>> datas = new ArrayList<LinkedHashMap<String, Object>>();
		int tempPageNo = pageIndex <= 0 ? 1 : pageIndex;
		String dataCountSql = " select count("+idColumn+") from  ( " + sql+" ) ";
		logger.debug("total sql :" + dataCountSql);
		int total = sqlExecuteIntQuery(dataCountSql,params);
		if( total > 0) {
			int maxPage = total % pageSize == 0 ? total / pageSize: total /pageSize +1;
			if(tempPageNo > maxPage){
				tempPageNo = maxPage;
			}
			page.setPageSize(pageSize);
			page.setPageNum(tempPageNo);
			page.setTotal(total);
			page.setPages(maxPage);
			if(tempPageNo == 1){
				page.setIsFirstPage(true);
				page.setHasPreviousPage(false);
				if(maxPage > 1){
					page.setHasNextPage(true);
				}else{
					page.setIsLastPage(true);
				}
			}else if(tempPageNo == maxPage && maxPage > 1){
				page.setIsLastPage(true);
				page.setHasPreviousPage(true);
				page.setHasNextPage(false);
			}
			try {
				if (conn != null) {
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					setParameters(stmt, params);
					if (pageSize > 0) {
						stmt.setMaxRows((tempPageNo - 1) * pageSize + pageSize);
					}
					rs = stmt.executeQuery();
					if (pageSize > 0) {
						rs.first();
						rs.relative((tempPageNo - 1) * pageSize - 1);
					}
					ResultSetMetaData rsmd = rs.getMetaData();
					int count = rsmd.getColumnCount();
					while (rs.next()) {
						LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
						for (int i = 0; i < count; i++) {
							String sFieldName = rsmd.getColumnLabel(i + 1).toUpperCase();
							String propertyName = toPropertyName(sFieldName);
							properties.put(propertyName, showNull(rs, sFieldName, rsmd.getScale(i + 1), rsmd.getColumnType(i + 1)));
						}
						datas.add(properties);
					}
					page.setList(datas);
					page.setSize(datas.size());
				}
			} catch (Exception e) {
				throw new TPSException(e);
			} finally {
				freeConnection(rs, stmt, conn);
			}
		}else{
			page.setIsFirstPage(true);
			page.setHasPreviousPage(false);
			page.setHasNextPage(false);
			page.setIsLastPage(true);
			freeConnection(rs, stmt, conn);
		}
		return page;
	}
	
	private List<LinkedHashMap<String, Object>> sqlQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException {
		logger.debug("sql:"+sql);
		printParam(params);
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LinkedHashMap<String, Object>> datas = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				setParameters(stmt, params);
				rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				while (rs.next()) {
					LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
					for (int i = 0; i < count; i++) {
						String sFieldName = rsmd.getColumnLabel(i + 1).toUpperCase();
						String propertyName = toPropertyName(sFieldName);
						properties.put(propertyName, showNull(rs, sFieldName, rsmd.getScale(i + 1), rsmd.getColumnType(i + 1)));
					}
					datas.add(properties);
				}
			}
		} catch (Exception e) {
			throw new TPSException(e);
		} finally {
			freeConnection(rs, stmt, conn);
		}
		return datas;
	}

	public int sqlExecuteIntQuery(String sql) throws TPSException {
		return sqlExecuteIntQuery(sql, null);
	}

	public int sqlExecuteIntQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException {
		logger.debug("sql:"+sql);
		printParam(params);
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setMaxRows(1);
				setParameters(stmt, params);
				rs = stmt.executeQuery();
				if (rs.next())
					count = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new TPSException(e);
		} finally {
			freeConnection(rs, stmt, conn);
		}
		return count;
	}
	
	private void setParameters(PreparedStatement _oPreStmt, LinkedHashMap<String, Object> params) throws TPSException {
		if (null == params || params.isEmpty()){
			return;
		}
		int i = 0;
		try {
			i = 0;
			for (Iterator<Entry<String, Object>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
				Object value = entry.getValue();
				if (value != null){
					if (value instanceof Long){
						_oPreStmt.setLong(i + 1, ((Long) value).longValue());
					}
					else if (value instanceof Double){
						_oPreStmt.setDouble(i + 1, ((Double) value).doubleValue());
					}else if (value instanceof Integer){
						_oPreStmt.setInt(i + 1, ((Integer) value).intValue());
					}else if (value instanceof String){
						_oPreStmt.setString(i + 1, (String) value);
					}else if (value instanceof java.util.Date){
						_oPreStmt.setTimestamp(i + 1, new Timestamp(((java.util.Date) value).getTime()));
					}else if (value instanceof java.sql.Date){
						_oPreStmt.setTimestamp(i + 1, new Timestamp(((java.sql.Date) value).getTime()));
					}else if (value instanceof NullValue) {
						NullValue nullValue = (NullValue) value;
						_oPreStmt.setNull(i + 1, nullValue.getDataType());
					} else{
						_oPreStmt.setObject(i + 1, value);
					}	
				}
				i++;
			}
		} catch (Exception e) {
			throw new TPSException("参数设置出错。",e);
		}
	}
	
	private Object showNull(ResultSet _rsData, String sFieldName, int scale, int dataType) throws SQLException,IOException {
		Object tempValue = null;
//		logger.debug("sFieldName:{};scale:{};dataType:{}",new Object[]{sFieldName,scale,dataType});
		switch (dataType) {
		case java.sql.Types.BIT: // -7
		case java.sql.Types.TINYINT: // -6
		case java.sql.Types.BIGINT: // 5
			tempValue = _rsData.getObject(sFieldName);
			break;
		case java.sql.Types.INTEGER: // 4
			int IValue = _rsData.getInt(sFieldName);
			if (!_rsData.wasNull()) {
				tempValue = new Integer(IValue);
			} else {
				tempValue = null;
			}
			break;
		case java.sql.Types.SMALLINT: // 5
			long lValue = _rsData.getLong(sFieldName);
			if (!_rsData.wasNull()) {
				tempValue = new Long(lValue);
			} else {
				tempValue = null;
			}
			break;
		case java.sql.Types.FLOAT: // 6
			float fValue = _rsData.getFloat(sFieldName);
			if (!_rsData.wasNull()) {
				tempValue = new Float(fValue);
			} else {
				tempValue = null;
			}
			break;
		case java.sql.Types.REAL: // 7
			tempValue = _rsData.getObject(sFieldName);
			break;
		case java.sql.Types.DOUBLE: // 8
			double dValue = _rsData.getDouble(sFieldName);
			if (!_rsData.wasNull()) {
				tempValue = new Double(dValue);
			} else {
				tempValue = null;
			}
			break;
		case java.sql.Types.NUMERIC: // 2
		case java.sql.Types.DECIMAL: // 3
			if (scale <= 0) {
				long lValue1 = _rsData.getLong(sFieldName);
				if (!_rsData.wasNull()) {
					tempValue = new Long(lValue1);
				} else {
					tempValue = null;
				}
			} else {
				Double dValue1 = new Double(_rsData.getDouble(sFieldName));
				if (!_rsData.wasNull()) {
					tempValue = dValue1;
				} else {
					tempValue = null;
				}
			}
			break;
		case java.sql.Types.CHAR: // 1
			tempValue = _rsData.getObject(sFieldName);
			break;
		case java.sql.Types.VARCHAR: // 12
			tempValue = _rsData.getString(sFieldName);
			break;
		case java.sql.Types.LONGVARCHAR: // -1
			tempValue = _rsData.getString(sFieldName);
			break;
		case java.sql.Types.CLOB: // 2005
			Clob p_clob = _rsData.getClob(sFieldName);
			if (p_clob == null) {
				tempValue = null;
				break;
			}
			Reader clobIn = p_clob.getCharacterStream();
			char clobCharBuff[] = new char[8192];
			int cloLength = -1;
			StringBuffer clobStrBuff = new StringBuffer();
			while ((cloLength = clobIn.read(clobCharBuff)) != -1)
				clobStrBuff.append(clobCharBuff, 0, cloLength);
			clobIn.close();
			tempValue = clobStrBuff.toString();
			break;
		case java.sql.Types.DATE: // 91
		case java.sql.Types.TIME: // 92
		case java.sql.Types.TIMESTAMP: // 93
			Timestamp tsDateTime = _rsData.getTimestamp(sFieldName);
			if (null == tsDateTime) {
				tempValue = null;
				break;
			}
			tempValue = new Date(tsDateTime.getTime());
			break;
		case java.sql.Types.BLOB:
			break;
		default:
			tempValue = _rsData.getObject(sFieldName);
			break;
		}
		if (null == tempValue) {
			return new NullValue(dataType);
		} else {
			return tempValue;
		}
	}
	
	private void printParam(LinkedHashMap<String, Object> params){
		if (null == params || params.isEmpty()){
			return;
		}
		if(logger.isDebugEnabled()){
			try {
				int i = 0;
				for (Iterator<Entry<String, Object>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					Object value = entry.getValue();
					if (value != null){
						if (value instanceof Long){
							logger.debug((i+1) +" "+ entry.getKey()+":"+((Long) value).longValue());
						}
						else if (value instanceof Double){
							logger.debug((i+1) +" "+ entry.getKey()+":"+((Double) value).doubleValue());
						}else if (value instanceof Integer){
							logger.debug((i+1) +" "+ entry.getKey()+":"+((Integer) value).intValue());
						}else if (value instanceof String){
							logger.debug((i+1) +" "+ entry.getKey()+":"+((String) value));
						}else if (value instanceof java.util.Date){
							logger.debug((i+1) +" "+ entry.getKey()+":"+new Timestamp(((java.util.Date) value).getTime()));
						}else if (value instanceof java.sql.Date){
							logger.debug((i+1) +" "+ entry.getKey()+":"+new Timestamp(((java.sql.Date) value).getTime()));
						}else if (value instanceof NullValue) {
							NullValue nullValue = (NullValue) value;
							logger.debug((i+1) +" "+ entry.getKey()+":"+nullValue.getDataType());
						} else{
							logger.debug((i+1) +" "+ entry.getKey()+":"+value);
						}	
					}
					i++;
				}
			} catch (Exception e) {
				logger.debug("打印参数出错",e);
			}
			
		}
	}
	
	/**
	 * 转换SQL查询结果中的字段名称，
	 * @param fieldName SQL字段名
	 * @return
	 */
	private String toPropertyName(String fieldName){
		if(StringUtils.isNotBlank(fieldName) && fieldName.indexOf("_") > -1){
			String[] fields = fieldName.toLowerCase().split("_");
			StringBuilder builder = new StringBuilder();
			for(int i = 0 ; i < fields.length;i++){
				String f = fields[i];
				if(i == 0){
					builder.append(f);
				}else{
					if(f.length() > 1){
						builder.append(f.substring(0, 1).toUpperCase());
						builder.append(f.substring(1));
					}else{
						builder.append(f.toUpperCase());
					}
				}
			}
			return builder.toString();
		}
		if(StringUtils.isNotBlank(fieldName)){
			return fieldName.toLowerCase();
		}
		return fieldName;
	}
	
	protected void freeConnection(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
					st = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
