package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();
	ArrayList<MemberDTO> list = dao.getAll();
	Scanner sc=new Scanner(System.in);
	int index = -1;
	
	public void insert() {
		// TODO Auto-generated method stub
		
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
	}

	public void modify() {
		// TODO Auto-generated method stub
		System.out.println("������ ID�� �Է��ϼ���.");
		String id = sc.nextLine();
		index = check(id);
		if (index == -1) {
			System.out.println("�������� �ʴ� ID�Դϴ�.");
			
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
		}
	}

	public void select() {
		// TODO Auto-generated method stub
		System.out.println("��ȸ�� ID�� �Է��ϼ���.");
		String id = sc.nextLine();
		index = check(id);
		if (index == -1) {
			System.out.println("�������� �ʴ� ID�Դϴ�.");
		} else
			System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", list.get(index).getMemberno(), list.get(index).getId(),
					list.get(index).getPassword(), list.get(index).getName(), list.get(index).getEmail(),
					list.get(index).getSdate());
	}

	public void printAll() {
		// TODO Auto-generated method stub
		System.out.println("Memberno\tID\tPassword\t�̸�\tEmail\t������");
		for (MemberDTO item : list) {
			System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", item.getMemberno(), item.getId(), item.getPassword(),
					item.getName(), item.getEmail(), item.getSdate());
		}
	}
	
	public int check(String id) {
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getId()))
					return i;
			}
		}
		return -1;
	}
	
}
