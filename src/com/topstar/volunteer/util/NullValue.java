package com.topstar.volunteer.util;

import java.io.Serializable;

public class NullValue implements Serializable {

	private static final long serialVersionUID = -2065918689680886981L;
	private int m_nDataType;

	public NullValue(int _nDataType) {
		m_nDataType = _nDataType;
	}

	public int getDataType() {
		return m_nDataType;
	}

	public void setDataType(int dataType) {
		m_nDataType = dataType;
	}

	@Override
	public String toString() {
		return "";
	}
}
