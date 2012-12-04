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
INT
	: [0-9]+
	;

// whitespace
WS
	: [ \r\t\u000C\n]+ -> channel(HIDDEN)
	;

// identifiers
ID
	: LETTER (LETTER|INT)*
	;

// C-style comment
COMMENT
	: '/*' .*? '*/' -> channel(HIDDEN)
	;

// C++-style comment
LINE_COMMENT
	: '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
	;

/*----------------------------------------------------------------------------*
 * vim: set syntax=antlr :
 *----------------------------------------------------------------------------*/
