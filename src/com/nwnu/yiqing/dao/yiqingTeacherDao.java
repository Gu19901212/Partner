package com.nwnu.yiqing.dao;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.nwnu.yiqing.entity.yiqingTeacher;

public class yiqingTeacherDao extends BaseDao<yiqingTeacher> {
	/**
	 * ѧԺ�������ύ��ѧԺѧ�����ʱ�޸ı�ѧԺѧ����state���Ƿ�����ˣ�����
	 */
	public boolean updateTeacheryiqing(String College){
		String sql = " update yiqing_teacher set state ='�����' where college='"+College+"' ";
		try {
			PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sql);
			return prepareStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
