/*----------------------------------------------------------------------------*
 * Grammar for the phaze language.
 *----------------------------------------------------------------------------*/

grammar phaze;

import common;

@header {
	package Phaze;
}

init
	: typeDecl* EOF
	;

typeDecl
	: cellDecl
	;

cellDecl
	: 'cell' cellBody
	;

cellBody
	: '{' varDecl* '}'
	;

varDecl
	: ';'
	| type ID ';'
	;

type
	: primitive
	;

primitive
	: 'integer'
	| 'real'
	;

/*----------------------------------------------------------------------------*
 * vim: set syntax=antlr : set ts=3
 *----------------------------------------------------------------------------*/
