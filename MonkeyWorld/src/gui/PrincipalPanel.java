package gui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalPanel extends JPanel {

  /**
   * 
   */
	private static final long serialVersionUID = 1L;
		
	int choice;
	
	JFrame f;	
	
	
	public PrincipalPanel( JFrame f ) 
	{
		super();
		this.f = f;		
		createPanel();		
	}

	private void createPanel() 
	{		
		this.setLayout( new GridLayout( 2, 1 ) );			
		
		final JComboBox mode = new JComboBox();
		mode.addItem("Static Environment");
		mode.addItem("Semi Dynamic Environment");
		mode.addItem("Dynamic Environment");
				
		mode.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				choice = mode.getSelectedIndex();
			}
		});
		
		JButton start = new JButton( "Start" );
		start.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				f.dispose();

				JFrame frame = new JFrame();
				frame.setResizable( false );
				frame.setVisible( true );
				frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				
				frame.setLocation( 0, 0 );
				frame.setTitle( "Monkey World" );
				
				frame.setContentPane( new FinalPanel( frame, choice ) );
			}			
		});
		
		this.add( mode );
		this.add( start );	
	}	
}