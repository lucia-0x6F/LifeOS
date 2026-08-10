@echo off
setlocal
set "PROJECT_DIR=%~dp0"
set "BUILD_DIR=%PROJECT_DIR%.lifeos-package"
set "INPUT_DIR=%BUILD_DIR%\input"
if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%INPUT_DIR%\lib" "%INPUT_DIR%\data" "%BUILD_DIR%\classes"
dir /s /b "%PROJECT_DIR%src\main\*.java" > "%BUILD_DIR%\sources.txt"
javac -cp "%PROJECT_DIR%lib\*" -d "%BUILD_DIR%\classes" @"%BUILD_DIR%\sources.txt"
copy /y "%PROJECT_DIR%LifeOS.png" "%INPUT_DIR%" > nul
copy /y "%PROJECT_DIR%image.png" "%INPUT_DIR%" > nul
copy /y "%PROJECT_DIR%data\LongTerm.json" "%INPUT_DIR%data" > nul
copy /y "%PROJECT_DIR%data\ShortTerm.json" "%INPUT_DIR%data" > nul
copy /y "%PROJECT_DIR%lib\json-20251224.jar" "%INPUT_DIR%lib" > nul
jar --create --file "%INPUT_DIR%LifeOS.jar" --main-class ui.gui.Main -C "%BUILD_DIR%\classes" .
if not exist "%PROJECT_DIR%dist" mkdir "%PROJECT_DIR%dist"
jpackage --type exe --name LifeOS --input "%INPUT_DIR%" --main-jar LifeOS.jar --main-class ui.gui.Main --dest "%PROJECT_DIR%dist"
