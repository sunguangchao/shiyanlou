

/**
 * Created by 11981 on 2016/9/24.
 */
public interface Subject {
    public void registerObservers(Observer o);
    public void removeObservers(Observer o);/*这两个方法都需要观察者作为变量，该观察者是用来注册或被删除的*/
    public void notifyObservers();/*当主题改变时，这个方法会被调用*/

}
