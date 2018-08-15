package org.smart4j.framework.proxy;
/**
 * 接口代理
 * 
 * 接口的实现会提供相应的横切逻辑
 * @author taojiajun
 *
 */
public interface Proxy {
/**
 * 执行链式代理
 * 即：可以将多个代理通过一条链子串起来
 *一个一个地去执行
 * 执行顺序取决于添加到链子上的顺序
 */
	Object doProxy(ProxyChain proxyChain) throws Throwable;
}
