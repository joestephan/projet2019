/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2019;

import com.alee.laf.text.WebTextField;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static projet2019.Projet2019.annee;

public class Etudiant extends JPanel {

    Collection<Langues> collLangues = new ArrayList();
    File file = new File("Etudiants.out");
    private JPanel IPPanel, IAPanel, EtudiantsPanel, ButtonsPanel, leftPanel, rightPanel;
    private JLabel label1, label2, label3, label11, label12, label13, label14, label15, label21, label22, label23, label24;
    private JTextField numD, anneeO, anneeA;
    public static JTextArea text;
    private JComboBox villeN, Diplome;
    private JCheckBox Fr, An, Ar;
    private ButtonGroup bg;
    private JRadioButton M, F;
    private JButton button1, button2, button3;

    private WebTextField dateN, nomE;
    ArrayList<objetEtudiant> listEtud = new ArrayList();
    ArrayList<objetEtudiant> tempList = new ArrayList();

    public Etudiant() {

        //size of mainPanel 
        setPreferredSize(new Dimension(800, 500));
        setLayout(new FlowLayout());

        JLabel labelSpace = new JLabel("                  ");
        JLabel labelSpace2 = new JLabel("     ");
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        //panels
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());
        leftPanel.setPreferredSize(new Dimension(342, 700));
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        rightPanel.setPreferredSize(new Dimension(327, 700));
        IPPanel = new JPanel();
        IPPanel.setLayout(gbl);
        IPPanel.setPreferredSize(new Dimension(342, 174));
        IAPanel = new JPanel();
        IAPanel.setLayout(gbl);
        IAPanel.setPreferredSize(new Dimension(342, 145));
        EtudiantsPanel = new JPanel();
        ButtonsPanel = new JPanel();
        ButtonsPanel.setLayout(new FlowLayout());
        ButtonsPanel.setPreferredSize(new Dimension(342, 80));

        //labels
        label1 = new JLabel("Infos Personnelles");
        label2 = new JLabel("Info Académiques");
        label3 = new JLabel("Etudiants");

        //label du panel IPPanel
        label11 = new JLabel("Numero Dossier ");
        label12 = new JLabel("Nom Etudiant ");
        label13 = new JLabel("Date Naissance ");
        label14 = new JLabel("Ville Naissance ");
        label15 = new JLabel("Sexe ");

        //labels du panel IAPanel
        label21 = new JLabel("Diplome ");
        label22 = new JLabel("Année Obtenu ");
        label23 = new JLabel("Langues ");
        label24 = new JLabel("Année Acad ");

        //les combobox
        villeN = new JComboBox<>();
        villeN.setModel(new DefaultComboBoxModel(Localite.Localite().toArray()));
        Diplome = new JComboBox<>();
        Diplome.setModel(new DefaultComboBoxModel(Diplomes.Diplomes().toArray()));

        //les textFields du panel IPPanel
        numD = new JTextField(15);
        nomE = new com.alee.laf.text.WebTextField(20);
        nomE.setHideInputPromptOnFocus(false);
        nomE.setInputPrompt("Name FatherName LastName");

        dateN = new com.alee.laf.text.WebTextField(15);
        dateN.setHideInputPromptOnFocus(false);
        dateN.setInputPrompt("JJ/MM/YYYY");

        //les textfields du panel IAPanel
        anneeO = new JTextField(15);

