package com.kosta.vo;

import java.util.ArrayList;
import java.util.Scanner;

import com.kosta.dao.MemberDAO;

/*
member
1.ȸ������
���̵� ������ "���� ���̵� �ֽ��ϴ�"

���̵� ������
memberno : sequence�� �ڵ�����
id
password
�̸�
�̸���
�������� sysdate
���ԿϷ�

2.ȸ������
���̵� ������ "�ش� ���̵� �����ϴ�"
���̵� ������
pw ����, �̸��� ����

3.ȸ������
���̵� ������ "�ش� ���̵� �����ϴ�"
���̵� ������
���̵�, �н�����, �̸�, �̸���, ��������

4.��ü����
��ȣ, ���̵�, �̸�, �̸���, ��������

�䱸����
1. git�� ������Ʈ�� ���� ó���Ұ�!
2. database�� Ȱ���Ұ�
	=>�ȵɰ�� hashmap Ȱ��
3. 

*/
public class MemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		MemberDAO dao = new MemberDAO();

		int index = -1;

		while (true) {
			System.out.println("1. ȸ������ | 2. ȸ������ | 3. ȸ������ | 4. ��ü����  | 5. ����");
			System.out.println("===================================================");
			System.out.println("�̿��ϰ��� �ϴ� ����� �����ϼ���.");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				System.out.println("������ ID�� �Է��ϼ���.");
				String id = sc.nextLine();
				index = check(id);
				if (index == -1) {
					System.out.println("Password�� �Է��ϼ���.");
					String password = sc.nextLine();
					System.out.println("�̸��� �Է��ϼ���.");
					String name = sc.nextLine();
					System.out.println("Email�� �Է��ϼ���.");
					String email = sc.nextLine();
					int result = dao.insert(new MemberDTO(id, password, name, email));
					if (result > 0)
						System.out.println("���ԿϷ�");
					else
						System.out.println("���Խ���");
				} else
					System.out.println("�ߺ��� ID�Դϴ�.");
				break;

			case 2:
				ArrayList<MemberDTO> list = dao.getAll();
				System.out.println("������ ID�� �Է��ϼ���.");
				id = sc.nextLine();
				index = check(id);
				if (index == -1) {
					System.out.println("�������� �ʴ� ID�Դϴ�.");
					break;
				} else {
					System.out.println("������ Password�� �Է��ϼ���.");
					String password = sc.nextLine();
					System.out.println("������ Email�� �Է��ϼ���.");
					String email = sc.nextLine();
					int result = dao.update(index
							, new MemberDTO(list.get(index).getMemberno(), id, password, list.get(index).getName(), email, list.get(index).getSdate()));
					if (result > 0)
						System.out.println("�����Ϸ�");
					else
						System.out.println("��������");
					break;
				}

			case 3:
				list = dao.getAll();
				System.out.println("��ȸ�� ID�� �Է��ϼ���.");
				id = sc.nextLine();
				index = check(id);
				if (index == -1) {
					System.out.println("�������� �ʴ� ID�Դϴ�.");
				} else
					System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", list.get(index).getMemberno(), list.get(index).getId(),
							list.get(index).getPassword(), list.get(index).getName(), list.get(index).getEmail(),
							list.get(index).getSdate());
				break;
			case 4:
				list = dao.getAll();
				System.out.println("Memberno\tID\tPassword\t�̸�\tEmail\t������");
				for (MemberDTO item : list) {
					System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", item.getMemberno(), item.getId(), item.getPassword(),
							item.getName(), item.getEmail(), item.getSdate());
				}
				break;
			case 5:
				System.out.println("�����մϴ�.");
				break;
			default:
				System.out.println("����� �߸� �Է��Ͽ����ϴ�.");
			}
		}
	}

	public static int check(String id) {
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> list = dao.getAll();

		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getId()))
					return i;
			}
		}
		return -1;
	}

}
