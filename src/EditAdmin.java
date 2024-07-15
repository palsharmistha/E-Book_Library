import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EditAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

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
                    EditAdmin frame = new EditAdmin();
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
    public EditAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("EDIT ADMIN");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(131, 25, 135, 24);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(60, 53, 314, 40);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"user_ID", "Name", "Password", "Contact"}));
        contentPane.add(comboBox);

        JLabel lblNewLabel_1 = new JLabel("Enter Updated Values");
        lblNewLabel_1.setBounds(70, 106, 305, 40);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(47, 143, 350, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("UPDATE");
        btnNewButton.setBounds(66, 206, 91, 33);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(271, 206, 91, 33);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
        contentPane.add(btnCancel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedField = comboBox.getSelectedItem().toString();
                String newValue = textField.getText();
                updateAdmin(selectedField, newValue);
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

    private void updateAdmin(String field, String newValue) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "UPDATE Admin SET " + field + " = ? WHERE user_ID = 'Admin123'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newValue);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Admin details updated successfully!");
                textField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update admin details.");
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
}
