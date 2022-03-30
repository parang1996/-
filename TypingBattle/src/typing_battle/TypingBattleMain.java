package typing_battle;

import java.util.ArrayList;
import java.util.Scanner;

import typing_battle.commons.UtillClass;
import typing_battle.model.DeveloperVO;
import typing_battle.model.WordDTO;
import typing_battle.service.TypingBattleService;

public class TypingBattleMain {
	
	public static void main(String[] args) {
		TypingBattleService service = TypingBattleService.getInstance();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("행동을 선택해주세요.");
			System.out.println("1. 회원가입 | 2. 로그 | 3. 종료");
			System.out.print(">>> ");
			
			int select = 0;
			
			try {
				select = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			
			if(select == 1) {
				// 로그인
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();
				
				DeveloperVO dev = service.getDev(id);
				
				if(dev.getDevPw().equals(pw)) {
					System.out.println(dev.getDevId() + "님으로 접속했습니다.");
					
					// 로그인 이후 프로세스
					while(true) {
						System.out.println(dev.getDevId() + "님 레벨: " + dev.getDevLevel()
									+ " 소지금: " + UtillClass.strMoney(dev.getDevMoney()));
						System.out.println("1. 타자 전투 | 2. 상점 | 3. 명예의 전당 | 4. 로그아웃");
						System.out.print(">>> ");
						
						select = 0;
						
						try {
							select = Integer.parseInt(sc.nextLine());
						} catch (Exception e) {
							System.out.println("숫자만 입력해주세요.");
							continue;
						}
						
						if(select == 1) {
							// 타자 전투
							System.out.println("1. 신입 | 2. 초급(Lv.10) "
									+ "| 3. 중급(Lv.20) | 4. 고급(Lv.30) | 5. 나가기");
							System.out.print(">>> ");
							
							select = 0;
							
							try {
								select = Integer.parseInt(sc.nextLine());
							} catch (Exception e) {
								System.out.println("숫자만 입력해주세요.");
								continue;
							}
							
							ArrayList<WordDTO> wordList = new ArrayList<WordDTO>();

							if(select == 1) {
								wordList = service.getWordList(1);
								service.playTyping(dev, wordList, sc);
							}else if(select == 2) {
								if(dev.getDevLevel() < 10) {
									System.out.println("레벨 10이상부터 입장 가능");
									continue;
								}else {
									wordList = service.getWordList(2);
									service.playTyping(dev, wordList, sc);
								}
							}else if(select == 3) {
								if(dev.getDevLevel() < 20) {
									System.out.println("레벨 20이상부터 입장 가능");
									continue;
								}else {
									wordList = service.getWordList(3);
									service.playTyping(dev, wordList, sc);
								}
							}else if(select == 4) {
								if(dev.getDevLevel() < 30) {
									System.out.println("레벨 30이상부터 입장 가능");
									continue;
								}else {
									wordList = service.getWordList(4);
									service.playTyping(dev, wordList, sc);
								}
							}else if(select == 5) {
								continue;
							}else {
								System.out.println("잘못 입력하셨습니다.");
							}
						}else if(select == 2) {
							// 상점
							System.out.println("1. 경험치 획득량 증가(500원) "
									+ "| 2. 돈 획득량 증가(500원) | 3. 나가기");
							System.out.print(">>> ");
							
							select = 0;
							
							try {
								select = Integer.parseInt(sc.nextLine());
							} catch (Exception e) {
								System.out.println("숫자만 입력해주세요.");
								continue;
							}
							
							if(select == 1) {
								// 경험치 획득량 증가 구매
								service.upgradeExp(dev);
							}else if(select == 2) {
								// 돈 획득량 증가 구매
								service.upgradeMoney(dev);
							}else if(select == 3) {
								break;
							}else {
								System.out.println("잘못 입력하셨습니다.");
							}
							
						}else if(select == 3) {
							// 명예의 전당
							ArrayList<DeveloperVO> devList = service.devList();
							
							System.out.println("===========================");
							for(int i = 0; i < devList.size(); i++) {
								System.out.println((i+1) + ". " 
											+ devList.get(i).toString());
							}
							System.out.println("===========================");
						}else if(select == 4) {
							// 로그아웃
							int saveResult = service.saveDev(dev);
							
							if(saveResult > 0) {
								System.out.println("로그아웃 되었습니다.");
								break;
							}else {
								System.out.println("저장 실패");
							}
						}else {
							System.out.println("잘못 입력하셨습니다.");
						}
						
						
					}
				}else {
					System.out.println("회원정보가 일치하지 않습니다.");
				}
			}else if(select == 2) {
				// 회원가입
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("이름을 입력해주세요.");
				System.out.print(">>> ");
				String name = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();
				
				DeveloperVO dev = service.getDev(id);
				
				if(dev.getDevId() != null) {
					System.out.println("중복된 아이디 입니다.");
				}else {
					int resultCnt = service.registDev(id, name, pw);

					if(resultCnt > 0) {
						System.out.println("회원가입 완료");
					}else {
						System.out.println("오류가 발생했습니다.");
					}
				}
			}else if(select == 3) {
				System.out.println("종료합니다.");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}
	}
}
