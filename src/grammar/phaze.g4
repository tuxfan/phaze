/*----------------------------------------------------------------------------*
 * Grammar for the phaze language.
 *----------------------------------------------------------------------------*/

grammar phaze;

import common;

@header {
	package Phaze;
}

init
	: type_decl* EOF
	;

type_decl
	: cell_decl
	;

cell_decl
	: 'cell' cell_body
	;

cell_body
	: '{' var_decl* '}'
	;

var_decl
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
