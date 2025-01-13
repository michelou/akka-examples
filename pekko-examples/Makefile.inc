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
MVN        = $(MAVEN_HOME)/bin/mvn
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
RM         = rm
TOUCH      = touch
WGET       = wget
endif

## https://mvnrepository.com/artifact/com.typesafe/config
CONFIG_VERSION = 1.4.3
## https://mvnrepository.com/artifact/org.apache.pekko/pekko-actor
PEKKO_VERSION = 1.1.3
## https://mvnrepository.com/artifact/org.scala-lang/scala-library
SCALA_VERSION = 2.13.15
## https://mvnrepository.com/artifact/org.slf4j/slf4j-api
SLF4J_VERSION = 2.0.16

SCALA_LIB_JAR         = $(shell $(FIND) "$(LOCAL_REPO)/org/scala-lang" -name "scala-library-2.13.*.jar" | tail -1)
CONFIG_JAR            = $(shell $(FIND) "$(LOCAL_REPO)/com/typesafe" -name "config-$(CONFIG_VERSION).jar")
PEKKO_ACTOR_JAR       = $(shell $(FIND) "$(LOCAL_REPO)/org/apache/pekko" -name "pekko-actor_2.13-$(PEKKO_VERSION).jar")
PEKKO_ACTOR_TYPED_JAR = $(shell $(FIND) "$(LOCAL_REPO)/org/apache/pekko" -name "pekko-actor-typed_2.13-$(PEKKO_VERSION).jar")
PEKKO_SLF4J_JAR       = $(shell $(FIND) "$(LOCAL_REPO)/org/apache/pekko" -name "pekko-slf4j_2.13-$(PEKKO_VERSION).jar")
PEKKO_TESTKIT_JAR     = $(shell $(FIND) "$(LOCAL_REPO)/org/apache/pekko" -name "pekko-testkit_2.13-$(PEKKO_VERSION).jar")
SLF4J_API_JAR         = $(shell $(FIND) "$(LOCAL_REPO)/org/slf4j/slf4j-api" -name "slf4j-api-$(SLF4J_VERSION).jar")
SLF4J_SIMPLE_JAR      = $(shell $(FIND) "$(LOCAL_REPO)/org/slf4j/slf4j-simple" -name "slf4j-simple-$(SLF4J_VERSION).jar")

MAVEN_REPO = https://repo1.maven.org/maven2

deps:
	$(eval MVN_OPTS := -quiet)
	$(eval WGET_OPTS := --user-agent="Mozilla/5.0" --no-check-certificate --directory-prefix="$(TMP)")
	@$(ECHO) "## Check Maven dependencies on $(MAVEN_REPO)"
ifeq (,$(wildcard $(SCALA_LIB_JAR)))
	$(eval GROUP_ID := org.scala-lang)
	$(eval ARTIFACT_ID := scala-library)
	$(eval VERSION := $(SCALA_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval SCALA_LIB_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(CONFIG_JAR)))
	$(eval GROUP_ID := com.typesafe)
	$(eval ARTIFACT_ID := config)
	$(eval VERSION := $(CONFIG_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	## e.g. https://repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval CONFIG_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(PEKKO_ACTOR_JAR)))
	$(eval GROUP_ID := org.apache.pekko)
	$(eval ARTIFACT_ID := pekko-actor_2.13)
	$(eval VERSION := $(PEKKO_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) ##|| $(error Failed to download $(URL) into directory $(TMP))
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval PEKKO_ACTOR_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(PEKKO_ACTOR_TYPED_JAR)))
	$(eval GROUP_ID := org.apache.pekko)
	$(eval ARTIFACT_ID := pekko-actor-typed_2.13)
	$(eval VERSION := $(PEKKO_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(URL) into directory $(TMP))
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval PEKKO_ACTOR_TYPED_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(PEKKO_SLF4J_JAR)))
	$(eval GROUP_ID := org.apache.pekko)
	$(eval ARTIFACT_ID := pekko-slf4j_2.13)
	$(eval VERSION := $(PEKKO_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval PEKKO_SLF4J_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(PEKKO_TESTKIT_JAR)))
	$(eval GROUP_ID := org.apache.pekko)
	$(eval ARTIFACT_ID := pekko-testkit_2.13)
	$(eval VERSION := $(PEKKO_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(ARTIFACT_ID)-$(VERSION).jar)
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval PEKKO_TESTKIT_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(SLF4J_API_JAR)))
	$(eval GROUP_ID := org.slf4j)
	$(eval ARTIFACT_ID := slf4j-api)
	$(eval VERSION := $(SLF4J_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(URL) into directory $(TMP))
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval SLF4J_API_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
ifeq (,$(wildcard $(SLF4J_SIMPLE_JAR)))
	$(eval GROUP_ID := org.slf4j)
	$(eval ARTIFACT_ID := slf4j-simple)
	$(eval VERSION := $(SLF4J_VERSION))
	$(eval JAR_FILE := $(TMP)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(eval URL := $(MAVEN_REPO)/$(subst .,/,$(GROUP_ID))/$(ARTIFACT_ID)/$(VERSION)/$(ARTIFACT_ID)-$(VERSION).jar)
	$(WGET) $(WGET_OPTS) $(URL) || $(error Failed to download $(URL) into directory $(TMP))
	$(MVN) $(MVN_OPTS) install:install-file -Dfile="$(JAR_FILE)" -DgroupId=$(GROUP_ID) -DartifactId=$(ARTIFACT_ID) -Dversion=$(VERSION) -Dpackaging=jar
	$(eval SLF4J_SIMPLE_JAR := $(shell $(FIND) "$(LOCAL_REPO)/$(subst .,/,$(GROUP_ID))" -name $(ARTIFACT_ID)-$(VERSION).jar))
endif
