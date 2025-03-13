package org.community.DatabaseProvider.MySQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.community.DatabaseProvider.DatabaseProvider;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQL {

	private Connection conn = null;
	private Statement stmt = null;

	public MySQL(){
		
	}
	
	public ResultSet getMetaData() {
		
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public int create(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			DatabaseProvider.log.LogError("", e);
			return 0;
		}

	}

	public int update(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			DatabaseProvider.log.LogError("", e);
			return 0;
		}

	}

	public int insert(String statement) {
		
		try {
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(statement);
			return rs;
		} catch (Exception e) {
			DatabaseProvider.log.LogError("", e);
			return 0;
		}

	}

	public int alterTable(String statement, String tableName, String columnName) {
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();
			ResultSet rs = md.getColumns(null, null, tableName, columnName);
			if (rs.next()) {
				
			} else {
				try {
					stmt = conn.createStatement();
					int result = stmt.executeUpdate(statement);
					return result;
				} catch (Exception e) {
					DatabaseProvider.log.LogError("", e);
					return 0;
				}				
			}
		} catch (SQLException e1) {
			DatabaseProvider.log.LogError("", e1);
			return 0;
		}
		return 0;
	}

	public ResultSet selectRS(String statement) {
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			return rs;
		} catch (Exception e) {
			DatabaseProvider.log.LogError("", e);
			return null;
		}

	}

	public String[][] selectString(String statement) {
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			try {
				if (!rs.isBeforeFirst() ) {    
					return null;
				} else {
					int columns = rs.getMetaData().getColumnCount();
					rs.last();
					int rows = rs.getRow();
					rs.beforeFirst();
					
					String[][] result = new String[rows][columns];
					while(rs.next()) {
						for(int i = 1; i <= columns; i++) {
							result[rs.getRow()-1][i-1] = rs.getString(i);
						}
						
					}
					return result;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				DatabaseProvider.log.LogError("", e);
				return null;
			}
		} catch (Exception e) {
			DatabaseProvider.log.LogError("", e);
			return null;
		}

	}

	public void connect() {		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUrl("jdbc:mysql://" + DatabaseProvider.config.getString("Default.Url") + ":" + DatabaseProvider.config.getInt("Default.Port") + "/" + DatabaseProvider.config.getString("Default.Database"));
			ds.setUser(DatabaseProvider.config.getString("Default.Username"));
			ds.setPassword(DatabaseProvider.config.getString("Default.Password"));
			this.conn = ds.getConnection();
			DatabaseProvider.log.getLogger().debug("Successfully connected to Database.");
		} catch (Exception e) {
			DatabaseProvider.log.getLogger().error("Can't connect to MySQL Database", e);
		}

	}

	public void connect(String url, int port, String database, String username, String password) {		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUrl("jdbc:mysql://" + url);
			//ds.setPort(port);
			//ds.setDatabaseName(database);
			ds.setUser(username);
			ds.setPassword(password);
			this.conn = ds.getConnection();
			//this.conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database + "?user="+ username + "&password=" + password + "");
			//this.conn = DriverManager.getConnection("jdbc:mysql://10.0.100.111/test?user=test&password=test");
			//this.conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database, username, password);
			DatabaseProvider.log.getLogger().debug("Successfully connected to Database.");
		} catch (Exception e) {
			DatabaseProvider.log.getLogger().error("Can't connect to MySQL Database", e);
		}

	}

	public void disconnect() {

		try {
			if(stmt != null)
				this.stmt.close();
			this.conn.close();
			DatabaseProvider.log.getLogger().debug("Successfully disconnected from Database.");
		} catch (Exception e) {
			DatabaseProvider.log.getLogger().error("", e);
		}

	}

}
