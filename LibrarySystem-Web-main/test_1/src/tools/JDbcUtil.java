package tools;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDbcUtil {
	// ���ݿ���������ļ���
		private static final String JDBCPROPERTY = "jdbc.properties";
		// ׼�����ݿ���Ĵ������
		private static String DBDRIVER = "";
		private static String DBURL = "";
		private static String DBUSER = "";
		private static String PASSWORD = "";

		private Connection conn; // ׼����һ�����ݿ����Ӷ���

		/**
		 * ʹ�þ�̬��̬�����������ݿ������ļ� ��̬����飬��������������ʱ��ͻ����ִ�У�����ִֻ��һ��;
		 * �Ǿ�̬����飬�ڴ��������ʱ��(��newһ�������ʱ��)ִ�У�ÿ�δ������󶼻�ִ��һ��
		 */
		static {
			try {
				Properties property = new Properties();
				//ʹ���������������Դ,path ���ԡ�/'��ͷʱĬ���ǴӴ������ڵİ���ȡ��Դ���ԡ�/'��ͷ���Ǵ�ClassPath���»�ȡ
				System.out.println("|"+DBDRIVER+"|"+DBURL+"|"+DBUSER+"|"+PASSWORD);
				InputStream is = JDbcUtil.class.getResourceAsStream("/resources/jdbc.properties");
				property.load(new InputStreamReader(is, "utf-8"));
				is.close();
				DBDRIVER = property.getProperty("DBDRIVER");
				DBURL = property.getProperty("DBURL");
				DBUSER = property.getProperty("DBUSER");
				PASSWORD = property.getProperty("PASSWORD");
				// ����������ֻ��ע��һ�ξ���
				Class.forName(DBDRIVER);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public JDbcUtil() {
			try {
				System.out.println("|"+DBURL+"|"+DBUSER+"|"+PASSWORD+"|");
				this.conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Connection getConnection() {
			return this.conn;
		}

		public void close() {
			if (this.conn != null) {
				try {
					this.conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
	

}
