# AndroidAppFramework


在项目根目录下执行

	git submodule add https://github.com/huabeiyipilang/AndroidAppFramework.git submodules/framework


setting.gradle

	include ':framework'
	project(':framework').projectDir = new File('submodules/framework/framework')

主模块build.gradle	

    implementation project(':framework')
    //ButterKnife
    implementation 'com.jakewharton:butterknife:10.1.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'

Application类onCreate中

    AppDelegate.init(this);
    
如果遇到如下问题：

    Error:Execution failed for task ':app:processBaiduDebugManifest'.
    > Manifest merger failed : uses-sdk:minSdkVersion 10 cannot be smaller than version 14 declared in library [AppManager:framework:unspecified] /Users/baidu/private/projects/AppManager/app/build/intermediates/exploded-aar/AppManager/framework/unspecified/AndroidManifest.xml
      	Suggestion: use tools:overrideLibrary="com.penghaonan.appframework" to force usage
      	
在AndroidManifest.xml文件中加入
    
    <uses-sdk tools:overrideLibrary="com.penghaonan.appframework" />