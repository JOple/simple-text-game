grammar DRoomGrammar;

statement
	: clause+
	;
	
clause
	: CONJUNCTION* verb? target
	| verb
	;
	
verb
	: COMBINE
	| INPUT
	| INSPECT
	| INVENTORY
	| INVENTORY
	| LOOK
	| MOVE
	| OPEN
	| TAKE
	| USE
	;
	
target
	: PREPOSITION? ARTICLE? ADJECTIVE* object
	| PREPOSITION
	| ARTICLE
	| ADJECTIVE
	;
	
object
	: BACK
	| BED
	| BLADE
	| CHEST
	| CLOCK
	| DOOR
	| DOWN
	| FRONT
	| HANDLE
	| DRAWER
	| LEFT
	| PAINTING
	| RIGHT
	| SAW
	| SHELF
	| UP
	| WINDOW
	| IDENTIFIER
	;

// POS
CONJUNCTION
	: 'and'
	| 'then'
	| 'next'
	;

PREPOSITION
	: 'on'
	| 'at'
	;
	
ARTICLE
	: 'a'
	| 'an'
	| 'the'
	;

ADJECTIVE
	: 'wooden'
	| 'iron'
	| 'dark'
	;
	
// Verbs
LOOK
	: 'look'
	| 'see'
	| 'face'
	;
	
MOVE
	: 'move to'
	| 'move'
	| 'go to'
	| 'goto'
	| 'go'
	;

INSPECT
	: 'inspect'
	| 'examine'
	| 'observe'
	;

TAKE
	: 'get'
	| 'take'
	;

INVENTORY
	: 'inventory'
	| 'check bag'
	;
	
INPUT
	: 'put'
	| 'write'
	| 'provide'
	| 'input'
	;
	
COMBINE
	: 'combine'
	| 'mix'
	| 'glue t,ogether'
	| 'put together'
	| 'make'
	;

USE
	: 'use'
	| 'utilize'
	;

OPEN
	: 'open'
	| 'push'
	;
	
// Objects
LEFT
	: 'left'
	| 'leftward'
	| 'leftwards'
	;
	
RIGHT
	: 'right'
	| 'rightward'
	| 'rightwards'
	;

FRONT
	: 'front'
	| 'forward'
	| 'forwards'
	;
	
BACK
	: 'back'
	| 'backward'
	| 'backwards'
	;

UP
	: 'up'
	| 'upward'
	;
	
DOWN
	: 'down'
	| 'downward'
	;
	
BED
	: 'bed'
	| 'resting place'
	;

DRAWER
	: 'cabinet'
	| 'bedside drawer'
	| 'drawer'
	;

CLOCK
	: 'clock'
	| 'timer'
	;
	
DOOR
	: 'door'
	| 'wooden door'
	;
	
PAINTING
	: 'painting'
	| 'paintings'
	| 'image'
	| 'images'
	;

CHEST
	: 'vault'
	| 'chest'
	| 'box'
	;
	
SHELF
	: 'shelf'
	| 'shelves'
	| 'cabinet'
	;

WINDOW
	: 'window'
	| 'railing'
	;
	
BLADE
	: 'sawblade'
	| 'blade'
	| 'edge'
	;
		
HANDLE
	: 'sawhandle'
	| 'handle'
	| 'holder'
	;
	
SAW
	: 'saw'
	| 'cutter'
	;

// Others
IDENTIFIER
	: ~[ \t\r\n\u000C]+
	;

WS
	: [ \t\r\n\u000C]+ -> skip
	;