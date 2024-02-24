@echo off
setlocal enabledelayedexpansion

@rem only for interactive debugging !
set _DEBUG=0

@rem #########################################################################
@rem ## Environment setup

set _EXITCODE=0

@rem files build.sbt, build.sc and ivy.xml
set _DOTTY_VERSION_OLD="3.3.2-RC3"
set _DOTTY_VERSION_NEW="3.3.2"

@rem files project\build.properties
set _SBT_VERSION_OLD=sbt.version=1.9.8
set _SBT_VERSION_NEW=sbt.version=1.9.9

@rem files project\plugins.sbt
@rem see https://search.maven.org/artifact/ch.epfl.lamp/sbt-dotty/
set _SBT_DOTTY_VERSION_OLD="0.5.4"
set _SBT_DOTTY_VERSION_NEW="0.5.5"

@rem files ivy.xml (NB. PS regex)
set _IVY_AKKA_VERSION_GREP=\"akka-actor_2.13\".^*rev=\"2.9.1\"
set _IVY_AKKA_VERSION_OLD=^\^(akka-actor.*_2.13\" rev=^\^)\"2.9.1\"
set _IVY_AKKA_VERSION_NEW=^\1\"2.9.2\"

set _IVY_DOTTY_VERSION_OLD=^(scala3-[a-z]+^)_3.3.2-RC3
set _IVY_DOTTY_VERSION_NEW=$1_3.3.2

set _IVY_TASTY_VERSION_OLD=^(tasty-[a-z]+^)_3.3.2-RC3
set _IVY_TASTY_VERSION_NEW=\1_3.3.2

@rem files pom.xml (NB. PS regex)
set _POM_SCALA2_VERSION_OLD=scala.version^>2.13.12
set _POM_SCALA2_VERSION_NEW=scala.version^>2.13.13

set _POM_SCALA3_VERSION_OLD=scala3.version^>3.3.2-RC3
set _POM_SCALA3_VERSION_NEW=scala3.version^>3.3.2

@rem files common.gradle
set _GRADLE_DOTTY_VERSION_OLD=scalaVersion : "3.3.2-RC3"
set _GRADLE_DOTTY_VERSION_NEW=scalaVersion : "3.3.2"

@rem files cpath.bat
set _BATCH_AKKA_VERSION_OLD=__AKKA_VERSION=2.8.5
set _BATCH_AKKA_VERSION_NEW=__AKKA_VERSION=2.9.1

set _BATCH_SCALATEST_VERSION_OLD=__SCALATEST_VERSION=3.2.17
set _BATCH_SCALATEST_VERSION_NEW=__SCALATEST_VERSION=3.2.18

set _BATCH_SLF4J_VERSION_OLD=__SLF4J_VERSION=2.0.11
set _BATCH_SLF4J_VERSION_NEW=__SLF4J_VERSION=2.0.12

@rem files Makefile.inc
set _MAKE_SLF4J_VERSION_OLD=SLF4J_VERSION = 2.0.11
set _MAKE_SLF4J_VERSION_NEW=SLF4J_VERSION = 2.0.12

@rem copyright dates
set _COPYRIGHT_DATES_OLD=2018-2023
set _COPYRIGHT_DATES_NEW=2018-2024

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
if %_RUN%==1 (
    call :run
    if not !_EXITCODE!==0 goto end
)
goto end

@rem #########################################################################
@rem ## Subroutines

@rem output parameters: _DEBUG_LABEL, _ERROR_LABEL, _WARNING_LABEL
:env
set _BASENAME=%~n0
for /f "delims=" %%f in ("%~dp0\.") do set "_ROOT_DIR=%%~dpf"
@rem remove trailing backslash for virtual drives
if "%_ROOT_DIR:~-2%"==":\" set "_ROOT_DIR=%_ROOT_DIR:~0,-1%"

call :env_colors
set _DEBUG_LABEL=%_NORMAL_BG_CYAN%[%_BASENAME%]%_RESET%
set _ERROR_LABEL=%_STRONG_FG_RED%Error%_RESET%:
set _WARNING_LABEL=%_STRONG_FG_YELLOW%Warning%_RESET%:

if not exist "%GIT_HOME%\usr\bin\grep.exe" (
    echo %_ERROR_LABEL% Grep command not found 1>&2
    set _EXITCODE=1
    goto :eof
)
set "_GREP_CMD=%GIT_HOME%\usr\bin\grep.exe"
set "_SED_CMD=%GIT_HOME%\usr\bin\sed.exe"
set "_UNIX2DOS_CMD=%GIT_HOME%\usr\bin\unix2dos.exe"
goto :eof

:env_colors
@rem ANSI colors in standard Windows 10 shell
@rem see https://gist.github.com/mlocati/#file-win10colors-cmd

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

@rem we define _RESET in last position to avoid crazy console output with type command
set _BOLD=[1m
set _INVERSE=[7m
set _UNDERSCORE=[4m
set _RESET=[0m
goto :eof

@rem input parameter: %*
:args
set _HELP=0
set _RUN=1
set _VERBOSE=0
set __N=0
:args_loop
set "__ARG=%~1"
if not defined __ARG goto args_done

if "%__ARG:~0,1%"=="-" (
    @rem option
    if "%__ARG%"=="-debug" ( set _DEBUG=1
    ) else if "%__ARG%"=="-help" ( set _HELP=1
    ) else if "%__ARG%"=="-verbose" ( set _VERBOSE=1
    ) else (
        echo %_ERROR_LABEL% Unknown option "%__ARG%" 1>&2
        set _EXITCODE=1
        goto args_done
    )
) else (
    @rem subcommand
    if "%__ARG%"=="help" ( set _HELP=1
    ) else if "%__ARG%"=="run" ( set _RUN=1
    ) else (
        echo %_ERROR_LABEL% Unknown subcommand "%__ARG%" 1>&2
        set _EXITCODE=1
        goto args_done
    )
    set /a __N+=1
)
shift
goto args_loop
:args_done
if %_DEBUG%==1 (
    echo %_DEBUG_LABEL% Options    : _VERBOSE=%_VERBOSE% 1>&2
    echo %_DEBUG_LABEL% Subcommands: _HELP=%_HELP% _RUN=%_RUN%  1>&2
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
echo     %__BEG_O%-debug%__END%       print commands executed by this script
echo     %__BEG_O%-verbose%__END%     print progress messages
echo.
echo   %__BEG_P%Subcommands:%__END%
echo     %__BEG_O%help%__END%         print this help message
echo     %__BEG_O%run%__END%          execute main class
goto :eof

:run
for %%i in (akka-concurrency akka-cookbook akka-essentials alexandreesl-examples examples pekko-examples) do (
     set "__PROJECT_DIR=%_ROOT_DIR%\%%i"
    if exist "!__PROJECT_DIR!\" (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% call :update_project "!__PROJECT_DIR!" 1>&2
        call :update_project "!__PROJECT_DIR!"
    ) else (
        echo %_WARNING_LABEL% Project directory not found ^("!__PROJECT_DIR!"^) 1>&2
    )
)
goto :eof

:update_project
set __PARENT_DIR=%~1
set __N1=0
set __N2=0
set __N3=0
set __N_SC=0
set __N_SH=0
set __N_BAT=0
set __N_IVY=0
set __N_POM=0
set __N_GRA=0
set __N_INC=0
echo Parent directory: %__PARENT_DIR%
for /f %%i in ('dir /ad /b "%__PARENT_DIR%" ^| findstr /v /c:"lib"') do (
    set "__BUILD_SBT=%__PARENT_DIR%\%%i\build.sbt"
    if exist "!__BUILD_SBT!" (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_DOTTY_VERSION_OLD%" "!__BUILD_SBT!" 1>&2
        call "%_GREP_CMD%" -q "%_DOTTY_VERSION_OLD%" "!__BUILD_SBT!"
        if !ERRORLEVEL!==0 (
            if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_DOTTY_VERSION_OLD%@%_DOTTY_VERSION_NEW%@g" "!__BUILD_SBT!" 1>&2
            call "%_SED_CMD%" -i "s@%_DOTTY_VERSION_OLD%@%_DOTTY_VERSION_NEW%@g" "!__BUILD_SBT!"
            call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
            set /a __N1+=1
        )
    ) else (
       @rem echo    %_WARNING_LABEL% Could not find file "%%i\build.sbt" 1>&2
    )
    set "__BUILD_PROPS=%__PARENT_DIR%\%%i\project\build.properties"
    if exist "!__BUILD_PROPS!" (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_SBT_VERSION_OLD%" "!__BUILD_PROPS!" 1>&2
        call "%_GREP_CMD%" -q "%_SBT_VERSION_OLD%" "!__BUILD_PROPS!"
        if !ERRORLEVEL!==0 (
            if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_SBT_VERSION_OLD%@%_SBT_VERSION_NEW%@g" "!__BUILD_PROPS!" 1>&2
            call "%_SED_CMD%" -i "s@%_SBT_VERSION_OLD%@%_SBT_VERSION_NEW%@g" "!__BUILD_PROPS!"
            call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
            set /a __N2+=1
        )
    ) else (
       @rem echo    %_WARNING_LABEL% Could not find file "%%i\project\build.properties" 1>&2
    )
    set "__PLUGINS_SBT=%__PARENT_DIR%\%%i\project\plugins.sbt"
    if exist "!__PLUGINS_SBT!" (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_SBT_DOTTY_VERSION_OLD%" "!__PLUGINS_SBT!" 1>&2
        call "%_GREP_CMD%" -q "%_SBT_DOTTY_VERSION_OLD%" "!__PLUGINS_SBT!"
        if !ERRORLEVEL!==0 (
            if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_SBT_DOTTY_VERSION_OLD%@%_SBT_DOTTY_VERSION_NEW%@g" "!__PLUGINS_SBT!" 1>&2
            call "%_SED_CMD%" -i "s@%_SBT_DOTTY_VERSION_OLD%@%_SBT_DOTTY_VERSION_NEW%@g" "!__PLUGINS_SBT!"
            call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
            set /a __N3+=1
        )
    ) else (
       @rem echo    %_WARNING_LABEL% Could not find file "%%i\project\plugins.sbt" 1>&2
    )
    set "__BUILD_SC=%__PARENT_DIR%\%%i\build.sc"
    if exist "!__BUILD_SC!" (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_DOTTY_VERSION_OLD%" "!__BUILD_SC!" 1>&2
        call "%_GREP_CMD%" -q "%_DOTTY_VERSION_OLD%" "!__BUILD_SC!"
        if !ERRORLEVEL!==0 (
            if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_DOTTY_VERSION_OLD%@%_DOTTY_VERSION_NEW%@g" "!__BUILD_SC!" 1>&2
            call "%_SED_CMD%" -i "s@%_DOTTY_VERSION_OLD%@%_DOTTY_VERSION_NEW%@g" "!__BUILD_SC!"
            call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
            set /a __N_SC+=1
        )
    ) else (
       @rem echo    %_WARNING_LABEL% Could not find file "%%i\build.sc" 1>&2
    )
    set "__BUILD_SH=%__PARENT_DIR%\%%i\build.sh"
    if exist "!__BUILD_SH!" (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_COPYRIGHT_DATES_OLD%" "!__BUILD_SH!" 1>&2
        call "%_GREP_CMD%" -q "%_COPYRIGHT_DATES_OLD%" "!__BUILD_SH!"
        if !ERRORLEVEL!==0 (
            if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_COPYRIGHT_DATES_OLD%@%_COPYRIGHT_DATES_NEW%@g" "!__BUILD_SH!" 1>&2
            call "%_SED_CMD%" -i "s@%_COPYRIGHT_DATES_OLD%@%_COPYRIGHT_DATES_NEW%@g" "!__BUILD_SH!"
            @rem call "%_DOS2UNIX_CMD%" --force "!__BUILD_SH!"
            set /a __N_SH+=1
        )
    ) else (
       echo    %_WARNING_LABEL% Could not find file "%%i\build.sh" 1>&2
    )
)
@rem Configuration files common to all projects
set "__IVY_XML=%__PARENT_DIR%\ivy.xml"
if exist "%__IVY_XML%" (
    set __N_IVY_OLD=!__N_IVY!
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_DOTTY_VERSION_OLD%" "!__IVY_XML!" 1>&2
    call "%_GREP_CMD%" -q "%_DOTTY_VERSION_OLD%" "!__IVY_XML!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_DOTTY_VERSION_OLD%@%_DOTTY_VERSION_NEW%@g" "!__IVY_XML!" 1>&2
        call "%_SED_CMD%" -i "s@%_DOTTY_VERSION_OLD%@%_DOTTY_VERSION_NEW%@g" "!__IVY_XML!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        set /a __N_IVY+=1
    )
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_IVY_AKKA_VERSION_GREP%" "!__IVY_XML!" 1>&2
    call "%_GREP_CMD%" -q "%_IVY_AKKA_VERSION_GREP%" "!__IVY_XML!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_IVY_AKKA_VERSION_OLD%@%_IVY_AKKA_VERSION_NEW%@g" "!__IVY_XML!" 1>&2
        call "%_SED_CMD%" -i "s@%_IVY_AKKA_VERSION_OLD%@%_IVY_AKKA_VERSION_NEW%@g" "!__IVY_XML!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        if !__N_IVY!==!__N_IVY_OLD! set /a __N_IVY+=1
    )
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_IVY_DOTTY_VERSION_OLD%" "!__IVY_XML!" 1>&2
    call "%_GREP_CMD%" -q "%_IVY_DOTTY_VERSION_OLD%" "!__IVY_XML!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_IVY_DOTTY_VERSION_OLD%@%_IVY_DOTTY_VERSION_NEW%@g" "!__IVY_XML!" 1>&2
        call "%_SED_CMD%" -i "s@%_IVY_DOTTY_VERSION_OLD%@%_IVY_DOTTY_VERSION_NEW%@g" "!__IVY_XML!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        if !__N_IVY!==!__N_IVY_OLD! set /a __N_IVY+=1
    )
) else (
   echo    %_WARNING_LABEL% Could not find file "%__IVY_XML%" 1>&2
)
set "__POM_XML=%__PARENT_DIR%\pom.xml"
if exist "%__POM_XML%" (
    set __N_POM_OLD=!__N_POM!
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_POM_SCALA2_VERSION_OLD%" "!__POM_XML!" 1>&2
    call "%_GREP_CMD%" -q "%_POM_SCALA2_VERSION_OLD%" "!__POM_XML!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_POM_SCALA2_VERSION_OLD%@%_POM_SCALA2_VERSION_NEW%@g" "!__POM_XML!" 1>&2
        call "%_SED_CMD%" -i "s@%_POM_SCALA2_VERSION_OLD%@%_POM_SCALA2_VERSION_NEW%@g" "!__POM_XML!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        set /a __N_POM+=1
    )
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_POM_SCALA3_VERSION_OLD%" "!__POM_XML!" 1>&2
    call "%_GREP_CMD%" -q "%_POM_SCALA3_VERSION_OLD%" "!__POM_XML!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_POM_SCALA3_VERSION_OLD%@%_POM_SCALA3_VERSION_NEW%@g" "!__POM_XML!" 1>&2
        call "%_SED_CMD%" -i "s@%_POM_SCALA3_VERSION_OLD%@%_POM_SCALA3_VERSION_NEW%@g" "!__POM_XML!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        if !__N_POM!==!__N_POM_OLD! set /a __N_POM+=1
    )
) else (
    echo    %_WARNING_LABEL% Could not find file "%__POM_XML%" 1>&2
)
set "__COMMON_GRADLE=%__PARENT_DIR%\common.gradle"
if exist "%__COMMON_GRADLE%" (
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_GRADLE_DOTTY_VERSION_OLD%" "!__COMMON_GRADLE!" 1>&2
    call "%_GREP_CMD%" -q "%_GRADLE_DOTTY_VERSION_OLD%" "!__COMMON_GRADLE!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_GRADLE_DOTTY_VERSION_OLD%@%_GRADLE_DOTTY_VERSION_NEW%@g" "!__COMMON_GRADLE!" 1>&2
        call "%_SED_CMD%" -i "s@%_GRADLE_DOTTY_VERSION_OLD%@%_GRADLE_DOTTY_VERSION_NEW%@g" "!__COMMON_GRADLE!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        set /a __N_GRA+=1
    )
) else (
    echo    %_WARNING_LABEL% Could not find file "%__COMMON_GRADLE%" 1>&2
)
set "__CPATH_BAT=%__PARENT_DIR%\cpath.bat"
if exist "%__CPATH_BAT%" (
    set __N_BAT_OLD=!__N_BAT!
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_BATCH_AKKA_VERSION_OLD%" "!__CPATH_BAT!" 1>&2
    call "%_GREP_CMD%" -q "%_BATCH_AKKA_VERSION_OLD%" "!__CPATH_BAT!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_BATCH_AKKA_VERSION_OLD%@%_BATCH_AKKA_VERSION_NEW%@g" "!__CPATH_BAT!" 1>&2
        call "%_SED_CMD%" -i "s@%_BATCH_AKKA_VERSION_OLD%@%_BATCH_AKKA_VERSION_NEW%@g" "!__CPATH_BAT!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        set /a __N_BAT+=1
    )
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_BATCH_SCALATEST_VERSION_OLD%" "!__CPATH_BAT!" 1>&2
    call "%_GREP_CMD%" -q "%_BATCH_SCALATEST_VERSION_OLD%" "!__CPATH_BAT!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_BATCH_SCALATEST_VERSION_OLD%@%_BATCH_SCALATEST_VERSION_NEW%@g" "!__CPATH_BAT!" 1>&2
        call "%_SED_CMD%" -i "s@%_BATCH_SCALATEST_VERSION_OLD%@%_BATCH_SCALATEST_VERSION_NEW%@g" "!__CPATH_BAT!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        if !__N_BAT!==!__N_BAT_OLD! set /a __N_BAT+=1
    )
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_BATCH_SLF4J_VERSION_OLD%" "!__CPATH_BAT!" 1>&2
    call "%_GREP_CMD%" -q "%_BATCH_SLF4J_VERSION_OLD%" "!__CPATH_BAT!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_BATCH_SLF4J_VERSION_OLD%@%_BATCH_SLF4J_VERSION_NEW%@g" "!__CPATH_BAT!" 1>&2
        call "%_SED_CMD%" -i "s@%_BATCH_SLF4J_VERSION_OLD%@%_BATCH_SLF4J_VERSION_NEW%@g" "!__CPATH_BAT!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        if !__N_BAT!==!__N_BAT_OLD! set /a __N_BAT+=1
    )
) else (
    echo    %_WARNING_LABEL% Could not find file "%__CPATH_BAT%" 1>&2
)
set "__MAKEFILE_INC=%__PARENT_DIR%\makefile.inc"
if exist "%__MAKEFILE_INC%" (
    if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_GREP_CMD%" -q "%_MAKE_SLF4J_VERSION_OLD%" "!__MAKEFILE_INC!" 1>&2
    call "%_GREP_CMD%" -q "%_MAKE_SLF4J_VERSION_OLD%" "!__MAKEFILE_INC!"
    if !ERRORLEVEL!==0 (
        if %_DEBUG%==1 echo %_DEBUG_LABEL% "%_SED_CMD%" -i "s@%_MAKE_SLF4J_VERSION_OLD%@%_MAKE_SLF4J_VERSION_NEW%@g" "!__MAKEFILE_INC!" 1>&2
        call "%_SED_CMD%" -i "s@%_MAKE_SLF4J_VERSION_OLD%@%_MAKE_SLF4J_VERSION_NEW%@g" "!__MAKEFILE_INC!"
        call "%_UNIX2DOS_CMD%" -q "!__INPUT_FILE!"
        set /a __N_INC+=1
    )
) else (
    echo    %_WARNING_LABEL% Could not find file "%__MAKEFILE_INC%" 1>&2
)
@rem call :message %__N1% "build.sbt"
@rem call :message %__N2% "project\build.properties"
@rem call :message %__N3% "project\plugins.sbt"
@rem call :message %__N_SC% "build.sc"
call :message %__N_SH% "build.sh"
call :message %__N_IVY% "ivy.xml"
call :message %__N_POM% "pom.xml"
call :message %__N_GRA% "common.gradle"
call :message %__N_BAT% "cpath.bat"
call :message %__N_INC% "makefile.inc"
goto :eof

@rem input parameters: %1=nr of updates, %2=file name
:message
set __N=%~1
set __FILE_NAME=%~2
if %__N% gtr 1 ( set __STR=files ) else ( set __STR=file )
echo    Updated %__N% %__FILE_NAME% %__STR%
goto :eof

@rem #########################################################################
@rem ## Cleanups

:end
if %_DEBUG%==1 echo %_DEBUG_LABEL% _EXITCODE=%_EXITCODE% 1>&2
exit /b %_EXITCODE%
endlocal
