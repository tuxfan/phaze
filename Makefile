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

clean: build.xml
	@(${ANT} ${LOGGER} clean)
