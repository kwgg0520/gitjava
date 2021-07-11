package com.kosta.vo;

import java.util.ArrayList;
import java.util.Scanner;

import com.kosta.dao.MemberDAO;

/*
member
1.회원가입
아이디가 있으면 "기존 아이디가 있습니다"

아이디가 없으면
memberno : sequence로 자동증가
id
password
이름
이메일
가입일자 sysdate
가입완료

2.회원수정
아이디 없으면 "해당 아이디가 없습니다"
아이디가 있으면
pw 수정, 이메일 수정

3.회원보기
아이디가 없으면 "해당 아이디가 없습니다"
아이디가 있으면
아이디, 패스워드, 이름, 이메일, 가입일자

4.전체보기
번호, 아이디, 이름, 이메일, 가입일자

요구사항
1. git에 프로젝트를 만들어서 처리할것!
2. database를 활용할것
	=>안될경우 hashmap 활용
3. 

*/
public class MemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		MemberDAO dao = new MemberDAO();

		int index = -1;

		while (true) {
			System.out.println("1. 회원가입 | 2. 회원수정 | 3. 회원보기 | 4. 전체보기  | 5. 종료");
			System.out.println("===================================================");
			System.out.println("이용하고자 하는 기능을 선택하세요.");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
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
				break;

			case 2:
				ArrayList<MemberDTO> list = dao.getAll();
				System.out.println("수정할 ID를 입력하세요.");
				id = sc.nextLine();
				index = check(id);
				if (index == -1) {
					System.out.println("존재하지 않는 ID입니다.");
					break;
				} else {
					System.out.println("수정할 Password를 입력하세요.");
					String password = sc.nextLine();
					System.out.println("수정할 Email을 입력하세요.");
					String email = sc.nextLine();
					int result = dao.update(index
							, new MemberDTO(list.get(index).getMemberno(), id, password, list.get(index).getName(), email, list.get(index).getSdate()));
					if (result > 0)
						System.out.println("수정완료");
					else
						System.out.println("수정실패");
					break;
				}

			case 3:
				list = dao.getAll();
				System.out.println("조회할 ID를 입력하세요.");
				id = sc.nextLine();
				index = check(id);
				if (index == -1) {
					System.out.println("존재하지 않는 ID입니다.");
				} else
					System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", list.get(index).getMemberno(), list.get(index).getId(),
							list.get(index).getPassword(), list.get(index).getName(), list.get(index).getEmail(),
							list.get(index).getSdate());
				break;
			case 4:
				list = dao.getAll();
				System.out.println("Memberno\tID\tPassword\t이름\tEmail\t가입일");
				for (MemberDTO item : list) {
					System.out.printf("%d\t\t%s\t%s\t\t%s\t%s\t%s\n", item.getMemberno(), item.getId(), item.getPassword(),
							item.getName(), item.getEmail(), item.getSdate());
				}
				break;
			case 5:
				System.out.println("종료합니다.");
				break;
			default:
				System.out.println("기능을 잘못 입력하였습니다.");
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
