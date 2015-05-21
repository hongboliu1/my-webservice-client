# my-webservice-client
1、根据wsdl生成客户端的代码：
一种方法是使用cxf官方工具，wsdl2java命令，另一种办法是使用JDK自带的命令行工作wsimport。
实例：wsimport http://127.0.0.1/TicketMobile/services/Cococ?wsdl  -keep -p com.llg.ws2 -s g:/ws
-keep  是否生成源文件
-p com.llg.ws2  生成后的java包名
-s g:/ws    生成后放哪个目录

参考资料：http://suexiukjung.blog.51cto.com/1169266/1174741