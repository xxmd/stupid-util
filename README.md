### Introduction
A android scaffold project for publish android library to maven center.


### Why create this project
In 2023 years, publishing a android library is very simple. However, I mean it in the case if you use environment of gradle and gradle-plugin above 7.0. When you use gradle below 7.0 and gradle-plugin below 4.2.2. The situation is totally different.


Main different points in list there:
1. The `setting.gradle` file play different role in gradle version above 7.0 and below 7.0.
- In gradle version greater than 7.0, `setting.gradle` file will include some modules and manage dependency repository.  
- In gradle version litter than 7.0, `setting.gradle` file only include modules, the dependency repository script will be written in `build.gradle` file.
2. Build artifact generate
- In Version above 7.0 will auto generator build artifct, you only need to include the release artifact. It will include source code, doc file, resource file, etc.
- In version below 7.0 you need to generate buld artifact by write task, then include the task in script.


### Reference
Publish configuration mostly reference from
[android-gpuimage](https://github.com/cats-oss/android-gpuimage) project.
It's build.gradle file location is [there](https://github.com/cats-oss/android-gpuimage/blob/master/library/build.gradle).
If you want to publish library on old gradle and gradle-plugin version, you could refer this project carefully.