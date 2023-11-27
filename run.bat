@echo off

:: Change to the directory where your server and client files are located
cd %~dp0

:: Compile the server
javac JavaCode/*.java

:: Compile and run each client in separate command prompt windows
start cmd /k "java JavaCode.Server" 
start cmd /k "java JavaCode.Client"
start cmd /k "java JavaCode.Client"
start cmd /k "java JavaCode.Client"