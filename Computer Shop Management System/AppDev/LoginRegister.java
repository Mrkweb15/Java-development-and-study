package AppDev;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class LoginRegister extends JFrame {

	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtRegisterUsername;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	LoginRegister loginRegister = new LoginRegister();
                loginRegister.setVisible(true);
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginRegister() {
    	setTitle("Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1381, 837);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel pnlRegister = new JPanel();
        pnlRegister.setBackground(new Color(0, 128, 192));

        JPanel pnlLogin = new JPanel();
        pnlLogin.setBackground(new Color(255, 255, 128));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(pnlRegister, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        			.addGap(1)
        			.addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addComponent(pnlRegister, GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        		.addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, 798, Short.MAX_VALUE)
        );

        JPanel pnlRegForm = new JPanel();
        pnlRegForm.setBackground(new Color(0, 128, 192));
        pnlRegForm.setVisible(false);

        JPanel pnlRegMes = new JPanel();
        pnlRegMes.setBackground(new Color(0, 128, 192));
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\UserPC\\Desktop\\mrkweb\\images\\nami.png"));

        GroupLayout gl_pnlRegister = new GroupLayout(pnlRegister);
        gl_pnlRegister.setHorizontalGroup(
        	gl_pnlRegister.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_pnlRegister.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_pnlRegister.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, gl_pnlRegister.createSequentialGroup()
        					.addComponent(lblNewLabel_1)
        					.addGap(194))
        				.addGroup(Alignment.TRAILING, gl_pnlRegister.createSequentialGroup()
        					.addGroup(gl_pnlRegister.createParallelGroup(Alignment.TRAILING)
        						.addComponent(pnlRegForm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        						.addComponent(pnlRegMes, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
        					.addContainerGap())))
        );
        gl_pnlRegister.setVerticalGroup(
        	gl_pnlRegister.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlRegister.createSequentialGroup()
        			.addContainerGap(79, Short.MAX_VALUE)
        			.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(pnlRegForm, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(pnlRegMes, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
        			.addGap(47))
        );
        pnlRegMes.setLayout(null);

        JLabel lblRegisterPrompt = new JLabel("You don't have an account yet?");
        lblRegisterPrompt.setBounds(169, 60, 310, 19);
        pnlRegMes.add(lblRegisterPrompt);
        lblRegisterPrompt.setForeground(Color.WHITE);
        lblRegisterPrompt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRegisterPrompt.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnRegisterNow = new JButton("Register now");
        btnRegisterNow.setBounds(266, 90, 109, 23);
        pnlRegMes.add(btnRegisterNow);
        btnRegisterNow.setFont(new Font("SansSerif", Font.PLAIN, 11));

        txtRegisterUsername = new JTextField();
        txtRegisterUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtRegisterUsername.getText().trim().equals("Username")) {
                    txtRegisterUsername.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtRegisterUsername.getText().trim().equals("")) {
                    txtRegisterUsername.setText("Username");
                }
            }
        });
        txtRegisterUsername.setText("Username");
        txtRegisterUsername.setForeground(Color.BLACK);
        txtRegisterUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtRegisterUsername.setColumns(10);

        JPasswordField txtRegisterPassword = new JPasswordField();
        txtRegisterPassword.setEchoChar((char)0);
        txtRegisterPassword.setText("Password");
        txtRegisterPassword.setForeground(Color.BLACK);
        txtRegisterPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtRegisterPassword.setColumns(10);

        txtRegisterPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String password = new String(txtRegisterPassword.getPassword());
                if (password.equals("Password")) {
                    txtRegisterPassword.setText("");
                    txtRegisterPassword.setEchoChar('\u2022');
                    txtRegisterPassword.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(txtRegisterPassword.getPassword());
                if (password.isEmpty()) {
                    txtRegisterPassword.setText("Password");
                    txtRegisterPassword.setEchoChar((char)0);
                    txtRegisterPassword.setForeground(Color.BLACK);
                }
            }
        });

        JPasswordField txtRetypePassword = new JPasswordField();
        txtRetypePassword.setEchoChar((char)0);
        txtRetypePassword.setText("Retype password");
        txtRetypePassword.setForeground(Color.BLACK);
        txtRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtRetypePassword.setColumns(10);

        txtRetypePassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String retypePassword = new String(txtRetypePassword.getPassword());
                if (retypePassword.equals("Retype password")) {
                    txtRetypePassword.setText("");
                    txtRetypePassword.setEchoChar('\u2022');
                    txtRetypePassword.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String retypePassword = new String(txtRetypePassword.getPassword());
                if (retypePassword.isEmpty()) {
                    txtRetypePassword.setText("Retype password");
                    txtRetypePassword.setEchoChar((char)0);
                    txtRetypePassword.setForeground(Color.BLACK);
                }
            }
        });



        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                String username = txtRegisterUsername.getText();
                String password = new String(txtRegisterPassword.getPassword());
                String retypePassword = new String(txtRetypePassword.getPassword());

                if (!password.equals(retypePassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                    return;
                }

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "");
                    String checkUserQuery = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement checkUserStmt = conn.prepareStatement(checkUserQuery);
                    checkUserStmt.setString(1, username);
                    ResultSet rs = checkUserStmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Username already exists.");
                    } else {
                        String insertUserQuery = "INSERT INTO users (username, password, status, isAdmin) VALUES (?, ?, 'offline', 0)";
                        PreparedStatement insertUserStmt = conn.prepareStatement(insertUserQuery);
                        insertUserStmt.setString(1, username);
                        insertUserStmt.setString(2, password);
                        int rowsInserted = insertUserStmt.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "User registered successfully!");
                            txtRegisterUsername.setText("");
                            txtRegisterPassword.setText("");
                            txtRetypePassword.setText("");
                        }
                    }
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred during registration.");
                }
            }
        });

        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel lblNewLabel = new JLabel("Register now");
        lblNewLabel.setForeground(new Color(255, 255, 128));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 27));

        GroupLayout gl_pnlRegForm = new GroupLayout(pnlRegForm);
        gl_pnlRegForm.setHorizontalGroup(
        	gl_pnlRegForm.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlRegForm.createSequentialGroup()
        			.addGap(48)
        			.addGroup(gl_pnlRegForm.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnRegister, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        				.addComponent(txtRegisterUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        				.addComponent(txtRegisterPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        				.addComponent(txtRetypePassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
        			.addGap(56))
        		.addGroup(gl_pnlRegForm.createSequentialGroup()
        			.addContainerGap(237, Short.MAX_VALUE)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
        			.addGap(235))
        );
        gl_pnlRegForm.setVerticalGroup(
        	gl_pnlRegForm.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlRegForm.createSequentialGroup()
        			.addContainerGap(24, Short.MAX_VALUE)
        			.addComponent(lblNewLabel)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txtRegisterUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(txtRegisterPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(txtRetypePassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnRegister)
        			.addGap(25))
        );
        pnlRegForm.setLayout(gl_pnlRegForm);
        pnlRegister.setLayout(gl_pnlRegister);

        JPanel pnlLoginForm = new JPanel();
        pnlLoginForm.setBackground(new Color(255, 255, 128));

        JPanel pnlLoginMes = new JPanel();
        pnlLoginMes.setLayout(null);
        pnlLoginMes.setBackground(new Color(255, 255, 128));
        pnlLoginMes.setVisible(false);

        JLabel lblAlreadyHaveAn = new JLabel("Already have an account?");
        lblAlreadyHaveAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlreadyHaveAn.setForeground(new Color(0, 0, 0));
        lblAlreadyHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblAlreadyHaveAn.setBounds(174, 63, 323, 19);
        pnlLoginMes.add(lblAlreadyHaveAn);

        JButton btnLoginNow = new JButton("Login now");
        btnLoginNow.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnLoginNow.setBounds(277, 87, 109, 23);
        pnlLoginMes.add(btnLoginNow);

        txtUsername = new JTextField();
        txtUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsername.getText().trim().equals("Username")) {
                    txtUsername.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsername.getText().trim().equals("")) {
                    txtUsername.setText("Username");
                }
            }
        });
        txtUsername.setText("Username");
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtUsername.setColumns(10);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setEchoChar((char)0);
        txtPassword.setText("Password");
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPassword.setColumns(10);

        txtPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(txtPassword.getPassword()).trim().equals("Password")) {
                    txtPassword.setText("");
                    txtPassword.setEchoChar('\u2022');
                    txtPassword.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(txtPassword.getPassword()).trim().isEmpty()) {
                    txtPassword.setText("Password");
                    txtPassword.setEchoChar((char)0);
                    txtPassword.setForeground(Color.BLACK);
                }
            }
        });


        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesdacomshop", "root", "");
                    Statement stmt = con.createStatement();
                    String sql = "SELECT * FROM users WHERE username = '" + txtUsername.getText() + "' AND password = '" + txtPassword.getText() + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    
                    if (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String status = rs.getString("status");
                        
                        String enteredUsername = txtUsername.getText();
                        String enteredPassword = txtPassword.getText();
                        
                        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                            if (status.equals("banned")) {
                                JOptionPane.showMessageDialog(null, "Your account is banned. Ask Admin for help.");
                            } else {
                                boolean isAdmin = rs.getBoolean("isAdmin");
                                
                                if (isAdmin) {
                                    JOptionPane.showMessageDialog(null, "Welcome Admin");
                                    setVisible(false);
                                    AdminForm adminForm = new AdminForm();
                                    adminForm.setVisible(true);
                                    
                                } else {
                                    JOptionPane.showMessageDialog(null, "Login Success");
                                    setVisible(false);
                                    UserForm userForm = new UserForm(username);
                                    userForm.setVisible(true);
                                }
                            }
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password");
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }
                    
                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
                
                txtUsername.setText("Username");
                txtPassword.setText("Password");
            }
        });

        
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setForeground(new Color(0, 128, 192));
        lblLogin.setBackground(new Color(0, 0, 0));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 27));

        

        GroupLayout gl_pnlLoginForm = new GroupLayout(pnlLoginForm);
        gl_pnlLoginForm.setHorizontalGroup(
        	gl_pnlLoginForm.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlLoginForm.createSequentialGroup()
        			.addContainerGap(253, Short.MAX_VALUE)
        			.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
        			.addGap(229))
        		.addGroup(gl_pnlLoginForm.createSequentialGroup()
        			.addGap(74)
        			.addGroup(gl_pnlLoginForm.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        				.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        				.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
        			.addGap(55))
        );
        gl_pnlLoginForm.setVerticalGroup(
        	gl_pnlLoginForm.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlLoginForm.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnLogin)
        			.addGap(34))
        );
        pnlLoginForm.setLayout(gl_pnlLoginForm);
        
        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\UserPC\\Desktop\\mrkweb\\images\\brook.png"));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout gl_pnlLogin = new GroupLayout(pnlLogin);
        gl_pnlLogin.setHorizontalGroup(
        	gl_pnlLogin.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlLogin.createSequentialGroup()
        			.addGroup(gl_pnlLogin.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_pnlLogin.createSequentialGroup()
        					.addGap(183)
        					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_pnlLogin.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_pnlLogin.createParallelGroup(Alignment.LEADING)
        						.addComponent(pnlLoginForm, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        						.addComponent(pnlLoginMes, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))))
        			.addContainerGap())
        );
        gl_pnlLogin.setVerticalGroup(
        	gl_pnlLogin.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_pnlLogin.createSequentialGroup()
        			.addContainerGap(81, Short.MAX_VALUE)
        			.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlLoginForm, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(pnlLoginMes, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
        			.addGap(49))
        );
        pnlLogin.setLayout(gl_pnlLogin);
        contentPane.setLayout(gl_contentPane);

        btnRegisterNow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GroupLayout layout = (GroupLayout) contentPane.getLayout();
                layout.setHorizontalGroup(
                    layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(pnlRegister, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
                            .addGap(1))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlRegister, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)
                );
                
                pnlLoginForm.setVisible(false);
                pnlRegForm.setVisible(true);
                pnlLoginMes.setVisible(true);
                pnlRegMes.setVisible(false);
            }
        });

        btnLoginNow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GroupLayout layout = (GroupLayout) contentPane.getLayout();
                layout.setHorizontalGroup(
                    layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pnlRegister, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
                            .addGap(1))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(pnlRegister, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)
                );

                pnlRegForm.setVisible(false);
                pnlLoginForm.setVisible(true);
                pnlRegMes.setVisible(true);
                pnlLoginMes.setVisible(false);
            }
        });

    }
}

