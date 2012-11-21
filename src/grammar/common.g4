/*----------------------------------------------------------------------------*
 * Common lexer rules
 *----------------------------------------------------------------------------*/

lexer grammar common;

// letters
fragment
LETTER
	: '$'
	| [a-zA-Z]
	| '-'
	;

// integer
INTEGER
	: [0-9]+
	;

// integers

// newline
NEWLINE
	: '\r'? '\n'
	;

// whitespace
WHITESPACE
	: [ \t]+ -> skip
	;

// identifiers
IDENTIFIER
	: LETTER (LETTER|INTEGER)*
	;

/*----------------------------------------------------------------------------*
 * vim: set syntax=antlr :
 *----------------------------------------------------------------------------*/
