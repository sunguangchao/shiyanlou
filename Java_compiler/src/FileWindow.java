import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by 11981 on 2016/9/20.
 */
@SuppressWarnings("serial")//压制信息
public class FileWindow extends JFrame implements ActionListener,Runnable{

    //定义两个线程，一个用于编译，一个用于运行程序
    Thread compiler = null;
    Thread run_prom = null;
    boolean bn = true;
    CardLayout mycard;//声明布局
    File file_saved = null;
    JButton button_input_txt,//定义按钮
            button_compiler_text,
            button_compiler,
            button_run_prom,
            button_see_doswin;
    JPanel p = new JPanel();
    JTextArea input_text = new JTextArea();//程序输入区
    JTextArea compiler_text = new JTextArea();//编译错误显示区
    JTextArea dos_out_text = new JTextArea();//程序的输出信息

    JTextField input_file_name_text = new JTextField();
    JTextField run_file_name_text = new JTextField();

    public FileWindow()
    {
        super("Java语言编译器");
        mycard = new CardLayout();
        compiler = new Thread(this);
        run_prom = new Thread(this);
        button_input_txt = new JButton("程序输入区（白色）");
        button_compiler_text = new JButton("编译结果区（粉红色）");
        button_see_doswin = new JButton("程序运行结果（浅蓝色）");
        button_compiler = new JButton("编译程序");
        button_run_prom = new JButton("运行程序");

        p.setLayout(mycard);//设置卡片布局
        p.add("input",input_text);//定义卡片名称
        p.add("compiler",compiler_text);
        p.add("dos",dos_out_text);
        add(p,"Center");

        compiler_text.setBackground(Color.pink);
        dos_out_text.setBackground(Color.cyan);
        JPanel p1 = new JPanel();

        p1.setLayout(new GridLayout(3,3));//设置表格布局

        p1.add(button_input_txt);
        p1.add(button_compiler_text);
        p1.add(button_see_doswin);
        p1.add(new JLabel("输入编译文件名（.java）："));
        p1.add(input_file_name_text);
        p1.add(button_compiler);
        p1.add(new JLabel("输入应用程序主类名"));
        p1.add(run_file_name_text);
        p1.add(button_run_prom);
        add(p1,"North");

        //定义事件
        button_input_txt.addActionListener(this);
        button_compiler.addActionListener(this);
        button_compiler_text.addActionListener(this);
        button_run_prom.addActionListener(this);
        button_see_doswin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button_input_txt)//程序输入区
        {
            mycard.show(p,"input");
        }
        else if(e.getSource() == button_compiler_text)//编译结果区
        {
            mycard.show(p,"compiler");
        }
        else if(e.getSource() == button_see_doswin)//程序运行结果
        {
            mycard.show(p,"dos");
        }else if(e.getSource() == button_compiler)//编译程序
        {
            if(!(compiler.isAlive()))//测试compiler线程是否处于活动状态
            {
                compiler = new Thread(this);//如果不处于活动状态，则创建新的线程
            }
            try{
                compiler.start();
            }catch (Exception e2)
            {
                e2.printStackTrace();
            }

            mycard.show(p,"compiler");
        }else if(e.getSource() == button_run_prom)//运行程序
        {
            if(!(run_prom.isAlive()))
            {
                run_prom = new Thread(this);
            }
            try{
                run_prom.start();
            }catch (Exception e2){
                e2.printStackTrace();
            }
            mycard.show(p,"dos");
        }

    }

    public void run(){

        if(Thread.currentThread() == compiler)
        {
            compiler_text.setText(null);
            String temp = input_text.getText().trim();
            byte [] buffer = temp.getBytes();
            int b = buffer.length;
            String file_name = null;
            file_name = input_file_name_text.getText().trim();

            try{
                file_saved = new File(file_name);
                FileOutputStream writefile = null;
                writefile = new FileOutputStream(file_saved);
                writefile.write(buffer,0,b);
                writefile.close();
            }catch (Exception e){
                System.out.println("ERROR");
            }

            try{
                //获得该进程的错误流，才可以知道运行结果到底是失败了还是成功。
                Runtime rt = Runtime.getRuntime();
                InputStream in = rt.exec("javac "+file_name).getErrorStream();
                //通过Runtime调用javac命令。注意：“javac ”这个字符串是有一个空格的！！
                BufferedInputStream bufIn = new BufferedInputStream(in);
                byte[] shuzu = new byte[100];
                int n = 0;
                boolean flag = true;

                while((n = bufIn.read(shuzu,0,shuzu.length))!= -1)
                {
                    String s = null;
                    s = new String(shuzu,0,n);
                    compiler_text.append(s);
                    if(s != null)
                    {
                        flag = false;
                    }
                }
                if(flag) //判断是否编译成功
                {
                    compiler_text.append("Compiler Succeed!");
                }
            }catch (Exception e){

            }
        }
        else if(Thread.currentThread() == run_prom){
            dos_out_text.setText(null);
            //运行文件，并将结果输出到dos_out_text

            try{
                Runtime rt = Runtime.getRuntime();
                String path = run_file_name_text.getText().trim();
                Process stream = rt.exec("java "+path);
                InputStream in = stream.getInputStream();
                BufferedInputStream bisErr = new BufferedInputStream(stream.getErrorStream());
                BufferedInputStream bisIn = new BufferedInputStream(in);

                byte[] buf = new byte[150];
                byte[] err_buf = new byte[150];

                @SuppressWarnings("unused")
                int m = 0;
                @SuppressWarnings("unused")
                int i  =0;
                String s = null;
                String err = null;

                //打印编译信息及错误信息
                while((m = bisIn.read(buf,0,150)) != -1)
                {
                    s = new String(buf,0,150);
                    dos_out_text.append(s);
                }
                while((i = bisErr.read(err_buf))!= -1)
                {
                    err = new String(err_buf,0,150);
                    dos_out_text.append(err);
                }

            }catch (Exception e){

            }
        }

    }

}
