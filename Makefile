#------------------------------------------------------------------------------#
# Top-level Makefile for phaze project
#------------------------------------------------------------------------------#

ANT = ant
LOGGER = -logger org.apache.tools.ant.listener.AnsiColorLogger

all: build.xml
	@(${ANT} ${LOGGER} phaze)

grammar: build.xml
	@(${ANT} ${LOGGER} grammar)

install: build.xml
	@(${ANT} ${LOGGER} install)

dist: build.xml
	@(${ANT} ${LOGGER} dist)

clean: build.xml
	@(${ANT} ${LOGGER} clean)

distclean: build.xml
	@(${ANT} ${LOGGER} distclean)
