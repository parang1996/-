package typing_battle.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection 객체를 만들어주는 클래스
 * 
 * @author pc21
 *
 */
public class ConnectionFactory {
	private String driver;
	private String url;
	private String user;
	private String password;
	private int maxConn;

	private ConnectionFactory() {

		try {
			Properties prop = new Properties();

			// 프로퍼티 읽어오기
			InputStream reader = getClass().getClassLoader().getResourceAsStream("typing_battle/db.properties");
			prop.load(reader);

			// 프로퍼티 내용으로 필드변수 세팅
			this.driver = prop.getProperty("driver");
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("user");
			this.password = prop.getProperty("password");
			this.maxConn = Integer.parseInt(prop.getProperty("maxConn"));

			// 드라이버 로딩
			Class.forName(driver);
			System.out.println("드라이버 로딩 성공");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

		}
	}

	// DB와의 Connection 객체 내어주는 메소드
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	private static ConnectionFactory instance = new ConnectionFactory();

	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionFactory(); // 안전빵으로 넣어둠.
		}
		return instance;
	}
	
	public int getMaxConn() {
		return maxConn;
	}

}
