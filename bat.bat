mkdir bin
javac -d bin -sourcepath src -cp resources\libs\spring-context-5.1.4.RELEASE.jar;resources\libs\spring-core-5.1.4.RELEASE.jar;resources\libs\spring-beans-5.1.4.RELEASE.jar src\com\lib\StartMain.java src\com\lib\*.java 
chcp 65001 
java -classpath bin;resources\libs\commons-logging-1.2.jar;resources\libs\spring-context-5.1.4.RELEASE.jar;resources\libs\spring-core-5.1.4.RELEASE.jar;resources\libs\spring-beans-5.1.4.RELEASE.jar;resources\libs\spring-expression-5.1.4.RELEASE.jar;resources\libs\spring-aop-5.1.4.RELEASE.jar lib.StartMain
pause