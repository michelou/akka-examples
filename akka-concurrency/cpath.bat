@echo off
setlocal enabledelayedexpansion

@rem output parameter: _CPATH, _CPATH_TEST

if not defined _DEBUG set _DEBUG=%~1
if not defined _DEBUG set _DEBUG=0
set _VERBOSE=0

if not defined _MVN_CMD set "_MVN_CMD=%MAVEN_HOME%\bin\mvn.cmd"
if %_DEBUG%==1 echo [%~n0] "_MVN_CMD=%_MVN_CMD%"

if %_DEBUG%==1 ( set _MVN_OPTS=
) else ( set _MVN_OPTS=--quiet
)
set _CENTRAL_REPO=https://repo1.maven.org/maven2
set "_LOCAL_REPO=%USERPROFILE%\.m2\repository"

set "_TEMP_DIR=%TEMP%\lib"
if not exist "%_TEMP_DIR%" mkdir "%_TEMP_DIR%"
if %_DEBUG%==1 echo [%~n0] "_TEMP_DIR=%_TEMP_DIR%"

set __SCALA_BINARY_VERSION=2.13

set __AKKA_VERSION=2.8.4
set __SCALA_VERSION=2.13.12
set __SCALATEST_VERSION=3.2.17
set __SLF4J_VERSION=2.0.9

@rem #########################################################################
@rem ## Libraries to be added to _LIBS_CPATH

set _LIBS_CPATH=

@rem https://mvnrepository.com/artifact/org.scala-lang/scala-library
call :add_jar "org.scala-lang" "scala-library" "%__SCALA_VERSION%"

@rem https://mvnrepository.com/artifact/com.typesafe/config
call :add_jar "com.typesafe" "config" "1.4.2"

@rem https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
call :add_jar "com.typesafe.akka" "akka-actor_%__SCALA_BINARY_VERSION%" "%__AKKA_VERSION%"

@rem https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor-typed
call :add_jar "com.typesafe.akka" "akka-actor-typed_%__SCALA_BINARY_VERSION%" "%__AKKA_VERSION%"

@rem https://mvnrepository.com/artifact/com.typesafe.akka/akka-slf4j
call :add_jar "com.typesafe.akka" "akka-slf4j_%__SCALA_BINARY_VERSION%" "%__AKKA_VERSION%"

@rem https://mvnrepository.com/artifact/org.slf4j/slf4j-api
call :add_jar "org.slf4j" "slf4j-api" "%__SLF4J_VERSION%"

@rem https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
call :add_jar "org.slf4j" "slf4j-simple" "%__SLF4J_VERSION%"

set "_LIBS_CPATH1=%_LIBS_CPATH%"

@rem test scope

set _LIBS_CPATH=

@rem https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
call :add_jar "com.typesafe.akka" "akka-testkit_%__SCALA_BINARY_VERSION%" "%__AKKA_VERSION%"

@rem https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml
@rem ScalaTest 3.2 dependency
call :add_jar "org.scala-lang.modules" "scala-xml_%__SCALA_BINARY_VERSION%" "2.1.0"

@rem https://mvnrepository.com/artifact/org.scalactic/scalactic
call :add_jar "org.scalactic" "scalactic_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest
call :add_jar "org.scalatest" "scalatest_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest-compatible
call :add_jar "org.scalatest" "scalatest-compatible" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest-core
call :add_jar "org.scalatest" "scalatest-core_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest-matchers-core
call :add_jar "org.scalatest" "scalatest-matchers-core_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest-mustmatchers
call :add_jar "org.scalatest" "scalatest-mustmatchers_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest-shouldmatchers
call :add_jar "org.scalatest" "scalatest-shouldmatchers_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest-wordspec
call :add_jar "org.scalatest" "scalatest-wordspec_%__SCALA_BINARY_VERSION%" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core
@rem JUnit 4 depends on Hamcrest 1.3
call :add_jar "org.hamcrest" "hamcrest-core" "1.3"

