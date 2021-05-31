import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FereastraMeniu extends JFrame {
    private Utilizator u;
    private JButton afiseazaDateProfil = new JButton("Afiseaza informatii profil");
    private JButton programeazaVaccinare = new JButton("Programeaza vaccinare");
    private JButton anulareProgramare = new JButton("Anuleaza programarea la vaccin");

    Container container = getContentPane();

    public FereastraMeniu(Utilizator u) {
        super("E-ProgramareVaccinare");
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
        afiseazaDateProfil.setBounds(15, 20, 250, 50);
        programeazaVaccinare.setBounds(15, 100, 250, 50);
        anulareProgramare.setBounds(15, 180, 250, 50);
    }

    public void addComponentsToContainer() {
        container.add(afiseazaDateProfil);
        container.add(programeazaVaccinare);
        container.add(anulareProgramare);
    }

    public void addACtions() {
        afiseazaDateProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = "Datele profilului sunt urmatoarele: \n" + ManagerConturi.getProfilFromCont(u.getId());
                JOptionPane.showMessageDialog(null, date);
            }
        });

        programeazaVaccinare.addActionListener(e -> {
            JFrame fereastra = new FereastraProgramare(u);
        fereastra.setSize(400, 300);
        fereastra.setLocation(600, 320);
        fereastra.setVisible(true);
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.setResizable(false);
        closeWindow();
        });

        anulareProgramare.addActionListener(e -> {
            if(ManagerProgramari.existaProgramare(u.getId()).equals("")){
                JOptionPane.showMessageDialog(this, "Nu exista nicio programare inregistrata");
            }
            else {
                String[] info = ManagerProgramari.existaProgramare(u.getId()).split("@");
                ManagerProgramari.stergeProgramare(u.getId());
                ManagerProgramari.memoreazaDateVaccin(Integer.parseInt(info[0]), info[1]);
                JOptionPane.showMessageDialog(this, "Programarea dumneavoastra a fost anulata");

            }
        });


    }

    public void closeWindow() {
        this.dispose();
    }

}
