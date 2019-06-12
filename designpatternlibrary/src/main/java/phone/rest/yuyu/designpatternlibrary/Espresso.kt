package phone.rest.yuyu.designpatternlibrary

/**
 * @author yujialing
 * 2019/2/1
 */
class Espresso : Beverage() {

    init {
        description = "Espresso"
    }

    override fun cost(): Double {
        return 1.99
    }
}

