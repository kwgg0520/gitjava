package member;

import java.util.Scanner;

public class MemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		MemberService ms = new MemberService();

		while (true) {
			System.out.println("1. 회원가입 | 2. 회원수정 | 3. 회원보기 | 4. 회원탈퇴 | 5. 전체보기  | 6. 종료");
			System.out.println("===================================================");
			System.out.println("이용하고자 하는 기능을 선택하세요.");
			String choice = sc.nextLine();

			switch (choice) {
			case "1":
				ms.insert();
				break;

			case "2":
				ms.modify();
				break;

			case "3":
				ms.select();
				break;

			case "4":
				ms.delete();
				break;

			case "5":
				ms.printAll();
				break;

			case "6":
				System.out.println("종료합니다.");
				break;

			default:
				System.out.println("기능을 잘못 입력하였습니다.");
			}
		}
	}
}
