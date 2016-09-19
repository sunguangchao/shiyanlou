

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by 11981 on 2016/9/17.
 */
public class FireEditor extends JFrame {

    private JTextField selectField;//����һ��˽���ļ��ľ���·���ı������
    private JTextArea editArea;//����һ��˽�еı༭������
    private JButton saveBtn;
    private JButton openFileBtn;
    private int level = 0;//����һ��˽�еļ�¼Ŀ¼����������ʼֵΪ0

    public FireEditor() {
        this.init();
    }

    public void init() {
        this.setTitle("Editor");
        this.setBounds(300, 50, 600, 650);//��������Ĵ�С
        selectField = new JTextField(40);//����һ��ѡ������
        openFileBtn = new JButton("Browse");//����һ����ť����

        openFileBtn.addActionListener(new ActionListener() {
            //Ϊ�մ����İ�ť��Ӽ���¼�
            @Override
            public void actionPerformed(ActionEvent e) {
                FireEditor.this.level = 0;
                String path = selectField.getText();
                //���Ŀ¼�����ļ�
                openDirOrFile(path.replaceAll("//","\\\\"));
                //String��replaceAll()�������ǲ���������ʽ����ȥƥ��ġ�
                // �����еġ�//����java�����б�����Ϊ��/��������\\\\����java�����б������ɡ�\\������Ҫ��������ʽת��Ϊ��\����
            }
        });

         //�½�һ�������֣��������������
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPanel.setBackground(Color.CYAN);
        upPanel.add(selectField);
        upPanel.add(openFileBtn);
        this.add(upPanel, BorderLayout.NORTH);

        //�����ı��༭���������뵽�������ֵ��м�����
        editArea = new JTextArea();
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.add(editArea);
        this.add(scrollPane, BorderLayout.CENTER);

        //�������水ť����Ϊ��ť��Ӽ���¼�
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.green);
        southPanel.add(saveBtn);
        this.add(southPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void saveFile() {
        FileDialog dg = new FileDialog(this,"save file");
        dg.setFile("untitled.txt");
        dg.setMode(FileDialog.SAVE);//����Ϊ����ģʽ
        dg.setVisible(true);
        String fileName = dg.getFile();//��ȡ�ļ���
        String dir = dg.getDirectory();//��ȡ�Ի���ĵ�ǰĿ¼

        //����Ŀ¼�����ļ�������һ���ļ�����Ҫ�����Ŀ���ļ�
        File newFile = new File(dir + File.separator+fileName);
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
            String str = editArea.getText();
            pw.println(str);
            pw.flush();
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }

    private void openDirOrFile(String absolutePath){
        File file = new File(absolutePath);
        //absolutePath:ָ��Ŀ¼���ļ��ľ���·����
        if(!(file.exists()))//�ж��ļ���Ŀ¼�Ƿ����
        {
            editArea.setText("The file does not exits!");
        }else if(file.isDirectory())
        {
            editArea.setText(null);
            showDir(file);
        }else if(file.isFile())//�ж��Ƿ���һ���ļ�
        {
            try{
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String str = null;
                editArea.setText(null);
                while ((str = br.readLine()) != null){
                    editArea.append(str + "\r\n");
                }
                br.close();

            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    //���Ŀ¼��������ͼ
    private void showDir(File directory)
    {
        File[] files = directory.listFiles();
        int len = files.length;
        for(int i = 0; i < len; i++)
        {
            if(files[i].isDirectory())
            {
                for(int j = 0; j < this.level; j++)
                {
                    editArea.append("    ");
                }
                editArea.append("|-- "+ files[i].getName() + " (Folder)\r\n");
                this.level++;
                showDir(files[i]);
                this.level--;
            }
            else if(files[i].isFile())
            {
                for(int j = 0; j < this.level; j++)
                {
                    editArea.append("    ");
                }
                editArea.append("|-- " + files[i].getAbsolutePath() + "\r\n");
            }
        }

    }

    public static void main(String[] args){
        new FireEditor();
    }

}
