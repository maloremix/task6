package com.company;

// Пример работы со стандартной моделью таблицы JTable

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table1;
    private JTextArea area;
    private int x = 1;
    private int number = 1;

    private void removeColumn(int index, JTable myTable) {
        System.out.println(index);
        int nRow = myTable.getRowCount();
        int nCol = myTable.getColumnCount() - 1;
        Object[][] cells = new Object[nRow][nCol];
        String[] names = new String[nCol];

        for (int j = 0; j < nCol; j++) {
            if (j < index) {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j);
                }
            } else {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j + 1);
                }
            }
        }

        tableModel = new DefaultTableModel(cells, names);
        myTable.setModel(tableModel);
        number--;
    }

    public Interface() {
        super("Пример использования TableModel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание стандартной модели
        tableModel = new DefaultTableModel();
        table1 = new JTable(tableModel);
        table1.setRowHeight(200);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setColumnSelectionAllowed(true);
        JPanel gui = new JPanel(new GridLayout(0, 1, 10, 10));
        gui.setBorder(new EmptyBorder(20, 30, 20, 30));


        Insets margin = new Insets(20, 150, 20, 150);
        JButton b = new JButton("Собственная реализация");
        JButton v = new JButton("Java реализация");
        b.setMargin(margin);
        gui.add(b);
        gui.add(v);

        JFrame f = new JFrame("Centered Single Column of Buttons");
        f.add(gui);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();


        f.pack();

        f.setMinimumSize(f.getSize());

        f.setLocationRelativeTo(null);

        f.setVisible(true);
        System.out.println(b.getSize());
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                f.setVisible(false);
                setVisible(true);
                x = 0;
            }
        });
        v.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                f.setVisible(false);
                setVisible(true);
            }
        });
        JButton add3 = new JButton("Из txt");
        add3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer[][] dv;
                    JFileChooser jfc = new JFileChooser(".");
                    jfc.setDialogType(JFileChooser.OPEN_DIALOG);
                    if (jfc.showOpenDialog(Interface.this) != JFileChooser.APPROVE_OPTION)
                        return;
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    File file = new File(path);
                    Scanner scanner = null;
                    scanner = new Scanner(file);
                    String s = "";
                    while (scanner.hasNextLine()) {
                        s = s + scanner.nextLine() + "\n";
                    }
                    area.insert(s,0);
                } catch (Exception v) {
                    System.out.println("Введите нормальное имя");
                }
            }
        });

        JButton add5 = new JButton("Вычислить");
        add5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[][] dv2 = new int[table1.getRowCount()][table1.getColumnCount()];
                for (int i = 0; i <= table1.getRowCount() - 1; i++) {
                    for (int j = 0; j <= table1.getColumnCount() - 1; j++) {
                        dv2[i][j] = Integer.parseInt(String.valueOf(table1.getValueAt(i, j)));
                    }
                }
                if (x == 0) {
                        String SolString ="";
                        ArrayList<String> Solution = ownHashMapReal.isk(area.getText());
                    for (String s : Solution) {
                        SolString = SolString + s + "\n";
                    }
                        JOptionPane.showMessageDialog(null,SolString);
                } else {
                    String SolString ="";
                    ArrayList<String> Solution = javaHashMapReal.isk(area.getText());
                    for (String s : Solution) {
                        SolString = SolString + s + "\n";
                    }
                    JOptionPane.showMessageDialog(null,SolString);
                }
            }
        });


        Box contents = new Box(BoxLayout.Y_AXIS);
        area = new JTextArea();
        contents.add(new JScrollPane(area));
        getContentPane().add(contents);

        JPanel buttons = new JPanel();
        buttons.add(add3);
        buttons.add(add5);
        getContentPane().add(buttons, "South");
        // Вывод окна на экран
        setSize(900, 300);
        setLocationRelativeTo(null);
    }
}
