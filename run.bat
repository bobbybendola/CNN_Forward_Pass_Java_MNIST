@echo off
echo ========================================
echo Compiling project...
echo ========================================
javac -d out src/*.java
if %errorlevel% neq 0 (
    echo Compilation failed! Please check your code.
    exit /b %errorlevel%
)

echo.
echo ========================================
echo Running SampleTest...
echo ========================================
java -cp out SampleTest

echo.
echo ========================================
echo Running Main Classifier...
echo ========================================
java -cp out Main

pause