/*----------------------------------------------------------------------------*
 * Base C language grammar adapted from the antlr3 example C.g created by
 * Terrence Parr.  Parr credits Jutta Degener's 1995 ANSI C yacc grammar.
 *
 * The original files and comment are available from http://www.antlr.org
 *----------------------------------------------------------------------------*/

 grammar cbase;

 import common;

 type_specifier
 	: 'int'
	| 'signed'
	| 'unsigned'
	| 'float'
	| 'double'
	;

/*----------------------------------------------------------------------------*
 * vim: set syntax=antlr :
 *----------------------------------------------------------------------------*/
