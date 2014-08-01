package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TimerAction implements ActionListener
{
	UI applet;
	
	public TimerAction(UI a)
	{
		this.applet = a;
	}

	public void actionPerformed(ActionEvent e) 
	{
		applet.swingTimer.stop();
		applet.doStep();
	}
	
 }