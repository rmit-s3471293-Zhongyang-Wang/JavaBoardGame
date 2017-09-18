package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.Game;
import view.GameGUI;
import view.SettingMenuWindow;

/** implements WindowsListner to launch initialization of MVC binds*/
public class GameController implements WindowListener{

	public static int squareSize = 60;
	
	private Game game;
	private GameGUI gameGUI;
	
	public void setGameModel(Game g){
		this.game = g;
	}
	
	public void setView(GameGUI frame){
		this.gameGUI = frame;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		int boardHight = game.getBoardDimensionHeight();
		int boardWidth = game.getBoardDimensionWidth();	
		
		this.gameGUI.addBoardPanel(boardHight, boardWidth);
		this.gameGUI.revalidate();
		this.gameGUI.repaint();	
		this.game.startOneTurn();
		
		this.addBoardController();
		this.addMenuItemControllers();
	}
	/* add controllers to Game Menu(not player menu)*/
	private void addMenuItemControllers() {
		this.gameGUI.addMenuSettingItemController(new MenuSettingListener());
		UndoController uc = new UndoController(game);
		this.gameGUI.addUndoController(uc);
		this.gameGUI.addSaveLoadControllers(new SaveController(game), new LoadController(gameGUI));
	}

	
	private void addBoardController() {
		BoardController bController = new BoardController();
		bController.setModel(game.getBoard(), game.getCommandStack());
		bController.setView(gameGUI.getBoardPane());
		bController.setBackPanel(gameGUI.getLayeredBoardPanel());
		gameGUI.addBoardListener(bController);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class MenuSettingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			/* show setting window*/
			SettingMenuWindow sw = new SettingMenuWindow();
			
			/* bind controller and setting window*/
			GameSetController gc = new GameSetController();
			gc.setGameModel(game);
			gc.setGameView(gameGUI);
			gc.setSettingView(sw);
		    sw.addSettingOKButtonListener(gc);
		}
	}


}
