package iShop.dao;

import iShop.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JDBCGroupDAOImpl implements JDBCGroupDAO {

	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Group> getGroups() {
		String sql = "SELECT * FROM t_group";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Group> groupsList = new ArrayList<Group>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Group group = new Group(rs.getInt("GROUP_ID"), rs.getString("GROUP_NAME"));
				groupsList.add(group);
			}
			rs.close();
			ps.close();
			return groupsList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
