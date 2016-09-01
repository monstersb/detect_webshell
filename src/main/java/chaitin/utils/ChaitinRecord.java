package chaitin.utils;

import java.util.Date;

import com.aliyun.odps.Column;
import com.aliyun.odps.data.Record;

public class ChaitinRecord implements Record {

	Object[] obj;
	
	public ChaitinRecord(Object[] obj) {
		this.obj = obj;
	}
	
	@Override
	public Object get(int arg0) {
		return obj[arg0];
	}

	@Override
	public Object get(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getBigint(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getBigint(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getBoolean(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getBoolean(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Column[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDatetime(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDatetime(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getDouble(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getDouble(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(Object[] arg0) {
		obj = arg0;
		
	}

	@Override
	public void set(int arg0, Object arg1) {
		obj[arg0] = arg1;
		
	}

	@Override
	public void set(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBigint(int arg0, Long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBigint(String arg0, Long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBoolean(int arg0, Boolean arg1) {
		obj[arg0] = arg1;
		
	}

	@Override
	public void setBoolean(String arg0, Boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDatetime(int arg0, Date arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDatetime(String arg0, Date arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDouble(int arg0, Double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDouble(String arg0, Double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setString(int arg0, String arg1) {
		obj[arg0] = arg1;
	}

	@Override
	public void setString(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setString(int arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setString(String arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return obj;
	}

}
