package phone.rest.yuyu.designpatternlibrary

/**
 * @author yujialing
 * 2019/2/1
 */
abstract class Beverage {

    var description: String = "Unknown Beverage"

    open fun getDescriptions(): String {
        return description
    }

    abstract fun cost(): Double

}