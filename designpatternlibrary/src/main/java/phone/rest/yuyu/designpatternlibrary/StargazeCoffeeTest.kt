package phone.rest.yuyu.designpatternlibrary

/**
 * @author yujialing
 * 2019/2/1
 */
object StargazeCoffeeTest {

    @JvmStatic
    fun main(args: Array<String>) {
        var espresso = Espresso()
        System.out.print(espresso.description + "," + espresso.cost())

        var houseBlend:Beverage = HouseBlend()
        houseBlend = Mocha(houseBlend)
        houseBlend = Mocha(houseBlend)
        System.out.print(houseBlend.description + "," + houseBlend.cost())
    }

}