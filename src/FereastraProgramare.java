import javax.swing.*;
import java.awt.*;

public class FereastraProgramare extends JFrame {
    private Utilizator u;
    private JLabel anunt = new JLabel("<html>Alegeti judetul unde doriti sa va programati,<br> dupa care centrul de vaccinare:<html>");
    private JLabel judet = new JLabel("Judet");
    private JComboBox judeteCombo = new JComboBox(ManagerZone.getJudete());
    private JLabel centru = new JLabel("Centru vaccinare");
    private JComboBox centre;
    private JLabel dateLabel = new JLabel("Date disponibile programare:");
    private JComboBox date;
    private JButton confirma = new JButton("Confirma programare");

    Container container = getContentPane();

    public FereastraProgramare(Utilizator u) {
        super("Programare");

        this.u = u;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addACtions();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        anunt.setBounds(15, 1, 300, 80);
        judet.setBounds(50, 80, 50, 30);
        judeteCombo.setBounds(180, 80, 150, 30);
        centru.setBounds(15, 110, 100, 50);
        dateLabel.setBounds(10, 160, 170, 30);
        confirma.setBounds(100, 200, 200, 30);


    }

    public void addComponentsToContainer() {
        container.add(anunt);
        container.add(judet);
        container.add(judeteCombo);
        container.add(centru);
        container.add(dateLabel);
        container.add(confirma);

    }

    public void addACtions() {
        judeteCombo.addActionListener(e -> {
            if (centre != null) container.remove(centre);
            String denumireJudet = String.valueOf(judeteCombo.getSelectedItem());
            int id = ManagerZone.getIdJudet(denumireJudet);
            centre = new JComboBox(ManagerZone.getCentre(id).split("@"));
            centre.setBounds(180, 120, 150, 30);
            container.add(centre);

            centre.addActionListener(e1 -> {
                if (date != null) container.remove(date);
                String denumireCentru = String.valueOf(centre.getSelectedItem());
                int id1 = ManagerZone.getIdCentru(denumireCentru);
                date = new JComboBox(ManagerZone.getDate(id1).split("@"));
                date.setBounds(180, 160, 150, 30);
                container.add(date);
            });

        });

        confirma.addActionListener(e -> {
            String centru = String.valueOf(centre.getSelectedItem());
            ManagerProgramari.memoreazaProgramare(ManagerZone.getIdCentru(centru), String.valueOf(date.getSelectedItem()), u.getId());
            ManagerProgramari.stergeDataVaccin(ManagerZone.getIdCentru(centru), String.valueOf(date.getSelectedItem()));
            closeWindow();
            JFrame fereastra = new FereastraMeniu(u);
            fereastra.setSize(310, 300);
            fereastra.setLocation(600, 320);
            fereastra.setVisible(true);
            fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fereastra.setResizable(false);
        });
    }

    public void closeWindow() {
        this.dispose();
    }



}
