package AppDev;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String username;
    private JComboBox<String> cbPcNum;
    private JLabel lblTimer;
    private JTextArea txtMessages; // Added JTextArea
    private Timer timer;
    private long sessionStartTime;
	private int pcNumber;
	private Timestamp startTime;
	private JLabel lblPrice;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserForm frame = new UserForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserForm() {
        initialize();
    }

    public UserForm(String username) {
        this();
        this.username = username;
        JLabel lblUsername = new JLabel("Welcome user: " + username);
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Lucida Fax", Font.PLAIN, 15));
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setBounds(295, 42, 221, 44);
        contentPane.add(lblUsername);

        // Load messages after initializing the form
        loadMessages();
    }

    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 841, 686);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        cbPcNum = new JComboBox<>();
        cbPcNum.setFont(new Font("Lucida Fax", Font.BOLD, 15));
        cbPcNum.setMaximumRowCount(10);
        cbPcNum.setBounds(386, 164, 61, 32);
        contentPane.add(cbPcNum);

        loadPcNumbers();

        JButton btnStart = new JButton("Start");
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnStart.setBounds(254, 303, 115, 32);
        btnStart.addActionListener(this::toggleSession);
        contentPane.add(btnStart);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Sure to Logout?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (isSessionActive()) {
                    stopSession();
                }
                setVisible(false);
                LoginRegister mainForm = new LoginRegister();
                mainForm.setVisible(true);
            }
        });
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLogout.setBounds(468, 303, 115, 32);
        contentPane.add(btnLogout);

        JLabel lblAdminMSG = new JLabel("Admin Message: ");
        lblAdminMSG.setForeground(new Color(255, 255, 255));
        lblAdminMSG.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblAdminMSG.setBounds(33, 473, 134, 32);
        contentPane.add(lblAdminMSG);

        lblTimer = new JLabel("TIMER: 00:00:00");
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setForeground(Color.WHITE);
        lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTimer.setBounds(352, 346, 134, 32);
        contentPane.add(lblTimer);

        txtMessages = new JTextArea();
        txtMessages.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtMessages.setEditable(false);
        txtMessages.setLineWrap(true);
        txtMessages.setWrapStyleWord(true);
        txtMessages.setBounds(33, 502, 299, 107);
        contentPane.add(txtMessages);
        
        JButton btnReloadMessage = new JButton("Reload Message");
        btnReloadMessage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loadMessages();
        	}
        });
        btnReloadMessage.setFont(new Font("Tahoma", Font.BOLD, 9));
        btnReloadMessage.setBounds(217, 609, 115, 19);
        contentPane.add(btnReloadMessage);
        
        JLabel lblPrice = new JLabel("PHP: 00.00");
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrice.setForeground(Color.WHITE);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPrice.setBounds(352, 373, 134, 32);
        contentPane.add(lblPrice);
    }
    
    private void toggleSession(ActionEvent e) {
        JButton btnStart = (JButton) e.getSource();
        if ("Start".equals(btnStart.getText())) {
            btnStart.setText("Stop");
            startSession();
        } else {
            btnStart.setText("Start");
            stopSession();
        }
    }

    private void startSession() {
        String pcNumber = (String) cbPcNum.getSelectedItem();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "")) {
            con.setAutoCommit(false);

            try (PreparedStatement pstmtUser = con.prepareStatement("UPDATE users SET status = 'active' WHERE username = ?")) {
                pstmtUser.setString(1, username);
                pstmtUser.executeUpdate();
            }

            try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO sessions (username, pcNumber, startTime, endTime, totalDuration, price) VALUES (?, ?, NOW(), NULL, NULL, NULL)")) {
                pstmt.setString(1, username);
                pstmt.setString(2, pcNumber);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    try (PreparedStatement pstmtPc = con.prepareStatement("UPDATE pcs SET status = 'in_use' WHERE pcNumber = ?")) {
                        pstmtPc.setString(1, pcNumber);
                        pstmtPc.executeUpdate();
                    }
                    cbPcNum.setEnabled(false);

                    try (PreparedStatement pstmtStartTime = con.prepareStatement("SELECT startTime FROM sessions WHERE username = ? AND pcNumber = ? AND endTime IS NULL")) {
                        pstmtStartTime.setString(1, username);
                        pstmtStartTime.setString(2, pcNumber);
                        try (ResultSet rs = pstmtStartTime.executeQuery()) {
                            if (rs.next()) {
                                Timestamp startTime = rs.getTimestamp("startTime");
                                sessionStartTime = startTime.getTime();
                                startTimer();
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Session started successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to start session.");
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Failed to start session: " + e.getMessage());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void stopSession() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "")) {
            con.setAutoCommit(false);
            String pcNumber = (String) cbPcNum.getSelectedItem();

            String durationQuery = "TIMESTAMPDIFF(SECOND, startTime, NOW())";

            try (PreparedStatement pstmtUpdateSession = con.prepareStatement("UPDATE sessions SET endTime = NOW(), totalDuration = " + durationQuery + " WHERE username = ? AND pcNumber = ? AND endTime IS NULL")) {
                pstmtUpdateSession.setString(1, username);
                pstmtUpdateSession.setString(2, pcNumber);
                pstmtUpdateSession.executeUpdate();
            }

            try (PreparedStatement pstmtUpdatePc = con.prepareStatement("UPDATE pcs SET status = 'available' WHERE pcNumber = ?")) {
                pstmtUpdatePc.setString(1, pcNumber);
                pstmtUpdatePc.executeUpdate();
            }

            cbPcNum.setEnabled(true);
            stopTimer();

            JOptionPane.showMessageDialog(null, "Session stopped successfully!");
            con.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error stopping session: " + e.getMessage());
        }
    }

    private void startTimer() {
        final double ratePer90Seconds = 1.0;
        lblPrice = (JLabel) contentPane.getComponent(7);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - sessionStartTime;
                    double elapsedTimeInSeconds = elapsedTime / 1000.0;
                    double price = (elapsedTimeInSeconds / 90) * ratePer90Seconds;

                    int hours = (int) (elapsedTime / (1000 * 60 * 60));
                    int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
                    int seconds = (int) ((elapsedTime / 1000) % 60);

                    lblTimer.setText(String.format("TIMER: %02d:%02d:%02d", hours, minutes, seconds));
                    lblPrice.setText(String.format("Price PHP: %.2f", price));
                });
            }
        }, 0, 1000);
    }


    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private boolean isSessionActive() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "");
             PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM sessions WHERE username = ? AND endTime IS NULL")) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error checking session status: " + e.getMessage());
        }
        return false;
    }

    private void loadPcNumbers() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "");
             PreparedStatement pstmt = con.prepareStatement("SELECT pcNumber FROM pcs WHERE status = 'available'");
             ResultSet rs = pstmt.executeQuery()) {
            cbPcNum.removeAllItems();
            while (rs.next()) {
                cbPcNum.addItem(rs.getString("pcNumber"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading PC numbers: " + e.getMessage());
        }
    }

    private void loadMessages() {
        String query = "SELECT message FROM msgs WHERE username = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "");
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, username); // Set the current username in the query
            
            try (ResultSet rs = pstmt.executeQuery()) {
                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append(rs.getString("message")).append("\n"); // Append each message to the StringBuilder
                }
                txtMessages.setText(sb.toString()); // Set the text area with the collected messages
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading messages: " + e.getMessage()); // Show error if any
        }
    }
}
