package phone.rest.yuyu.designpatternlibrary

/**
 * @author yujialing
 * 2019/2/1
 */
class Mocha(beverage: Beverage) : CondimentDecorator() {

    private var beverage = beverage

    override fun cost(): Double {
        return .20 + beverage.cost()
    }

    override fun getDescriptions(): String {
        return beverage.getDescriptions() + ",Mocha"
    }
}