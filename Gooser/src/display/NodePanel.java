package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import nodes.Graph;
import nodes.Node;

public class NodePanel extends JPanel {
	
	private static int DEFAULT_CIR_RAD = 50;
	
	List<Node> nodeList = new ArrayList<Node>();
	List<Shape> shapeList = new ArrayList<Shape>();
	
	int xoff = DEFAULT_CIR_RAD;
	int yoff = DEFAULT_CIR_RAD;
	
	public NodePanel()
	{
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				do_mouseReleased(e);
			}
		});
	}
	
	//Used if user clicks on nodes on panel
	protected void do_mouseReleased(MouseEvent e) {
		for(Shape s : shapeList)
		{
			if(s.contains(e.getPoint()))
			{
				
			}
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        xoff = DEFAULT_CIR_RAD;
        xoff = DEFAULT_CIR_RAD;
        Shape nodeCircle;
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLUE);
        for(Node node : nodeList)
        {
        	nodeCircle = new Ellipse2D.Double(xoff,yoff,DEFAULT_CIR_RAD,DEFAULT_CIR_RAD);
        	shapeList.add(nodeCircle);
        	g.fillOval(xoff, yoff, DEFAULT_CIR_RAD, DEFAULT_CIR_RAD);
        	xoff = xoff + DEFAULT_CIR_RAD;
        	g2d.draw(nodeCircle);
        }
        
    }
	
	public void DrawNodes(List<Node> inNodeList, Graph inGraph)
	{
		nodeList = inNodeList;
		shapeList = new ArrayList<Shape>();
		this.repaint();
	}

}
