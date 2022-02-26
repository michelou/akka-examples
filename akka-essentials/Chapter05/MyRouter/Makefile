include ../../Makefile.inc

SOURCE_DIR  = src
TARGET_DIR  = target
CLASSES_DIR = $(TARGET_DIR)/classes

CPATH       = $(SCALA_LIB_JAR)$(PSEP)$(CONFIG_JAR)$(PSEP)$(AKKA_ACTOR_JAR)$(PSEP)$(CLASSES_DIR)
JAVAC_FLAGS = -deprecation -classpath "$(CPATH)"
JAVA_FLAGS  = -classpath "$(CPATH)"

##############################################################################
## main rules

SOURCES   = $(shell $(FIND) "$(SOURCE_DIR)/main/java" -name *.java 2>/dev/null)
RESOURCES = $(shell $(FIND) "$(SOURCE_DIR)/main/resources" -name *.conf -o -name *.xml 2>/dev/null)
TARGET    = $(TARGET_DIR)/.latest-build

OPTS_FILE    = $(TARGET_DIR)/javac_opts.txt
SOURCES_FILE = $(TARGET_DIR)/javac_sources.txt

MAIN_CLASS = akka.examples.MyRouterApp
MAIN_ARGS ?= 

all: build

build: $(TARGET)

$(TARGET): $(SOURCES)
	[ -d "$(CLASSES_DIR)" ] || "$(MKDIR)" -p "$(CLASSES_DIR)"
	@$(ECHO) $(JAVAC_FLAGS) -d "$(CLASSES_DIR)" > "$(OPTS_FILE)"
	@$(ECHO) $(SOURCES) > "$(SOURCES_FILE)"
	"$(JAVAC)" "@$(OPTS_FILE)" "@$(SOURCES_FILE)"
	[ -z "$(RESOURCES)" ] || "$(CP)" $(RESOURCES) "$(CLASSES_DIR)"
	@$(TOUCH) "$(TARGET)"

clean:
	"$(RM)" -rf "project\target"
	"$(RM)" -rf "$(TARGET_DIR)"

run: build
	"$(JAVA)" $(JAVA_FLAGS) $(MAIN_CLASS) $(MAIN_ARGS)

help:
	@$(ECHO) "Usage: make all|build|clean|doc|help|run"
	@$(ECHO) ""
	@$(ECHO) "  Subcommands:"
	@$(ECHO) "    all    alias for build"
	@$(ECHO) "    build  compile Java source files"
	@$(ECHO) "    clean  delete generated files"
	@$(ECHO) "    doc    generate HTML documentation"
	@$(ECHO) "    help   display this help message"
	@$(ECHO) "    run    execute main program $(MAIN_CLASS)"

##############################################################################
## doc rules

DOC_TARGET_DIR = $(TARGET_DIR)/docs
DOC_SOURCES    = $(shell $(FIND) "$(SOURCE_DIR)/main/java" -name *.java 2>/dev/null)
DOC_TARGET     = $(DOC_TARGET_DIR)/.latest-build

PROJECT_NAME    ?= $(shell basename $(CURDIR))
PROJECT_URL     ?= https://github.com/$(USERNAME)/akka-examples
PROJECT_VERSION ?= 1.0.0

DOC_OPTS_FILE    = $(TARGET_DIR)/javadoc_opts.txt
DOC_SOURCES_FILE = $(TARGET_DIR)/javadoc_sources.txt

$(DOC_TARGET): $(DOC_SOURCES)
	[ -d "$(DOC_TARGET_DIR)" ] || "$(MKDIR)" -p "$(DOC_TARGET_DIR)"
	@$(ECHO) -siteroot "$(DOC_TARGET_DIR)" -d "$(DOC_TARGET_DIR)" -project "$(PROJECT_NAME)" -project-version "$(PROJECT_VERSION)" > "$(DOC_OPTS_FILE)"
	@$(ECHO) $(DOC_SOURCES) > "$(DOC_SOURCES_FILE)"
	"$(JAVADOC)" "@$(DOC_OPTS_FILE)" "@$(DOC_SOURCES_FILE)"
	@$(TOUCH) "$(DOC_TARGET)"

doc: build $(DOC_TARGET)

##############################################################################
## phony

.PHONY: all build clean doc help run
