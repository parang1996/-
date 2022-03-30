package typing_battle.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import typing_battle.commons.UtillClass;
import typing_battle.dao.typingBattleDao;
import typing_battle.jdbc.ConnectionPool;
import typing_battle.model.DeveloperVO;
import typing_battle.model.WordDTO;

public class TypingBattleService {
	
	private TypingBattleService() {	}
	
	private static TypingBattleService instance = new TypingBattleService();
	private typingBattleDao dao = typingBattleDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	public static TypingBattleService getInstance() {
		if(instance == null) {
			instance = new TypingBattleService();
		}
		return instance;
	}
	
	// 회원목록 리턴(레벨 높은순)
	public ArrayList<DeveloperVO> devList(){
		Connection conn = cp.getConnection();
		
		try {
			return dao.devList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		return new ArrayList<DeveloperVO>();
	}
	
	// 회원가입
	public int registDev(String id, String name, String pw) {
		Connection conn = cp.getConnection();
		
		try {
			return dao.registDev(conn, id, name, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		return 0;
	}
	
	// 파라미터 id에 대한 Developer 데이터 리
	public DeveloperVO getDev(String id) {
		Connection conn = cp.getConnection();
		
		try {
			return dao.getDev(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		return new DeveloperVO();
	}
	
	// 회원 정보 업데이트
	public int saveDev(DeveloperVO dev) {
		Connection conn = cp.getConnection();
		
		try {
			return dao.saveDev(conn, dev);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		return 0;
	}
	
	// 레벨에 따른 단어 목록 리턴
	public ArrayList<WordDTO> getWordList(int level){
		Connection conn = cp.getConnection();
		
		try {
			return dao.getWordList(conn, level);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		return new ArrayList<WordDTO>();
	}
	
	public void upgradeExp(DeveloperVO dev) {
			if(dev.getDevMoney() >= 500) {
				dev.setDevMoney(dev.getDevMoney() - 500);
				dev.setDevUpgradeExp(dev.getDevUpgradeExp() + 1);
			}else {
				System.out.println("돈이 모자라요 더 가져와");
			}
	}
	
	public void upgradeMoney(DeveloperVO dev) {
		if(dev.getDevMoney() >= 500) {
			dev.setDevMoney(dev.getDevMoney() - 500);
			dev.setDevUpgradeMoney(dev.getDevUpgradeMoney() + 1);
		}else {
			System.out.println("돈이 모자라요 더 가져와");
		}
	}
	
	public void playTyping(DeveloperVO dev, ArrayList<WordDTO> wordList, Scanner sc) {
		// wordList에서 랜덤하게 10개만 뽑아쓰기
		ArrayList<WordDTO> playList = UtillClass.randArray(wordList);
		
		for(int i = 0; i < playList.size(); i++) {
			System.out.println(i+1 + ". " + playList.get(i).getWordsword());
			System.out.print(">>> ");
			
			String inputText = sc.nextLine();
			
			if(inputText.equals(playList.get(i).getWordsword())) {
				// 타이핑 성공시 경험치 획득량 amountEXp
				// (10 * wordLevel) * (1+(0.1 * devUpgradeExp))
				int amountExp = (int)((10 * playList.get(i).getWordsLevel() * (1+(0.1 * dev.getDevUpgradeExp()))));
				
				// 레벨업에 필요한 경험치량
				int amountLevel = 100 + (dev.getDevLevel() * 10);
				
				if(amountExp + dev.getDevExp() >= amountLevel) {
					dev.setDevExp(dev.getDevExp() + amountExp);
					dev.setDevLevel(dev.getDevLevel() + (dev.getDevExp()/amountLevel));
					dev.setDevExp(dev.getDevExp()%amountLevel);
					System.out.println("레벨업 ~ 현재 레벨은:  " + dev.getDevLevel());
				}
					else {
						dev.setDevExp(dev.getDevExp() + amountExp);
					} 
				//  타이핑 성공시 돈 획득량 amountMoney
				int amountMoney = (int)((10 * playList.get(i).getWordsLevel() * (1+(0.1 * dev.getDevUpgradeMoney()))));
				dev.setDevMoney(dev.getDevMoney() + amountMoney);
				
				// 현재 경험치 비율(30%)
				int expState = (int)(((double)dev.getDevExp()/amountLevel)*100);
				System.out.println("경험치를 " + amountExp + ", 돈 "
						+ amountMoney + "를 획득 (" + expState + "%)");
				
				}
				
				
			}
	}
}