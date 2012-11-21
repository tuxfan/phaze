/*----------------------------------------------------------------------------*
 * Grammar for the phaze language.
 *----------------------------------------------------------------------------*/

grammar phaze;

import cbase;

@header {
	package Phaze;
}

/*
cell_specifier
	: cell IDENTIFIER? '{' cell_declaration_list '}'
	| cell IDENTIFIER
	;

cell
	: 'cell'
	;

cell_declaration_list
	: cell_declaration+
	;

cell_declaration
	: specifier_qualifier_list cell_declarator_list ';'
	;

specifier_qualifier_list
	: ( type_qualifier | type_specifier )+
	;

cell_declarator_list
	: cell_declarator (',' cell_declarator)*
	;

cell_declarator
	: declarator (':' constant_expression)?
	| ':' constant_expression
	;
*/

init : '{' value (',' value)* '}';

value
	: init
	| INTEGER
	;

/*----------------------------------------------------------------------------*
 * vim: set syntax=antlr : set ts=3
 *----------------------------------------------------------------------------*/
