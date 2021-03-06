

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by 11981 on 2016/9/17.
 */
public class FireEditor extends JFrame {

    private JTextField selectField;//定义一个私有文件的绝对路径文本域对象
    private JTextArea editArea;//定义一个私有的编辑区对象
    private JButton saveBtn;
    private JButton openFileBtn;
    private int level = 0;//定义一个私有的记录目录层次数，其初始值为0

    public FireEditor() {
        this.init();
    }

    public void init() {
        this.setTitle("Editor");
        this.setBounds(300, 50, 600, 650);//设置组件的大小
        selectField = new JTextField(40);//创建一个选择框对象
        openFileBtn = new JButton("Browse");//创建一个按钮对象

        openFileBtn.addActionListener(new ActionListener() {
            //为刚创建的按钮添加监控事件
            @Override
            public void actionPerformed(ActionEvent e) {
                FireEditor.this.level = 0;
                String path = selectField.getText();
                //浏览目录或者文件
                openDirOrFile(path.replaceAll("//","\\\\"));
                //String的replaceAll()方法，是采用正则表达式规则去匹配的。
                // 参数中的“//”在java语言中被解析为“/”，而“\\\\”在java语言中被解析成“\\”，还要经正则表达式转换为“\”。
            }
        });

         //新建一个流布局，并且左对齐的面板
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPanel.setBackground(Color.CYAN);
        upPanel.add(selectField);//将选择框加入画板中
        upPanel.add(openFileBtn);
        this.add(upPanel, BorderLayout.NORTH);

        //创建文本编辑区，并加入到整个布局的中间区域
        editArea = new JTextArea();
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.add(editArea);
        this.add(scrollPane, BorderLayout.CENTER);

        //创建保存按钮，并为按钮添加监控事件
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

    private void saveFile() {//保存文件
        FileDialog dg = new FileDialog(this,"Save File");//创建一个具有指定标题的文件对话框窗口，用于加载文件
        dg.setFile("untitled.txt");//设置需要保存文件的后缀
        dg.setMode(FileDialog.SAVE);//设置为保存模式
        dg.setVisible(true);
        String fileName = dg.getFile();//获取文件名
        String dir = dg.getDirectory();//获取对话框的当前目录

        //根据目录名、文件名创建一个文件，即要保存的目标文件
        File newFile = new File(dir + File.separator+fileName);//通过将给定路径名字符串转换成抽象路径名来创建一个新 File 实例
        PrintWriter pw = null;//PrintWriter：向文本输出流打印对象的格式化表示形式
        try{
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
            String str = editArea.getText();
            pw.println(str);
            pw.flush();//通过将所有已缓冲输出写入基础流来刷新此流。
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }

    //打开目录或文件
    private void openDirOrFile(String absolutePath){
        File file = new File(absolutePath);
        //absolutePath:指定目录或文件的绝对路径名
        if(!(file.exists()))//判断文件或目录是否存在
        {
            editArea.setText("The file does not exits!");
        }else if(file.isDirectory())//判断是否是一个目录
        {
            editArea.setText(null);
            showDir(file);
        }else if(file.isFile())//判断是否是一个文件
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

    //浏览目录建立树形图
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
