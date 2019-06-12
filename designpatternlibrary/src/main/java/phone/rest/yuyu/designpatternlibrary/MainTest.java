package phone.rest.yuyu.designpatternlibrary;

/**
 * @author yujialing
 * 2019/2/1
 */
public class MainTest {
    public static void main(String[] args) {
        Espresso espresso = new Espresso();
        System.out.println(espresso.getDescription() + "," + espresso.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Mocha(houseBlend);
        System.out.println(houseBlend.getDescriptions() + ",一共是" + houseBlend.cost());
    }
}
