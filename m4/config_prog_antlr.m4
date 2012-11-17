dnl ------------------------------------------------------------------------ dnl
dnl Copyright (c) 2012 Los Alamos National Security, LLC
dnl All rights reserved.
dnl ------------------------------------------------------------------------ dnl

dnl ------------------------------------------------------------------------ dnl
dnl Look for antlr program
dnl ------------------------------------------------------------------------ dnl

AC_DEFUN([CONFIG_PROG_ANTLR], [
	AC_ARG_VAR([ANTLR], [User specified path to the antlr executable])

	
	if test -n "$ANTLR" ; then
		AC_PATH_PROG(ANTLR, $ANTLR)
	else
		AC_PATH_PROG(ANTLR, antlr)
	fi

	if test -n "$ANTLR" ; then
		AC_SUBST(HAS_ANTLR, [yes])
	else
		AC_SUBST(HAS_ANTLR, [no])
	fi
])
