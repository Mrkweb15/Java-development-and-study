package AppDev;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblPinnedPCNo;
    private JLabel lblPinnedUsername;
    private JLabel lblPinnedPrice;
    private JLabel lblPinTimer;
    
    private static final int MENU_WIDTH = 707;
    private static final int MENU_HEIGHT = 400;
    private static final int DISPLAY_PIN_WIDTH = 707;
    private static final int DISPLAY_PIN_HEIGHT = 616;
    private static final int PINNED_WIDTH = 687;
    private static final int PINNED_HEIGHT = 582;
    private static final int PINNED_PC_WIDTH = 667;
    private static final int PINNED_PC_HEIGHT = 358;
    private static final int BUTTON_WIDTH = 89;
    private static final int BUTTON_HEIGHT = 23;
    private static final int LABEL_WIDTH = 149;
    private static final int LABEL_HEIGHT = 45;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color MENU_COLOR = Color.DARK_GRAY;
    private static final Color PINNED_COLOR = Color.LIGHT_GRAY;
    private static final Color LABEL_COLOR = Color.WHITE;
    private static final Color PC_OFF_COLOR = Color.RED;
    private static final Color PC_ON_COLOR = Color.GREEN;
    private static final Font LABEL_FONT = new Font("Tahoma", Font.PLAIN, 15);
    private static final Font PC_NO_FONT = new Font("Leelawadee UI", Font.BOLD, 14);
    private int pinnedPCNumber = -1;
    
    private Map<Integer, Timer> pcTimers = new HashMap<>();
    private JTable tblPCs;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminForm frame = new AdminForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }
    
    //restart/refresh
    public void refreshForm() {
    	this.dispose();
    	AdminForm newAdminForm = new AdminForm();
    	newAdminForm.setVisible(true);
    	System.out.println("AdminForm is being refreshed");
    	}
    
    

    public AdminForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        
        contentPane = new JPanel();
        contentPane.setBackground(BACKGROUND_COLOR);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel pnlMenu = new JPanel();
        pnlMenu.setBackground(MENU_COLOR);
        pnlMenu.setBounds(10, 638, MENU_WIDTH, MENU_HEIGHT);
        contentPane.add(pnlMenu);
        pnlMenu.setLayout(null);
        
        JTable tblPCs = new JTable();
        tblPCs.setForeground(new Color(0, 0, 0));
        tblPCs.setBackground(Color.LIGHT_GRAY);
        tblPCs.setFont(new Font("Tahoma", Font.BOLD, 13));
        tblPCs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"PC Number", "Username"}
        );
        tblPCs.setModel(model);

        loadSessionData(model);

        
        /*
         *  sql = Select pcNumber, username from sessions 
         * */
        tblPCs.setBounds(32, 104, 126, 267);
        pnlMenu.add(tblPCs);
        
        JLabel lblUSEDPC = new JLabel("Active PCs");
        lblUSEDPC.setHorizontalAlignment(SwingConstants.CENTER);
        lblUSEDPC.setForeground(new Color(255, 255, 0));
        lblUSEDPC.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblUSEDPC.setBounds(10, 38, 172, 45);
        pnlMenu.add(lblUSEDPC);
        
        JLabel lblPcInUse = new JLabel("      PC    |   USER");
        lblPcInUse.setForeground(new Color(0, 128, 255));
        lblPcInUse.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPcInUse.setBounds(29, 74, 129, 33);
        pnlMenu.add(lblPcInUse);
        
        JComboBox cbUsers = new JComboBox();
        cbUsers.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbUsers.setMaximumRowCount(9);
        cbUsers.setToolTipText("Select User");
        cbUsers.setBounds(189, 103, 116, 22);
        pnlMenu.add(cbUsers);
        loadUserData(cbUsers);
        
        JLabel lblRegisterdUsers = new JLabel("Registerd users ");
        lblRegisterdUsers.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegisterdUsers.setForeground(new Color(0, 128, 255));
        lblRegisterdUsers.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRegisterdUsers.setBounds(179, 74, 145, 33);
        pnlMenu.add(lblRegisterdUsers);
        
        JButton btnBanning = new JButton("BAN USER");

     cbUsers.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             String selectedUser = (String) cbUsers.getSelectedItem();
             
             if (selectedUser != null) {
                 String currentStatus = getUserStatus(selectedUser);
                 if ("banned".equals(currentStatus)) {
                     btnBanning.setText("UNBAN USER");
                 } else {
                     btnBanning.setText("BAN USER");
                 }
             }
         }
     });

     btnBanning.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             String selectedUser = (String) cbUsers.getSelectedItem();
             
             if (selectedUser == null) {
                 JOptionPane.showMessageDialog(null, "No user selected.");
                 return;
             }
             
             String currentStatus = getUserStatus(selectedUser);
             String newStatus = "banned".equals(currentStatus) ? "offline" : "banned";
             
             updateUserStatus(selectedUser, newStatus);
             
             if ("banned".equals(newStatus)) {
                 btnBanning.setText("UNBAN USER");
             } else {
                 btnBanning.setText("BAN USER");
             }

             DefaultTableModel tableModel = (DefaultTableModel) cbUsers.getModel();
             tableModel.setRowCount(0);
             
             loadUserData(cbUsers);
         }
     });

        btnBanning.setBounds(314, 101, 116, 23);
        pnlMenu.add(btnBanning);
        
        JTextArea txtMSG = new JTextArea();
        txtMSG.setText("Add Message");
        txtMSG.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMSG.getText().trim().equals("Add Message")) {
                    txtMSG.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMSG.getText().trim().equals("")) {
                    txtMSG.setText("Add Message");
                }
            }
        });
        txtMSG.setBackground(Color.LIGHT_GRAY);
        txtMSG.setForeground(Color.BLACK);
        txtMSG.setBounds(189, 202, 227, 88);
        pnlMenu.add(txtMSG);

        JButton btnSend = new JButton("SEND");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        btnSend.setBounds(341, 301, 75, 23);
        pnlMenu.add(btnSend);
        
        JComboBox<String> cbSendTo = new JComboBox<>();
        cbSendTo.setToolTipText("Select User");
        cbSendTo.setMaximumRowCount(9);
        cbSendTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbSendTo.setBounds(215, 301, 116, 22);
        pnlMenu.add(cbSendTo);

        populateUsernames(cbSendTo);

        
        JLabel lblTo = new JLabel("To.");
        lblTo.setForeground(Color.WHITE);
        lblTo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTo.setBounds(189, 301, 30, 23);
        pnlMenu.add(lblTo);

        JButton btnRefresh = new JButton("REFRESH");
        btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		refreshForm();
        	}
        });
        btnRefresh.setBounds(620, 367, 77, 22);
        pnlMenu.add(btnRefresh);
        
        JPanel pnlDisplaypin = new JPanel();
        pnlDisplaypin.setBackground(MENU_COLOR);
        pnlDisplaypin.setBounds(10, 11, DISPLAY_PIN_WIDTH, DISPLAY_PIN_HEIGHT);
        contentPane.add(pnlDisplaypin);
        pnlDisplaypin.setLayout(null);
        
        JPanel pnlPinned_1 = new JPanel();
        pnlPinned_1.setBackground(BACKGROUND_COLOR);
        pnlPinned_1.setBounds(10, 11, PINNED_WIDTH, PINNED_HEIGHT);
        pnlDisplaypin.add(pnlPinned_1);
        pnlPinned_1.setLayout(null);
        
        JPanel pnlPinnedPC = new JPanel();
        pnlPinnedPC.setBackground(PINNED_COLOR);
        pnlPinnedPC.setBounds(10, 83, PINNED_PC_WIDTH, PINNED_PC_HEIGHT);
        pnlPinned_1.add(pnlPinnedPC);
        pnlPinnedPC.setLayout(null);
        
        lblPinnedPCNo = new JLabel("No Pinned PC");
        lblPinnedPCNo.setFont(PC_NO_FONT);
        lblPinnedPCNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblPinnedPCNo.setForeground(LABEL_COLOR);
        lblPinnedPCNo.setBounds((PINNED_WIDTH - 82) / 2, 11, 82, 36);
        pnlPinned_1.add(lblPinnedPCNo);
        
        JButton btnUnpin = addButton(pnlPinned_1, "Unpin", (PINNED_WIDTH - 89) / 3 * 2, 535, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnUnpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unpinPC();
            }
        });
        
        lblPinnedPrice = addLabel(pnlPinned_1, "Price PHP: 00.00", PINNED_WIDTH - LABEL_WIDTH - 20, 452, LABEL_WIDTH, LABEL_HEIGHT);
        lblPinnedUsername = addLabel(pnlPinned_1, "Username:", 10, 42, 287, 30);
        
        lblPinTimer = addLabel(pnlPinned_1, "Time:", 31, 452, 149, 45);
        
        JPanel pnlPCDisplay = new JPanel();
        pnlPCDisplay.setBackground(MENU_COLOR);
        pnlPCDisplay.setBounds(724, 11, 1186, 1027);
        pnlPCDisplay.setLayout(new GridLayout(5, 10, 10, 10));
        contentPane.add(pnlPCDisplay);
        
        for (int i = 101; i <= 150; i++) {
            pnlPCDisplay.add(createPCPanel(i));
        }
    }
    
    private JButton addButton(JPanel panel, String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(578, 537, width, height);
        panel.add(button);
        return button;
    }
    
    private JLabel addLabel(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(LABEL_COLOR);
        lbl.setFont(LABEL_FONT);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setBounds(x, y, width, height);
        panel.add(lbl);
        return lbl;
    }
    
    private JPanel createPCPanel(int pcNumber) {
        JPanel pcPanel = new JPanel();
        pcPanel.setLayout(null);
        pcPanel.setBackground(BACKGROUND_COLOR);
        pcPanel.setBorder(BorderFactory.createLineBorder(PC_OFF_COLOR));

        JLabel lblPCNo = new JLabel("PC " + String.format("%03d", pcNumber));
        lblPCNo.setForeground(LABEL_COLOR);
        lblPCNo.setFont(LABEL_FONT);
        lblPCNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblPCNo.setBounds(10, 10, 80, 30);
        pcPanel.add(lblPCNo);

        JButton btnPower = new JButton("Turn On");
        btnPower.setBounds(10, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
        pcPanel.add(btnPower);
        btnPower.setVisible(false);

        JButton btnPin = new JButton("Pin");
        btnPin.setBounds(10, 90, BUTTON_WIDTH, BUTTON_HEIGHT);
        pcPanel.add(btnPin);

        JLabel lblTimer = new JLabel("00:00:00");
        lblTimer.setForeground(LABEL_COLOR);
        lblTimer.setFont(LABEL_FONT);
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setBounds(10, 130, 100, 30);
        pcPanel.add(lblTimer);

        List<Integer> pcsInUse = getPCsInUse();
        if (pcsInUse.contains(pcNumber)) {
            btnPower.setText("Turn Off");
            pcPanel.setBorder(BorderFactory.createLineBorder(PC_ON_COLOR));
            
            String username = getPCUsername(pcNumber);
            if (username != null) {
                Timestamp startTime = getPCStartTime(pcNumber, username);
                if (startTime != null) {
                    startTimer(pcNumber, lblTimer, startTime);
                }
            }
        }

        btnPower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePCPower(pcNumber, btnPower, pcPanel, lblTimer, "User" + pcNumber);
            }
        });

        btnPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getPCUsername(pcNumber);
                if (username != null) {
                    pinPC(pcNumber, username);
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found for PC " + pcNumber, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return pcPanel;
    }


    
    private List<Integer> getPCsInUse() {
        List<Integer> pcsInUse = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT pcNumber FROM pcs WHERE status = 'in_use'")) {
            while (rs.next()) {
                pcsInUse.add(rs.getInt("pcNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pcsInUse;
    }
    
    private void togglePCPower(int pcNumber, JButton btnPower, JPanel pcPanel, JLabel lblTimer, String username) {
        String status = btnPower.getText().equals("Turn On") ? "in_use" : "available";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("UPDATE pcs SET status = ? WHERE pcNumber = ?")) {
            pstmt.setString(1, status);
            pstmt.setInt(2, pcNumber);
            pstmt.executeUpdate();
            
            if (status.equals("in_use")) {
                btnPower.setText("Turn Off");
                pcPanel.setBorder(BorderFactory.createLineBorder(PC_ON_COLOR));
                
                Timestamp startTime = getPCStartTime(pcNumber, username);
                if (startTime != null) {
                    startTimer(pcNumber, lblTimer, startTime);
                }
            } else {
                btnPower.setText("Turn On");
                pcPanel.setBorder(BorderFactory.createLineBorder(PC_OFF_COLOR));
                stopTimer(pcNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Timestamp getPCStartTime(int pcNumber, String username) {
        Timestamp startTime = null;
        String sql = "SELECT startTime FROM sessions WHERE pcNumber = ? AND username = ? AND endTime IS NULL";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pcNumber);
            pstmt.setString(2, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    startTime = rs.getTimestamp("startTime");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return startTime;
    }

    private void startTimer(int pcNumber, JLabel lblTimer, Timestamp startTime) {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime.getTime();
                int seconds = (int) (elapsedTime / 1000) % 60;
                int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
                int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
                String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                SwingUtilities.invokeLater(() -> {
                    lblTimer.setText(timeString);
                    if (pcNumber == pinnedPCNumber) {
                        lblPinTimer.setText("Time: " + timeString);
                        double price = calculatePCPrice(pcNumber, startTime);
                        lblPinnedPrice.setText(String.format("Price PHP: %.2f", price));
                    }
                });
            }
        });
        timer.start();
        pcTimers.put(pcNumber, timer);
    }


    private double calculatePCPrice(int pcNumber, Timestamp startTime) {
        double ratePer90Seconds = 1.0;
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime.getTime();
        double elapsedTimeInSeconds = elapsedTime / 1000.0;
        double price = (elapsedTimeInSeconds / 90) * ratePer90Seconds;
        return price;
    }

    
    private void pinPC(int pcNumber, String username) {
        pinnedPCNumber = pcNumber;
        Timestamp startTime = getPCStartTime(pcNumber, username);
        
        if (username != null) {
            SwingUtilities.invokeLater(() -> {
                lblPinnedPCNo.setText("PC " + pcNumber);
                lblPinnedUsername.setText("Username: " + username);
                if (startTime != null) {
                    startTimer(pcNumber, lblPinTimer, startTime);
                }
                double price = calculatePCPrice(pcNumber, startTime);
                lblPinnedPrice.setText(String.format("Price PHP: %.2f", price));
            });
        }
    }

    private void unpinPC() {
        if (pinnedPCNumber != -1) {
            stopTimer(pinnedPCNumber);
        }
        pinnedPCNumber = -1;
        SwingUtilities.invokeLater(() -> {
            lblPinnedPCNo.setText("No Pinned PC");
            lblPinnedUsername.setText("Username:");
            lblPinTimer.setText("Time:");
            lblPinnedPrice.setText("Price PHP:00.00");
        });
    }


    private void stopTimer(int pcNumber) {
        Timer timer = pcTimers.get(pcNumber);
        if (timer != null) {
            timer.stop();
            pcTimers.remove(pcNumber);
        }
    }

    private String getPCUsername(int pcNumber) {
        String username = null;
        String sql = "SELECT username FROM sessions WHERE pcNumber = ? AND endTime IS NULL";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pcNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    
    public class DatabaseUtil {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/tesdacomshop";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
    }
    
    private static void loadSessionData(DefaultTableModel model) {
        String query = "SELECT pcNumber, username FROM sessions WHERE endTime IS NULL";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String pcNumber = rs.getString("pcNumber");
                String username = rs.getString("username");
                model.addRow(new Object[]{pcNumber, username});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void loadUserData(JComboBox<String> comboBox) {
        String query = "SELECT username FROM users WHERE isAdmin = 0";  // Exclude admins

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                comboBox.addItem(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String getUserStatus(String username) {
        String query = "SELECT status FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateUserStatus(String username, String newStatus) {
        String query = "UPDATE users SET status = ? WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //MSG
    private void populateUsernames(JComboBox<String> comboBox) {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            String query = "SELECT username FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                while (rs.next()) {
                    String username = rs.getString("username");
                    model.addElement(username);
                }
                
                comboBox.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}
