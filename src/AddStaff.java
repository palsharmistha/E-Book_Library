import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AddStaff extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldStaffId;
    private JTextField textFieldName;
    private JTextField textFieldContact;

    // Database Credentials
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    static final String USER = "root";
    static final String PASS = "root";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddStaff frame = new AddStaff();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AddStaff() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 496, 479);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labelStaffId = new JLabel("STAFF ID");
        labelStaffId.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelStaffId.setHorizontalAlignment(SwingConstants.CENTER);
        labelStaffId.setBounds(41, 65, 146, 34);
        contentPane.add(labelStaffId);

        JLabel lblName = new JLabel("NAME");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblName.setBounds(41, 124, 146, 34);
        contentPane.add(lblName);

        JLabel lblContact = new JLabel("CONTACT");
        lblContact.setHorizontalAlignment(SwingConstants.CENTER);
        lblContact.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblContact.setBounds(41, 181, 146, 34);
        contentPane.add(lblContact);

        textFieldStaffId = new JTextField();
        textFieldStaffId.setBounds(249, 74, 161, 19);
        contentPane.add(textFieldStaffId);
        textFieldStaffId.setColumns(10);

        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        textFieldName.setBounds(249, 133, 161, 19);
        contentPane.add(textFieldName);

        textFieldContact = new JTextField();
        textFieldContact.setColumns(10);
        textFieldContact.setBounds(249, 190, 161, 19);
        contentPane.add(textFieldContact);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdd.setBounds(102, 385, 117, 34);
        contentPane.add(btnAdd);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancel.setBounds(285, 386, 125, 33);
        contentPane.add(btnCancel);
        
        JLabel lblNewLabel = new JLabel("ADD STAFF");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(150, 21, 177, 34);
        contentPane.add(lblNewLabel);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStaff();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Dashboard dashboard = new Dashboard("Admin User");
                dashboard.setVisible(true);
            }
        });
    }

    private void addStaff() {
        String staffId = textFieldStaffId.getText();
        String name = textFieldName.getText();
        String contact = textFieldContact.getText();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO Staffs (Staff_ID, Name, Contact) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            pstmt.setString(2, name);
            pstmt.setString(3, contact);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Staff added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add staff.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clearFields() {
        textFieldStaffId.setText("");
        textFieldName.setText("");
        textFieldContact.setText("");
    }
}
