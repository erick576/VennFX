package login;

public class AccessToLogin {
	private final static String username = "root";
	private final static String password = "Deffoz101.,LO";
	private final static String jdbcURL = "jdbc:mysql://localhost:3306/login";

	public static String getUsername() {
		return new String(username);
	}

	public static String getPassword() {
		return new String(password);
	}

	public static String getJdbcURL() {
		return new String(jdbcURL);
	}
}