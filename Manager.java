package AppDev;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminForm extends JFrame {

	private JPanel pnlPinned;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblPinnedPCNo;
    private JLabel lblPinnedUsername;
    private JLabel lblPinnedTimer;
    private JLabel lblPinnedPrice;
    private Timer pinnedTimer;

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

    public AdminForm() {
    	
    	pnlPinned = new JPanel();
    	
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

        JPanel pnlDisplaypin = new JPanel();
        pnlDisplaypin.setBackground(MENU_COLOR);
        pnlDisplaypin.setBounds(10, 11, DISPLAY_PIN_WIDTH, DISPLAY_PIN_HEIGHT);
        contentPane.add(pnlDisplaypin);
        pnlDisplaypin.setLayout(null);

        JPanel pnlPinned = new JPanel();
        pnlPinned.setBackground(BACKGROUND_COLOR);
        pnlPinned.setBounds(10, 11, PINNED_WIDTH, PINNED_HEIGHT);
        pnlDisplaypin.add(pnlPinned);
        pnlPinned.setLayout(null);

        JPanel pnlPinnedPC = new JPanel();
        pnlPinnedPC.setBackground(PINNED_COLOR);
        pnlPinnedPC.setBounds(10, 83, PINNED_PC_WIDTH, PINNED_PC_HEIGHT);
        pnlPinned.add(pnlPinnedPC);
        pnlPinnedPC.setLayout(null);

        lblPinnedPCNo = new JLabel("");
        lblPinnedPCNo.setFont(new Font("Lucida Fax", Font.BOLD, 20));
        lblPinnedPCNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblPinnedPCNo.setForeground(new Color(255, 128, 0));
        lblPinnedPCNo.setBounds((PINNED_PC_WIDTH - 106) / 2, 148, 106, 33);
        pnlPinnedPC.add(lblPinnedPCNo);

        lblPinnedPCNo = new JLabel("No Pinned PC");
        lblPinnedPCNo.setForeground(LABEL_COLOR);
        lblPinnedPCNo.setFont(PC_NO_FONT);
        lblPinnedPCNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblPinnedPCNo.setBounds((PINNED_WIDTH - 82) / 2, 11, 82, 36);
        pnlPinned.add(lblPinnedPCNo);

        addButton(pnlPinned, "Warning", (PINNED_WIDTH - 82) / 2, 535, BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton btnUnpin = addButton(pnlPinned, "Unpin", (PINNED_WIDTH - 89) / 3 * 2, 535, BUTTON_WIDTH,
                BUTTON_HEIGHT);

        btnUnpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unpinPC();
            }
        });

        lblPinnedTimer = addLabel(pnlPinned, "", 20, 452, LABEL_WIDTH, LABEL_HEIGHT);

        lblPinnedPrice = addLabel(pnlPinned, "Price PHP:00.00", PINNED_WIDTH - LABEL_WIDTH - 20, 452, LABEL_WIDTH,
                LABEL_HEIGHT);

        lblPinnedUsername = addLabel(pnlPinned, "Username:", 10, 42, 287, 30);

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
        button.setBounds(x, y, width, height);
        panel.add(button);
        return button;
    }

    private JLabel addLabel(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel lblpinTimer = new JLabel(text);
        lblpinTimer.setForeground(LABEL_COLOR);
        lblpinTimer.setFont(LABEL_FONT);
        lblpinTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblpinTimer.setBounds(x, y, width, height);
        panel.add(lblpinTimer);
        return lblpinTimer;
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

        JButton btnPin = new JButton("Pin");
        btnPin.setBounds(10, 90, BUTTON_WIDTH, BUTTON_HEIGHT);
        pcPanel.add(btnPin);

        JLabel lblTimer = new JLabel("00:00:00");
        lblTimer.setForeground(LABEL_COLOR);
        lblTimer.setFont(LABEL_FONT);
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setBounds(10, 130, 100, 30);
        pcPanel.add(lblTimer);

        // Check if the current PC number is in use and update the display accordingly
        List<Integer> pcsInUse = getPCsInUse();
        if (pcsInUse.contains(pcNumber)) {
            btnPower.setText("Turn Off");
            pcPanel.setBorder(BorderFactory.createLineBorder(PC_ON_COLOR));
        }

        btnPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Pin PC " + pcNumber + "?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    pinPC(pcNumber, lblPCNo.getText(), btnPower, lblTimer);
                }
            }
        });

        btnPower.addActionListener(new ActionListener() {
            Timer timer = new Timer(1000, new ActionListener() {
                int seconds = 0;
                double basePricePerSecond = 0.10;

                @Override
                public void actionPerformed(ActionEvent e) {
                    seconds++;
                    int hour = seconds / 3600;
                    int min = (seconds % 3600) / 60;
                    int sec = seconds % 60;
                    lblTimer.setText(String.format("%02d:%02d:%02d", hour, min, sec));

                    calculateAndDisplayPrice(lblPCNo.getText(), seconds);
                }
            });

            boolean timerInitialized = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnPower.getText().equals("Turn On")) {
                    if (!timerInitialized) {
                        timerInitialized = true;
                    }
                    stopTimer(timer);
                    timer = new Timer(1000, new ActionListener() {
                        int seconds = 0;
                        double basePricePerSecond = 0.10;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            seconds++;
                            int hour = seconds / 3600;
                            int min = (seconds % 3600) / 60;
                            int sec = seconds % 60;
                            lblTimer.setText(String.format("%02d:%02d:%02d", hour, min, sec));

                            calculateAndDisplayPrice(lblPCNo.getText(), seconds);
                        }
                    });
                    btnPower.setText("Turn Off");
                    pcPanel.setBorder(BorderFactory.createLineBorder(PC_ON_COLOR));
                    startTimer(timer);
                    lblTimer.setText("00:00:00");
                } else {
                    btnPower.setText("Turn On");
                    pcPanel.setBorder(BorderFactory.createLineBorder(PC_OFF_COLOR));
                    stopTimer(timer);
                }
            }
        });

        return pcPanel;
    }

    

    private void calculateAndDisplayPrice(String pcName, int seconds) {
        if (pinnedPCNumber != -1) {
            if (pcName.equals("PC " + String.format("%03d", pinnedPCNumber))) {
                int intervals = seconds / 9;
                double basePricePerInterval = 0.10;
                double price = intervals * basePricePerInterval;

                String formattedPrice = String.format("%.2f", price);

                SwingUtilities.invokeLater(() -> {
                    lblPinnedPrice.setText("Price PHP: " + formattedPrice);
                });
            }
        }
    }


    private void startTimer(Timer timer) {
        timer.start();
    }

    private void stopTimer(Timer timer) {
        timer.stop();
    }

    private void pinPC(int pcNumber, String pcName, JButton btnPower, JLabel lblTimer) {
        if (pnlPinned != null) {
            pnlPinned.setVisible(true);
        } else {
            System.out.println("pnlPinned is null");
        }
        lblPinnedPCNo.setText(pcName);
        lblPinnedUsername.setText("Username: SampleUser");
        pinnedPCNumber = pcNumber;

        if (pinnedTimer != null && pinnedTimer.isRunning()) {
            pinnedTimer.stop();
        }

        pinnedTimer = new Timer(1000, e -> {
            lblPinnedTimer.setText(lblTimer.getText());
        });

        if (btnPower.getText().equals("Turn Off")) {
            pinnedTimer.start();
        } else {
            pinnedTimer.stop();
            lblPinnedTimer.setText("Timer: 00:00:00");
        }

        pnlPinned.setVisible(true);
    }

    private void unpinPC() {
        lblPinnedPCNo.setText("");
        lblPinnedUsername.setText("Username:");
        lblPinnedTimer.setText("Timer: 00:00:00");
        lblPinnedPrice.setText("Price PHP: 00.00");

        if (pinnedTimer != null && pinnedTimer.isRunning()) {
            pinnedTimer.stop();
        }

        pinnedPCNumber = -1;

        pnlPinned.setVisible(false);
    }

//database
    private Connection connectToDatabase() throws Exception {
        String url = "jdbc:mysql://localhost:3306/tesdacomshop";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    private List<Integer> getPCsInUse() {
        List<Integer> pcsInUse = new ArrayList<>();
        try (Connection conn = connectToDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT pcNumber FROM pcs WHERE status = 'in_use'")) {

            while (rs.next()) {
                pcsInUse.add(rs.getInt("pcNumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcsInUse;
    }

}
