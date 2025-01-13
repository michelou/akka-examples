##
## Copyright (c) 2018-2025 Stéphane Micheloud
##
## Licensed under the MIT License.
##
##############################################################################

include ../Makefile.inc

SOURCE_DIR  = src
TARGET_DIR  = target
CLASSES_DIR = $(TARGET_DIR)/classes

CPATH       = $(SCALA_LIB_JAR)$(PSEP)$(CONFIG_JAR)$(PSEP)$(AKKA_ACTOR_JAR)$(PSEP)$(AKKA_ACTOR_TYPED_JAR)$(PSEP)$(LOMBOK_JAR)$(PSEP)$(SLF4J_JAR)$(PSEP)$(SLF4J_SIMPLE_JAR)$(PSEP)$(CLASSES_DIR)
JAVAC_FLAGS = -deprecation -classpath "$(CPATH)"
JAVA_FLAGS  = -classpath "$(CPATH)"

##############################################################################
## main rules

SOURCES   = $(shell $(FIND) "$(SOURCE_DIR)/main/java" -type f -name "*.java" 2>/dev/null)
RESOURCES = $(shell $(FIND) "$(SOURCE_DIR)/main/resources" -type f -name "*.conf" -o -name "*.xml" 2>/dev/null)
TARGET    = $(TARGET_DIR)/.latest-build

OPTS_FILE    = $(TARGET_DIR)/javac_opts.txt
SOURCES_FILE = $(TARGET_DIR)/javac_sources.txt

MAIN_CLASS = com.gkatzioura.Application
MAIN_ARGS ?= 

all: build

build: deps $(TARGET)

$(TARGET): $(SOURCES) $(RESOURCES)
	[ -d "$(CLASSES_DIR)" ] || "$(MKDIR)" -p "$(CLASSES_DIR)"
	@$(ECHO) $(JAVAC_FLAGS) -d "$(CLASSES_DIR)" > "$(OPTS_FILE)"
	@$(ECHO) $(SOURCES) > "$(SOURCES_FILE)"
	"$(JAVAC)" "@$(OPTS_FILE)" "@$(SOURCES_FILE)"
	for f in $(RESOURCES); do "$(CP)" "$$f" "$(CLASSES_DIR)"; done
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
	@$(ECHO) "    help   print this help message"
	@$(ECHO) "    run    execute main program \"$(MAIN_CLASS)\""

##############################################################################
## doc rules

DOC_TARGET_DIR = $(TARGET_DIR)/docs
DOC_SOURCES    = $(shell $(FIND) "$(CLASSES_DIR)" -type f -name "*.tasty" 2>/dev/null)
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
## lint rules

LINT_SOURCES = $(shell $(FIND) "$(SOURCE_DIR)" -type f -name "*.scala")
LINT_TARGET  = $(TARGET_DIR)/.latest-lint

SCALAFMT_CONFIG_FILE = $(shell dirname $(CURDIR))/.scalafmt.conf
SCALAFMT_OPTS = --test --config "$(SCALAFMT_CONFIG_FILE)"

lint: $(LINT_TARGET)

$(LINT_TARGET): $(LINT_SOURCES)
	"$(SCALAFMT)" $(SCALAFMT_OPTS) $(SOURCE_DIR)
	@$(TOUCH) "$(LINT_TARGET)"

##############################################################################
## phony

.PHONY: all build clean deps doc help run

.SUFFIXES:
.SUFFIXES: .class .html .jar .java .scala .tasty .xml
