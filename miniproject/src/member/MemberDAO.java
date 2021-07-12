package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

	// db 연결 메서드
	private Connection getConnection() {
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String password = "hr";
		Connection conn = null;

		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return conn;

	}

	// 회원가입
	public int insert(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into members(memberno, id, password, name, email, sdate) ");
		sql.append(" values(membersseq.nextval,?,?,?,?,sysdate) ");

		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(pstmt, conn);
		}
		return result;
	}

	// 회원수정
	public int update(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" update members 		");
		sql.append(" set password=? 	");
		sql.append(" 	,email=? 		");
		sql.append(" where id=?			");

		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getId());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(pstmt, conn);
		}
		return result;
	}

	// 회원탈퇴
	public int delete(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" delete members 		");
		sql.append(" where id=? and password=?	");

		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(pstmt, conn);
		}
		return result;
	}

	// 전체보기
	public ArrayList<MemberDTO> getAll() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select 				");
			sql.append(" 		memberno		");
			sql.append(" 		,id				");
			sql.append(" 		,password		");
			sql.append("		,name			");
			sql.append("		,email			");
			sql.append("		,sdate			");
			sql.append(" from members 			");
			sql.append(" order by memberno 		");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new MemberDTO(rs.getInt("memberno"), rs.getString("id"), rs.getString("password"),
						rs.getString("name"), rs.getString("email"), rs.getDate("sdate")));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(pstmt, conn);
		}
		return arr;
	}

	// close 기능 메서드
	private void close(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}
}
