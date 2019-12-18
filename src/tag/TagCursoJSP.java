package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagCursoJSP extends SimpleTagSupport{
	
	@Override
	public void doTag() throws JspException, IOException{
		JspWriter out = getJspContext().getOut();
		out.println("This is my own custom tag");
	}
}