        anneeO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (anneeO.getText().length() >= 4) // limit textfield to 4 characters
                {
                    e.consume();
                }
            }
        });

        anneeA = new JTextField(15);
        anneeA.setEditable(false);

        //les chaeck boxes du panel IAPanel
        Fr = new JCheckBox("Francais ");
        An = new JCheckBox("Anglais         ");
        Ar = new JCheckBox("Arabe ");

        //les radioButtons du IPPanel
        M = new JRadioButton("Male ");
        M.setSelected(true);
        F = new JRadioButton("Female ");

        //ButtonGroup
        bg = new ButtonGroup();
        bg.add(M);
        bg.add(F);

        //les buttons
        button1 = new JButton("Nouveau");
        button1.addActionListener(new nouveauListener());
        button1.setPreferredSize(new Dimension(109, 50));
        button2 = new JButton("Enregistrer");
        button2.addActionListener(new enregistrerListener());
        button2.setPreferredSize(new Dimension(109, 50));
        button3 = new JButton("Afficher");
        button3.addActionListener(new afficherListener());
        button3.setPreferredSize(new Dimension(109, 50));

        //textArea
        text = new JTextArea(20, 30);
        text.setEditable(false);

        leftPanel.add(label1);

        //ippanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label11, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        IPPanel.add(label11);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(numD, gbc);
        IPPanel.add(numD);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label12, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        IPPanel.add(label12);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(nomE, gbc);
        IPPanel.add(nomE);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label13, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        IPPanel.add(label13);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(dateN, gbc);
        IPPanel.add(dateN);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label14, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        IPPanel.add(label14);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(villeN, gbc);
        IPPanel.add(villeN);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label15, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        IPPanel.add(label15);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(M, gbc);
        IPPanel.add(M);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(F, gbc);
        IPPanel.add(F);
        IPPanel.setBackground(Color.gray);

        leftPanel.add(IPPanel);

        leftPanel.add(label2);

        //iapanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label21, gbc);
        IAPanel.add(label21);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(Diplome, gbc);
        IAPanel.add(Diplome);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label22, gbc);
        IAPanel.add(label22);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(anneeO, gbc);
        IAPanel.add(anneeO);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label23, gbc);
        IAPanel.add(label23);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(Fr, gbc);
        IAPanel.add(Fr);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(An, gbc);
        IAPanel.add(An);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(Ar, gbc);
        IAPanel.add(Ar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(label24, gbc);
        IAPanel.add(label24);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbl.setConstraints(anneeA, gbc);
        IAPanel.add(anneeA);
        IAPanel.setBackground(Color.gray);

        leftPanel.add(IAPanel);

        //adding to buttonsPAnel
        ButtonsPanel.add(button1);
        ButtonsPanel.add(button2);
        ButtonsPanel.add(button3);

        leftPanel.add(ButtonsPanel);

        //adding to rightPanel
        rightPanel.add(label3);
        rightPanel.add(text);

        //adding left panel and rightPanel to main panel
        add(leftPanel);
        add(labelSpace2);
        add(rightPanel);
        add(labelSpace);
    }

    //boutton nouveau
    public class nouveauListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            numD.setText("");
            nomE.setText("");
            dateN.setText("");
            M.setSelected(true);
            bg.clearSelection();
            anneeO.setText("");
            Fr.setSelected(false);
            An.setSelected(false);
            Ar.setSelected(false);
            anneeA.setText("");
        }

    }

    public class afficherListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           for(int i = 0; i<listEtud.size();i++){
               text.append(listEtud.get(i).numD.toString() + "   " + listEtud.get(i).nomE.toString() + "\n");
           }
        }
    }

    public class enregistrerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            String anneeUniversitaire, specialite, numeroDossier = null, nomEtudiant = null, dateNaissance = null, villeNaissance = null, sexe = null, diplome = null, langue1 = null, langue2 = null, langue3 = null;
            int anneeObtenue = 0, anneeAcademique = 0;
            Collection langues = new ArrayList();
            boolean successdossier = false, successannee = false, successdate = false, successnom = false;

            anneeUniversitaire = annee.getSelectedItem().toString();

            specialite = Projet2019.specialite.getSelectedItem().toString();
            FileInputStream fi;
            ObjectInputStream oi;

            FileInputStream fin;
            ObjectInputStream oin;
            try {
                fin = new FileInputStream(file);
                oin = new ObjectInputStream(fin);
                tempList = (ArrayList) oin.readObject();
                if (tempList.isEmpty()) {
                    successdossier = true;
                    numeroDossier = numD.getText().toUpperCase();
                } else {
                    for (int i = 0; i < tempList.size(); i++) {

                        if (numD.getText() == tempList.get(i).numD) {
                            JOptionPane.showMessageDialog(null, "ID not available", "Message", JOptionPane.OK_OPTION);
                            successdossier = false;
                        } else {
                            numeroDossier = numD.getText().toUpperCase();
                            successdossier = true;
                        }

                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Error with Etudiants.out");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println("list fadye");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            if (containsDigit(nomE.getText())) {
                successnom = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid name", "Message", JOptionPane.OK_OPTION);
                nomE.setText("");
            } else {
                nomEtudiant = nomE.getText();
                successnom = true;
            }

            if (isValidDate(dateN.getText())) {
                dateNaissance = dateN.getText();
                successdate = true;
            } else {
                successdate = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid date", "Message", JOptionPane.OK_OPTION);
                dateN.setText("");
            }

            int yearN = 0;
            yearN = Integer.parseInt(dateNaissance.substring(dateNaissance.length() - 4, dateNaissance.length()));
            if (yearN < 1940 || yearN > 2000) {
                successdate = false;
                JOptionPane.showMessageDialog(null, "Please enter a reasonable date", "Message", JOptionPane.OK_OPTION);
                dateN.setText("");
            } else {
                successdate = true;
            }

            villeNaissance = villeN.getSelectedItem().toString();

            if (M.isSelected()) {
                sexe = "Male";
            } else {
                sexe = "Female";
            }

            diplome = Diplome.getSelectedItem().toString();

            if (isValidYear(anneeO.getText()) == true) {
                successannee = true;
                anneeObtenue = Integer.parseInt(anneeO.getText());
            } else {
                successannee = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid Year", "Message", JOptionPane.OK_OPTION);
                anneeO.setText("");
            }

            if (Fr.isSelected()) {
                langues.add(new Langues.Francais());
            }
            if (An.isSelected()) {
                langues.add(new Langues.Anglais());
            }
            if (Ar.isSelected()) {
                langues.add(new Langues.Arabe());
            }

            int anneeC = AnneeUniversitaire.anneeCourante;
            String anneeU = annee.getSelectedItem().toString();
            if (anneeU.contains(anneeC + "")) {
                anneeA.setText("1");
            } else {

                String[] parts = annee.getSelectedItem().toString().split("-");
                String part1 = parts[0];
                String part2 = parts[1];
                int res = anneeC - Integer.parseInt(part2);
                anneeA.setText(res + "");
            }
            if (successdossier == true && successdate == true && successnom == true && successannee == true) {

                objetEtudiant oe = new objetEtudiant(anneeUniversitaire, specialite, numeroDossier, nomEtudiant, dateNaissance, villeNaissance, sexe, diplome, anneeObtenue, langues, anneeAcademique);
                listEtud.add(oe);
                ObjectOutputStream out;
                FileOutputStream fo;
                try {
                    fo = new FileOutputStream(file);
                    out = new ObjectOutputStream(fo);
                    out.writeObject(oe+"\n");
                    out.flush();
                    JOptionPane.showMessageDialog(null, "Student added successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Object written to file");
                } catch (FileNotFoundException ex) {
                    System.out.println("Error with Etudiants.out");
                    ex.printStackTrace();
                } catch (IOException ex) {
                    System.out.println("Error with class");
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean isValidDate(String dateN) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateN.trim());
        } catch (ParseException pe) {

            return false;
        }
        return true;
    }

    public boolean isValidYear(String year) {
        boolean validYear = false;
        int yearInt = 0;
        if (year.matches("[0-9]+")) {
            validYear = true;
            yearInt = Integer.parseInt(year);
        }

        if (yearInt < 2012 || yearInt > 2018) {

            validYear = false;

        } else {
            validYear = true;
        }
        return validYear;
    }

    public boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }

        return containsDigit;
    }

}
