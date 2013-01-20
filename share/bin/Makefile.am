#------------------------------------------------------------------------------#
# Phaze bin Makefile.am
#------------------------------------------------------------------------------#

bin_PROGRAMS = \
	test

AM_CPPFLAGS = -I${top_srcdir}/src

test_SOURCES = \
	@top_srcdir@/bin/test.cc
test_LDADD = ${top_builddir}/lib/libphz.la
