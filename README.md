# springWebsockeForTomcatLog

相比于原始博客http://blog.csdn.net/smile326/article/details/52218264
文中的内容，主要作以下两点改变
1.为了在建立连接之后能够获取到上传的参数，用MyHandShake实现了HandshakeInterceptor接口，在beforeHandshake方法里面对上传参数进行了处理，所以后面才可以获取到。
2.session的容器，使用ConcurrentHashMap，保证线程的足够安全。

用一个简单的servlet类test，模拟接口，部署之后访问test，即可看到test接口中打印的内容出现在log.html的页面上
