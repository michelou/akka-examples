@echo off
setlocal enabledelayedexpansion

@rem output parameter: _CPATH

if not defined _DEBUG set _DEBUG=%~1
if not defined _MVN_CMD set _MVN_CMD=mvn.cmd

if %_DEBUG%==1 ( set _MVN_OPTS=
) else ( set _MVN_OPTS=--quiet
)
set __CENTRAL_REPO=https://repo1.maven.org/maven2
set "__LOCAL_REPO=%USERPROFILE%\.m2\repository"

set "__TEMP_DIR=%TEMP%\lib"
if not exist "%__TEMP_DIR%" mkdir "%__TEMP_DIR%"

set _LIBS_CPATH=

set __SCALA_BINARY_VERSION=2.13

set __AKKA_VERSION=2.6.18
set __SCALA_VERSION=2.13.8
set __SCALATEST_VERSION=3.2.11
set __SLF4J_VERSION=1.7.36

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

@rem https://mvnrepository.com/artifact/org.scalatest/scalatest
call :add_jar "org.scalatest" "scalatest_3" "%__SCALATEST_VERSION%"

@rem https://mvnrepository.com/artifact/io.spray/spray-json
call :add_jar "io.spray" "spray-json_%__SCALA_BINARY_VERSION%" "1.3.6"

@rem https://mvnrepository.com/artifact/org.reactivestreams/reactive-streams
call :add_jar "org.reactivestreams" "reactive-streams" "1.0.3"

@rem https://mvnrepository.com/artifact/com.typesafe.scala-logging/scala-logging
call :add_jar "com.typesafe.scala-logging" "scala-logging_%__SCALA_BINARY_VERSION%" "3.9.4"

@rem https://mvnrepository.com/artifact/com.typesafe.akka/akka-stream
call :add_jar "com.typesafe.akka" "akka-stream_%__SCALA_BINARY_VERSION%" "%__AKKA_VERSION%"

@rem https://mvnrepository.com/artifact/com.typesafe.akka/akka-stream-kafka
call :add_jar "com.typesafe.akka" "akka-stream-kafka_%__SCALA_BINARY_VERSION%" "3.0.0"

@rem https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
call :add_jar "org.apache.kafka" "kafka-clients" "3.1.0"

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
for /f "usebackq delims=" %%f in (`where /r "%__LOCAL_REPO%\%__JAR_PATH%" %__JAR_NAME% 2^>NUL`) do (
    set "__JAR_FILE=%%f"
)
if not exist "%__JAR_FILE%" (
    set __JAR_URL=%__CENTRAL_REPO%/%__GROUP_ID:.=/%/%__ARTIFACT_ID%/%__VERSION%/%__JAR_NAME%
    set "__JAR_FILE=%__TEMP_DIR%\%__JAR_NAME%"
    if not exist "!__JAR_FILE!" (
        if %_DEBUG%==1 ( echo %_DEBUG_LABEL% powershell -c "Invoke-WebRequest -Uri '!__JAR_URL!' -Outfile '!__JAR_FILE!'" 1>&2
        ) else if %_VERBOSE%==1 ( echo Download file "%__JAR_NAME%" to directory "!__TEMP_DIR:%USERPROFILE%=%%USERPROFILE%%!" 1>&2
        )
        powershell -c "$progressPreference='silentlyContinue';Invoke-WebRequest -Uri '!__JAR_URL!' -Outfile '!__JAR_FILE!'"
        if not !ERRORLEVEL!==0 (
            echo %_ERROR_LABEL% Failed to download file %__JAR_NAME% 1>&2
            set _EXITCODE=1
            goto :eof
        )
        if %_DEBUG%==1 ( echo %_DEBUG_LABEL% "%_MVN_CMD%" install:install-file -Dfile="!__JAR_FILE!" -DgroupId="%__GROUP_ID%" -DartifactId=%__ARTIFACT_ID% -Dversion=%__VERSION% -Dpackaging=jar 1>&2
        ) else if %_VERBOSE%==1 ( echo Install Maven archive "%__JAR_NAME%" into directory "!__LOCAL_REPO:%USERPROFILE%=%%USERPROFILE%%!\%__SCALA_XML_PATH%" 1>&2
        )
        %_MVN_CMD% %_MVN_OPTS% install:install-file -Dfile="!__JAR_FILE!" -DgroupId="%__GROUP_ID%" -DartifactId=%__ARTIFACT_ID% -Dversion=%__VERSION% -Dpackaging=jar
        for /f "usebackq delims=" %%f in (`where /r "%__LOCAL_REPO%\%__JAR_PATH%" %__JAR_NAME% 2^>NUL`) do (
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
    set "_CPATH=%_LIBS_CPATH%"
)