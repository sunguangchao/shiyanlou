import java.security.PublicKey;

/**
 * Created by 11981 on 2016/9/24.
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription(){
        return description;
    }
    public abstract double cost();
}
