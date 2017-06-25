@echo off
set binDirectory=%maven_uploader%
set jarDirectory=%binDirectory:~,-3%lib\nexus-uploader.jar
IF "%binDirectory%"=="" GOTO :MISSING_ENVIROMENT_VARIABLE
IF "%1"=="-h" GOTO :PRINT_HELP
IF "%1"=="--help" GOTO :PRINT_HELP
IF NOT "%1"=="-d" IF NOT "%1"=="--directory" GOTO :MISSING_FIRST
IF "%2"=="" GOTO :MISSING_SECOND
java -jar %jarDirectory% %1 %2
GOTO done
:MISSING_FIRST
ECHO mvnUploader command is missing -d parameter
ECHO See 'mvnUploader -h'.
ECHO.
ECHO Usage: mvnUploader -d DIRECTORY_TO_ARTIFACTS
ECHO.
GOTO done
:MISSING_SECOND
ECHO -d parameter require 1 argument
ECHO See 'mvnUploader -h'.
ECHO.
ECHO Usage: mvnUploader [options] [arguments]
ECHO.
GOTO done
:PRINT_HELP
java -jar %jarDirectory% %1
GOTO done
:MISSING_ENVIROMENT_VARIABLE
ECHO maven_uploader enviroment variable is missing
ECHO.
GOTO done
:done

