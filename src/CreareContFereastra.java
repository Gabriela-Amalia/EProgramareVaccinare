import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreareContFereastra extends JFrame {
    private JLabel welcome = new JLabel("<html>Va rugam sa introduceti o adresa de email valida. Parola trebuie sa contina 8-16 caractere din care: o majusculă, o minusculă, o cifră si un caracter special (#$*). </html>");
    private JLabel emailLabel = new JLabel("E-mail");
    private JLabel passwordLabel = new JLabel("Parola");
    private JLabel passwordLabelVerificare = new JLabel("Rescrie parola");
    private JButton creareCont = new JButton("Finalizare creare cont");
    private JTextField email = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JPasswordField passwordVerificare = new JPasswordField();

    Container container = getContentPane();

    public CreareContFereastra(){
        super("Creare Cont");

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addACtions();

    }

    public void setLayoutManager(){
        container.setLayout(null);
    }

    public void setLocationAndSize(){
        welcome.setBounds(30, 1, 300, 80);
        emailLabel.setBounds(75, 80, 50, 30);
        email.setBounds(125, 80, 150, 30);
        passwordLabel.setBounds(75, 125, 50, 30);
        password.setBounds(125, 125, 150, 30);
        passwordLabelVerificare.setBounds(35, 175, 120, 30);
        passwordVerificare.setBounds(125, 175, 150, 30);
        creareCont.setBounds(60, 220, 220,30);

    }

    public void addComponentsToContainer(){
        container.add(welcome);
        container.add(emailLabel);
        container.add(email);
        container.add(passwordLabel);
        container.add(password);
        container.add(passwordLabelVerificare);
        container.add(passwordVerificare);
        container.add(creareCont);

    }

    public boolean isValidEmail(String email){
        if(email.indexOf("@")==email.lastIndexOf("@") & email.contains("@"))
        return true;
        return false;
        }

    public boolean verificaParola(String s)
    {
        String sablon = "((?=.*[a-z])(?=.*[0-9])(?=.*[@&%#$])(?=.*[A-Z]).{8,16})";
        Pattern p = Pattern.compile(sablon);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public void addACtions(){

        creareCont.addActionListener(new ActionListener() {

            private Director d =new Director();;
            private AbstractBuilder ab=AbstractBuilder.getInstance();

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isValidEmail(email.getText())){
                    JOptionPane.showMessageDialog(null, "Adresa de mail invalida!");
                    email.setText("");
                    password.setText("");
                    passwordVerificare.setText("");
                }
                else if(!verificaParola(String.valueOf(password.getPassword()))){
                    JOptionPane.showMessageDialog(null, "Parola nu respecta formatul enuntat!");
                    System.out.println(String.valueOf(password.getPassword()));
                    email.setText("");
                    password.setText("");
                    passwordVerificare.setText("");
                }
                else if(!String.valueOf(password.getPassword()).equals(String.valueOf(passwordVerificare.getPassword()))){
                    JOptionPane.showMessageDialog(null, "Parolele nu sunt identice!");
                    email.setText("");
                    password.setText("");
                    passwordVerificare.setText("");
                }
                else {
                            ab.addAdresaDestinatar(email.getText());
                            ab.addSubiect("Creare cont EProgramareVaccinare");
                            ab.addContinut("Buna ziua! Prin aceasta cale va informam ca finalizat crearea contului pe aplicatia EProgramareVaccinare");
                            ab.addUsernamePwd("eprogramare.vaccinare@gmail.com", "vaccin1234!");
                            MesajEmail m = ab.getMesaj();//d.build(ab, ad, s, c);
                            //m.trimiteEmail();

                            ManagerConturi.memoreazaCont(email.getText(),String.valueOf(password.getPassword()));

                    JFrame fereastra = new AdaugaProfilFereastra(ManagerConturi.getIdCont(email.getText(), String.valueOf(password.getPassword())));
                    fereastra.setSize(400, 450);
                    fereastra.setLocation(600, 320);
                    fereastra.setVisible(true);
                    fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fereastra.setResizable(false);
                    closeWindow();

                }
            }
        });
    }

    public void closeWindow()
    {
        this.dispose();
    }

}
