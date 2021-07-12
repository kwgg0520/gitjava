package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {

	MemberDAO dao = new MemberDAO();

	Scanner sc = new Scanner(System.in);
	int index = -1;

	public void insert() {
		// TODO Auto-generated method stub

		System.out.println("가입할 ID를 입력하세요.");
		String id = sc.nextLine();
		index = check(id);
		if (index == -1) {
			System.out.println("Password를 입력하세요.");
			String password = sc.nextLine();
			System.out.println("이름을 입력하세요.");
			String name = sc.nextLine();
			System.out.println("Email을 입력하세요.");
			String email = sc.nextLine();
			int result = dao.insert(new MemberDTO(id, password, name, email));
			if (result > 0)
				System.out.println("가입완료");
			else
				System.out.println("가입실패");
		} else
			System.out.println("중복된 ID입니다.");
	}

	public void modify() {
		// TODO Auto-generated method stub
		System.out.println("수정할 ID를 입력하세요.");
		String id = sc.nextLine();
		index = check(id);
		if (index == -1) {
			System.out.println("존재하지 않는 ID입니다.");

		} else {
			System.out.println("수정할 Password를 입력하세요.");
			String password = sc.nextLine();
			System.out.println("수정할 Email을 입력하세요.");
			String email = sc.nextLine();
			int result = dao.update(new MemberDTO(id, password, email));
			if (result > 0)
				System.out.println("수정완료");
			else
				System.out.println("수정실패");
		}
	}

	public void select() {
		// TODO Auto-generated method stub
		ArrayList<MemberDTO> list = dao.getAll();
		System.out.println("조회할 ID를 입력하세요.");
		String id = sc.nextLine();
		index = check(id);
		if (index == -1) {
			System.out.println("존재하지 않는 ID입니다.");
		} else
			System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", list.get(index).getMemberno(), list.get(index).getId(),
					list.get(index).getPassword(), list.get(index).getName(), list.get(index).getEmail(),
					list.get(index).getSdate());
	}

	public void delete() {
		// TODO Auto-generated method stub
		ArrayList<MemberDTO> list = dao.getAll();
		System.out.println("삭제할 ID를 입력하세요.");
		String id = sc.nextLine();
		index = check(id);
		if (index == -1) {
			System.out.println("존재하지 않는 ID입니다.");
		} else {
			System.out.println("삭제할 ID의 비밀번호를 입력하세요.");
			String password = sc.nextLine();
			if (list.get(index).getPassword().equals(password)) {
				int result = dao.delete(new MemberDTO(id, password));
				if (result > 0)
					System.out.println("삭제완료");
				else
					System.out.println("삭제실패");
			} else
				System.out.println("비밀번호가 틀렸습니다.");
		}
	}

	public void printAll() {
		// TODO Auto-generated method stub
		ArrayList<MemberDTO> list = dao.getAll();
		System.out.println("Memberno\tID\tPassword\t이름\tEmail\t가입일");
		for (MemberDTO item : list) {
			System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", item.getMemberno(), item.getId(), item.getPassword(),
					item.getName(), item.getEmail(), item.getSdate());
		}
	}

	public int check(String id) {
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
