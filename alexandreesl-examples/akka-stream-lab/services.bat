@echo off
setlocal enabledelayedexpansion

@rem enabled only for interactive debugging !
set _DEBUG=0

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
if %_KAFKA_STOP%==1 (
    call :kafka_stop
    if %_FULL_STACK%==1 call :zk_stop
    set _KAFKA_LOGS_DIR=c:\temp\kafka-logs
    if exist "!_KAFKA_LOGS_DIR!" (
        del /s /q "!_KAFKA_LOGS_DIR!\*"
    )
) else if %_KAFKA_START%==1 (
    call :zk_start
    if not !_EXITCODE!==0 goto end
    call :kafka_start
    if not !_EXITCODE!==0 goto end
)
goto end

@rem #########################################################################
@rem ## Subroutines

:env
set _BASENAME=%~n0
set "_ROOT_DIR=%~dp0"

call :env_colors
set _DEBUG_LABEL=%_NORMAL_BG_CYAN%[%_BASENAME%]%_RESET%
set _ERROR_LABEL=%_STRONG_FG_RED%Error%_RESET%:
set _WARNING_LABEL=%_STRONG_FG_YELLOW%Warning%_RESET%:

set "_CONFIG_DIR=%_ROOT_DIR%config"

set "_KAFKA_PROPS_FILE=%_CONFIG_DIR%\server.properties"
if not exist "%_KAFKA_PROPS_FILE%" (
    echo %_ERROR_LABEL% Kafka configuration file not found 1>&2
    set _EXITCODE=1
    goto :eof
)
set "_ZK_PROPS_FILE=%_CONFIG_DIR%\zookeeper.properties"
if not exist "%_ZK_PROPS_FILE%" (
    echo %_ERROR_LABEL% ZooKeeper configuration file not found 1>&2
    set _EXITCODE=1
    goto :eof
)

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo %_ERROR_LABEL% Java SDK installation directory not found 1>&2
    set _EXITCODE=1
    goto :eof
)
if not exist "%KAFKA_HOME%\bin\windows\kafka-server-start.bat" (
    echo %_ERROR_LABEL% Kafka installation directory not found 1>&2
    set _EXITCODE=1
    goto :eof
)
set "_KAFKA_START_CMD=%KAFKA_HOME%\bin\windows\kafka-server-start.bat"
set "_KAFKA_STOP_CMD=%KAFKA_HOME%\bin\windows\kafka-server-stop.bat"

if not exist "%KAFKA_HOME%\bin\windows\zookeeper-server-start.bat" (
    echo %_ERROR_LABEL% ZooKeeper installation directory not found 1>&2
    set _EXITCODE=1
    goto :eof
)
set "_ZK_START_CMD=%KAFKA_HOME%\bin\windows\zookeeper-server-start.bat"
set "_ZK_STOP_CMD=%KAFKA_HOME%\bin\windows\zookeeper-server-stop.bat"
goto :eof

