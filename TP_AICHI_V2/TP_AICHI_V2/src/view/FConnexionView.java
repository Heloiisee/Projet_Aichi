package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class FConnexionView extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JTextField txtSaisieUtilisateur;
    private final JPasswordField pwdMotDePasse;
    private final JButton btnQuitter;
    private final JButton btnValider;


    /**
     * Create the frame.
     */
    public FConnexionView() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        setTitle("Gestion Aichi");

        JPanel panel_global = new JPanel();
        panel_global.setBorder(new CompoundBorder(new EmptyBorder(14, 14, 14, 14), new LineBorder(new Color(0x33, 0XB5, 0XE5), 3, true)));
        getContentPane().add(panel_global, BorderLayout.CENTER);
        panel_global.setLayout(new BorderLayout(0, 0));


        JPanel panel_connexion = new JPanel();
        panel_connexion.setBackground(Color.WHITE);
        panel_connexion.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel_global.add(panel_connexion);
        GridBagLayout gbl_panel_connexion = new GridBagLayout();
        gbl_panel_connexion.columnWidths = new int[]{134, 525, 0};
        gbl_panel_connexion.rowHeights = new int[]{26, 26, 66, 0};
        gbl_panel_connexion.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_connexion.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_connexion.setLayout(gbl_panel_connexion);

        JLabel lblUtilisateur = new JLabel("Nom d'utilisateur");
        lblUtilisateur.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUtilisateur.setHorizontalTextPosition(SwingConstants.LEADING);
        GridBagConstraints gbc_lblUtilisateur = new GridBagConstraints();
        gbc_lblUtilisateur.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblUtilisateur.anchor = GridBagConstraints.LINE_END;
        gbc_lblUtilisateur.insets = new Insets(0, 0, 5, 5);
        gbc_lblUtilisateur.gridx = 0;
        gbc_lblUtilisateur.gridy = 0;
        panel_connexion.add(lblUtilisateur, gbc_lblUtilisateur);

        txtSaisieUtilisateur = new JTextField();
        GridBagConstraints gbc_txtSaisieUtilisateur = new GridBagConstraints();
        gbc_txtSaisieUtilisateur.weightx = 1.0;
        gbc_txtSaisieUtilisateur.anchor = GridBagConstraints.NORTH;
        gbc_txtSaisieUtilisateur.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSaisieUtilisateur.insets = new Insets(0, 0, 5, 0);
        gbc_txtSaisieUtilisateur.gridx = 1;
        gbc_txtSaisieUtilisateur.gridy = 0;
        panel_connexion.add(txtSaisieUtilisateur, gbc_txtSaisieUtilisateur);
        txtSaisieUtilisateur.setColumns(10);

        JLabel lblMotDePasse = new JLabel("Mot de passe");
        lblMotDePasse.setHorizontalAlignment(SwingConstants.TRAILING);
        lblMotDePasse.setHorizontalTextPosition(SwingConstants.LEADING);
        GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
        gbc_lblMotDePasse.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblMotDePasse.anchor = GridBagConstraints.LINE_END;
        gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
        gbc_lblMotDePasse.gridx = 0;
        gbc_lblMotDePasse.gridy = 1;
        panel_connexion.add(lblMotDePasse, gbc_lblMotDePasse);

        pwdMotDePasse = new JPasswordField();
        pwdMotDePasse.setText("javaSestSuper");
        GridBagConstraints gbc_pwdMotDePasse = new GridBagConstraints();
        gbc_pwdMotDePasse.weightx = 1.0;
        gbc_pwdMotDePasse.anchor = GridBagConstraints.NORTH;
        gbc_pwdMotDePasse.fill = GridBagConstraints.HORIZONTAL;
        gbc_pwdMotDePasse.insets = new Insets(0, 0, 5, 0);
        gbc_pwdMotDePasse.gridx = 1;
        gbc_pwdMotDePasse.gridy = 1;
        panel_connexion.add(pwdMotDePasse, gbc_pwdMotDePasse);

        JTextPane txtpnInfo = new JTextPane();
        txtpnInfo.setText("Veuillez saisir \nle nom de l'utilisateur et le mot de passe \npour accéder à l'application");
        txtpnInfo.setForeground(Color.GRAY);
        txtpnInfo.setBackground(Color.WHITE);
        GridBagConstraints gbc_txtpnInfo = new GridBagConstraints();
        gbc_txtpnInfo.insets = new Insets(5, 0, 0, 0);
        gbc_txtpnInfo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtpnInfo.gridwidth = 2;
        gbc_txtpnInfo.gridx = 0;
        gbc_txtpnInfo.gridy = 2;
        panel_connexion.add(txtpnInfo, gbc_txtpnInfo);

        btnValider = new JButton("Valider");
        contentPane.add(btnValider);
        btnQuitter = new JButton("Quitter");
        contentPane.add(btnQuitter);



        JPanel panel_actions = new JPanel();
        panel_actions.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel_actions, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_actions = new GridBagLayout();

        gbl_panel_actions.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel_actions.rowHeights = new int[]{0, 0};
        gbl_panel_actions.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_actions.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_actions.setLayout(gbl_panel_actions);



        setSize(550, 370);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public String getLogin() {

        return txtSaisieUtilisateur.getText();
    }

    public String getMotDePasse() {

        return new String(pwdMotDePasse.getPassword());
    }


    // Méthodes pour ajouter les listeners sur les boutons
    public void addValiderListener(ActionListener listener) {
        btnValider.addActionListener(listener);
    }

    public void addQuitterListener(ActionListener listener) {
        btnQuitter.addActionListener(listener);
    }

    // Méthodes pour obtenir les boutons
    public JButton getBtnValider() {
        return btnValider;
    }

    public JButton getBtnQuitter() {
        return btnQuitter;
    }



    public void showMessage(String message) {
        // Ensure this runs on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message);
        });
    }


    public void fermerFenetre() {
        dispose();
    }


}