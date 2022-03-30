package typing_battle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import typing_battle.model.DeveloperVO;
import typing_battle.model.WordDTO;

public class typingBattleDao {
	// DAO 는 싱글톤으로
	// 파라미터에는 꼭 connection
	private typingBattleDao() {}
	
	private static typingBattleDao instance = new typingBattleDao();
	
	public static typingBattleDao getInstance() {
		if(instance == null) {
			instance = new typingBattleDao();
		}
		return instance;
	}
	
	// 명예의 전당
	// 개발자 목록을 레벨로 정렬해서 리턴
	public ArrayList<DeveloperVO> devList(Connection conn) throws SQLException{
		StringBuffer query = new StringBuffer();
		
		query.append("select                 			");
		query.append("      dev_id      				");
		query.append("    , dev_name    				");
		query.append("    , dev_pw   			   		");
		query.append("    , dev_level   			   ");
		query.append("    , dev_exp   				   ");
		query.append("    , dev_money   			   ");
		query.append("    , dev_upgrade_exp   		   ");
		query.append("    , dev_upgrade_mon	ey	   ");
		query.append("    , dev_pw   				   ");
		query.append("FROM           	       	 	");
		query.append("	 developers           		");
		query.append("ORDER BY dev_level  desc;   	");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());

		ResultSet rs = ps.executeQuery();

		ArrayList<DeveloperVO> result = new ArrayList<DeveloperVO>();

		while (rs.next()) {
			DeveloperVO temp = new DeveloperVO();

			temp.setDevId(rs.getString("dev_id"));
			temp.setDevName(rs.getString("dev_name"));
			temp.setDevPw(rs.getString("dev_pw"));
			temp.setDevLevel(rs.getInt("dev_level"));
			temp.setDevExp(rs.getInt("dev_exp"));
			temp.setDevMoney(rs.getInt("dev_money"));
			temp.setDevUpgradeExp(rs.getInt("dev_UpgradeExp"));
			temp.setDevUpgradeMoney(rs.getInt("dev_UpgradeMoney"));
			
			result.add(temp);
		}
		if (ps != null)ps.close();
		if (rs != null)rs.close();

		return result;
	}
	
	// 회원가입
	public int registDev(Connection conn, String id, String name, String pw) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("insert into					");
		query.append("			developers			");
		query.append("values(						");
		query.append("	        ?					");
		query.append("	      , ?					");
		query.append("	      , ?					");
		query.append("	      , ?					");
		query.append("	      , ?					");
		query.append("	      , ?					");
		query.append("	      , ?					");
		query.append("	      , ?					");
		query.append("	      )					");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		DeveloperVO temp = new DeveloperVO(id, name, pw);
		
		int idx = 1;
		ps.setString(idx++, temp.getDevId());
		ps.setString(idx++, temp.getDevName());
		ps.setString(idx++, temp.getDevPw());
		ps.setInt(idx++, temp.getDevLevel());
		ps.setInt(idx++, temp.getDevExp());
		ps.setInt(idx++, temp.getDevMoney());
		ps.setInt(idx++, temp.getDevUpgradeExp());
		ps.setInt(idx++, temp.getDevUpgradeMoney());
		
		int cnt = ps.executeUpdate();
		
		if(ps!=null)ps.close();
		
		return cnt;
	}
	
	// 회원가입 할 때, 입력한 아이디 중복체크
	// 로그인 시, 입력한 아이디에 대한 패스워드 검사
	// 로그인 성공 시, 입력한 아이디에 대한 developer 데이터 가져옴
	public DeveloperVO getDev(Connection conn, String id) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("select                 			");
		query.append("      dev_id      				");
		query.append("    , dev_name    				");
		query.append("    , dev_pw   			   		");
		query.append("    , dev_level   			   ");
		query.append("    , dev_exp   				   ");
		query.append("    , dev_money   			   ");
		query.append("    , dev_upgrade_exp   		   ");
		query.append("    , dev_upgrade_mon	ey	   ");
		query.append("    , dev_pw   				   ");
		query.append("FROM           	       	 	");
		query.append("	 developers           		");
		query.append("WHERE 1=1						   	");
		query.append("			AND dev_id = ?	   	");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		DeveloperVO temp = new DeveloperVO();

		while (rs.next()) {

			temp.setDevId(rs.getString("dev_id"));
			temp.setDevName(rs.getString("dev_name"));
			temp.setDevPw(rs.getString("dev_pw"));
			temp.setDevLevel(rs.getInt("dev_level"));
			temp.setDevExp(rs.getInt("dev_exp"));
			temp.setDevMoney(rs.getInt("dev_money"));
			temp.setDevUpgradeExp(rs.getInt("dev_UpgradeExp"));
			temp.setDevUpgradeMoney(rs.getInt("dev_UpgradeMoney"));
	
		}
		if (ps != null)ps.close();
		if (rs != null)rs.close();

		return temp;
	}
	// 회원 정보 업데이트
	public int saveDev(Connection conn, DeveloperVO dev) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("update						");
		query.append("			developers		");
		query.append("set							");
		query.append("    dev_level = ?			");
		query.append("    dev_exp = ?			");
		query.append("    dev_money = ?			");
		query.append("    dev_UpgradeExp = ?	");
		query.append("    dev_UpgradeMoney = ?	");
		query.append("where		1=1				");
		query.append("	and		dev_id = ?		");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setInt(idx++, dev.getDevLevel());
		ps.setInt(idx++, dev.getDevExp());
		ps.setInt(idx++, dev.getDevMoney());
		ps.setInt(idx++, dev.getDevUpgradeExp());
		ps.setInt(idx++, dev.getDevUpgradeMoney());
		ps.setString(idx++, dev.getDevId());
		
		int cnt = ps.executeUpdate();
		
		if(ps != null) ps.close();
		
		return cnt;
	}
	// 레벨에 따른 단어 목록 리턴
	public ArrayList<WordDTO> getWordList(Connection conn, int level) throws SQLException{
		StringBuffer query = new StringBuffer();
		
		query.append("select                 				");
		query.append("      words_level     				");
		query.append("    , words_word 						");
		query.append("FROM           	       	 		");
		query.append("	 words           					");
		query.append("WHERE 1=1							   	");
		query.append("			AND words_level = ?	   	");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setInt(1, level);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<WordDTO> result = new ArrayList<WordDTO>();
		
		while(rs.next()) {
			WordDTO temp = new WordDTO();
			
			temp.setWordsLevel(rs.getInt("words_level"));
			temp.setWordsword(rs.getString("words_word"));
			result.add(temp);
		}
		
		if(ps != null) ps.close();
		if(rs != null) rs.close();
		
		return result;
	}
}
