import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RemoveBooks extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField DeleteField;

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
                    RemoveBooks frame = new RemoveBooks();
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
    public RemoveBooks() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ENTER BOOK ID OR BOOK NAME TO DELETE");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(61, 33, 329, 39);
        contentPane.add(lblNewLabel);

        DeleteField = new JTextField();
        DeleteField.setBounds(61, 93, 329, 48);
        contentPane.add(DeleteField);
        DeleteField.setColumns(10);

        JButton btnDelete = new JButton("CANCEL");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDelete.setBounds(254, 169, 136, 39);
        contentPane.add(btnDelete);

        JButton btnDelete_1 = new JButton("DELETE");
        btnDelete_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDelete_1.setBounds(61, 169, 136, 39);
        contentPane.add(btnDelete_1);

        btnDelete_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Dashboard dashboard = new Dashboard("Admin User");
                dashboard.setVisible(true);
            }
        });
    }

    private void deleteBook() {
        String bookInput = DeleteField.getText();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "DELETE FROM Books WHERE BOOK_ID = ? OR Name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookInput);
            pstmt.setString(2, bookInput);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                DeleteField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No book found with the given ID or Name.");
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