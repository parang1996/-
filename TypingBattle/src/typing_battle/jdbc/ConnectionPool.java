package typing_battle.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

/**
 * ConnectionFactory에서 maxConn만큼
 * Connection 객체를 생성해서 Pool에 보관하는 클래스
 */
public class ConnectionPool {
	
	// Vector는 동기화(synchronized)가 적용되는 ArrayList이다.
	private static Vector<Connection> pool = new Vector<Connection>();
	
	private ConnectionPool() {
		try {
			initPool();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static ConnectionPool instance = new ConnectionPool();
	
	public static ConnectionPool getInstance() {
		if(instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	private synchronized void initPool() throws SQLException {
		destroyPool();
		
		ConnectionFactory factory = ConnectionFactory.getInstance();
		int maxConn = factory.getMaxConn();
		
		for(int i = 0; i < maxConn; i++) {
			pool.add(factory.getConnection());
		}
	}
	
	private synchronized void destroyPool() throws SQLException {
		for(Connection conn : pool) {
			conn.close();
		}
		pool.clear();
	}
	
	public synchronized Connection getConnection() {
		if(pool.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Connection conn = pool.remove(pool.size() - 1);
		return conn;
	}
	
	public synchronized void releaseConnection(Connection conn) {
		pool.add(conn);
		notify();
	}
}