:env_colors
@rem ANSI colors in standard Windows 10 shell
@rem see https://gist.github.com/mlocati/#file-win10colors-cmd
set _RESET=[0m
set _BOLD=[1m
set _UNDERSCORE=[4m
set _INVERSE=[7m

@rem normal foreground colors
set _NORMAL_FG_BLACK=[30m
set _NORMAL_FG_RED=[31m
set _NORMAL_FG_GREEN=[32m
set _NORMAL_FG_YELLOW=[33m
set _NORMAL_FG_BLUE=[34m
set _NORMAL_FG_MAGENTA=[35m
set _NORMAL_FG_CYAN=[36m
set _NORMAL_FG_WHITE=[37m

@rem normal background colors
set _NORMAL_BG_BLACK=[40m
set _NORMAL_BG_RED=[41m
set _NORMAL_BG_GREEN=[42m
set _NORMAL_BG_YELLOW=[43m
set _NORMAL_BG_BLUE=[44m
set _NORMAL_BG_MAGENTA=[45m
set _NORMAL_BG_CYAN=[46m
set _NORMAL_BG_WHITE=[47m

@rem strong foreground colors
set _STRONG_FG_BLACK=[90m
set _STRONG_FG_RED=[91m
set _STRONG_FG_GREEN=[92m
set _STRONG_FG_YELLOW=[93m
set _STRONG_FG_BLUE=[94m
set _STRONG_FG_MAGENTA=[95m
set _STRONG_FG_CYAN=[96m
set _STRONG_FG_WHITE=[97m

@rem strong background colors
set _STRONG_BG_BLACK=[100m
set _STRONG_BG_RED=[101m
set _STRONG_BG_GREEN=[102m
set _STRONG_BG_YELLOW=[103m
set _STRONG_BG_BLUE=[104m
goto :eof

@rem input parameter: %*
:args
set _KAFKA_START=0
set _KAFKA_STOP=0
set _FULL_STACK=0
set _HELP=0
set _VERBOSE=0
set __N=0
:args_loop
set "__ARG=%~1"
if not defined __ARG (
    if !__N!==0 set _HELP=1
    goto args_done
)
if "%__ARG:~0,1%"=="-" (
    @rem option
    if "%__ARG%"=="-all" ( set _FULL_STACK=1
    ) else if "%__ARG%"=="-debug" ( set _DEBUG=1
    ) else if "%__ARG%"=="-help" ( set _HELP=1
    ) else if "%__ARG%"=="-verbose" ( set _VERBOSE=1
    ) else (
        echo %_ERROR_LABEL% Unknown option %__ARG% 1>&2
        set _EXITCODE=1
        goto args_done
    )
) else (
    @rem subcommand
    if "%__ARG%"=="help" ( set _HELP=1
    ) else if "%__ARG%"=="start" ( set _KAFKA_START=1
    ) else if "%__ARG%"=="stop" ( set _KAFKA_STOP=1
    ) else (
        echo %_ERROR_LABEL% Unknown subcommand %__ARG% 1>&2
        set _EXITCODE=1
        goto args_done
    )
    set /a __N=!__N!+1
)
shift
goto args_loop
:args_done
if %_DEBUG%==1 ( set _STDOUT_REDIRECT=
) else ( set _STDOUT_REDIRECT=1^>NUL
)

set _ZK_SESSION_NAME=zk-server
set _KAFKA_SESSION_NAME=kafka-server

if %_DEBUG%==1 (
    echo %_DEBUG_LABEL% Options    : _ALL=%_ALL% _DEBUG=%_DEBUG% _VERBOSE=%_VERBOSE% 1>&2
    echo %_DEBUG_LABEL% Subcommands: _HELP=%_HELP% _KAFKA_START=%_KAFKA_START% _KAFKA_STOP=%_KAFKA_STOP% 1>&2
    echo %_DEBUG_LABEL% Variables  : "GIT_HOME=%GIT_HOME%" 1>&2
    echo %_DEBUG_LABEL% Variables  : "JAVA_HOME=%JAVA_HOME%" 1>&2
    echo %_DEBUG_LABEL% Variables  : "KAFKA_HOME=%KAFKA_HOME%" 1>&2
    echo %_DEBUG_LABEL% Variables  : "SCALA_HOME=%SCALA_HOME%" 1>&2
)
goto :eof

:help
if %_VERBOSE%==1 (
    set __BEG_P=%_STRONG_FG_CYAN%
    set __BEG_O=%_STRONG_FG_GREEN%
    set __BEG_N=%_NORMAL_FG_YELLOW%
    set __END=%_RESET%
) else (
    set __BEG_P=
    set __BEG_O=
    set __BEG_N=
    set __END=
)
echo Usage: %__BEG_O%%_BASENAME% { ^<option^> ^| ^<subcommand^> }%__END%
echo.
echo   %__BEG_P%Options:%__END%
echo     %__BEG_O%-all%__END%        start/stop both servers ^(ZooKeeper/Kafka^)
echo     %__BEG_O%-debug%__END%      display commands executed by this script
echo     %__BEG_O%-verbose%__END%    display progress messages
echo.
echo   %__BEG_P%Subcommands:%__END%
echo     %__BEG_O%help%__END%        display this help message
echo     %__BEG_O%start%__END%       start the Kafka server ^(see option %__BEG_O%-all%__END%^)
echo     %__BEG_O%stop%__END%        stop the Kafka server ^(see option %__BEG_O%-all%__END%^)
goto :eof

:zk_stop
if %_DEBUG%==1 ( echo %_DEBUG_LABEL% "%_ZK_STOP_CMD%" 1^>NUL 1>&2
) else if %_VERBOSE%==1 ( echo Stop Zookeeper server "%_ZK_SESSION_NAME%" 1>&2
)
call "%_ZK_STOP_CMD%" %_STDOUT_REDIRECT%
if not %ERRORLEVEL%==0 (
    echo %_ERROR_LABEL% Failed to stop ZooKeeper server "%_ZK_SESSION_NAME%" 1>&2
    set _EXITCODE=1
    goto :eof
)
goto :eof

:zk_start
for /f "tokens=1,2,*" %%i in ('tasklist /nh /v /fi "IMAGENAME eq cmd.exe" ^|findstr "%_ZK_SESSION_NAME%" 2^>NUL') do (
    set __PID=%%j
    if %_DEBUG_LABEL%==1 ( echo %_DEBUG_LABEL% Zookeeper server is already started ^(pid: !__PID!^) 1>&2
    ) else if %_VERBOSE%==1 ( echo Zookeeper server is already started ^(pid: !__PID!^) 1>&2
    )
    goto :eof
)
if %_DEBUG%==1 ( echo %_DEBUG_LABEL% start "%_ZK_SESSION_NAME%" "%_ZK_START_CMD%" "%_ZK_PROPS_FILE%" 1>&2
) else if %_VERBOSE%==1 ( echo Start Zookeeper server "%_ZK_SESSION_NAME%" 1>&2
)
start "%_ZK_SESSION_NAME%" %_ZK_START_CMD% "%_ZK_PROPS_FILE%"
if not %ERRORLEVEL%==0 (
    echo %_ERROR_LABEL% Failed to start ZooKeeper "%_ZK_SESSION_NAME%" 1>&2
    set _EXITCODE=1
    goto :eof
)
@rem We introduce a 20 second delay between Zookeeper and Kafka.
@rem The zookeeper has a session expiry time of 18000 ms.
timeout /t 20 1>NUL
goto :eof

:kafka_start
set KAFKA_LOG4J_OPTS="-Dlog4j.configuration^=file:%_CONFIG_DIR%\log4j.properties"
@rem see book The Definitive Guide, p.37
@rem set KAFKA_JVM_PERFORMANCE_OPTS=-server -XX:+UseG1GC -XX:MaxGCPauseMillis=20 -XX:InitiatingHeapOccupancyPercent=35 -XX:+DisableExplicitGC -Djava.awt.headless=true

if %_DEBUG%==1 ( echo %_DEBUG_LABEL% start "%_KAFKA_SESSION_NAME%" "%_KAFKA_START_CMD%" "%_KAFKA_PROPS_FILE%" 1>&2
) else if %_VERBOSE%==1 ( echo Start Kafka server "%_KAFKA_SESSION_NAME%" 1>&2
)
start "%_KAFKA_SESSION_NAME%" %_KAFKA_START_CMD% "%_KAFKA_PROPS_FILE%"
if not %ERRORLEVEL%==0 (
    echo %_ERROR_LABEL% Failed to start Kafka server "%_KAFKA_SESSION_NAME%" 1>&2
    call :zk_stop
    set _EXITCODE=1
    goto :eof
)
goto :eof

:kafka_stop
if %_DEBUG%==1 ( echo %_DEBUG_LABEL% "%_KAFKA_STOP_CMD%" 1^>NUL 1>&2
) else if %_VERBOSE%==1 ( echo Stop Kafka server "%_KAFKA_SESSION_NAME%" 1>&2
)
call "%_KAFKA_STOP_CMD%" %_STDOUT_REDIRECT%
if not %ERRORLEVEL%==0 (
    echo %_ERROR_LABEL% Failed to stop Kafka server "%_KAFKA_SESSION_NAME%" 1>&2
    set _EXITCODE=1
    goto :eof
)
for /f "tokens=1,2,*" %%i in ('tasklist /nh /v /fi "IMAGENAME eq cmd.exe" ^|findstr "%_KAFKA_SESSION_NAME%" 2^>NUL') do (
    set __PID=%%j
    if %_DEBUG_LABEL%==1 ( echo %_DEBUG_LABEL% taskkill /pid !__PID! 1>&2
    ) else if %_VERBOSE%==1 ( echo Kill process "!__PID!" 1>&2
    )
    taskkill /pid "!__PID!" %_STDOUT_REDIRECT%
)
goto :eof

rem ##########################################################################
rem ## Cleanups

:end
if %_DEBUG%==1 echo %_DEBUG_LABEL% _EXITCODE=%_EXITCODE% 1>&2
exit /b %_EXITCODE%
endlocal
