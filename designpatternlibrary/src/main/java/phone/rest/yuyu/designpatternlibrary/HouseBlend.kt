package phone.rest.yuyu.designpatternlibrary

/**
 * @author yujialing
 * 2019/2/1
 */
class HouseBlend : Beverage() {

    init {
        description = "House Blend Coffee"
    }

    override fun cost(): Double {
        return .89
    }
}