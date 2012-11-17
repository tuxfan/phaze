/*----------------------------------------------------------------------------*
 * Common lexer rules
 *----------------------------------------------------------------------------*/

lexer grammar common;

// identifiers
ID : [a-zA-Z]+;

// integers
INT : [0-9]+;

// newline
NEWLINE : '\r'? '\n';

// whitespace
WS : [ \t]+ -> skip;
