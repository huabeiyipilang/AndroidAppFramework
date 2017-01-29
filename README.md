# AndroidAppFramework


在项目根目录下执行
	git submodule add https://github.com/huabeiyipilang/AndroidAppFramework.git submodules/framework


setting.gradle

	include ':framework'
	project(':framework').projectDir = new File('submodules/framework/framework')

项目build.gradle
	
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

主模块build.gradle	

	apply plugin: 'com.neenbedankt.android-apt'

    compile project(':framework')
    compile 'com.jakewharton:butterknife:8.4.0'
    apt 'com.jakewharton:butterknife-compiler:8.4.0'
