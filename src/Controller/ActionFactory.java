package Controller;

import Controller.Board.*;
import Interface.Action;

public class ActionFactory {

	private static ActionFactory instance = null;

	public ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		
		if ( instance == null ) {
			instance = new ActionFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String command) {
		
		Action action = null;
		
		if ( command.equals("board_list") ) 			action = new BoardListAction(); 
		else if ( command.equals("board_write_form") ) 	action = new BoardWriteFormAction();
		else if ( command.equals("board_write") )		action = new BoardWriteAction();
		else if ( command.equals("board_view") )		action = new BoardViewAction();
		else if ( command.equals("board_update_form") )	action = new BoardUpdateFormAction();
		else if ( command.equals("board_update") )		action = new BoardUpdateAction();
		else if ( command.equals("board_delete") )		action = new BoardDeleteAction();
		
		return action;
	}
}