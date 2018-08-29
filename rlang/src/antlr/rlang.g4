grammar rlang;

extentions:
	extention+;
	
extention:cl=ID 'extends' extention_list ;
extention_list: cl=ID ((',' cl=ID)*);

ID : [a-zA-Z] [a-zA-Z0-9_]*;
