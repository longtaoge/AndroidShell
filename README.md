#  AndroidShell

Android APK 加壳保护

----------

### 原理介绍 ###

  为了防止直接对APK反编译，提高Android 程序安全性，可以给真正的APK加上一个外壳，当外壳运行的时侯，把真正的程序解密出来，动态加载到系统中。

----------

#### 基本步骤： ####

##### 1 ： 将我们的APK打包（AndroidShellDome）


##### 2  ：运行外壳程序，就是保护我们直正APK的程序，得到 classes.dex 文件 （AndroidShell）

##### 3 ： 运行加壳程序（ApkShellUtil），将我们要保护的APK 合并（可加密）到外壳程序的classes.dex中

##### 4 ：  将合并的后的classes.dex 放入 外壳程序（AndroidShell）bin 目录下重新运行打包

##### 5 ： 在外壳程序的 Application 中将真正的APK解密并释放出来加载运行




----------
### 需要注意的问题 ###



1. 程序中所用到的资源文件要全部复制到 外壳程序中
2. 应用中 AndroidManifest.xml 中所有内容需要在外壳程序的AndroidManifest.xml 重新注册
3. 如果应用中定义了自己的Application  需要在 外壳程序中添加 
       
    <meta-data android:name="APPLICATION_CLASS_NAME" android:value="xxx.xxx.Application"/>
    

    xxx.xxx.Application 是指我们定义的Application全称



----------

#### APK：  需要加壳的程序 ####

![](https://github.com/longtaoge/AndroidShell/blob/master/apk.gif)


#### APKshell ： 需要加的壳 ####

![](https://github.com/longtaoge/AndroidShell/blob/master/unshell.gif)








----------

### 参考资料  ###

本示例完全参照以下博客内容实现，感谢他们的奉献。

Jack_Jia的博客

[http://blog.csdn.net/jiazhijun/article/details/8678399](http://blog.csdn.net/jiazhijun/article/details/8678399)


pvlking的专栏

[http://blog.csdn.net/pvlking/article/details/42168233](http://blog.csdn.net/pvlking/article/details/42168233)
