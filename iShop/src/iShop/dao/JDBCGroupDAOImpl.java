package iShop.dao;

import iShop.exception.CustomException;
import iShop.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCGroupDAOImpl implements JDBCGroupDAO {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Group> getGroups() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Group> groupsList = new ArrayList<Group>();
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM t_group");
			rs = ps.executeQuery();
			while (rs.next()) {
				Group group = new Group(rs.getInt("GROUP_ID"), rs.getString("GROUP_NAME"));
				groupsList.add(group);
			}
			return groupsList;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public Group getGroup(Integer groupId) {
		String sql = "SELECT * FROM t_group WHERE group_id="+groupId;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Group(rs.getInt("GROUP_ID"), rs.getString("GROUP_NAME"));
			}
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
			}
		}
		return null;
	}

}
