package consistent_hashing;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 9:29 AM
 * 对服务器的抽象，一般是ip+port的形式
 */
public class Server {
    private String url;

    public Server(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
