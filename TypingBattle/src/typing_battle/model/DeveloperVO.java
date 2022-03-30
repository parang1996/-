package typing_battle.model;

public class DeveloperVO {
	private String devId;						// 아이디
	private String devName;						// 이름
	private String devPw;						// 비밀번호
	private int devLevel;						// 레벨
	private int devExp;							// 경험치
	private int devMoney;						// 소지금
	private int devUpgradeExp;					// 경험치 업그레이드 횟수
	private int devUpgradeMoney;				// 돈 업그레이드 횟수
	
	public DeveloperVO() {}
	
	// 회원가입 때 쓸 생성자
	// 아이디, 이름, 비밀번호
	public DeveloperVO(String devId, String devName, String devPw) {
		this(devId, devName, devPw, 1, 0, 0, 0, 0);
	}
	
	// 로그인할 때 DB 저장된 developer 불러다 담을때 쓰는 생성자
	public DeveloperVO(String devId, String devName, String devPw, int devLevel, int devExp, int devMoney,
			int devUpgradeExp, int devUpgradeMoney) {
		super();
		this.devId = devId;
		this.devName = devName;
		this.devPw = devPw;
		this.devLevel = devLevel;
		this.devExp = devExp;
		this.devMoney = devMoney;
		this.devUpgradeExp = devUpgradeExp;
		this.devUpgradeMoney = devUpgradeMoney;
		
		
	}

	@Override
	public String toString() {
		return "[아이디: " + devId + ", 이름: " + devName + ", 레벨: " + devLevel
				+  ", 경험치 증가량: " + devUpgradeExp
				+ ", 돈 증가량: " + devUpgradeMoney + "]";
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevPw() {
		return devPw;
	}

	public void setDevPw(String devPw) {
		this.devPw = devPw;
	}

	public int getDevLevel() {
		return devLevel;
	}

	public void setDevLevel(int devLevel) {
		this.devLevel = devLevel;
	}

	public int getDevExp() {
		return devExp;
	}

	public void setDevExp(int devExp) {
		this.devExp = devExp;
	}

	public int getDevMoney() {
		return devMoney;
	}

	public void setDevMoney(int devMoney) {
		this.devMoney = devMoney;
	}

	public int getDevUpgradeExp() {
		return devUpgradeExp;
	}

	public void setDevUpgradeExp(int devUpgradeExp) {
		this.devUpgradeExp = devUpgradeExp;
	}

	public int getDevUpgradeMoney() {
		return devUpgradeMoney;
	}

	public void setDevUpgradeMoney(int devUpgradeMoney) {
		this.devUpgradeMoney = devUpgradeMoney;
	}
	
	
	
}
