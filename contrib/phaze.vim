" Vim syntax file
" Language:		Phaze
" Maintainer:	Ben Bergen
" Last Change:	Ben Bergen

" Quit when a (custom) syntax file was already loaded
if version < 600
	syntax clear
elseif exists("b:current_syntax")
  finish
endif

if version < 600
	so <sfile>:p:h/java.vim
else
	runtime! syntax/java.vim
	unlet b:current_syntax
endif

syn keyword		phzType					size_t
syn keyword		phzType					bool
syn keyword		phzType					int8_t int16_t int32_t int64_t
syn keyword		phzType					uint8_t uint16_t uint32_t uint64_t
syn keyword		phzType					position vector
syn keyword		phzStructure			cell material isotope composition reaction

if version >= 508 || !exists("did_phz_syntax_inits")
	if version < 508
		let did_phz_syntax_inits = 1
		command -nargs=+ HiLink hi link <args>
	else
		command -nargs=+ HiLink hi def link <args>
	endif

	HiLink phzType							Type
	HiLink phzStructure					Structure
	HiLink phzStorageClass				StorageClass
	delcommand HiLink
endif

let b:current_syntax = "phaze"

" vim: ts=3
