package gui;

import javax.swing.JFrame;

public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public PrincipalFrame() {
		PrincipalPanel panel = new PrincipalPanel( this );
		this.setContentPane( panel );
		
		this.setResizable( false );
		this.setVisible( true );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setSize( 200, 100 );		
		this.setLocation( 300, 300 );
		this.setTitle( "Monkey World" );
	}
}