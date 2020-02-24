package com.bawei.dimensionshoppingmal.baseActivity;

/**
 * TIme:2020/2/24
 * Author:孙帅喜
 *  这里是一个空接口
 *  * 在我们每一个页面的契约类里，V层的接口是不一样的
 *  * 正因为他们不一样，才会导致在BasePresenter中，无法统一使用
 *  * 为了实现无论哪一个契约类中的V层，都能传入到BasePresenter中
 *  * 所以定义了一个空的接口，由每一个契约类的V层接口来继承他
 *  * 继承过后，IBaseView就是我们每一个契约类中V层接口的父类
 *  * 方便我们在BasePresenter添加泛型的限制
 * Descriotion:
 */
public interface IBaseView {
}
