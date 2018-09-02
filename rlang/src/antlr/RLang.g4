grammar RLang;

lang
:
	extentions
;

extentions
:
	extention+
;

extention
:
	cl = ID extention_list ';'
;

extention_list
:
	EXTENDS cl = ID
	(
		(
			',' cl = ID
		)*
	)
;

EXTENDS
:
	'extends'
;

ID
:
	[a-zA-Z] [a-zA-Z0-9_]*
;

WS
:
	(
		' '
		| '\n'
		| '\r'
		| '\t'
	)+ -> skip
;