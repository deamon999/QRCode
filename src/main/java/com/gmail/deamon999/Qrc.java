package com.gmail.deamon999;

import net.glxn.qrgen.javase.QRCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;

public class Qrc extends JFrame implements ActionListener {
    private JLabel label;
    private JLabel showQrCode;
    private JPanel workPanel;
    private JTextField textToQrCode;
    private JButton button;

    public Qrc() {
        setTitle("QRCode Application");
        setSize(640, 480);


        label = new JLabel("Text to be converted: ");
        showQrCode = new JLabel();
        showQrCode.setPreferredSize(new Dimension(640, 450));
        workPanel = new JPanel();
        textToQrCode = new JTextField();
        textToQrCode.setPreferredSize(new Dimension(300, 28));
        button = new JButton("Convert");
        button.addActionListener(this);


        workPanel.setLayout(new FlowLayout());
        workPanel.add(label);
        workPanel.add(textToQrCode);
        workPanel.add(button);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(workPanel, BorderLayout.NORTH);
        container.add(showQrCode, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button) {
            String text = textToQrCode.getText();
            if (text.length() > 0) {
                ByteArrayOutputStream stream = QRCode.from(text).withSize(640, 450).stream();
                showQrCode.setIcon(new ImageIcon(stream.toByteArray()));
            }
        }
    }
}
