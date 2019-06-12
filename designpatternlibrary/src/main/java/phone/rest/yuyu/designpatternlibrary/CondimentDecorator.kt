package phone.rest.yuyu.designpatternlibrary

/**
 * @author yujialing
 * 2019/2/1
 */
abstract class CondimentDecorator : Beverage() {
    abstract override fun getDescriptions(): String
}