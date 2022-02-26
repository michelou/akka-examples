@echo off
setlocal enabledelayedexpansion

set _DEBUG=1

@rem #########################################################################
@rem ## Environment setup

set _EXITCODE=0

call :env
if not %_EXITCODE%==0 goto end

call :args %*
if not %_EXITCODE%==0 goto end

@rem #########################################################################
@rem ## Main

if %_HELP%==1 (
     call :help
     exit /b !_EXITCODE!
)

for %%i in (reference version) do (
    call :extract %%i
    if not !_EXITCODE!==0 goto end
)
goto end

@rem #########################################################################
@rem ## Subroutines

:env
set _BASENAME=%~n0

set _DEBUG_LABEL=[%_BASENAME%]
set _ERROR_LABEL=Error:

if not exist "%JAVA_HOME%\bin\jar.exe" (
    echo %_ERROR_LABEL% Java SDK installation not found 1>&2
    set _EXITCODE=1
    goto :eof
)
set "_JAR_CMD=%JAVA_HOME%\bin\jar.exe" 
goto :eof

:args
set _HELP=0
set _VERBOSE=0
goto :eof

:help
echo Usage: %_BASENAME%
echo.
goto :eof

:extract
set __CONF_NAME=%~1

set "__TMP_DIR=%TEMP%\%_BASENAME%"
if not exist "%__TMP_DIR%" mkdir "%__TMP_DIR%"

set __JAR_OPTS=xf

set __JAR_FILE=
for /f "usebackq delims=" %%f in (`dir /b /s "%USERPROFILE%\.m2\repository\com\typesafe\akka-actor_2.13*.jar" 2^>NUL`) do (
     set "__JAR_FILE=%%f"
)
if not defined __JAR_FILE (
    echo %_ERROR_LABEL% JAR file not found 1>&2
    set _EXITCODE=1
    goto :eof
)
set __CONF_FILE=%__CONF_NAME%.conf

if %_DEBUG%==1 (echo pushd "%__TMP_DIR%" 1>&2
) else if %_VERBOSE%==1 ( echo Set current directory to "%__TMP_DIR%" 1>&2
)
pushd "%__TMP_DIR%"

if %_DEBUG%==1 ( echo "%_JAR_CMD%" %__JAR_OPTS% "%__JAR_FILE%" %__CONF_FILE% 1>&2
) else if %_VERBOSE%==1 ( echo Extract file "%__CONF_FILE%" from JAR file 1>&2
)
call "%_JAR_CMD%" %__JAR_OPTS% "%__JAR_FILE%" %__CONF_FILE%
if not %ERRORLEVEL%==0 (
    popd
    echo %_ERROR_LABEL% Failed to extract file "%__JAR_FILE%" 1>&2
    set _EXITCODE=1
    goto :eof
)
popd
if exist "%__TMP_DIR%\%__CONF_FILE%" type "%__TMP_DIR%\%__CONF_FILE%"
goto :eof

@rem #########################################################################
@rem ## Cleanups

:end
if %_DEBUG%==1 echo %_DEBUG_LABEL% _EXITCODE=%_EXITCODE% 1>&2
exit /b %_EXITCODE%
endlocal
