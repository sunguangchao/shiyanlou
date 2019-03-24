package generic.type;

import java.util.List;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/9 3:14 PM
 *
 * 如果希望将某类型限制为特定类型或特定类型的子类型，请使用以下表示法：
 * <T extends UpperBoundType>
 *
 * 同样，如果希望将某个类型限制为特定类型或特定类型的超类型，请使用以下表示法：
 *
 * <T super LowerBoundType>
 */
public class GenericContainer<T extends Number> {

    private T obj;

    public GenericContainer(){

    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }


    public static <T> void checkList(List<?> list, T obj){
        if (list.contains(obj)){
            System.out.println("the list contains the element");
        }else{
            System.out.println("the list not contains the element");
        }
    }
}