@rem https://mvnrepository.com/artifact/junit/junit
call :add_jar "junit" "junit" "4.13.2"

set "_LIBS_CPATH2=%_LIBS_CPATH%"

goto end

@rem #########################################################################
@rem ## Subroutines

@rem input parameters: %1=group ID, %2=artifact ID, %3=version
@rem global variable: _LIBS_CPATH
:add_jar
@rem https://mvnrepository.com/artifact/org.portable-scala
set __GROUP_ID=%~1
set __ARTIFACT_ID=%~2
set __VERSION=%~3

set __JAR_NAME=%__ARTIFACT_ID%-%__VERSION%.jar
set __JAR_PATH=%__GROUP_ID:.=\%\%__ARTIFACT_ID:/=\%
set __JAR_FILE=
for /f "usebackq delims=" %%f in (`where /r "%_LOCAL_REPO%\%__JAR_PATH%" %__JAR_NAME% 2^>NUL`) do (
    set "__JAR_FILE=%%f"
)
if not exist "%__JAR_FILE%" (
    set __JAR_URL=%_CENTRAL_REPO%/%__GROUP_ID:.=/%/%__ARTIFACT_ID%/%__VERSION%/%__JAR_NAME%
    set "__JAR_FILE=%_TEMP_DIR%\%__JAR_NAME%"
    if not exist "!__JAR_FILE!" (
        if %_DEBUG%==1 ( echo %_DEBUG_LABEL% powershell -c "Invoke-WebRequest -Uri '!__JAR_URL!' -Outfile '!__JAR_FILE!'" 1>&2
        ) else if %_VERBOSE%==1 ( echo Download file "%__JAR_NAME%" to directory "!_TEMP_DIR:%USERPROFILE%=%%USERPROFILE%%!" 1>&2
        )
        powershell -c "$progressPreference='silentlyContinue';Invoke-WebRequest -Uri '!__JAR_URL!' -Outfile '!__JAR_FILE!'"
        if not !ERRORLEVEL!==0 (
            echo %_ERROR_LABEL% Failed to download file "%__JAR_NAME%" 1>&2
            set _EXITCODE=1
            goto :eof
        )
        if %_DEBUG%==1 ( echo %_DEBUG_LABEL% "%_MVN_CMD%" %_MVN_OPTS% install:install-file -Dfile="!__JAR_FILE!" -DgroupId="%__GROUP_ID%" -DartifactId=%__ARTIFACT_ID% -Dversion=%__VERSION% -Dpackaging=jar 1>&2
        ) else if %_VERBOSE%==1 ( echo Install Maven artifact into directory "!_LOCAL_REPO:%USERPROFILE%=%%USERPROFILE%%!\%__SCALA_XML_PATH%" 1>&2
        )
        @rem see https://stackoverflow.com/questions/16727941/how-do-i-execute-cmd-commands-through-a-batch-file
        call "%_MVN_CMD%" %_MVN_OPTS% install:install-file -Dfile="!__JAR_FILE!" -DgroupId="%__GROUP_ID%" -DartifactId=%__ARTIFACT_ID% -Dversion=%__VERSION% -Dpackaging=jar
        if not !ERRORLEVEL!==0 (
            echo %_ERROR_LABEL% Failed to install Maven artifact into directory "!_LOCAL_REPO:%USERPROFILE%=%%USERPROFILE%%!" ^(error:!ERRORLEVEL!^) 1>&2
        )
        for /f "usebackq delims=" %%f in (`where /r "%_LOCAL_REPO%\%__JAR_PATH%" %__JAR_NAME% 2^>NUL`) do (
            set "__JAR_FILE=%%f"
        )
    )
)
set "_LIBS_CPATH=%_LIBS_CPATH%%__JAR_FILE%;"
goto :eof

@rem #########################################################################
@rem ## Cleanups

:end
endlocal & (
    set "_CPATH=%_LIBS_CPATH1%"
    set "_CPATH_TEST=%_LIBS_CPATH2%"
)