package work;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11981 on 2017/11/9.
 */
public class FtpUtils {
    private final static Log LOGGER = LogFactory.getLog(FtpUtils.class);
    public FtpClient ftpClient;

    public void connectServer(String server, int port, String username, String password, String path)throws
            IOException, FtpProtocolException{
        ftpClient = FtpClient.create();
        if (port == -1){
            ftpClient.connect(new InetSocketAddress(server, port));
        }else{
            ftpClient.connect(new InetSocketAddress(server, port));
        }

        ftpClient.login(username, password.toCharArray());
        if (path.length() != 0){
            ftpClient.changeDirectory(path);
        }
        ftpClient.setBinaryType();
    }

    /**
     *
     * @param fileName
     * @param newName
     * @return
     */
    public long upload(String fileName, String newName){
        long result = 0;
        TelnetOutputStream os= null;
        FileInputStream is = null;

        try {
            File fileIn = new File(fileName);
            //如果文件不存在或者没有内容，则返回相应的错误码
            if (!fileIn.exists()){
                return -1;
            }
            if (fileIn.length() == 0){
                return -2;
            }
            os = (TelnetOutputStream) ftpClient.putFileStream(newName, true);
            result = fileIn.length();
            is = new FileInputStream(fileIn);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1){
                os.write(bytes, 0, c);
            }
        }catch (IOException | FtpProtocolException e){
            e.printStackTrace();
            LOGGER.error(e);
        }

        finally {
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return result;
    }

    public long upload(String fileName) throws IOException{
        String newName = "";
        if (fileName.indexOf("/") > -1){
            newName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }else{
            newName = fileName;
        }
        return upload(fileName, newName);
    }

    /**
     *
     * @param fileName
     * @param newFileName
     * @return
     * @throws IOException
     * @throws FtpProtocolException
     */
    public long download(String fileName, String newFileName) throws IOException, FtpProtocolException{
        long result = 0;
        TelnetInputStream is = null;
        FileOutputStream os = null;
        try {
            is = (TelnetInputStream) ftpClient.getFileStream(fileName);

            File outFile = new File(newFileName);
            os = new FileOutputStream(outFile);
            byte[] bytes = new byte[1024];
            int c;
            while ((c=is.read(bytes)) != -1){
                os.write(bytes, 0, c);
                result = result + c;
            }
        }finally {
            if (os != null){
                os.close();
            }
            if (is != null){
                is.close();
            }
        }
        return result;
    }

    /**
     * 重命名文件
     * @param oldName
     * @param newName
     * @return
     */
    public boolean rename(String oldName, String newName){
        boolean isSuccess = false;
        try {
            ftpClient.rename(oldName, newName);
            isSuccess = true;
        }catch (IOException| FtpProtocolException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 取得某个目录下的所有文件，包括文件夹
     * @param path
     * @return
     */
    public List getFileList(String path){
        List list = new ArrayList();
        try {
            DataInputStream dis = new DataInputStream(ftpClient.nameList(path));
            String filename = "";
            while ((filename=dis.readLine()) != null){
                list.add(filename);
            }
        }catch (IOException | FtpProtocolException e){
            e.printStackTrace();

        }
        return list;
    }

    public boolean isExistsFile(String path, String file){
        List<String> fileList = this.getFileList(path);
        if (fileList != null && fileList.size() > 0){
            for (String f : fileList){
                if (f.equals(file)){
                    return true;
                }

            }
        }
        return false;
    }

    public void closeServer(){
        try {
            if (ftpClient != null){
                ftpClient.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isFile(String fileName){
        return !isDir(fileName);
    }

    public boolean isDir(String fileName){
        boolean isDir = false;
        TelnetInputStream tis = null;
        String strPath = null;
        try {
            strPath = ftpClient.getWorkingDirectory();
            ftpClient.changeDirectory(fileName);
            ftpClient.changeToParentDirectory();
            isDir = true;
        }catch (Exception e){
            LOGGER.error("获取文件出错，文件名：" + fileName);
            LOGGER.error("获取文件出错，原因为：" + e.getMessage());
        }finally {
            if (strPath != null){
                try {
                    ftpClient.changeDirectory(strPath);
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }
        return isDir;
    }

    public void cd(String path) throws Exception{
        ftpClient.changeDirectory(path);
    }
}
