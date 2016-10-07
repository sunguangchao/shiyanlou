import jdk.nashorn.internal.runtime.ECMAException;
import sun.security.rsa.RSASignature;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by 11981 on 2016/10/6.
 */
public class Md5UiFrame extends JFrame{

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    private JFileChooser jfc;


    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Md5UiFrame frame = new Md5UiFrame();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Md5UiFrame(){
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450,300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);

        jfc = new JFileChooser();

        JPanel panel = new JPanel();
        contentPane.add(panel,BorderLayout.CENTER);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Browse");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = jfc.showOpenDialog(null);
                String filepath = "";
                if(result == JFileChooser.APPROVE_OPTION){
                    filepath = jfc.getSelectedFile().getAbsolutePath();
                    textField.setText(filepath);
                }
            }
        });
        btnNewButton.setBounds(307,70,102,28);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Cal MD5");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = jfc.getSelectedFile();
                String fileMd5 = Md5Util.getMd5ByFile(file);
                textField_1.setText(fileMd5);
            }
        });
        btnNewButton_1.setBounds(307,110,102,28);
        panel.add(btnNewButton_1);

        textField = new JTextField();
        textField.setBounds(89,70,206,28);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(89,110,206,28);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblSelectFile = new JLabel("Set File");
        lblSelectFile.setBounds(22,75,59,18);
        panel.add(lblSelectFile);

        JLabel lblMd = new JLabel("MD5");
        lblMd.setBounds(22,115,59,18);
        panel.add(lblMd);
    }
}
