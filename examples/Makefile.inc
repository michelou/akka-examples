ifndef GIT_HOME
$(error Variable GIT_HOME is not set)
endif
ifndef JAVA_HOME
$(error Variable JAVA_HOME is not set)
endif
ifndef SCALA_HOME
$(error Variable SCALA_HOME is not set)
endif

ifeq ($(OS),Windows_NT)
JAR        = $(subst \,/,$(JAVA_HOME))/bin/jar.exe
JAVA       = $(subst \,/,$(JAVA_HOME))/bin/java.exe
JAVAC      = $(subst \,/,$(JAVA_HOME))/bin/javac.exe
JAVADOC    = $(subst \,/,$(JAVA_HOME))/bin/javadoc.exe
SCALA      = $(subst \,/,$(SCALA_HOME))/bin/scala.bat
SCALAC     = $(subst \,/,$(SCALA_HOME))/bin/scalac.bat
SCALADOC   = $(subst \,/,$(SCALA_HOME))/bin/scaladoc.bat
LOCAL_REPO = $(subst \,/,$(USERPROFILE))/.m2/repository
PSEP       = ;
CP         = $(subst \,/,$(GIT_HOME))/usr/bin/cp.exe
ECHO       = $(subst \,/,$(GIT_HOME))/usr/bin/echo.exe
## We grab Mingw version instead of C:\Windows\System32\find.exe
FIND       = $(subst \,/,$(GIT_HOME))/usr/bin/find.exe
MKDIR      = $(subst \,/,$(GIT_HOME))/usr/bin/mkdir.exe
RM         = $(subst \,/,$(GIT_HOME))/usr/bin/rm.exe
TOUCH      = $(subst \,/,$(GIT_HOME))/usr/bin/touch.exe
else
JAR        = $(JAVA_HOME)/bin/jar
JAVA       = $(JAVA_HOME)/bin/java
JAVAC      = $(JAVA_HOME)/bin/javac
JAVADOC    = $(JAVA_HOME)/bin/javadoc
SCALA      = $(SCALA_HOME)/bin/scala
SCALAC     = $(SCALA_HOME)/bin/scalac
SCALADOC   = $(SCALA_HOME)/bin/scaladoc
LOCAL_REPO = $(HOME)/.m2/repository
PSEP       = :
CP         = cp
ECHO       = echo
FIND       = find
MKDIR      = mkdir
RM         = rm
TOUCH      = touch
endif

SCALA_LIB_JAR        = $(shell $(FIND) "$(LOCAL_REPO)/org/scala-lang" -name scala-library-2.13.10.jar)
CONFIG_JAR           = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name config-1.4.2.jar)
AKKA_ACTOR_JAR       = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name akka-actor_2.13-2.7.0.jar)
AKKA_ACTOR_TYPED_JAR = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name akka-actor-typed_2.13-2.7.0.jar)
LOMBOK_JAR           = $(shell $(FIND) "$(LOCAL_REPO)/org/projectlombok" -name lombok-1.18.24.jar)
SLF4J_JAR            = $(shell $(FIND) "$(LOCAL_REPO)/org/slf4j/slf4j-api" -name slf4j-api-2.0.6.jar)
SLF4J_SIMPLE_JAR     = $(shell $(FIND) "$(LOCAL_REPO)/org/slf4j/slf4j-simple" -name slf4j-simple-2.0.6.jar)

ifeq (,$(wildcard $(CONFIG_JAR)))
$(error Variable CONFIG_JAR is not set)
endif
ifeq (,$(wildcard $(AKKA_ACTOR_JAR)))
$(error Variable AKKA_ACTOR_JAR is not set)
endif
ifeq (,$(wildcard $(AKKA_ACTOR_TYPED_JAR)))
$(error Variable AKKA_ACTOR_TYPED_JAR is not set)
endif
ifeq (,$(wildcard $(SLF4J_JAR)))
$(error Variable SLF4J_JAR is not set)
endif
