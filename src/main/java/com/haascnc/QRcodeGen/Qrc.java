package com.haascnc.QRcodeGen;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import net.glxn.qrgen.javase.QRCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private JButton buttonPdf;

    public Qrc() {
        setTitle("QRCode Application");
        setSize(640, 480);


        label = new JLabel("Text to be converted: ");
        //
        labelTwo = new JLabel("Phone info");
        textToQrCodeTwo = new JTextField();
        textToQrCodeTwo.setPreferredSize(new Dimension(90, 28));
        //
        showQrCode = new JLabel();
        showQrCode.setPreferredSize(new Dimension(640, 450));
        workPanel = new JPanel();
        textToQrCode = new JTextField();
        textToQrCode.setPreferredSize(new Dimension(90, 28));
        button = new JButton("Convert");
        buttonPdf = new JButton("Write PDF");
        button.addActionListener(this);
        buttonPdf.addActionListener(this);


        workPanel.setLayout(new FlowLayout());
        workPanel.add(label);
        workPanel.add(textToQrCode);
        //
        workPanel.add(labelTwo);
        workPanel.add(textToQrCodeTwo);
        //
        workPanel.add(button);
        workPanel.add(buttonPdf);
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
        if (source == buttonPdf) {
            String text = textToQrCode.getText();
            String phone = textToQrCodeTwo.getText();
            User user = new User(text, phone);
            if (text.length() > 0 && phone.length() > 0) {
                JFileChooser jFileChooser = new JFileChooser();
                int result = jFileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    Document document = new Document();
                    try {
                        PdfWriter.getInstance(document, new FileOutputStream(selectedFile.toString() + ".pdf"));
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    document.open();
                    document.newPage();
                    Image image = null;
                    try {
                        image = Image.getInstance(QRCode.from(user.toString()).stream().toByteArray());
                    } catch (BadElementException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    image.setAbsolutePosition(0, 0);
                    image.setBorderWidth(0);
                    image.scaleAbsolute(PageSize.A4);
                    try {
                        document.add(image);
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    }
                    document.close();
                }
            }
        }
    }
}
