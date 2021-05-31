import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

public class AdaugaProfilFereastra extends JFrame {
    private int idCont;
    private JLabel anunt = new JLabel("<html>Va rugam sa completati campurile ce urmeaza pentru <br>finalizarea procesului d einregistrare in aplicatie<html>");
    private JLabel nume = new JLabel("Nume");
    private JLabel prenume = new JLabel("Prenume");
    private JLabel cnp = new JLabel("CNP");
    private JLabel serie = new JLabel("Serie carte de identitate");
    private JLabel numar = new JLabel("Numar carte de identitate");
    private JLabel judet = new JLabel("Judet/Rezidenta");
    private JLabel localitate = new JLabel("Localitate");
    private JTextField numeField = new JTextField(20);
    private JTextField prenumeField = new JTextField(20);
    private JTextField cnpField = new JTextField(20);
    private JTextField serieField = new JTextField(20);
    private JTextField numarField = new JTextField(20);
    private JComboBox judeteCombo = new JComboBox(ManagerZone.getJudete());
    private JComboBox localitati;
    private JButton confirma = new JButton("Confirma Date");

    Container container = getContentPane();

    public AdaugaProfilFereastra(int idCont) {
        super("Completare profil");

        this.idCont = idCont;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActions();
        judeteCombo.setEditable(false);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        anunt.setBounds(15, 1, 300, 80);
        nume.setBounds(50, 80, 50, 30);
        numeField.setBounds(160, 80, 150, 30);
        prenume.setBounds(30, 110, 70, 50);
        prenumeField.setBounds(160, 120, 150, 30);
        cnp.setBounds(50, 160, 100, 30);
        cnpField.setBounds(160, 160, 150, 30);
        serie.setBounds(10, 200, 140, 30);
        serieField.setBounds(160, 200, 150, 30);
        numar.setBounds(10, 240, 150, 30);
        numarField.setBounds(160, 240, 150, 30);
        judet.setBounds(10, 280, 140, 30);
        judeteCombo.setBounds(160, 280, 150, 30);
        localitate.setBounds(30, 320, 140, 30);
        confirma.setBounds(95, 375, 140, 30);


    }

    public void addComponentsToContainer() {
        container.add(anunt);
        container.add(nume);
        container.add(numeField);
        container.add(prenume);
        container.add(prenumeField);
        container.add(cnp);
        container.add(cnpField);
        container.add(serie);
        container.add(serieField);
        container.add(numar);
        container.add(numarField);
        container.add(judet);
        container.add(judeteCombo);
        container.add(localitate);
        container.add(confirma);
    }

    public boolean verificaDate(){
        String CNP = cnpField.getText();
        if(!(CNP.length()==13 & Pattern.matches("[0-9]+",CNP))){
            JOptionPane.showMessageDialog(null, "CNP invalid");
            return false;
        }

        String[] seriiBuletin = {"AX", "TR", "AR", "XC", "ZC", "MM", "XM", "XB", "XT", "BV", "ZV", "XR", "DP", "DR", "DT", "DX", "RD", "RR", "RT", "RX","RK", "IF", "XZ", "KL", "KX", "CJ", "KT", "KZ", "DX", "DZ", "HD", "VN", "GL", "ZL", "GG", "MX", "MZ", "MH", "HR", "XH", "ZH", "NT", "AS", "AZ", "PH", "PX", "KS", "VX", "SM", "KV", "SB", "OT", "SZ", "SV", "XV", "TM", "TZ", "DD", "GZ", "ZS", "MS", "TC", "VS", "SX"};
        String serie = serieField.getText();
        if(!Arrays.asList(seriiBuletin).contains(serie)){
            JOptionPane.showMessageDialog(null, "Serie buletin invalida");
            return false;
        }

        String numarCI = numarField.getText();
        if(!(numarCI.length()==6 & Pattern.matches("[0-9]+",numarCI))){
            System.out.println(numarCI.length()==6);
            System.out.println(numarCI.length());
            System.out.println(Pattern.matches("[0-9]+",numarCI));
            JOptionPane.showMessageDialog(null, "Numar buletin invalid");
            return false;
        }

        return true;
    }

    public void addActions() {
        judeteCombo.addActionListener(e -> {
            if(localitati!=null) container.remove(localitati);
            String denumireJudet = String.valueOf(judeteCombo.getSelectedItem());
            int id = ManagerZone.getIdJudet(denumireJudet);
            localitati = new JComboBox(ManagerZone.getLocalitati(id).split("@"));
            localitati.setBounds(160, 320, 150, 30);
            container.add(localitati);

        });

        confirma.addActionListener(e -> {
            if(verificaDate()){
                ManagerConturi.memoreazaProfil(numeField.getText(), prenumeField.getText(), cnpField.getText(), serieField.getText(), numarField.getText(), String.valueOf(judeteCombo.getSelectedItem()),  String.valueOf(localitati.getSelectedItem()), idCont);
                JFrame fereastra = new FereastraMeniu(new Utilizator(idCont));
                fereastra.setSize(310, 300);
                fereastra.setLocation(600, 320);
                fereastra.setVisible(true);
                fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fereastra.setResizable(false);
                closeWindow();
            }
        });

    }

    public void closeWindow() {
        this.dispose();
    }

}
