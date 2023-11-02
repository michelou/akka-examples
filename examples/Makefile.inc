ifndef GIT_HOME
$(error Variable GIT_HOME is not set)
endif
ifndef JAVA_HOME
$(error Variable JAVA_HOME is not set)
endif
ifndef MAVEN_HOME
$(error Variable MAVEN_HOME is not set)
endif
ifndef SCALA_HOME
$(error Variable SCALA_HOME is not set)
endif

ifeq ($(OS),Windows_NT)
JAR        = $(subst \,/,$(JAVA_HOME))/bin/jar.exe
JAVA       = $(subst \,/,$(JAVA_HOME))/bin/java.exe
JAVAC      = $(subst \,/,$(JAVA_HOME))/bin/javac.exe
JAVADOC    = $(subst \,/,$(JAVA_HOME))/bin/javadoc.exe
MVN        = $(subst \,/,$(MAVEN_HOME))/bin/mvn.cmd
SCALA      = $(subst \,/,$(SCALA_HOME))/bin/scala.bat
SCALAC     = $(subst \,/,$(SCALA_HOME))/bin/scalac.bat
SCALADOC   = $(subst \,/,$(SCALA_HOME))/bin/scaladoc.bat
SCALAFMT   = $(subst \,/,$(LOCALAPPDATA))/Coursier/data/bin/scalafmt.bat
LOCAL_REPO = $(subst \,/,$(USERPROFILE))/.m2/repository
PSEP       = ;
## $(MSYS_HOME)\usr\bin\{cp.exe, make.exe, wget.exe}
CP         = /usr/bin/cp.exe
ECHO       = /usr/bin/echo.exe
FIND       = /usr/bin/find.exe
MKDIR      = /usr/bin/mkdir.exe
RM         = /usr/bin/rm.exe
TOUCH      = /usr/bin/touch.exe
WGET       = /usr/bin/wget.exe
else
JAR        = $(JAVA_HOME)/bin/jar
JAVA       = $(JAVA_HOME)/bin/java
JAVAC      = $(JAVA_HOME)/bin/javac
JAVADOC    = $(JAVA_HOME)/bin/javadoc
SCALA      = $(SCALA_HOME)/bin/scala
SCALAC     = $(SCALA_HOME)/bin/scalac
SCALADOC   = $(SCALA_HOME)/bin/scaladoc
SCALAFMT   = $(HOME)/Coursier/data/bin/scalafmt
LOCAL_REPO = $(HOME)/.m2/repository
PSEP       = :
CP         = cp
ECHO       = echo
FIND       = find
MKDIR      = mkdir
MVN        = mvn
RM         = rm
TOUCH      = touch
WGET       = wget
endif

## https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
AKKA_VERSION = 2.8.5
## https://mvnrepository.com/artifact/com.typesafe/config
CONFIG_VERSION = 1.4.3
## https://mvnrepository.com/artifact/org.slf4j/slf4j-api
SLF4J_VERSION = 2.0.9

SCALA_LIB_JAR        = $(shell $(FIND) "$(LOCAL_REPO)/org/scala-lang" -name "scala-library-2.13.*.jar" | tail -1)
CONFIG_JAR           = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name "config-$(CONFIG_VERSION).jar")
AKKA_ACTOR_JAR       = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name "akka-actor_2.13-$(AKKA_VERSION).jar")
AKKA_ACTOR_TYPED_JAR = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name "akka-actor-typed_2.13-$(AKKA_VERSION).jar")
AKKA_SLF4J_JAR       = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name "akka-slf4j_2.13-$(AKKA_VERSION).jar")
LOMBOK_JAR           = $(shell $(FIND) "$(LOCAL_REPO)/org/projectlombok" -name "lombok-1.18.26.jar")
SLF4J_API_JAR        = $(shell $(FIND) "$(LOCAL_REPO)/org/slf4j/slf4j-api" -name "slf4j-api-$(SLF4J_VERSION).jar")
SLF4J_SIMPLE_JAR     = $(shell $(FIND) "$(LOCAL_REPO)/org/slf4j/slf4j-simple" -name "slf4j-simple-$(SLF4J_VERSION).jar")

deps:
	$(eval MVN_OPTS := -quiet)
	$(eval REMOTE_REPO := https://repo1.maven.org/maven2)
	$(eval WGET_OPTS := --user-agent="Mozilla/5.0 (Windows NT 6.3; WOW64; rv:40.0)" --no-check-certificate --directory-prefix="$(TMP)")
	@$(ECHO) "## Check Maven dependencies on $(REMOTE_REPO)"
ifeq (,$(wildcard $(SCALA_LIB_JAR)))
	$(eval GROUP_ID := org.scala-lang)
	$(eval ARTIFACT_ID := scala-library)
	$(eval VERSION := 2.13.12)
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval SCALA_LIB_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(CONFIG_JAR)))
	$(eval GROUP_ID := com.typesafe)
	$(eval ARTIFACT_ID := config)
	$(eval VERSION := $(CONFIG_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(SCALA_VERSION).jar)
	## e.g. https://repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval CONFIG_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(AKKA_ACTOR_JAR)))
	$(eval GROUP_ID := com.typesafe)
	$(eval ARTIFACT_ID := akka-actor_2.13)
	$(eval VERSION := $(AKKA_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(SCALA_VERSION).jar)
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval AKKA_ACTOR_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(AKKA_ACTOR_TYPED_JAR)))
	$(eval GROUP_ID := com.typesafe)
	$(eval ARTIFACT_ID := akka-actor-typed_2.13)
	$(eval VERSION := $(AKKA_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(SCALA_VERSION).jar)
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval AKKA_ACTOR_TYPED_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(AKKA_SLF4J_JAR)))
	$(eval GROUP_ID := com.typesafe)
	$(eval ARTIFACT_ID := akka-slf4j_2.13)
	$(eval VERSION := $(AKKA_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(SCALA_VERSION).jar)
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval AKKA_SLF4J_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(SLF4J_API_JAR)))
	$(eval GROUP_ID := org.slf4j)
	$(eval ARTIFACT_ID := slf4j-api)
	$(eval VERSION := $(AKKA_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(SCALA_VERSION).jar)
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval SLF4J_API_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(SLF4J_SIMPLE_JAR)))
	$(eval GROUP_ID := org.slf4j)
	$(eval ARTIFACT_ID := slf4j-simple)
	$(eval VERSION := $(AKKA_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(SCALA_VERSION).jar)
	$(eval URL := $(REMOTE_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval SLF4J_SIMPLE_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
