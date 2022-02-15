
import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("serial")
public class StudentJTable extends JFrame {

	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "students";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "root";
	@SuppressWarnings("deprecation")
	public StudentsJTable() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("students");
	setBounds(100, 100, 579, 242);
	Container c = this.getContentPane();
	JLabel lb1 = new JLabel("Hw students");
	c.add(lb1);
	JScrollPane jsp = new JScrollPane();
	c.add(jsp);
	JTable tb = new JTable();
	DefaultTableModel model = (DefaultTableModel) tb.getModel();
	model.addColumn("students_ID");
	model.addColumn("firstname");
	model.addColumn("lastname");
	model.addColumn("age");
	try {
	Class.forName(driver).newInstance();
	conn = DriverManager.getConnection(url + dbName, userName, password);
	Statement stm = conn.createStatement();
	ResultSet rs = stm.executeQuery("select * from students");
	System.out.println("students_ID\t" + "firstname\t" + "lastname\t" +
	"age");
	int row = 0;
	while (rs.next()) {
	model.addRow(new Object[0]);
	model.setValueAt(rs.getInt("students_ID"), row, 0);
	model.setValueAt(rs.getString("firstname"), row, 1);
	model.setValueAt(rs.getString("lastname"), row, 2);
	model.setValueAt(rs.getInt("age"), row, 3);
	row++;
	}
	rs.close();
	System.out.println("Connected to the database");
	conn.close();
	System.out.println("Disconnected from database");
	} catch (Exception e) {
	e.printStackTrace();
	}
	jsp.setViewportView(tb);
	}
	public static void main(String[] args) {
	StudentsJTable mf = new StudentsJTable();
	mf.setVisible(true);
	}
	}