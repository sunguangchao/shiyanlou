

/**
 * Created by 11981 on 2016/9/24.
 */
public interface Subject {
    public void registerObservers(Observer o);
    public void removeObservers(Observer o);/*��������������Ҫ�۲�����Ϊ�������ù۲���������ע���ɾ����*/
    public void notifyObservers();/*������ı�ʱ����������ᱻ����*/

}
