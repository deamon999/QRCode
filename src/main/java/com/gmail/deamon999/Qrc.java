package com.gmail.deamon999;

import net.glxn.qrgen.javase.QRCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;

public class Qrc extends JFrame implements ActionListener {
    private JLabel label;
    private JLabel labelTwo;
    //i added these 2 lines
    private JTextField textToQrCode;
    private JTextField textToQrCodeTwo;
    //
    private JLabel showQrCode;
    private JPanel workPanel;

    private JButton button;

    public Qrc() {
        setTitle("QRCode Application");
        setSize(640, 480);


        label = new JLabel("Text to be converted: ");
        //
        labelTwo = new JLabel("Phone info");
        textToQrCodeTwo = new JTextField();
        textToQrCodeTwo.setPreferredSize(new Dimension(100, 28));
        //
        showQrCode = new JLabel();
        showQrCode.setPreferredSize(new Dimension(640, 450));
        workPanel = new JPanel();
        textToQrCode = new JTextField();
        textToQrCode.setPreferredSize(new Dimension(100, 28));
        button = new JButton("Convert");
        button.addActionListener(this);


        workPanel.setLayout(new FlowLayout());
        workPanel.add(label);
        workPanel.add(textToQrCode);
        //
        workPanel.add(labelTwo);
        workPanel.add(textToQrCodeTwo);
        //
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
            String phone = textToQrCodeTwo.getText();
            User user = new User(text, phone);
            if (text.length() > 0 && phone.length() > 0) {
                ByteArrayOutputStream stream = QRCode.from(user.toString()).withSize(640, 450).stream();
                showQrCode.setIcon(new ImageIcon(stream.toByteArray()));
            }
        }
    }
}
