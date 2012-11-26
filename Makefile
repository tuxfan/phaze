#------------------------------------------------------------------------------#
# Top-level Makefile for phaze project
#------------------------------------------------------------------------------#

all: build.xml
	@(ant -buildfile build.xml phaze)

grammar: build.xml
	@(ant -buildfile build.xml grammar)

clean: build.xml
	@(ant -buildfile build.xml clean)
