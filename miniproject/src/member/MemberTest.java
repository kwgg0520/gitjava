package member;

import java.util.Scanner;

public class MemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		MemberService ms = new MemberService();

		while (true) {
			System.out.println("1. ȸ������ | 2. ȸ������ | 3. ȸ������ | 4. ��ü����  | 5. ����");
			System.out.println("===================================================");
			System.out.println("�̿��ϰ��� �ϴ� ����� �����ϼ���.");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				ms.insert();
				break;

			case 2:
				ms.modify();
				break;

			case 3:
				ms.select();
				break;

			case 4:
				ms.printAll();
				break;

			case 5:
				System.out.println("�����մϴ�.");
				break;

			default:
				System.out.println("����� �߸� �Է��Ͽ����ϴ�.");
			}
		}
	}
}
