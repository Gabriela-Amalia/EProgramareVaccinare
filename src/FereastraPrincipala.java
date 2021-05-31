import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FereastraPrincipala extends JFrame {
    private JLabel welcome = new JLabel("<html>Bine ati venit pe aplicatia \"E-ProgramareVaccinare\"<br>Va rugam sa va autentificati:</html>");
    private JLabel emailLabel = new JLabel("E-mail");
    private JLabel passwordLabel = new JLabel("Parola");
    private JButton creareCont = new JButton("Nu ai cont? Creaza unul acum!");
    private JButton logare = new JButton("Logare");
    private JTextField email = new JTextField();
    private JPasswordField password = new JPasswordField();

    Container container = getContentPane();

    public FereastraPrincipala() {
        super("E-ProgramareVaccinare");

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addACtions();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        welcome.setBounds(15, 1, 300, 80);
        emailLabel.setBounds(50, 80, 50, 30);
        email.setBounds(100, 80, 150, 30);
        passwordLabel.setBounds(50, 125, 50, 30);
        password.setBounds(100, 125, 150, 30);
        logare.setBounds(125, 175, 100, 30);
        creareCont.setBounds(60, 220, 220, 30);

    }

    public void addComponentsToContainer() {
        container.add(welcome);
        container.add(emailLabel);
        container.add(email);
        container.add(passwordLabel);
        container.add(password);
        container.add(logare);
        container.add(creareCont);

    }

    public void addACtions() {
        logare.addActionListener(e -> {
            int cont = ManagerConturi.existaCont(email.getText(), String.valueOf(password.getPassword()));
            if (cont == 0) {
                JOptionPane.showMessageDialog(null, "Nu exista un cont cu datele introduse!");
                email.setText("");
                password.setText("");
            } else {
                JFrame fereastra = new FereastraMeniu(new Utilizator(ManagerConturi.getIdCont(email.getText(), String.valueOf(password.getPassword()))));
                fereastra.setSize(310, 300);
                fereastra.setLocation(600, 320);
                fereastra.setVisible(true);
                fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fereastra.setResizable(false);
                closeWindow();
            }
        });

        creareCont.addActionListener(e -> {
            JFrame fereastra = new CreareContFereastra();
            fereastra.setSize(360, 300);
            fereastra.setLocation(600, 320);
            fereastra.setVisible(true);
            fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fereastra.setResizable(false);
            closeWindow();
        });
    }

    public void closeWindow() {
        this.dispose();
    }

    public static void main(String[] args) {
        JFrame fereastra = new FereastraPrincipala();
        fereastra.setSize(340, 300);
        fereastra.setLocation(600, 320);
        fereastra.setVisible(true);
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.setResizable(false);
    }
}
