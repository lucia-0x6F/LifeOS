@echo off
setlocal

set "PROJECT_DIR=%~dp0"
set "CLASSES_DIR=%PROJECT_DIR%.lifeos-classes"
set "DATA_DIR=%PROJECT_DIR%data"

if not exist "%DATA_DIR%" mkdir "%DATA_DIR%"

if not exist "%DATA_DIR%LongTerm.json" (
    >"%DATA_DIR%LongTerm.json" echo {
    >>"%DATA_DIR%LongTerm.json" echo     "name": "",
    >>"%DATA_DIR%LongTerm.json" echo     "goals": []
    >>"%DATA_DIR%LongTerm.json" echo }
)

if not exist "%DATA_DIR%ShortTerm.json" (
    >"%DATA_DIR%ShortTerm.json" echo {
    >>"%DATA_DIR%ShortTerm.json" echo     "name": "",
    >>"%DATA_DIR%ShortTerm.json" echo     "tasks": []
    >>"%DATA_DIR%ShortTerm.json" echo }
)

if exist "%CLASSES_DIR%" rmdir /s /q "%CLASSES_DIR%"
mkdir "%CLASSES_DIR%"

for /r "%PROJECT_DIR%src\main" %%f in (*.java) do echo "%%f" >> "%TEMP%\lifeos-sources.txt"
javac -cp "%PROJECT_DIR%lib\*" -d "%CLASSES_DIR%" @"%TEMP%\lifeos-sources.txt"
del "%TEMP%\lifeos-sources.txt"

cd /d "%PROJECT_DIR%"
java -cp "%CLASSES_DIR%;%PROJECT_DIR%lib\*" ui.gui.Main
