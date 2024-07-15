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

public class AddBooks extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

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
                    AddBooks frame = new AddBooks();
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
    public AddBooks() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 496, 479);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labelBookId = new JLabel("BOOK ID");
        labelBookId.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelBookId.setHorizontalAlignment(SwingConstants.CENTER);
        labelBookId.setBounds(41, 65, 146, 34);
        contentPane.add(labelBookId);

        JLabel lblCategory = new JLabel("CATEGORY");
        lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCategory.setBounds(41, 124, 146, 34);
        contentPane.add(lblCategory);

        JLabel lblName = new JLabel("NAME");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblName.setBounds(41, 181, 146, 34);
        contentPane.add(lblName);

        JLabel lblAuthor = new JLabel("AUTHOR");
        lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAuthor.setBounds(41, 242, 146, 34);
        contentPane.add(lblAuthor);

        JLabel lblCopies = new JLabel("COPIES");
        lblCopies.setHorizontalAlignment(SwingConstants.CENTER);
        lblCopies.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCopies.setBounds(41, 304, 146, 34);
        contentPane.add(lblCopies);

        textField = new JTextField();
        textField.setBounds(249, 74, 161, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(249, 133, 161, 19);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(249, 190, 161, 19);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(249, 251, 161, 19);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(249, 313, 161, 19);
        contentPane.add(textField_4);

        JButton btnNewButton = new JButton("ADD");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.setBounds(102, 385, 117, 34);
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCancel.setBounds(285, 386, 125, 33);
        contentPane.add(btnCancel);
        
        JLabel AddBooksLabel = new JLabel("ADD BOOKS");
        AddBooksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AddBooksLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        AddBooksLabel.setBounds(141, 10, 205, 34);
        contentPane.add(AddBooksLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
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

    private void addBook() {
        String bookId = textField.getText();
        String category = textField_1.getText();
        String name = textField_2.getText();
        String author = textField_3.getText();
        int copies = Integer.parseInt(textField_4.getText());

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO Books (BOOK_ID, Category, Name, Author, Copies) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookId);
            pstmt.setString(2, category);
            pstmt.setString(3, name);
            pstmt.setString(4, author);
            pstmt.setInt(5, copies);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book.");
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
        textField.setText("");
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        textField_4.setText("");
    }
}
