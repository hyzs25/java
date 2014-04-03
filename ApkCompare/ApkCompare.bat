@ECHO ===================================提醒========================================
@ECHO 初次执行，请确认config.properties中beyond compare的安装路径，scriptCompare的存放路径是否匹配，新旧APK的存放路径是否正确，若有误，请修改为正确的路径后再执行

pause


cd src
javac -encoding gbk -d .. com\qihoo\filecompare\*.java
cd ..
java com.qihoo.filecompare.FileCompare