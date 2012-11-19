#------------------------------------------------------------------------------#
# Top-level Makefile for phaze project
#------------------------------------------------------------------------------#

all: phaze.xml
	@(ant -buildfile phaze.xml phaze)

grammar: phaze.xml
	@(ant -buildfile phaze.xml grammar)

clean: phaze.xml
	@(ant -buildfile phaze.xml clean)
