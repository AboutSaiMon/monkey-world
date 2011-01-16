package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JPanel;

import core.Support;

public class BottomPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Image monkey;
	Image box;
	Image bananas;
	Support s;
	
	int size = 60;
	int topPosition = 200;
	int bottomPosition = 350;
	boolean edit;
	int choice;
	
	public BottomPanel( int c ) {
		// TODO Auto-generated constructor stub
		this.choice = c;
		this.s = new Support();
		edit = true;
		
		this.setBackground( Color.WHITE );
		
		Toolkit t = Toolkit.getDefaultToolkit();
		monkey = t.getImage( "src"+ File.separator + "resources" + File.separator + "scimmia.jpg" );	
		box = t.getImage( "src"+ File.separator + "resources" + File.separator + "box.png" );	
		bananas = t.getImage( "src"+ File.separator + "resources" + File.separator + "bananas.jpg" );	
		MediaTracker mt = new MediaTracker( this );
		mt.addImage( monkey, 0 );
		mt.addImage( box, 1 );
		mt.addImage( bananas, 2 );
		try{
			mt.waitForID( 0 );
			mt.waitForID( 1 );
			mt.waitForID( 2 );
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		this.addMouseListener( new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				
				int i = ( x - 100 ) / 60;
				
				if( i >= 0 && i < 10 )
				{
					if( y > 200 && y < 260 )
					{
						if( edit || choice == 2 )
							s.bananasPosition = i;
					}
					else if( y > 350 && y < 410 )
					{
						if( edit )
							s.boxPosition = i;
					}
					repaint();
				}
			}
		});
	}
	
	@Override
	public void paintComponent( Graphics arg0 ) {
		super.paintComponent( arg0 );
		arg0.drawImage( bananas, s.bananasPosition * size  + 105, topPosition+5, null );
		//arg0.drawImage( box, 0, 100, null );
		for( int i = 0; i < 10; i++ )
		{
			
			arg0.drawRect( i * size + 100, topPosition, size, size );
			
			if( i == s.boxPosition )
				arg0.fillRect( i * size + 100, bottomPosition, size, size);
			else				
				arg0.drawRect( i * size + 100, bottomPosition, size, size);
		}
		if( s.monkeyPosition == s.boxPosition )
			arg0.drawImage( monkey, s.monkeyPosition * size + 100, ( bottomPosition + topPosition )/2, null );
		else
			arg0.drawImage( monkey, s.monkeyPosition * size + 102, bottomPosition + 10, null );
	}
}