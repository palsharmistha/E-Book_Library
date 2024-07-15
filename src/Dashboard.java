import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard frame = new Dashboard("Admin User");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Dashboard(String adminName) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel dashboardLabel = new JLabel("DASHBOARD");
        dashboardLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        dashboardLabel.setBounds(38, 10, 362, 24);
        contentPane.add(dashboardLabel);

        JLabel adminNameLabel = new JLabel("Welcome, " + adminName);
        adminNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        adminNameLabel.setBounds(38, 34, 362, 24);
        contentPane.add(adminNameLabel);

        JButton btnBooksAvailable = new JButton("BOOKS AVAILABLE");
        btnBooksAvailable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Books_Available booksAvailable = new Books_Available();
                booksAvailable.setVisible(true);
            }
        });
        btnBooksAvailable.setBounds(51, 72, 163, 38);
        contentPane.add(btnBooksAvailable);

        JButton btnAddBooks = new JButton("ADD BOOKS");
        btnAddBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                AddBooks addBooks = new AddBooks();
                addBooks.setVisible(true);
            }
        });
        btnAddBooks.setBounds(51, 120, 163, 38);
        contentPane.add(btnAddBooks);

        JButton btnRemoveBooks = new JButton("REMOVE BOOKS");
        btnRemoveBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                RemoveBooks removebook = new RemoveBooks();
                removebook.setVisible(true);
            }
        });
        btnRemoveBooks.setBounds(51, 168, 163, 38);
        contentPane.add(btnRemoveBooks);

        JButton btnStaffDetails = new JButton("STAFF DETAILS");
        btnStaffDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                StaffDetails staffDetails = new StaffDetails();
                staffDetails.setVisible(true);
            }
        });
        btnStaffDetails.setBounds(248, 72, 140, 38);
        contentPane.add(btnStaffDetails);

        JButton btnAddStaff = new JButton("ADD STAFF");
        btnAddStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                AddStaff addstaff = new AddStaff();
                addstaff.setVisible(true);
            }
        });
        btnAddStaff.setBounds(248, 120, 140, 38);
        contentPane.add(btnAddStaff);

        JButton btnRemoveStaff = new JButton("REMOVE STAFF");
        btnRemoveStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                RemoveStaff removestaff = new RemoveStaff();
                removestaff.setVisible(true);
            }
        });
        btnRemoveStaff.setBounds(248, 168, 140, 38);
        contentPane.add(btnRemoveStaff);

        JButton btnEditAdmin = new JButton("EDIT ADMIN");
        btnEditAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                EditAdmin editadmin = new EditAdmin();
                editadmin.setVisible(true);
            }
        });
        btnEditAdmin.setBounds(169, 249, 140, 24);
        contentPane.add(btnEditAdmin);
    }
}
