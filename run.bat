@echo off
setlocal

set "PROJECT_DIR=%~dp0"
set "CLASSES_DIR=%PROJECT_DIR%.lifeos-classes"

if exist "%CLASSES_DIR%" rmdir /s /q "%CLASSES_DIR%"
mkdir "%CLASSES_DIR%"

for /r "%PROJECT_DIR%src\main" %%f in (*.java) do echo "%%f" >> "%TEMP%\lifeos-sources.txt"
javac -cp "%PROJECT_DIR%lib\*" -d "%CLASSES_DIR%" @"%TEMP%\lifeos-sources.txt"
del "%TEMP%\lifeos-sources.txt"

cd /d "%PROJECT_DIR%"
java -cp "%CLASSES_DIR%;%PROJECT_DIR%lib\*" ui.gui.Main
