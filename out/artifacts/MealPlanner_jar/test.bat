set JAVADIR="C:\Program Files\Java\jdk-22"
set JARFILE="D:\JavaPrj\MealPlanner\out\artifacts\MealPlanner_jar\MealPlanner.jar"
set STARTDIR="D:\JavaPrj\MealPlanner\out\artifacts\MealPlanner_jar"

cd %STARTDIR%
%JAVADIR%\bin\java.exe --module-path %JAVADIR%\javafx\lib --add-modules=javafx.controls,javafx.fxml -jar %JARFILE%

PAUSE