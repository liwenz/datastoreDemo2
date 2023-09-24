# datastoreDemo2
For Chinese, you could see https://blog.csdn.net/leon_zeng0/article/details/133156441

This is very simple demo project for preferences DataStore
1: create a project in ansroid studio , Empty Active Views as template, and name it as datastore Demo, kotlin as language

2: Add dependence: in build.gradle module:app
add following in dependencies {}
    //Preferences DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.datastore:datastore-preferences-core:1.0.0")

3: In res/layout activity_main.xml is replaced with this project file content.There are only 1 TextView, 1 EditView and  3 Buttons 

4: As project file MainActivity.kt 's contect, change or replace your MainActivity.kt
Then you could debug or run app

The effect of app running is: initially, textview displays hello world

When clearing or getting for the first time, office count=2 is displayed.

When saving, store home in name and edit row numbers in count.

When Get, display the name and count in the Textview area, such as home count=6677
